/**
 *
 */
package lumi.view;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import lombok.extern.log4j.Log4j2;

/**
 * Session属性のバインドリスナー。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Log4j2
@WebListener
public class SimpleHttpSessionBindingListener implements
		HttpSessionBindingListener {

	public void valueBound(HttpSessionBindingEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("bound  :{}" , event.getValue());
			log.debug("    --> {}" , event.getName());
		}
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Unbound:{}" , event.getValue());
			log.debug("    --> {}" , event.getName());
		}
	}
}
