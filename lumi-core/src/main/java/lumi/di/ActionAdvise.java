/**
 *
 */
package lumi.di;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import lumi.action.LumiActionSupport;
import lumi.service.LumiService;
import lumi.service.StoreMapService;
import lumi.vo.BridgeMessage;

import org.apache.struts2.convention.annotation.Action;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Actionアノテーションで定義したActionメソッドの前処理＆後処理。 ここではActionアノテーションで定義したメソッドの後処理を実装する。
 * IMessageAwareを実装(またはLumiActionSupportを継承)したActionクラスの場合、
 * Actionメソッドの後処理にて、Serviceクラスから画面へ表示するメッセージを取得し、Actionクラスへ渡す。
 * 定義できるメッセージは共通のServiceクラスで利用しているINFO,WARN,ERROR,FIELDの4種類に分類され、
 * それぞれがActionクラスへ自動的に渡す。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Aspect
@Slf4j
public class ActionAdvise extends AbstractAdvise {

	/**
	 * Actionアノテーションで定義したメソッドの前処理を行う。
	 *
	 * @param joinPoint
	 *            インターセプトした処理
	 * @param annotation
	 *            Actionアノテーション
	 * @throws Exception
	 *             DI実行中の例外
	 */
	@Before("execution(public * *..action..*.*(..)) && @annotation(annotation)")
	public void before(JoinPoint joinPoint, Action annotation) throws Exception {
		log.info("ActionAdvise(before) : @Action(" + annotation.value() + ") :"
				+ joinPoint.toLongString());

		// トレースログの出力
		trace(joinPoint);

		Object target = joinPoint.getTarget();

		// Actionクラスが持つServiceクラスの判定。
		// BasicActionSupport継承クラスであれば以下の内容を実施する。
		if (target instanceof LumiActionSupport) {
			LumiActionSupport action = (LumiActionSupport) target;

			// Screen属性のデシリアライズ
			action.setStoreMap(storeMapDeserialize(action.getStoreMapValue()));

			// 基底Serviceの取得
			LumiService service = findServiceInstance(action);

			// Action<->Service情報の共有
			if (service != null) {
				// セッションMap
				Map<String, Object> sessionMap = action.getSession();
				service.setSessionMap(sessionMap);
				if (log.isDebugEnabled()) {
					log.debug("  -- set sessionMap to service." + sessionMap);
				}
				// Screen属性
				Map<String, Object> storeMap = action.getStoreMap();
				service.setStoreMap(storeMap);
				if (log.isDebugEnabled()) {
					log.debug("  -- set storeMap to service." + storeMap);
				}
			} else {
				log.info("  -- Action is not LumiActionSupport|subclass , skipped.");
			}
		}
	}

	/**
	 * ActionクラスからSpring管理下のServiceインスタンスを取得する。
	 * @param action LumiActionSupport継承のインスタンス
	 * @return LumiService継承のインスタンス（ある場合のみ）
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IntrospectionException
	 */
	protected LumiService findServiceInstance(LumiActionSupport action) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException  {
		Field[] fields = action.getClass().getDeclaredFields();
		if ( fields == null ) {
			log.info("  -- no field , service is null.");
			return null;
		}

		for (Field field:fields) {
			// Actionのフィールドに@Autowiredがついているフィールドを取得する。
			Annotation autowired = field.getAnnotation(Autowired.class);
			if ( autowired != null ) {
				if ( log.isDebugEnabled()) {
					log.debug("find service-field: " + field.getName());
				}

				// PropertyDiscriptorを利用し、@Autowired対象のクラスを取得する。
				PropertyDescriptor descriptor =
						new PropertyDescriptor(field.getName() , action.getClass());

				// getterメソッドの取得
				Method getter = descriptor.getReadMethod();

				if ( getter != null ) {
					// getterメソッドを実行し、LumiService継承のクラスを返す。
					LumiService service = (LumiService)getter.invoke(action, (Object[])null);
					if ( log.isDebugEnabled()) {
						log.debug("  - service :" + service);
					}
					return service;
				}
			}
		}
		log.info("  -- no field , service is null.");
		return null;
	}

	/**
	 * Actionクラスで例外を検出した場合の後処理。
	 *
	 * @param joinPoint
	 *            インターセプトした処理
	 * @param annotation
	 *            Actionアノテーション
	 * @param exception
	 *            実行時例外
	 * @throws Throwable
	 *             DI実行中の例外
	 *
	 */
	@AfterThrowing(pointcut = "execution(public * *..action..*.*(..)) && @annotation(annotation)", throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Action annotation,
			Exception exception) throws Throwable {
		log.warn("ActionAdvise(afterThrowing) : " + joinPoint.toLongString());
		trace(joinPoint);

		// 例外スタックトレースの出力
		log.warn("- afterThrowing", exception);
	}

	/**
	 * Actionアノテーションで定義したメソッドの後処理を行う。
	 * IMessageAwareを実装しているActionクラス(=LumiActionSupportを継承しているクラス)の場合は、
	 * Serviceクラスからメッセージを取得し、Actionクラスへ渡す。 ワーニングメッセージはbindMessage()の一部として扱われる。
	 *
	 * @param joinPoint
	 *            インターセプトした処理
	 * @param annotation
	 *            Actionアノテーション
	 * @throws Throwable
	 *             DI処理中に発生した例外
	 */
	@AfterReturning("execution(public * *..action..*.*(..)) && @annotation(annotation)")
	public void afterReturning(JoinPoint joinPoint, Action annotation)
			throws Throwable {
		log.info("ActionAdvise(After ) : @Action(" + annotation.value() + ") :"
				+ joinPoint.toLongString());
		trace(joinPoint);

		Object target = joinPoint.getTarget();

		// 継承Actionによって処理を振り分け
		if (target instanceof LumiActionSupport) {
			LumiActionSupport action = (LumiActionSupport) target;

			// LumiActionSupport継承時は、SpringのServiceクラスからメッセージを受け取り
			// Actionへバインドする。
			bindMessage(action);

			// 基底Serviceの取得
			LumiService service = findServiceInstance(action);

			if (service != null) {
				// Screen属性をActionへ戻す。
				Map<String, Object> storeMap = service.getStoreMap();
				action.setStoreMap(storeMap);
				if (log.isDebugEnabled()) {
					log.debug("  -- get storeMap from service." + storeMap);
				}
			}

			// Screen属性のシリアライズ
			action.setStoreMapValue(storeMapSerialize(action.getStoreMap()));

			if ( log.isDebugEnabled()) {
				log.debug("  -- serialized." + action.getStoreMapValue());
			}
		}
	}

	/**
	 * LumiActionSupport継承クラスの場合は、 ServiceクラスからActionクラスへメッセージを格納する。
	 *
	 * 共通モーダルの動的メッセージ、ワーニングの動的メッセージ部分もここで実施する。
	 *
	 * @param action
	 *            Actionクラスのインスタンス
	 * @throws IntrospectionException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	protected void bindMessage(LumiActionSupport action) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException {
		if (log.isDebugEnabled()) {
			log.debug(" -- bindMessage");
		}

		LumiService service =  findServiceInstance(action);

		if (service == null) {
			log.info("LumiService is null -> bind message is skip.");
			return;
		}
		if (log.isDebugEnabled()) {
			log.debug(" -- Service Instance:" + service);
		}

		// Serviceクラスで格納したFieldError、ActionError、ActionMessage、ActionWarningをActionクラスへ反映する。
		List<BridgeMessage> messages = service.getMessages();
		if (messages != null) {
			for (BridgeMessage message : messages) {
				// ActionMessage
				if (message.getLevel() == BridgeMessage.MessageLevel.INFO) {
					action.addActionMessage(action.getText(
							message.getMessageId(), message.getPlaceHolder()));
				// ActionWarning
				} else if (message.getLevel() == BridgeMessage.MessageLevel.WARN) {
					action.addActionWarning(action.getText(
							message.getMessageId(), message.getPlaceHolder()));
				// ActionError
				} else if (message.getLevel() == BridgeMessage.MessageLevel.ERROR) {
					action.addActionError(action.getText(
							message.getMessageId(), message.getPlaceHolder()));
				// FieldError
				} else if (message.getLevel() == BridgeMessage.MessageLevel.FIELD) {
					action.addFieldError(
							message.getFieldname(),
							action.getText(message.getMessageId(),
									message.getPlaceHolder()));
				}
			}
			// Serviceクラスで格納しているメッセージを出力する。
			traceBindMessages(messages);
		} else {
			if (log.isDebugEnabled()) {
				log.debug("messages is null.");
			}
		}
	}

	/**
	 * ServiceからActionへ通知するメッセージをログトレースする。
	 *
	 * @param messages
	 *            サービスクラスのメッセージ一覧
	 */
	protected void traceBindMessages(List<BridgeMessage> messages) {
		if (log.isDebugEnabled()) {
			log.debug(String.format("[%-5s]\t%s\t%s\t%s", "Level", "MessageId",
					"Field", "PlaceHolder"));

			for (BridgeMessage message : messages) {
				log.debug(String.format("[%-5s]\t%s\t%s\t%s", message
						.getLevel().toString(), message.getMessageId(), message
						.getFieldname(), message.getPlaceHolder()));
			}
		}
	}

	/**
	 * メソッドの引数をデバッグログに出力する。
	 *
	 * @param joinPoint
	 *            ジョインポイント。
	 */
	protected void traceArguments(JoinPoint joinPoint) {
		if (log.isDebugEnabled()) {
			Object[] objs = joinPoint.getArgs();
			log.debug("-- arguments[" + objs.length + "]");
			for (Object obj : objs) {
				log.debug(" - " + obj.toString());
			}
		}
	}

	/**
	 * Screen属性の文字列をMapへデシアライズする。
	 *
	 * @param storeMapValue
	 *            画面から受け取るScreen属性のシリアライズ文字列。
	 * @return デシリアライズしたMap
	 */
	protected Map<String, Object> storeMapDeserialize(String storeMapValue) {
		return storeMapService.storeMapDeserialize(storeMapValue);
	}

	/**
	 * Screen属性Mapのシリアライズした文字列を返す。
	 *
	 * @param storeMap
	 *            Screen属性Map
	 * @return シリアライズ文字列
	 */
	protected String storeMapSerialize(Map<String, Object> storeMap) {
		return storeMapService.storeMapSerialize(storeMap);
	}

	/**
	 * Screen属性のサービス。
	 */
	@Autowired
	private StoreMapService storeMapService;
}
