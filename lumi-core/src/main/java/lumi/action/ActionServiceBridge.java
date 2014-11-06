/**
 *
 */
package lumi.action;

import java.util.List;
import java.util.Map;

import lumi.vo.BridgeMessage;

/**
 * ActionクラスとServiceクラスを連携する際に、Actionクラスでこのインタフェースを実装します。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
public interface ActionServiceBridge {
	/**
	 * メッセージ一覧を取得する。
	 * @return Serviceクラスで格納したメッセージのList
	 */
     List<BridgeMessage> getMessages();

    /**
     * メッセージ一覧を設定する。
     * @param messages ServiceMessageのList表現
     */
     void setMessages(List<BridgeMessage> messages);

    /**
     * セッションIDを格納する。
     * @param sessionId HttpSessionのsessionId
     */
     void setSessionId(String sessionId);

    /**
     * Actionクラスへ格納するセッションマップをServiceから取得する。
     * @return セッション属性情報を格納するStruts2のSessionMap
     */
     Map<String,Object> getSessionMap();

    /**
     * Actionクラスから取得したセッションマップをServiceへ格納する。
     * @param sessionMap セッション属性情報を格納するStruts2のSessionMap
     */
     void setSessionMap(Map<String,Object> sessionMap);
}
