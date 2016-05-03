/**
 *
 */
package lumi.service.mock;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import lumi.service.LumiService;

/**
 * 単体試験用サービス。
 * @author A-pZ
 *
 */
@Scope("prototype")
@Service
@Log4j2
@Transactional(
	    propagation = Propagation.REQUIRED,
	    isolation = Isolation.DEFAULT,
	    readOnly = false,
	    rollbackFor = { RuntimeException.class, Exception.class })
public class MockService extends LumiService {

}
