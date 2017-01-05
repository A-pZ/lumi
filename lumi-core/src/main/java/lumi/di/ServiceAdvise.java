/**
 *
 */
package lumi.di;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.log4j.Log4j2;
import lumi.function.IgnoreLogging;

/**
 * サービス層のAdvise。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Aspect
@Log4j2
public class ServiceAdvise extends AbstractAdvise {

	/**
	 * Serviceクラスの前処理。
	 * @param joinPoint 処理を差し込んだ箇所（ジョインポイント）
	 * @throws Exception ジョインポイント内で発生した例外すべて
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
	 * @param joinPoint 処理を差し込んだ箇所（ジョインポイント）
	 * @throws Throwable ジョインポイント内で発生した例外すべて
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
	 * @param joinPoint 処理を差し込んだ箇所（ジョインポイント）
	 * @return set/getで始まるメソッド名の場合はtrue
	 */
	protected boolean logIgnore(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName().substring(0,PREFIX_LENGTH);

		return IgnoreLogging.isIgnore(name);
	}

	/** 無視するメソッド名の接頭辞 */
	final static List<String> IGNORE_METHOD_NAME_STRINGS = Arrays.asList("set","get");
	final static int PREFIX_LENGTH = 3;
}
