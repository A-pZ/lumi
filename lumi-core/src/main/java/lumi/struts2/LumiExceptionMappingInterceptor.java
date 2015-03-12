/**
 *
 */
package lumi.struts2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ExceptionMappingConfig;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;

/**
 * 標準例外マッピングインターセプタの拡張。
 * @author A-pZ
 *
 */
@Slf4j
public class LumiExceptionMappingInterceptor extends ExceptionMappingInterceptor {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String intercept(ActionInvocation invocation) throws Exception {
		String result;

		try {
			log.debug("> invoke.");
			result = invocation.invoke();
		} catch (Exception e)
		{
			log.debug("catch exception.");
			if (isLogEnabled()) handleLogging(e);

			List exceptionMappings = invocation.getProxy().getConfig().getExceptionMappings();
			log.info("> exceptionMappings :" + exceptionMappings);

			ExceptionMappingConfig mappingConfig = findMappingFromExceptions(exceptionMappings, e);
			log.info("> ExceptionMappingConfig :" + mappingConfig);

			if (mappingConfig != null && mappingConfig.getResult() != null) {
				Map parameterMap = mappingConfig.getParams();

				invocation.getInvocationContext().setParameters(new HashMap(parameterMap));

				result = mappingConfig.getResult();

				log.info("-- publish Exception.");
				publishException(invocation, new ExceptionHolder(e));
			} else {
				log.info(" * ExceptionMappingConfig was null. *");
				throw e;
			}
		}

		return result;
	}
}
