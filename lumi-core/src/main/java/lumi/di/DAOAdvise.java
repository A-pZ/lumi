/**
 *
 */
package lumi.di;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.log4j.Log4j2;

/**
 * DAOクラスのアドバイザ。
 * 前処理、後処理に処理を追加できる。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Log4j2
@Aspect
public class DAOAdvise extends AbstractAdvise {

	/**
	 * DAOメソッドの前処理にロジックを挟み込む。
	 * @param joinPoint 対象メソッド
	 * @throws Exception 発生する例外
	 */
	@Before("execution(public * lumi.dao.DAOImpl.*(..))")
	public void before(JoinPoint joinPoint) throws Exception {
		log.info("DAOAdvise(before) : {}" , joinPoint.toLongString());
		trace(joinPoint);

		log.info(" -- begin ->");
	}

	/**
	 * DAOメソッドの後処理にロジックを挟みこむ。
	 * @param joinPoint 対象メソッド
	 * @param retVal メソッドの戻り値
	 * @throws Throwable 発生する例外
	 */
	@AfterReturning(pointcut="execution(public * lumi.dao.DAOImpl.*(..))" , returning="retVal")
	public void afterReturning(JoinPoint joinPoint , Object retVal) throws Throwable {
		log.info("DAOAdvise(After ) : {}" , joinPoint.toLongString());
		trace(joinPoint);

		log.info(" <- end ----");
	}
}
