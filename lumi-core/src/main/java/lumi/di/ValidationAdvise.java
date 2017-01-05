/**
 *
 */
package lumi.di;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.log4j.Log4j2;

/**
 * 拡張Validation用のアドバイザ。
 * 前処理、後処理、例外時に処理を挟むことができる。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Aspect
@Log4j2
public class ValidationAdvise extends AbstractAdvise {

	/**
	 * 拡張Validationの前処にロジックを挟む。
	 * @param joinPoint 処理を差し込んだ箇所（ジョインポイント）
	 * @throws Exception ジョインポイント内で発生した例外すべて
	 */
	@Before("execution(public * lumi.validator.*.validate(..))")
	public void before(JoinPoint joinPoint) throws Exception {
		log.info("ValidationAdvise(before) : {}" , joinPoint.toLongString());
		trace(joinPoint);
	}

	/**
	 * 拡張Validationの後処理にロジックを挟む。
	 * @param joinPoint 処理を差し込んだ箇所（ジョインポイント）
	 * @throws Exception ジョインポイント内で発生した例外すべて
	 */
	@AfterReturning("execution(public * lumi.validator.*.validate(..))")
	public void afterReturning(JoinPoint joinPoint) throws Throwable {
		log.info("ValidationAdvise(After ) : {}" , joinPoint.toLongString());
		trace(joinPoint);
	}

	/**
	 * 拡張Validationの例外処理にロジックを挟む。
	 * @param joinPoint 処理を差し込んだ箇所（ジョインポイント）
	 * @throws Exception ジョインポイント内で発生した例外すべて
	 */
	@AfterThrowing("execution(public * lumi.validator.*.*(..))")
	public void afterThrowing(JoinPoint joinPoint) throws Throwable {
		log.info("ValidationAdvise(afterThrowing) : {}"
				, joinPoint.toLongString());
		trace(joinPoint);
	}

}
