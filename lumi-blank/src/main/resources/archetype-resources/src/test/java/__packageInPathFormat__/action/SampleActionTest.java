package ${package}.action;

import static org.junit.Assert.*;

import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		  locations={"classpath:spring/applicationContext.xml",
		             "classpath:spring/applicationContext-*.xml" })
public class SampleActionTest extends
		StrutsSpringJUnit4TestCase<SampleAction> {

	@Test
	public void testStart() throws Exception {

		ActionProxy proxy = getActionProxy("/");

		String result = proxy.execute();

		assertEquals(ActionSupport.SUCCESS, result);
	}

}
