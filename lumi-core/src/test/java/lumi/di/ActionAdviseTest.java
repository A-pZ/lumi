package lumi.di;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.struts2.convention.annotation.Action;
import org.aspectj.lang.JoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import lumi.action.MockAction;
import lumi.service.LumiService;
import lumi.service.StoreMapService;
import lumi.service.mock.MockService;

public class ActionAdviseTest {

	@Spy
	@Autowired
	ActionAdvise advise;

	@Mock
	JoinPoint joinPoint;

	@Mock
	Action annotation;

	@InjectMocks
	MockAction mockAction;// = new MockAction();

	@Mock
	MockService mockService;

	@Mock
	StoreMapService storeMapService;

	@Mock
	ActionSupport actionSupport;

	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testBefore_LumiActionでない場合() throws Exception {
		when(joinPoint.getTarget()).thenReturn(actionSupport);

		advise.before(joinPoint, annotation);

		// 何も実施されない
		assert true;
	}

	@Test
	public void testFindServiceInstance() throws Exception {
		LumiService service = advise.findServiceInstance(mockAction);

		assertTrue( service instanceof MockService );
	}
}
