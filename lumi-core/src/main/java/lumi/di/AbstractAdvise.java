/**
 *
 */
package lumi.di;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;

/**
 * LumiのAdvise抽象クラス。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Slf4j
public abstract class AbstractAdvise {
	/**
	 * 引数オブジェクトのデバッグログを出力する。
	 *
	 * @param joinPoint
	 *            ポイントカットしたオブジェクト
	 */
	protected void trace(JoinPoint joinPoint) {

		Object[] objs = joinPoint.getArgs();

		if (log.isDebugEnabled()) {
			if (objs != null && objs.length > 0) {
				log.debug("-- arguments[{}]" , objs.length);
				for (Object obj : objs) {
					log.debug(" - {}" , obj);
				}
			}
		}
	}
}
