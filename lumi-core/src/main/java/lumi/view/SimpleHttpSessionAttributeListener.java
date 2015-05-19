/**
 *
 */
package lumi.view;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import lombok.extern.log4j.Log4j2;

/**
 * Session属性値の更新リスナー。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Log4j2
@WebListener
public class SimpleHttpSessionAttributeListener implements
		HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("add    : {}/{}" , event.getName() , event.getValue());
			log.debug("  +-> {}" , event.getSource());
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("remove : {}/{}" , event.getName() , event.getValue());
			log.debug("  +-> {}" , event.getSource());
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("replace: {}/{}" , event.getName() , event.getValue());
			log.debug("  +-> {}" , event.getSource());
		}
	}

}
