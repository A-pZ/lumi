/**
 *
 */
package lumi.view;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * Session属性値の更新リスナー。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Slf4j
public class SimpleHttpSessionAttributeListener implements
		HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("add: " + event.getName() + "/" + event.getValue());
			log.debug("  +-> " + event.getSource());
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("remove: " + event.getName() + "/" + event.getValue());
			log.debug("  +-> " + event.getSource());
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("replace from: " + event.getName() + "/"
					+ event.getValue());
			log.debug("  +-> " + event.getSource());
		}
	}

}
