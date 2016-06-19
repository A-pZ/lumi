/**
 *
 */
package lumi.di;

import org.aspectj.lang.JoinPoint;

import lombok.extern.log4j.Log4j2;

/**
 * LumiのAdvise抽象クラス。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Log4j2
public abstract class AbstractAdvise {

	/**
	 * 引数オブジェクトのデバッグログを出力する。
	 *
	 * @param joinPoint
	 *            ポイントカットしたオブジェクト
	 */
	protected void trace(JoinPoint joinPoint) {
		if (! log.isDebugEnabled()) {
			return;
		}

		Object[] objs = joinPoint.getArgs();
		if (objs == null || objs.length == 0) {
			return;
		}

		log.debug("-- arguments[{}]" , objs.length);
	}
}
