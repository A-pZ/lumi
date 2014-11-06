/**
 *
 */
package lumi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mybatisを利用した時のみ適用する、更新・登録・削除ロジックで利用するアノテーション。
 * Actionクラスから起動するSerivceクラスのメソッドに対して付記可能。
 * メソッドの戻り値(更新レコード数)を指定する。
 * すべてのServiceメソッドにつける必要はない。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleRecord {
	int count() default 1;
}
