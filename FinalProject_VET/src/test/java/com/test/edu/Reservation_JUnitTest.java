package com.test.edu;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.mapper.IReservation_Dao;
import com.min.edu.model.service.IReservation_Service;
import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.Reservation_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Reservation_JUnitTest {

	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private IReservation_Service service;
	
//	
//	@Before
	public void test() {
		assertNotNull(session);
	}
	
//	@Test
	public void resrv_monthYNCountTest() {
		
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("yyyy","2023");
			put("RESRV_HOPS","gana@naver.com");
		}};
//		Map<Object, Object> resultMap = service.resrv_monthYNCount(map);
		
//		resultMap.get("lists");
//		assertNotNull(resultMap);
	}
	
//	@Test
	public void resrv_monthResrvListsTest() {
		List<Reservation_VO> lists = service.resrv_ResrvLists("gana@naver.com");
		assertNotNull(lists);
	}
	
	
//	@Test
	public void resrv_detailTest() {
		Reservation_VO rvo = service.resrv_detail("RSV1");
		assertNotNull(rvo);
	}
	
//	@Test
	public void resrv_dayStatusTest() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("resrv_hops", "gana@naver.com");
			put("resrv_status", "W");
		}};
		List<Reservation_VO> lists = service.resrv_dayStatus(map);
		assertNotNull(lists);
	}

//	@Test
	public void resrv_insertTest() {
		Reservation_VO rvo = new Reservation_VO();
		rvo.setResrv_hops("gana@naver.com");
		rvo.setResrv_visit("2023-09-12");
		rvo.setResrv_time("17");
		rvo.setResrv_name("박백설");
		rvo.setResrv_tel("01099887766");
		rvo.setResrv_memo("강아지가 무서워해요");
//		int n = service.resrv_insert(rvo);
//		assertEquals(1, n);
	}
	
//	@Test
	public void resrv_updateTest() {
//		int n = service.resrv_updateToY("RSV2");
		int m = service.resrv_updateToN("RSV1");
//		assertEquals(1, n);
		assertEquals(1, m);
	}
	
//	@Test
	public void resrv_deleteTest() {
		int n = service.resrv_delete("RSV21");
		assertEquals(1, n);
	}
	
//	@Test
	public void resrv_reqPageTest() {
		Hospital_VO hvo= service.resrv_reqPage("suwu@naver.com");
		assertNotNull(hvo);
	}
	
//	@Test
	public void resrv_reqCalTest() {
		List<Reservation_VO> lists = service.resrv_reqCal("gana@naver.com");
		assertNotNull(lists);
	}
	
//	@Test
	public void resrv_recordListTest() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("resrv_userid", "elsa@disney.com");
			put("first","1");
			put("last","5");
		}};
//		List<Hospital_VO> list = service.resrv_recordList(map);
//		assertNotNull(list);
	}
	
	
	
}
