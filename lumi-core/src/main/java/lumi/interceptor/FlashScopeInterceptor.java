package lumi.interceptor;

import java.lang.reflect.Field;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import lumi.action.LumiAction;
import lumi.annotation.Flash;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Flash属性(1回のレスポンス→次のリクエストまでが寿命のスコープ)を管理するインターセプタ。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )(A-pZ)
 *
 */
@Service
@Slf4j
public class FlashScopeInterceptor extends AbstractInterceptor {

	/* (非 Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invoker) throws Exception {

		Object act = invoker.getAction();

		if ( act instanceof LumiAction) {
			LumiAction action = (LumiAction)act;

			Map<String,Object> session = action.getSession();
			if ( session != null) {
				Object flash = session.get(Flash.SESSION_KEYNAME);
				if ( flash != null ) {
					// Actionクラスの@FlashScope対象のフィールドを探す
					Class<? extends Object> clazz = action.getClass();

					Field[] fields = clazz.getDeclaredFields();
					for(Field field:fields) {
						// @Flashの定義があるか取得する
						Flash flashAnnotation =field.getAnnotation(Flash.class);
						if ( flashAnnotation != null ) {
							if ( log.isDebugEnabled()) {
								log.debug("  - flash hit. {}" , field.getName());
							}

							Object obj = session.get(Flash.SESSION_KEYNAME);
							if ( log.isDebugEnabled()) {
								log.debug("  - session object - {}" , obj);
							}
						}
					}

					// もし@FlashScope対象がいれば格納を試行する


					// セッションからFlashスコープのオブジェクトを取り除く
					session.remove(Flash.SESSION_KEYNAME);
				}
			}
		}

		return invoker.invoke();
	}

}
