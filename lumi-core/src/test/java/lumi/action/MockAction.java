/**
 *
 */
package lumi.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.interceptor.annotations.Blocked;

import lombok.Getter;
import lombok.Setter;
import lumi.service.mock.MockService;

/**
 * @author A-pZ
 *
 */
@Controller
@Scope("prototype")
public class MockAction extends LumiActionSupport {
	@Blocked
	@Autowired
	@Getter @Setter
	private MockService service;
}
