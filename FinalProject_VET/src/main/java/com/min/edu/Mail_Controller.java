package com.min.edu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.min.edu.model.service.IPayment_Service;
import com.min.edu.model.service.IReservation_Service;
import com.min.edu.vo.Payment_VO;
import com.min.edu.vo.Reservation_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Mail_Controller {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private IPayment_Service pay_service;
	
	@Autowired
	private IReservation_Service resrv_service;
	
	public void sendMailTest() {
		log.info("&&&&& Mail_Controller 메일 보냅니다 &&&&&");
		
		String subject = "[처음처럼] Email 인증번호 입니다.";
		String content = "메일 테스트 내용";
		String from = "likeFirstTime1010@gmail.com";
		String to = "beaver.kim0327@naver.com";
		
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
	
	@PostMapping(path="/mailSend.do")
	@ResponseBody
	public String mailSend(String users_id, Model model) {
		log.info("&&&&& Mail_Controller 회원가입 페이지에서 이메일인증 버튼 클릭 {} &&&&&", users_id);
		System.out.println(users_id);
				
		//난수 생성
		int randomNum = RandomUtils.nextInt(100000, 1000000);
		System.out.println(randomNum);
		
		//메일보내기
		//제목
		String subject = "[퍼펫트케어] Email 인증번호 입니다.";
		//내용
		String content = "안녕하세요, 퍼펫트케어입니다. \n\n인증번호는 "+randomNum+"입니다.";
		//보내는 사람
		String from = "likeFirstTime1010@gmail.com";
		//받는 사람
		String to = users_id;
		
		//이메일 전송
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
		
		//생성한 난수를 메일로 보낸 값과 비교하기 위해 ajax로 반환 
		String certNum = String.valueOf(randomNum);
		return certNum;
	}
	
	//결제 완료 메일 전송
	@GetMapping(value = "/paymentMail.do")
	public String paymentMail(String pay_id,String imp_uid) {
		log.info("&&&&& Mail_Controller paymentMail 전달받은 parameter값 : {} {} &&&&&", pay_id, imp_uid);
		
		Payment_VO pvo = pay_service.searchMID(imp_uid);	
		int pay_amount = pvo.getPay_amount();
		String pay_time = pvo.getPay_time();
		String pay_num = pvo.getPay_num();
		
		String subject = "[퍼펫트케어] 감사합니다. 결제가 정상적으로 완료되었습니다.";
		String content = "퍼펫트케어 포인트 결제가 완료되었습니다.\n"
						+"충전 포인트는 " + pay_amount + " 포인트 입니다.\n"
						+"결제번호 : " + pay_num + " 결제일시 : " + pay_time
						+"\n\n퍼펫트케어를 이용해주셔서 감사합니다.";
		String from = "likeFirstTime1010@gmail.com";
		String to = "zzflsk0112@naver.com";
		
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
		return "redirect:/selectPntList.do";
	}
	
	//결제 취소 메일 전송
	@GetMapping(value = "/cancelPayMail.do")
	public String cancelPayMail(String imp_uid) {
		log.info("&&&&& Mail_Controller paymentMail 전달받은 parameter값 : {}&&&&&", imp_uid);
		Payment_VO pvo = pay_service.searchMID(imp_uid);	
		int pay_amount = pvo.getPay_amount();
		String pay_num = pvo.getPay_num();
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cancle_time = format.format(now);
		
		String subject = "[퍼펫트케어] 결제가 정상적으로 취소되었습니다.";
		String content = "퍼펫트케어 결제가 취소되었습니다.\n"
						+"환불 금액은 " + pay_amount + "원 입니다.\n"
						+"결제번호 : " + pay_num + " 취소일시 : " + cancle_time
						+"\n\n퍼펫트케어를 이용해주셔서 감사합니다.";
		String from = "likeFirstTime1010@gmail.com";
		String to = "zzflsk0112@naver.com";
		
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
		
		return "redirect:/selectAllPayment.do";
	}
	
}
