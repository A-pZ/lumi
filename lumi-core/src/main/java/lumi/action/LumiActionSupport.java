/**
 *
 */
package lumi.action;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import lumi.service.IService;
import lumi.view.LumiValidationAwareSupport;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.Blocked;

/**
 * LumiのActionクラス。すべてのActionクラスはこのSupportクラスを継承する。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
public class LumiActionSupport extends ActionSupport implements LumiAction {
    /**
     * ワーニング情報をセットする。
     * @param warningMessages ワーニングメッセージのCollection
     */
    public void setActionWarnings(Collection<String> warningMessages) {
        validationAware.setActionWarnings(warningMessages);
    }

    /**
     * ワーニング情報を追加する。
     * @param anWarningMessage ワーニングメッセージ
     */
    public void addActionWarning(String anWarning) {
        validationAware.addActionWarning(anWarning);
    }

    /**
     * ワーニング情報のCollectionを取得する。
     * @return ワーニング情報のCollection
     */
    public Collection<String> getActionWarnings() {
        return validationAware.getActionWarnings();
    }

	/**
	 * Lumi実装のValidationAware。ワーニング情報を格納するために提供。
	 */
	private final LumiValidationAwareSupport validationAware =
            new LumiValidationAwareSupport();

	/**
	 * サーブレットリクエスト。
	 * {@see ServletRequestAware}
	 */
	@Blocked
	@Setter
	private HttpServletRequest servletRequest;

	/**
	 * HttpSessionのMap表現。
	 * {@see SessionAware}
	 */
	@Blocked
	@Setter @Getter
	private Map<String,Object> session;

	public IService getService() {
		return this.service;
	}

	public void setService(IService service) {
		this.service = service;
	}

	private IService service;

	@Blocked
	@Getter @Setter
	private Map<String, Map<String, String>> codemaster;

	@Blocked
	@Getter @Setter
	private Map<String, Object> storeMap;

	@Getter @Setter
	private String storeMapValue;
}
