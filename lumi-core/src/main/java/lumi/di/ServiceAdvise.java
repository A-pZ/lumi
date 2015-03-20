/**
 *
 */
package lumi.di;

import java.util.Arrays;
import java.util.List;

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
		if (!logIgnore(joinPoint)) {
			log.info("ServiceAdvise(before) : {}" , joinPoint.toLongString());
			trace(joinPoint);
		}
	}

	/**
	 * Serviceクラスの後処理。
	 * @param joinPoint
	 * @throws Throwable
	 */
	@AfterReturning("execution(public * lumi.service..*.*(..))")
	public void afterReturning(JoinPoint joinPoint) throws Throwable {
		if (!logIgnore(joinPoint)) {
			log.info("ServiceAdvise(After ) : {}" , joinPoint.toLongString());
			trace(joinPoint);
		}
	}

	/**
	 * ログ出力をスキップするメソッドであるかを判定する。
	 * @param joinPoint JoinPoint
	 * @return set/getで始まるメソッド名の場合はtrue
	 */
	protected boolean logIgnore(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName().substring(0,PREFIX_LENGTH);

		return IGNORE_METHOD_NAME_STRINGS.contains(name);
	}

	/** 無視するメソッド名の接頭辞 */
	public final static List<String> IGNORE_METHOD_NAME_STRINGS = Arrays.asList("set","get");
	public final static int PREFIX_LENGTH = 3;
}
