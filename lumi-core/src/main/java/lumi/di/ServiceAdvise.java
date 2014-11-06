/**
 *
 */
package lumi.di;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * サービス層のAdvise。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Aspect
@Slf4j
public class ServiceAdvise extends AbstractAdvise {

	/**
	 * Serviceクラスの前処理。
	 * @param joinPoint
	 * @throws Exception
	 */
	@Before("execution(public * lumi.service..*.*(..))")
	public void before(JoinPoint joinPoint) throws Exception {
		log.info("ServiceAdvise(before) : " + joinPoint.toLongString());
		trace(joinPoint);
	}

	/**
	 * Serviceクラスの後処理。
	 * @param joinPoint
	 * @throws Throwable
	 */
	@AfterReturning("execution(public * lumi.service..*.*(..))")
	public void afterReturning(JoinPoint joinPoint) throws Throwable {
		log.info("ServiceAdvise(After ) : " + joinPoint.toLongString());
		trace(joinPoint);
	}
}
