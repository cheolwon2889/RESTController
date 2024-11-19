package com.itwillbs.service;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service("mailService")
public class MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);	
	// mail-context.xml에서 정보를 주입
	@Inject
	private JavaMailSender mailSender;
	
	// @Async 해당 메서드를 비동기로 처리
	@Async
	public void sendMail(String to , String subject , String body) throws Exception {
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper msghelper = new MimeMessageHelper(msg, true, "UTF-8");
		
		msghelper.setTo(to); // 받는사람
		// msghelper.setFrom(from); // 보내는 사람 (사용X) => 보내는사람 이메일주소 
		// msghelper.setCc(cc); // 참조
		msghelper.setSubject(subject);
		msghelper.setText(body, true);
		
		// 메일 전송
		mailSender.send(msg);
		
		logger.info("메일 전송 성공! ");
		
	}
	
	
	

}
