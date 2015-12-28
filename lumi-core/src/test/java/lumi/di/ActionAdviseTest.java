package lumi.di;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.apache.struts2.convention.annotation.Action;
import org.aspectj.lang.JoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.opensymphony.xwork2.ActionSupport;

import lumi.action.LumiActionSupport;
import lumi.service.LumiService;
import lumi.service.SimpleStoreMapServiceImpl;

public class ActionAdviseTest {

	@InjectMocks
	ActionAdvise advise;

	@Mock
	JoinPoint joinPoint;

	@Mock
	Action annotation;

	@Mock
	LumiActionSupport lumiActionSupport;

	@Mock
	SimpleStoreMapServiceImpl storeMapService;

	@Mock
	ActionSupport actionSupport;

	@Mock
	LumiService service;

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
	public void testBefore_LumiAction_StoreMapValue生成() throws Exception {
		lumiActionSupport.setStoreMapValue("[]");
		when(joinPoint.getTarget()).thenReturn(lumiActionSupport);
		advise.before(joinPoint, annotation);

		// StoreMapValueの空Mapが生成される
		assertEquals(new HashMap(), lumiActionSupport.getStoreMap());
	}

	@Test
	public void testFindServiceInstance() throws Exception {
		LumiService service = advise.findServiceInstance(lumiActionSupport);
		assert true;
	}
}
