/**
 *
 */
package lumi.converter;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author A-pZ
 *
 */
public class BigDecimalConverterTest {

	@Mock
	Map context;

	@InjectMocks
	BigDecimalConverter converter;

	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testConvertFromString正常ケース_単数() {
		String[] values = (String[]) Arrays.asList("1234").toArray();
		Class toClass = BigDecimal.class;
		Object result = converter.convertFromString(context, values, toClass);
		assertEquals(new BigDecimal("1234") , result);
	}

	@Test
	public void testConvertFromString正常ケース_カンマ含み() {
		String[] values = (String[]) Arrays.asList("12,345").toArray();
		Class toClass = BigDecimal.class;
		Object result = converter.convertFromString(context, values, toClass);
		assertEquals(new BigDecimal("12345") , result);
	}
	@Test
	public void testConvertFromString正常ケース_カンマ位置が正しくない場合() {
		String[] values = (String[]) Arrays.asList("12345,67").toArray();
		Class toClass = BigDecimal.class;
		Object result = converter.convertFromString(context, values, toClass);
		assertEquals(new BigDecimal("1234567") , result);
	}
	@Test
	public void testConvertFromString正常ケース_複数() {
		String[] values = (String[]) Arrays.asList("12345","22,345").toArray();
		Class toClass = (new BigDecimal[2]).getClass();
		BigDecimal[] result = (BigDecimal[])converter.convertFromString(context, values, toClass);
		assertArrayEquals( Arrays.asList(new BigDecimal("12345"), new BigDecimal("22345")).toArray() , result);
	}

	@Test(expected=NumberFormatException.class)
	public void testConvertFromString異常ケース_単数() {
		String[] values = (String[]) Arrays.asList("").toArray();
		Class toClass = BigDecimal.class;
		Object result = converter.convertFromString(context, values, toClass);
		assertEquals(new BigDecimal("1234567") , result);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testConvertFromString異常ケース_複数() {
		String[] values = (String[]) Arrays.asList("12345","ABCDE").toArray();
		Class toClass = (new BigDecimal[2]).getClass();
		Object result = converter.convertFromString(context, values, toClass);
		assertArrayEquals( Arrays.asList(new BigDecimal("12345"), "ABCDE").toArray() , (Object[])result);
	}
	@Test
	public void testConvertToString正常ケース_単数() {
		BigDecimal dec = new BigDecimal(12345);
		String result = converter.convertToString(context, dec);
		assertEquals("12,345" , result);
	}
	@Test
	public void testConvertToString例外ケース_指定型以外() {
		String paramObject = "33,A33,A33";
		String result = converter.convertToString(context, paramObject);
		assertEquals(paramObject , result);
	}
}
