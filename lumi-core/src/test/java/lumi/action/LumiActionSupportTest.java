/**
 *
 */
package lumi.action;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
/**
 * @author A-pZ
 *
 */
public class LumiActionSupportTest {

	@InjectMocks
	LumiActionSupport action;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	HttpServletRequest request;

	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

	//

	@Test
	public void testユーザログイン() {
		action.setServletRequest(request);
		when(request.getUserPrincipal().getName()).thenReturn("user01");

		assertEquals("user01" , action.getLoginUsername());
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testユーザ表示名() {

		String username = action.getUserDisplayName();
	}
}
