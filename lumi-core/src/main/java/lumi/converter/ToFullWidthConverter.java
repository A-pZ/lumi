/**
 *
 */
package lumi.converter;

import java.lang.reflect.Array;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import lumi.function.StringConverter;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 自動半角->全角変換クラス。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Slf4j
public class ToFullWidthConverter extends StrutsTypeConverter {

	/* (非 Javadoc)
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertFromString(java.util.Map, java.lang.String[], java.lang.Class)
	 */
	@Override
	public Object convertFromString(@SuppressWarnings(value="rawtypes") Map paramMap, String[] paramArrayOfString, @SuppressWarnings(value="rawtypes") Class paramClass) {
		Object result = null;

		if (log.isDebugEnabled()) {
			log.debug("convertFromString -> {} , {} ,{}"
				,paramMap, paramArrayOfString.toString(),paramClass.getName());
		}

		if (paramArrayOfString != null) {
			if (paramArrayOfString.getClass().isArray() && paramClass.isArray()) {
				Class<?> componentType = paramClass.getComponentType();

				result = Array.newInstance(componentType,
						Array.getLength(paramArrayOfString));
				if (log.isDebugEnabled()) log.debug("result(pre):{}" , result);
				for (int i = 0, icount = Array.getLength(paramArrayOfString); i < icount; i++) {
					Array.set(result, i, convertFullWidthString(Array.get(
							paramArrayOfString, i)));
				}
			} else {
				result = convertFullWidthString(paramArrayOfString[0]);
			}
		}
		if (log.isDebugEnabled()) log.debug("result : {}" , result);
		return result;
	}

	/* (非 Javadoc)
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertToString(java.util.Map, java.lang.Object)
	 */
	@Override
	public String convertToString(@SuppressWarnings(value="rawtypes") Map context, Object o) {
		String[] ret = (String[])o;
		return ret[0];
	}

	/**
	 * 全て全角文字へ変換する。
	 * @param base 変換前文字列
	 * @return 全角変換後の文字列
	 */
	protected String convertFullWidthString(Object base) {
		return StringConverter.toZenkaku((String)base);
	}

	/**
	 * 変換チェック基本メソッド。
	 */
	public Object convertValue(@SuppressWarnings(value="rawtypes") Map context, Object o, @SuppressWarnings(value="rawtypes") Class toClass) {
		if (o instanceof String[]) {
            return convertFromString(context, (String[]) o, toClass);
        } else if (o instanceof String) {
            return convertFromString(context, new String[]{(String) o}, toClass);
        } else {
            return performFallbackConversion(context, o, toClass);
        }
    }

}
