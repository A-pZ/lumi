/**
 *
 */
package lumi.view;

import java.util.Enumeration;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.extern.log4j.Log4j2;

/**
 * セッションの生成/破棄のリスナー。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Log4j2
@WebListener
public class SimpleHttpSessionListener implements HttpSessionListener {

	/* (非 Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		if (log.isDebugEnabled()) {

			HttpSession session = event.getSession();
			if ( session != null ) {
				log.debug(" -- session created. [{}]" , session.getId());
			}
		}
	}

	/* (非 Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		if (log.isDebugEnabled()) {

			HttpSession session = event.getSession();
			if ( session != null ) {
				log.debug(" -- session destroyed.[{}]" , session.getId());

				Enumeration<String> names = session.getAttributeNames();
				while( names.hasMoreElements()) {
					log.debug("   - {}" , names.nextElement());
				}
			}
		}
	}
}
