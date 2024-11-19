package com.itwillbs.Controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.service.MailService;

// @EnableAsync

@Controller
@EnableAsync
public class MailController {
	
	private static final Logger logger = LoggerFactory.getLogger(MailController.class);	
	
	@Inject
	private MailService mService;
	
	@RequestMapping(value = "/sendMail" , method= RequestMethod.GET )
	public String sendMail() throws Exception{
		logger.info("sendMail 실행 ");
		
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h1> ITWILL 메일 전송 </h1>");
		sb.append(" <img src='https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5667%2F2021%2F01%2F29%2F0000017937_001_20210129103108102.jpg&type=sc960_832'></img>");
		sb.append("");
		sb.append("");
		sb.append("");
		sb.append("");
		sb.append("</body>");
		sb.append("</html>");
				
		mService.sendMail("kawa640213@naver.com","테스트 메일", sb.toString());
		
		logger.info(" 메일 전송 왼료!  ");
		
		
		return "redirect:/resultMail";
	}
}
