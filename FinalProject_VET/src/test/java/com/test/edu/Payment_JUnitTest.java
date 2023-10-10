package com.test.edu;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.min.edu.model.service.IPayment_Service;
import com.min.edu.vo.Payment_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class Payment_JUnitTest {

	@Autowired
	private IPayment_Service service; 

//	@Test
	public void selectAllPayment() {
		String pay_id = "elsa@disney.com";
		
		List<Payment_VO> lists = service.selectAllPayment(pay_id);
		assertNotNull(lists);
	}
	
//	@Test
	public void selectOnePayment() {
		String pay_num = "P2309061";
		Payment_VO pvo = service.selectOnePayment(pay_num);
		
		assertNotNull(pvo);
	}
	
//	@Test
	public void insertNewPayment() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("pay_id", "elsa@disney.com");
			put("pay_amount", 5000);
			put("pay_code", "inisis5");
		}};
		
 		int n = service.insertNewPayment(map);
 		assertEquals(n, 1);
	}
	
//	@Test
	public void canclePayment() {
		String pay_num = "P2309061";
		int n = service.canclePayment(pay_num);
		assertEquals(n, 1);
	}
	
//	@Test
	public void insertNewPnt() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("pnt_id", "elsa@disney.com");
			put("pnt_point", -10000);
		}};
		
		int n = service.insertNewPnt(map);
		assertEquals(n, 1);
	}
	
//	@Test
	public void selectAllPnt() {
		String pnt_id = "carrot@gmail.com";
		int n = service.selectAllPnt(pnt_id);
		assertEquals(n, 1);
	}
	
	
}
