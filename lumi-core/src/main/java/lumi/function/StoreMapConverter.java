/**
 *
 */
package lumi.function;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 格納したScreen属性VOを変換するクラス。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
public class StoreMapConverter {

	private static final Logger log = LoggerFactory.getLogger(StoreMapConverter.class);

	/**
	 * StoreMapをBASE64エンコードした文字列で返す。
	 * @param storeMap Screen属性VOを格納したMap
	 * @return BASE64エンコードした文字列
	 * @throws IOException
	 */
	public static String encode(Map<String, Object> storeMap)  {
		String retValue = null;

		try {
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(byteArray);
			os.writeObject(storeMap);

			byte[] encoded = Base64.encodeBase64(byteArray.toByteArray());
			retValue = new String(encoded);
		} catch (IOException e) {
			log.warn("StoreMapConverter#encode(VO -> String is failure" , e);
		}

		return retValue;
	}

	/**
	 * BASE64エンコードされた文字列をstoreMapへ変換する。
	 * @param base64String シリアライズしたstoreMapをBASE64文字列に変換した文字列
	 * @return storeMap
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> decode(String base64String) {
		Map<String,Object> returnObj = null;

		try {
			byte[] decoded = Base64.decodeBase64(base64String);

			ByteArrayInputStream byteArray = new ByteArrayInputStream(decoded);
			ObjectInputStream ois = new ObjectInputStream(byteArray);
			returnObj = (Map<String,Object>)ois.readObject();
		} catch (IOException e) {
			log.warn("StoreMapConverter#decode(String -> VO) is failure." , e);
		} catch (ClassNotFoundException e) {
			log.warn("StoreMapConverter#decode(generate VO) is failure." , e);
		}

		return returnObj;
	}
}
