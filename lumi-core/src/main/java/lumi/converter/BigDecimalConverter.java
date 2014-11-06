/**
 *
 */
package lumi.converter;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * BigDecimal型の型変換。カンマ区切りの文字列入力はカンマを取り除きBigDecimalにする。
 * BigDecimal型の数値を、文字列出力するときにカンマ表記に変換する。
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 *
 */
@Slf4j
public class BigDecimalConverter extends StrutsTypeConverter {

	/**
	 * リクエストパラメータからの置換を行う。
	 * @param Map コンテキスト情報Map
	 * @param String[] パラメータの配列
	 * @param Class 変換後のクラス
	 * @return 型変換後のパラメータ（オブジェクト）
	 */

	@Override
	public Object convertFromString(
			@SuppressWarnings(value="rawtypes") Map context, String[] values, @SuppressWarnings(value="rawtypes") Class toClass) {
		Object result = null;

		log.debug("convertFromString -> " + context + ", "
				+ values.toString() + ", " + toClass.getName());

		if (values != null) {
			if (values.getClass().isArray() && toClass.isArray()) {
				Class<?> componentType = toClass.getComponentType();

				result = Array.newInstance(componentType,
						Array.getLength(values));
				log.debug("result(pre):" + result);
				for (int i = 0, icount = Array.getLength(values); i < icount; i++) {
					Array.set(result, i,
							convertBigDecimalValue(
									context ,
									Array.get(values, i) ,
									toClass
							)
					);
				}
			} else {
				// カンマを取り除く
				result = convertBigDecimalValue(context , values[0] , toClass);
			}
		}
		log.debug("result : " + result);
		return result;
	}

	/**
	 * BigDecimalへの型変換。
	 *
	 * @param Map コンテキスト情報Map
	 * @param Object 変換対象の文字列
	 * @param Class 変換後のクラス
	 * @return 型変換した後のオブジェクト
	 */
	protected Object convertBigDecimalValue(
			@SuppressWarnings(value="rawtypes") Map context ,Object paramArrayOfString ,@SuppressWarnings(value="rawtypes") Class toClass
			) {
		BigDecimal result;

		String value = (String)paramArrayOfString;
		value = value.replace(",", "");
		if ( log.isDebugEnabled()) {
			log.debug("value -> " +value);
		}
		try {
			// Struts2標準で持っているBigDecimal変換
			result = bigDecValue(value);
		} catch (NumberFormatException e) {
			// 型変換エラーをStruts2へ通知する = JSPは変換前を表示
			log.warn("      -> performFallback." );
			super.performFallbackConversion(context, paramArrayOfString, toClass);

			return value;
		}

		if ( log.isDebugEnabled()) {
			log.debug("      -> " +value.toString());
		}
		return result;
	}

	/**
	 * BigDecimalからStringへの変換を行う。画面出力用。
	 * @param paramMap コンテキスト情報Map
	 * @param paramObject Actionクラスのフィールド
	 * @return 画面に表示する文字列
	 */
	public String convertToString(@SuppressWarnings("rawtypes") Map paramMap, Object paramObject) {
		if ( paramObject instanceof BigDecimal ) {
			NumberFormat fmt = NumberFormat.getInstance();
			BigDecimal dec = new BigDecimal(paramObject.toString());
			return fmt.format(dec);
		} else {
			return paramObject.toString();
		}
	}

}
