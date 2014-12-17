/**
 *
 */
package lumi.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Actionクラスと基底Serviceクラスの間で共有するStruts2のメッセージ情報。
 * ActionSupportのメッセージ定義、メッセージレベルを定義する。 項目エラーメッセージの場合は項目名を定義する。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
public class BridgeMessage {

	/**
	 * メッセージのレベル。
	 * {@link MessageLevel}を設定する。
	 */
	@Getter @Setter
    private MessageLevel level;

	/**
	 * 表示するメッセージのメッセージID。このメッセージIDはStruts2のメッセージ用プロパティに格納しているIDと照合する。
	 */
    @Getter @Setter
	private String messageId;
    /**
     * 項目エラーの項目名。
     */
    @Getter @Setter
    private String fieldname;
    /**
     * エラーの動的メッセージ。
     */
    @Getter @Setter
	private List<String> placeHolder;

    /**
     * ワーニング情報。
     */
    @Deprecated
    @Getter @Setter
    private Warning warning;

	/**
	 * メッセージレベル定義。画面に表示するメッセージレベルの定義。
	 *
	 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
	 *
	 */
	public enum MessageLevel {
		/**
		 * INFOレベルのメッセージ。 ActionMessageが該当する。
		 */
		INFO,
		/**
		 * WARNINGレベルのメッセージ。 Lumiで拡張したActionWarningが該当する。
		 **/
		WARN,
		/**
		 * ERRORレベルのメッセージ。 ActionErrorが該当する。
		 */
		ERROR,
		/**
		 * FIELDレベルのメッセージ。 FieldErrorが該当する。
		 */
		FIELD
	}
}
