/**
 *
 */
package lumi.service;

import java.util.Map;

/**
 * Screen属性領域サービスのインタフェース。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
public interface StoreMapService {

	/**
	 * 文字列化したScreen属性領域を、Map&lt;String,Object&gt;へ復元する。
	 * @param storeMapValue 文字列化したScreen属性領域
	 * @return Screen属性領域のMap。
	 */
	Map<String, Object> storeMapDeserialize(String storeMapValue);

	/**
	 * Screen属性領域のMapを、文字列化する。
	 * @param storeMap Screen属性領域のMap
	 * @return 文字列化したScreen属性領域
	 */
	String storeMapSerialize(Map<String, Object> storeMap);
}
