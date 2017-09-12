/**
 *
 */
package lumi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import lumi.vo.BridgeMessage;
import lumi.vo.BridgeMessageFactory;
import lumi.vo.Warning;

/**
 * Lumiで利用するSpringのServiceクラス。
 * Actionクラスから実行するServiceクラスはこのクラスを継承すると、メッセージ管理やセッション情報の取得などが自動的に行われる。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Log4j2
public class LumiService implements IService {

	/**
	 * 共通メッセージ領域。
	 * {@link List}で{@link BridgeMessage}を格納する。
	 */
	protected List<BridgeMessage> messages;


	/**
	 * INFOレベルのメッセージを追加する。
	 * @param messageId メッセージプロパティに定義しているメッセージID
	 */
	public void addInfoMessage(String messageId) {
		BridgeMessage message =
		        BridgeMessageFactory.getMessage(BridgeMessage.MessageLevel.INFO, messageId, null);

		addMessage(message);
	}

	/**
	 * INFOレベルのメッセージを追加する。動的メッセージ部分はplaceHolder部分で定義する。
	 * @param <T> メッセージプレースホルダの型
	 * @param messageId メッセージプロパティに定義しているメッセージID
	 * @param placeHolder 動的メッセージ部分。List&lt;String&gt;型ないしはString[]型
	 */
	public <T> void addInfoMessage(String messageId, T placeHolder) {

		BridgeMessage message =
		        BridgeMessageFactory.getMessage(BridgeMessage.MessageLevel.INFO, messageId, placeHolder);

		addMessage(message);
	}

	/**
	 * ERRORレベルのメッセージを追加する。
	 * @param messageId メッセージプロパティに定義しているメッセージID
	 */
	public void addErrorMessage(String messageId) {
		BridgeMessage message =
		        BridgeMessageFactory.getMessage(BridgeMessage.MessageLevel.ERROR, messageId, null);

		addMessage(message);
	}

	/**
	 * ERRORレベルのメッセージを追加する。動的メッセージ部分はplaceHolder部分で定義する。
	 * @param <T> メッセージプレースホルダの型
	 * @param messageId メッセージプロパティに定義しているメッセージID
	 * @param placeHolder 動的メッセージ部分。List&lt;String&gt;型ないしはString[]型
	 */
	public <T> void addErrorMessage(String messageId, T placeHolder) {
		BridgeMessage message =
		        BridgeMessageFactory.getMessage(BridgeMessage.MessageLevel.ERROR, messageId, placeHolder);

		addMessage(message);
	}

	/**
	 * WARNレベルのメッセージを追加する。
	 * @param messageId メッセージプロパティに定義しているメッセージID
	 */
	public void addWarnMessage(String messageId) {
		BridgeMessage message =
		        BridgeMessageFactory.getMessage(BridgeMessage.MessageLevel.WARN, messageId, null);

		addMessage(message);
	}

	/**
	 * WARNレベルのメッセージを追加する。動的メッセージ部分はplaceHolder部分で定義する。
	 * @param <T> メッセージプレースホルダの型
	 * @param messageId メッセージプロパティに定義しているメッセージID
	 * @param placeHolder プレースホルダ
	 */
	public <T> void addWarnMessage(String messageId , T placeHolder) {
		BridgeMessage message =
		        BridgeMessageFactory.getMessage(BridgeMessage.MessageLevel.WARN, messageId, placeHolder);

		addMessage(message);
	}

	/**
	 * ワーニングダイアログの情報を格納する。
	 * ワーニング情報は{@link Warning}を参照。
	 * @param warn ワーニング情報。
	 */
	public void addWarning(Warning warn) {
		BridgeMessage message =
		        BridgeMessageFactory.getWarning( warn);
		addMessage(message);
	}

	/**
	 * フィールドエラーを追加する。
	 * @param messageId メッセージプロパティに定義しているメッセージID
	 * @param fieldname 項目名
	 */
	public void addFieldError(String messageId, String fieldname) {
		BridgeMessage message =
		        BridgeMessageFactory.getMessage(BridgeMessage.MessageLevel.FIELD, messageId, fieldname, null);
		addMessage(message);
	}

	/**
	 * フィールドエラーのメッセージを追加する。動的メッセージ部分はplaceHolder部分で定義する。
	 * @param messageId メッセージプロパティに定義しているメッセージID
	 * @param placeHolder 動的メッセージ部分。List&lt;String&gt;型ないしはString型
	 * @param <T> プレースホルダ
	 * @param fieldname フィールド名
	 */
	public <T> void addFieldError(String messageId, T placeHolder, String fieldname) {
		BridgeMessage message =
		        BridgeMessageFactory.getMessage(BridgeMessage.MessageLevel.FIELD, messageId, fieldname, placeHolder);

		addMessage(message);
	}

	/**
	 * メッセージを追加する(内部処理用)。
	 * @param message メッセージ用インスタンス
	 */
	protected void addMessage(BridgeMessage message) {
		if (messages == null) {
			messages = new ArrayList<BridgeMessage>();
		}
		messages.add(message);
	}

	/**
	 * メッセージの集合を取得する。Actionクラス用。
	 * @return メッセージ用インスタンスのList
	 */
	public List<BridgeMessage> getMessages() {
		return messages;
	}

	/**
	 * メッセージの集合を設定する。
	 * @param messages メッセージ用インスタンスのList
	 */
	public void setMessages(List<BridgeMessage> messages) {
		this.messages = messages;
	}

	/** セッションID。Actionクラスから格納される。 */
	@Setter @Getter
	private String sessionId;

	/**
	 * ユーザID。Actionクラスから格納される。
	 */
	@Setter @Getter
	private String userId;

	/** Actionクラスと共有しているセッション情報のMap */
	@Getter @Setter
	protected Map<String, Object> sessionMap;

	/** Actionクラスと共有しているScreen属性領域。 */
	@Getter @Setter
	protected Map<String, Object> storeMap;
}
