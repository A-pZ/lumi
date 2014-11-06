/**
 *
 */
package lumi.service;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import lumi.function.StoreMapConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Screen属性を、画面のHIDDEN領域に格納するときに使う。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Slf4j
@Scope("singleton")
@Service
public class SimpleStoreMapServiceImpl implements StoreMapService {

	/* (非 Javadoc)
	 * @see lumi.service.StoreMapService#storeMapDeserialize(java.lang.String)
	 */
	@Override
	public Map<String, Object> storeMapDeserialize(String storeMapValue) {
		Map<String, Object> storeMap = null;

		if (StringUtils.isNotBlank(storeMapValue)) {
			if (log.isDebugEnabled()) {
				log.debug(" -- StoreMapConverter.set");
			}

			storeMap = StoreMapConverter.decode(storeMapValue);
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
			return StoreMapConverter.encode(storeMap);

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

}
