/**
 *
 */
package lumi.action;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lumi.service.IService;
import lumi.view.LumiValidationAwareSupport;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.Blocked;

/**
 * LumiのActionクラス。すべてのActionクラスはこのSupportクラスを継承する。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Slf4j
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
	@Setter @Getter
	HttpServletRequest servletRequest;

	/**
	 * HttpSessionのMap表現。
	 * {@see SessionAware}
	 */
	@Blocked
	@Setter @Getter
	private Map<String,Object> session;

	/**
	 * 認証済みのログイン名を表示する。未認証ないしはプリンシパルがない場合はnull。
	 * @return ログイン名
	 */
	public String getLoginUsername() {
		if ( servletRequest == null ) {
			log.warn("servletRequest is null.");
			return null;
		}

		Principal principal = servletRequest.getUserPrincipal();
		if ( principal == null || StringUtils.isBlank( principal.getName()) ) {
			log.warn("User principal is null.");
			return null;
		}

		return principal.getName();
	}

	public String getUserDisplayName() {
		throw new UnsupportedOperationException("未実装");
	}

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

	@Getter @Setter
	private String _csrf;
}
