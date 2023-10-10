package com.test.edu;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Mail_JUnitTest {

	@Autowired
	JavaMailSenderImpl mailSender;
	
	@Test
	public void mailSendTest() {
		
		String subject = "test 메일";
		String content = "메일 테스트 내용";
		String from = "likefirsttime1010@gmail.com";
		String to = "soi_yeoa@naver.com";

		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content);
			
			mailSender.send(mail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
	}

}
