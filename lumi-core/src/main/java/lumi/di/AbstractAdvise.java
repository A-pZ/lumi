<<<<<<< HEAD
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
=======
/**
 *
 */
package lumi.di;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
		for (Object obj : objs) {
			if ( objectSize(obj) <= 32*1024 ) {
				log.debug(" - {}" , obj);
			} else {
				log.debug(" - {} {}" , obj.getClass().getName() , "- over 32k");
			}
		}
	}

	protected int objectSize(Object object) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream( baos );
			oos.writeObject(object);
		} catch (IOException e) {
			log.warn(e);
			return -1;
		}
		return baos.size();
	}
}
>>>>>>> refs/remotes/origin/master
