/**
 *
 */
package lumi.action;

import java.util.Map;

/**
 * 表示する画面ごとにオブジェクトを保管する機能のインタフェース。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
public interface StoreMapAware {

	Map<String , Object> getStoreMap();

	void setStoreMap(Map<String , Object> storeMap);

	String getStoreMapValue();

	void setStoreMapValue(String storeMapValue);
}
