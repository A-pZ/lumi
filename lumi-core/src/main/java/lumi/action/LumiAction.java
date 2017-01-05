/**
 *
 */
package lumi.action;

import java.util.Map;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;


/**
 * Lumiが実装するActionインタフェース。Struts2のインタフェースのうち、必須実装のものを束ねている.
 *
 * ServletRequestAware,SessionAware,StoreMapAware
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
public interface LumiAction extends ServletRequestAware , SessionAware , StoreMapAware {
	/**
	 * Struts2が提供するHttpSessionのMap表現を返す。
	 * @return HttpSessionのMap表現。
	 */
	Map<String,Object> getSession();
}
