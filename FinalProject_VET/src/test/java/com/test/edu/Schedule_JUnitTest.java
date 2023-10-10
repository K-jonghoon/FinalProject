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

import com.min.edu.model.service.ISchedule_Service;
import com.min.edu.vo.SchedBoard_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class Schedule_JUnitTest {

	@Autowired
	private ISchedule_Service service;
	
//	@Test
	public void selectAllSchedule() {
		String sche_id = "merida@disney.com";
		List<SchedBoard_VO> vo = service.selectAllSchedule(sche_id);
		assertNotNull(vo);
	}
	
//	@Test
	public void selectOneSchedule() {
		int n = 4;
		
		SchedBoard_VO vo = service.selectOneSchedule(n);
		assertNotNull(vo);
	}
	
//	@Test
	public void modifySchedule() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sche_title", "까미네랑 애견카페");
		map.put("sche_content", "용인 카페댕스, 주차가능");
		map.put("sche_num", 4);
		
		service.modifySchedule(map);
	}
	
//	@Test
	public void deleteSchedule() {
		int sche_num = 12;
		service.deleteSchedule(sche_num);
	}

//	@Test
//	public void deleteRSV_Test() {
//		Map<String, Object> map = new HashMap<String, Object>(){{
//			put("sche_id","elsa@disney.com");
//			put("sche_date","2023-09-23");
//			put("sche_hour","13");
//			put("sche_title","진료예약");
//		}};
//		
//		int n = service.deleteRSV(map);
//		assertEquals(1, n);
//	}
	

}
