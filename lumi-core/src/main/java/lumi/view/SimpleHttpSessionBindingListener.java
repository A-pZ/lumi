/**
 *
 */
package lumi.view;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Session属性のバインドリスナー。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Slf4j
public class SimpleHttpSessionBindingListener implements
		HttpSessionBindingListener {

	public void valueBound(HttpSessionBindingEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("bound :" + event.getValue());
			log.debug(" ----- " + event.getName());
		}
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Unbound :" + event.getValue());
			log.debug(" ----- " + event.getName());
		}
	}
}
