/**
 *
 */
package lumi.vo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;

import lumi.vo.BridgeMessage.MessageLevel;

/**
 * Serviceクラスからメッセージを定義するBridgeMessageを生成するファクトリクラス。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Scope("singleton")
public class BridgeMessageFactory {
	private BridgeMessageFactory() {

	}

	/**
	 * Serviceクラスのメッセージインスタンスを生成する。
	 *
	 * @param level メッセージレベル
	 * @param messageId メッセージID
	 * @param placeHolder プレースホルダ
	 * @return メッセージ
	 */
	public static BridgeMessage getMessage(MessageLevel level,
			String messageId, List<String> placeHolder) {
		BridgeMessage instance = new BridgeMessage();
		instance.setLevel(level);
		instance.setMessageId(messageId);
		instance.setPlaceHolder(placeHolder);
		return instance;
	}

	/**
	 * Serviceクラスのメッセージインスタンスを生成する。FieldValidator用。
	 *
	 * @param level メッセージレベル
	 * @param messageId メッセージID
	 * @param fieldname 項目名
	 * @param placeHolder プレースホルダ
	 * @return メッセージ
	 */
	public static BridgeMessage getMessage(MessageLevel level,
			String messageId, String fieldname, List<String> placeHolder) {
		BridgeMessage instance = new BridgeMessage();
		instance.setLevel(level);
		instance.setMessageId(messageId);
		instance.setFieldname(fieldname);
		instance.setPlaceHolder(placeHolder);
		return instance;
	}

	/**
	 * Serviceクラスのメッセージインスタンスを生成する。
	 *
	 * @param level メッセージレベル
	 * @param messageId メッセージID
	 * @param placeHolder プレースホルダ
	 * @return メッセージ
	 */

	public static BridgeMessage getMessage(MessageLevel level,
			String messageId, Object placeHolder) {

		List<String> newPlaceHolder = generatePlaceHolder(placeHolder);
		return getMessage(level, messageId, newPlaceHolder);
	}

	/**
	 * Serviceクラスのメッセージインスタンスを生成する。FieldValidator用。
	 *
	 * @param level メッセージレベル
	 * @param messageId メッセージID
	 * @param fieldname 項目名
	 * @param placeHolder プレースホルダ
	 * @return メッセージ
	 */
	public static BridgeMessage getMessage(MessageLevel level,
			String messageId, String fieldname, Object placeHolder) {
		List<String> newPlaceHolder = generatePlaceHolder(placeHolder);
		return getMessage(level, messageId, fieldname, newPlaceHolder);
	}

	/**
	 * ワーニングを設定する。
	 * @deprecated ワーニング情報は現在非推奨です。
	 * @param warn
	 *            ワーニング情報インスタンス
	 * @return メッセージ
	 */
	@Deprecated
	public static BridgeMessage getWarning(Warning warn) {
		BridgeMessage instance = new BridgeMessage();
		instance.setLevel(BridgeMessage.MessageLevel.WARN);
		instance.setMessageId(warn.getId());
		instance.setWarning(warn);
		return instance;
	}

	/**
	 * 配列のPlaceHolderをList&lt;String&gt;に変更する。
	 *
	 * @param placeHolder String配列のプレースホルダ
	 * @return List&lt;String&gt;に変換したプレースホルダ
	 */
	@SuppressWarnings("unchecked")
	public static List<String> generatePlaceHolder(Object placeHolder) {
		List<String> newPlaceHolder = new ArrayList<String>();

		if (placeHolder.getClass().isArray()) {
			for (int i = 0; i < Array.getLength(placeHolder); i++) {
				String value = (String) Array.get(placeHolder, i);
				newPlaceHolder.add(value);
			}
		} else if (placeHolder instanceof String) {
			newPlaceHolder.add((String)placeHolder);
		} else {
			newPlaceHolder = (List<String>) placeHolder;
		}
		return newPlaceHolder;
	}
}
