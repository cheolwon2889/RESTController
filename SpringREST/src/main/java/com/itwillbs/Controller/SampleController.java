package com.itwillbs.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



//@RestController : 모든 매핑된 매서드가 ResponseBody을 포함하고 있다.
@RestController
@RequestMapping("/rest")
public class SampleController {
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	/* @ResponseBody */
	@RequestMapping(value="/test1" , method = RequestMethod.GET)
	public /* @ResponseBody */ void test1() {
		logger.debug(" test1() 실행 ");
	}
}
