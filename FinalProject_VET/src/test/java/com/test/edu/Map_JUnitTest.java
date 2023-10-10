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

import com.min.edu.model.service.IMap_Service;
import com.min.edu.vo.AnimalConn_VO;
import com.min.edu.vo.MapRegion_VO;
import com.min.edu.vo.MediConn_VO;
import com.min.edu.vo.NationwideHospital_VO;
import com.min.edu.vo.Users_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Map_JUnitTest {

	@Autowired
	private IMap_Service service;
	
//	@Test
	public void test() {
		assertNotNull(service);
	}

//	@Test
	public void hosp_userTest() {
		List<Users_VO> lists = service.hosp_user("H");
		for (int i = 0; i < lists.size(); i++) {
			System.out.println("병원 주소 : "+lists.get(i).getUsers_addr()+" / 병원이름 : "+lists.get(i).getUsers_name());
		}
		assertNotNull(lists);
	}
	
	
//	@Test
	public void hosp_detailTest() {
		Users_VO uvo = service.hosp_detail("gana@naver.com");
		assertNotNull(uvo);
	}
	
//	@Test
	public void hosp_anmTest() {
		List<AnimalConn_VO> lists =service.hosp_anm("gana@naver.com");
		assertNotNull(lists);
	}
	
//	@Test
	public void hosp_mediDeptTest() {
		List<MediConn_VO> lists = service.hosp_mediDept("gana@naver.com");
		assertNotNull(lists);
	}
	
//	@Test
	public void reqAddrTestTest() {
		String hosp_id = service.map_reqAddr("광명시 철산동 634");
		assertNotNull(hosp_id);
	}
	
//	@Test
	public void map_SidoTest() {
		List<MapRegion_VO> lists = service.map_Sido();
		System.out.println(lists);
		assertNotNull(lists);
		
	}
	
//	@Test
	public void map_SiGunGuTest() {
		List<MapRegion_VO> lists = service.map_SiGunGu("충청북도");
		System.out.println(lists);
		assertNotNull(lists);
	}
	
	@Test
	public void map_RegionDataTest() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("si_do", "인천광역시");
			put("si_gun_gu", "미추홀구");
		}};
		List<NationwideHospital_VO> hospLists = service.map_RegionData(map);
		for (int i = 0; i < hospLists.size(); i++) {
			System.out.println("#####"+hospLists.get(i));
		}
		assertNotNull(hospLists);
	}
}
