/**
 *
 */
package lumi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * サーブレット属性の拡張用、Flashスコープ。
 * Flashスコープとは、今回のリクエスト～次回レスポンスまでが寿命のサーブレット属性と定義する。
 * Flashスコープのオブジェクトはセッション属性に格納され、寿命がくることで破棄する。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Flash {
	/** Flashスコープの集合をセッションに格納する際のキー名 */
	String SESSION_KEYNAME = "lumi.scope.flash";
}
