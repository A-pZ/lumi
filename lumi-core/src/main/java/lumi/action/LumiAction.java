/**
 *
 */
package lumi.action;

import java.util.Map;

import lumi.service.IService;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;


/**
 * Lumiが実装するActionインタフェース。Struts2のインタフェースのうち、必須実装のものを束ねている。
 * {@link ServletRequestAware,SessionAware,StoreMapAware}
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
public interface LumiAction extends ServletRequestAware , SessionAware , StoreMapAware {
	/**
	 * Struts2が提供するHttpSessionのMap表現を返す。
	 * @return HttpSessionのMap表現。
	 */
	Map<String,Object> getSession();

	/**
	 * 基底Serviceクラスを取得する。
	 * Action/ActionAdviseで利用。
	 * @return 基底Serviceインスタンス
	 */
	IService getService();

	/**
	 * 基底Serviceクラスを設定する。
	 * Action/ActionAdviseで利用。
	 *
	 * @param service 基底Serviceクラス
	 */
	void setService(IService service);
}
