/**
 *
 */
package lumi.validator;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.CompositeTextProvider;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.validator.ActionValidatorManager;
import com.opensymphony.xwork2.validator.DelegatingValidatorContext;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.ValidatorContext;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

import lombok.extern.log4j.Log4j2;
import lumi.annotation.VisitorCreateIfNull;

/**
 * VisitorFieldValidator拡張。
 * VisitorFieldValidatorに適用するActionクラスのフィールドに@VisitorCreateIfNullがある場合は、
 * nullであるかを検知し、nullだった場合はインスタンスを生成する。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 * {@link VisitorCreateIfNull}
 *
 */
@Log4j2
public class VisitorFieldValidator extends FieldValidatorSupport {
    private String context;
    private boolean appendPrefix = true;
    private ActionValidatorManager actionValidatorManager;


    @Inject
    public void setActionValidatorManager(ActionValidatorManager mgr) {
        this.actionValidatorManager = mgr;
    }

    /**
     * Sets whether the field name of this field validator should be prepended to the field name of
     * the visited field to determine the full field name when an error occurs.  The default is
     * true.
     * @param appendPrefix true is Prefix use.
     */
    public void setAppendPrefix(boolean appendPrefix) {
        this.appendPrefix = appendPrefix;
    }

    /**
     * Flags whether the field name of this field validator should be prepended to the field name of
     * the visited field to determine the full field name when an error occurs.  The default is
     * true.
     * @return use prefix is true.
     */
    public boolean isAppendPrefix() {
        return appendPrefix;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    /**
     * 入力チェックを実施する。もし対象フィールドがnullだった場合はインスタンスを生成する。
    * @see com.opensymphony.xwork2.validator.Validator#validate(java.lang.Object)
     */
    public void validate(Object object) throws ValidationException {
        String fieldName = getFieldName();
        Object value = this.getFieldValue(fieldName, object);
        ValueStack stack = ActionContext.getContext().getValueStack();
        if (value == null) {
        	log.info("The visited object '{}' is null." , fieldName);
            value = generateVisitedIfNullObject(object,value);
            log.debug(" - {}" , value);

            stack.set(fieldName, value);
        }

        stack.push(object);

        String visitorContext = (context == null) ? ActionContext.getContext().getName() : context;

        if (value instanceof Collection) {
            Collection<?> coll = (Collection<?>) value;
            Object[] array = coll.toArray();

            validateArrayElements(array, fieldName, visitorContext);
        } else if (value instanceof Object[]) {
            Object[] array = (Object[]) value;

            validateArrayElements(array, fieldName, visitorContext);
        } else {
            validateObject(fieldName, value, visitorContext);
        }

        stack.pop();
    }

    private void validateArrayElements(Object[] array, String fieldName, String visitorContext) throws ValidationException {
        if (array == null) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            Object o = array[i];
            if (o != null) {
                validateObject(fieldName + "[" + i + "]", o, visitorContext);
            }
        }
    }

    private void validateObject(String fieldName, Object o, String visitorContext) throws ValidationException {
        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(o);

        ValidatorContext validatorContext;

        if (appendPrefix) {
            ValidatorContext parent = getValidatorContext();
            validatorContext = new AppendingValidatorContext(parent, createTextProvider(o, parent), fieldName, getMessage(o));
        } else {
            ValidatorContext parent = getValidatorContext();
            CompositeTextProvider textProvider = createTextProvider(o, parent);
            validatorContext = new DelegatingValidatorContext(parent, textProvider, parent);
        }

        actionValidatorManager.validate(o, visitorContext, validatorContext);
        stack.pop();
    }

    private CompositeTextProvider createTextProvider(Object o, ValidatorContext parent) {
        List<TextProvider> textProviders = new LinkedList<>();
        if (o instanceof TextProvider) {
            textProviders.add((TextProvider) o);
        } else {
            textProviders.add(textProviderFactory.createInstance(o.getClass()));
        }
        textProviders.add(parent);

        return new CompositeTextProvider(textProviders);
    }

    public static class AppendingValidatorContext extends DelegatingValidatorContext {
        private String field;
        private String message;
        private ValidatorContext parent;

        public AppendingValidatorContext(ValidatorContext parent, TextProvider textProvider, String field, String message) {
            super(parent, textProvider, parent);

            this.field = field;
            this.message = message;
            this.parent = parent;
        }

        /**
         * Translates a simple field name into a full field name in Ognl syntax
         *
         * @param fieldName field name in OGNL syntax
         * @return full field name in OGNL syntax
         */
        @Override
        public String getFullFieldName(String fieldName) {
            if (parent instanceof VisitorFieldValidator.AppendingValidatorContext) {
                return parent.getFullFieldName(field + "." + fieldName);
            }
            return field + "." + fieldName;
        }

        public String getFieldNameWithField(String fieldName) {
            return field + "." + fieldName;
        }

        @Override
        public void addActionError(String anErrorMessage) {
            super.addFieldError(getFieldNameWithField(field), message + anErrorMessage);
        }

        @Override
        public void addFieldError(String fieldName, String errorMessage) {
            super.addFieldError(getFieldNameWithField(fieldName), message + errorMessage);
        }
    }

    /**
     * Validation対象のObjectで、VisitorFieldValidator対象のフィールドに対し、
     * VisitorCreateIfNullアノテーションがついていた場合はインスタンスを生成する。
     * @param object 対象Actionクラス
     * @param value フィールド値
     * @return VisitorFieldValidator対象フィールドの空インスタンス
     * @throws ValidationException バリデーション用例外
     */
    protected Object generateVisitedIfNullObject(Object object,Object value) throws ValidationException {
    	Class<? extends Object> clazz = object.getClass();
    	String fieldName = getFieldName();
    	try {
    		// VisitorField対象のフィールド名から、getterメソッドを作成
    		String upperMethodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			String getMethodName = "get" + upperMethodName;

			// getterメソッドの取得
    		Method getterMethod = clazz.getDeclaredMethod(getMethodName);

    		// getterメソッドで取得するクラスを決定
    		Class<?> returnType = getterMethod.getReturnType();

    		// 対象フィールドが配列だった場合は、その構成クラスを取得する
    		Class<?> component = (returnType.isArray()) ?
    				returnType.getComponentType() : returnType;

    		// クラス名からフィールド名を取得
    		String targetFieldName = component.getName();

    		// Actionのフィールドに定義したクラスを取得し、そのインスタンスを取得する。
    		Class<?> fieldClazz = clazz.getClassLoader().loadClass(targetFieldName);

    		// 対象フィールドが配列だった場合は、インスタンスを配列で作成する
    		if ( returnType.isArray()) {
    			// FIXME 要素数
    			int length = 1;
    			value = Array.newInstance(fieldClazz ,length);
    			for ( int i=0;i<length;i++) {
    				Array.set(value, i, fieldClazz.newInstance());
    			}
    			log.debug("   -- isArray, count {}" , length);
    		} else {
    			value = fieldClazz.newInstance();
    		}

    		log.debug(" + {} generated." , targetFieldName );

			return value;
		} catch (Exception e) {
			throw new ValidationException( this.getMessage("lumi2.error.visitorfieldvalidator") ,e);
		} finally {

		}
    }

}
