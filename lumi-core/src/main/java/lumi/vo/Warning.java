/**
 *
 */
package lumi.vo;

import java.util.List;

import lombok.Data;

/**
 * ワーニング情報のVO。
 * この情報はlumiのテンプレートであるactionwarningにて出力される。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Deprecated
@Data
public class Warning {
	/** ワーニングID */
	private String id;
	/** ワーニングレベル */
	private Level level;
	/** ダイアログのタイトル */
	private String title;
	/** ワーニングダイアログのテキスト */
	private String bodyText;
	/** 再表示を許可するか */
	private boolean canRepeat;
	/** 動的メッセージ部分 */
	private List<String> messages;

	/**
	 * ワーニングダイアログの警告レベルを定義する。
	 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
	 *
	 */
	public enum Level {
		INFO {
			public String toString() {
				return this.name().toLowerCase();
			}
		},
		WARNING {
			public String toString() {
				return this.name().toLowerCase();
			}
		},
		ERROR {
			public String toString() {
				return this.name().toLowerCase();
			}
		}
	}

}
