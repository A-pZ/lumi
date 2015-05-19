/**
 *
 */
package lumi.service;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.log4j.Log4j2;
import lumi.dao.DAO;
import lumi.function.StoreMapConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Screen属性を永続化(DB格納)する際に使う。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
//@Service
//@Scope("singleton")
@Log4j2
public class PersistenceStoreMapService implements StoreMapService {

	/**
	 * 引数に指定した一意キーからScreen属性領域Mapを取得する。
	 *
	 * @param storeMapValue データベースに永続化していたScreen属性領域のキー文字列。
	 * @return Map<String,Object> データベースに永続化していたScreen属性領域
	 * @see lumi.service.StoreMapService#storeMapDeserialize(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> storeMapDeserialize(String storeMapValue) {
		Map<String, Object> storeMap = null;

		if (StringUtils.isNotBlank(storeMapValue)) {
			if (log.isDebugEnabled()) {
				log.debug(" -- StoreMapConverter.set");
			}

			// テーブルに保管されているScreen属性領域Mapの文字列表現を取得
			String persistStoredMapValue = (String)dao.selectObject(Query.loadPersistStoreMap.toString(), storeMapValue);

			if (! StringUtils.isBlank(persistStoredMapValue)) {
				// Screen属性領域Mapに復元する
				storeMap = StoreMapConverter.decode(persistStoredMapValue);
			}

			if (storeMap == null || storeMap.isEmpty()) {
				log.info("storeMap not found.");
				storeMap = generateBlankStoreMap();
			}

			if (log.isDebugEnabled()) {
				log.debug(storeMap.toString());
			}

		} else {
			if (log.isDebugEnabled()) {
				log.debug(" -- StoreMapConverter set blankMap.");
			}
			storeMap = generateBlankStoreMap();
		}

		return storeMap;
	}

	/* (非 Javadoc)
	 * @see lumi.service.StoreMapService#storeMapSerialize(java.util.Map)
	 */
	@Override
	public String storeMapSerialize(Map<String, Object> storeMap) {

		if (storeMap != null && !storeMap.isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug(" -- storeMapValue get.");
			}
			// 文字列表現に変換
			String persistStoreMapValue = StoreMapConverter.encode(storeMap);

			// テーブルに保管するときのキー名を生成
			String key = generatePersistenceKey();

			// テーブル登録のパラメータ
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("SEQ" , key);
			param.put("STOREMAP", persistStoreMapValue);

			log.debug(param.toString());

			// テーブルへ保管する
			dao.insert(Query.savePersistStoreMap.toString(), param);

			// 保管時のキー名を返す
			return key;

		} else {
			if (log.isDebugEnabled()) {
				log.debug(" -- storeMapValue doesn't get.");
			}
			return null;
		}
	}

	/**
	 * Screen属性領域の空Mapを生成する。LumiActionSupportから移動。
	 *
	 * @return 空(要素1)のMapを生成する。
	 */
	protected Map<String, Object> generateBlankStoreMap() {
		if (log.isDebugEnabled()) {
			log.debug(" -- StoreMap generate.");
		}
		return new HashMap<String, Object>(1);
	}

	@Autowired
	private DAO dao;

	/**
	 * Screen属性領域のMapを格納する際のキー名を取得する。
	 * @return 保存時のキー名
	 */
	protected String generatePersistenceKey() {
		long systime = System.currentTimeMillis();

		String key = String.valueOf(systime);

		return key;
	}
}
