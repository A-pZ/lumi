/**
 *
 */
package lumi.service;

import java.util.Map;

/**
 * Actionクラスから実行されるServiceクラス（＝基底Service）が実装するインタフェース。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
public interface IService {

	/**
     * セッションIDを格納する。
     * @param sessionId HttpSessionのセッションID。
     */
    public void setSessionId(String sessionId);

	/**
     * Actionクラスへ格納するセッションマップをServiceから取得する。
     * @return セッション属性を格納するStruts2のSessionMap
     */
    Map<String,Object> getSessionMap();

    /**
     * Actionクラスから取得したセッションマップをServiceへ格納する。
     * @param sessionMap セッション属性を格納するStruts2のSessionMap
     */
    void setSessionMap(Map<String,Object> sessionMap);
}
