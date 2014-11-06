/**
 *
 */
package lumi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import lombok.Getter;

/**
 * 一覧検索をするActionであるかを判定するアノテーション。
 * 判定はActionAdviseで行う。
 * Actionクラスへこのアノテーションを付記すると、一覧検索の最大表示件数に関するページング用オブジェクト(PagingFrontVO)を取得する。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 */
@Target(value={ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Pagination {
	/**
	 * 一覧検索対象Actionであるかの判定。
	 * @return
	 */
	boolean value() default true;

	/**
	 * 検索条件（フラグメント）に含める文字列。
	 * @return フラグメント文字列
	 */
	String fragment() default "";

	/**
	 * ページング遷移の種類。
	 * @return ページング遷移の種類
	 */
	FlowType flowType() default FlowType.none;

	/**
	 * ページング遷移の種類。「無使用」「次へ」「戻る」「再検索」の4種類。
	 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
	 *
	 */
	public enum FlowType {
		/** ページングボタンは使用しない（デフォルト値） */
		none(1),
		/** 「次へ」ボタン */
		forward(2),
		/** 「戻る」ボタン */
		previous(3),
		/** 「再検索」ボタン */
		current(4);

		@Getter
		private int value;

		/**
		 * コンストラクタ。
		 * @param value 遷移タイプの数値
		 */
		private FlowType(int value) {
			this.value = value;
		}
	}
}
