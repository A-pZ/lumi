/**
 *
 */
package lumi.di;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import lumi.annotation.SingleRecord;
import lumi.service.LumiService;
import lumi.vo.BridgeMessage;

/**
 * @author A-pZ
 *
 */
public class SingleRecordAdviseTest {

	@InjectMocks
	SingleRecordAdvise advise;

	@Mock
	JoinPoint joinPoint;

	@Mock
	SingleRecord annotation;

	@Spy
	LumiService service = new LumiService();

	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void 実行結果が0件() throws Throwable {
		int returnValue = 0;

		when(joinPoint.getTarget()).thenReturn(service);
		when(annotation.count()).thenReturn(1);

		advise.afterReturning(joinPoint, returnValue, annotation);

		// エラーメッセージを確認する
		List<BridgeMessage> messages = service.getMessages();

		assertEquals(1, messages.size());
	}

	@Test
	public void 実行結果が1件() throws Throwable {
		int returnValue = 1;

		when(joinPoint.getTarget()).thenReturn(service);
		when(annotation.count()).thenReturn(1);

		advise.afterReturning(joinPoint, returnValue, annotation);

		// エラーメッセージを確認する
		List<BridgeMessage> messages = service.getMessages();

		assertNull(messages);
	}

	@Test
	public void 実行結果が2件() throws Throwable {
		int returnValue = 2;

		when(joinPoint.getTarget()).thenReturn(service);
		when(annotation.count()).thenReturn(1);

		advise.afterReturning(joinPoint, returnValue, annotation);

		// エラーメッセージを確認する
		List<BridgeMessage> messages = service.getMessages();

		assertEquals(1, messages.size());
	}
}
