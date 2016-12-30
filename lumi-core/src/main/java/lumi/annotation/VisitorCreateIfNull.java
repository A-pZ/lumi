/**
 *
 */
package lumi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * VisitorFieldValidator対象のValueObject(VO)がnullだった場合に、自動的にインスタンスを生成するVOに対し設定する。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 * @see com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator
 *
 */
@Target({ ElementType.METHOD , ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitorCreateIfNull {
	boolean value() default true;
}
