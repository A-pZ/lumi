/**
 *
 */
package lumi.converter;

import java.lang.reflect.Array;
import java.util.Map;

import lombok.extern.log4j.Log4j2;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 大文字変換。
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Log4j2
public class UpperConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(@SuppressWarnings(value="rawtypes") Map paramMap, String[] paramArrayOfString, @SuppressWarnings(value="rawtypes") Class paramClass) {
		Object result = null;

		if (log.isDebugEnabled()) {
			log.debug("convertFromString -> {} , {} , {}"
				, paramMap , paramArrayOfString.toString() , paramClass.getName());
		}

		if (paramArrayOfString != null) {
			if (paramArrayOfString.getClass().isArray() && paramClass.isArray()) {
				Class<?> componentType = paramClass.getComponentType();

				result = Array.newInstance(componentType,
						Array.getLength(paramArrayOfString));
				if (log.isDebugEnabled()) log.debug("result(pre):{}" , result);
				for (int i = 0, icount = Array.getLength(paramArrayOfString); i < icount; i++) {
					Array.set(result, i, convertUpperString(Array.get(
							paramArrayOfString, i)));
				}
			} else {
				result = convertUpperString(paramArrayOfString[0]);
			}
		}
		if (log.isDebugEnabled()) log.debug("result : {}" , result);
		return result;
	}

	/**
	 * 大文字変換を実施する。
	 * @param value 変換前文字列
	 * @return 変換後文字列
	 */
	private String convertUpperString(Object paramArrayOfString) {
		return paramArrayOfString.toString().toUpperCase();
	}

	@Override
	public String convertToString(@SuppressWarnings(value="rawtypes") Map context, Object o) {
		String[] ret = (String[])o;
		return ret[0];
	}

	/**
	 * 変換チェック基本メソッド。
	 */
	public Object convertValue(@SuppressWarnings(value="rawtypes") Map context, Object o, @SuppressWarnings(value="rawtypes") Class toClass) {
		if (o instanceof String[]) {
            return convertFromString(context, (String[]) o, toClass);
        } else if (o instanceof String) {
            return convertFromString(context, new String[]{(String) o}, toClass);
        } else {
            return performFallbackConversion(context, o, toClass);
        }
    }

}
