/**
 *
 */
package lumi.di;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 拡張Validation用のアドバイザ。
 * 前処理、後処理、例外時に処理を挟むことができる。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Aspect
@Slf4j
public class ValidationAdvise extends AbstractAdvise {

	/**
	 * 拡張Validationの前処にロジックを挟む。
	 * @param joinPoint
	 * @throws Exception
	 */
	@Before("execution(public * lumi.validator.*.validate(..))")
	public void before(JoinPoint joinPoint) throws Exception {
		log.info("ValidationAdvise(before) : {}" , joinPoint.toLongString());
		trace(joinPoint);
	}

	/**
	 * 拡張Validationの後処理にロジックを挟む。
	 * @param joinPoint
	 * @throws Throwable
	 */
	@AfterReturning("execution(public * lumi.validator.*.validate(..))")
	public void afterReturning(JoinPoint joinPoint) throws Throwable {
		log.info("ValidationAdvise(After ) : {}" , joinPoint.toLongString());
		trace(joinPoint);
	}

	/**
	 * 拡張Validationの例外処理にロジックを挟む。
	 * @param joinPoint
	 * @throws Throwable
	 */
	@AfterThrowing("execution(public * lumi.validator.*.*(..))")
	public void afterThrowing(JoinPoint joinPoint) throws Throwable {
		log.info("ValidationAdvise(afterThrowing) : {}"
				, joinPoint.toLongString());
		trace(joinPoint);
	}

}
