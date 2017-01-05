/**
 *
 */
package lumi.di;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.log4j.Log4j2;
import lumi.annotation.SingleRecord;
import lumi.service.LumiService;

/**
 * Serviceクラスの@SingleRecordアノテーションがついたメソッドに対してDIを行う。
 * SingleRecordの値で定義したカウント以外の件数だった場合は例外をスローする。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Aspect
@Log4j2
public class SingleRecordAdvise extends AbstractAdvise {

	/**
	 * サービスクラスにて@SingleRecordアノテーションで定義したメソッドの後処理を行う参考実装。
	 * もし件数が1件でなかった場合は例外をスローする。
	 * @param joinPoint 対象ジョインポイント(Serviceクラスのメソッド)
	 * @param returnValue Serviceクラスreturn値
	 * @param annotation 対象メソッドのアノテーション
	 * @throws Throwable 対象メソッド実行中の例外
	 */
	@AfterReturning(
			pointcut = "execution(public * lumi.service.*.*(..)) && @annotation(annotation)",
			returning = "returnValue")
	public void afterReturning(JoinPoint joinPoint, Integer returnValue,
			SingleRecord annotation) throws Throwable {
		log.info("SingleRecordAdvise(After) : @SingleRecord({}) :{}"
				, annotation.count() , joinPoint.toLongString());

		trace(joinPoint);

		log.debug("return - " + returnValue);
		if (annotation.count() != returnValue) {
			log.warn("record insert/update error because insert/update count was {} ,but expected {}."
					, returnValue
					, annotation.count());
			LumiService service = (LumiService) joinPoint.getTarget();
			service.addErrorMessage("error.exclusive");
		}

		if ( log.isDebugEnabled()) {
			Object target = joinPoint.getTarget();
			log.debug("afterReturning:{}", target);
		}
	}
}
