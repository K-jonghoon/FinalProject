package com.test.edu;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.min.edu.model.service.IMediChart_Service;
import com.min.edu.vo.MediChart_VO;
import com.min.edu.vo.MediCode_VO;
import com.min.edu.vo.PetsInfo_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class MediChart_JUnitTest {

	
	@Autowired
	private IMediChart_Service service;
	
//	@Test
	public void countPet() {
		String id = "elsa@disney.com";
		int n = service.countPet(id);
		assertEquals(n, 2);
	}
//	@Test
	public void searchPet() {
		String id = "elsa@disney.com";
		List<PetsInfo_VO> lists = service.searchPet(id);
		assertNotNull(lists);
	}
	
//	@Test
	public void insertNewPet() {
		PetsInfo_VO pvo = new PetsInfo_VO();
		pvo.setPet_owner("elsa@disney.com");
		pvo.setPet_name("설탕");
		pvo.setPet_bday("20220911");
		pvo.setPet_species("C");
		pvo.setPet_gender("F");
		pvo.setPet_neut("Y");
		pvo.setPet_report("랙돌");
		
		int n = service.insertNewPet(pvo);
		assertEquals(1, n);
	
	}
	
//	@Test
	public void deletePet() {
		int pet_seq = 7;
		int n = service.deletePet(pet_seq);
		assertEquals(n, 1);
	}
	
//	@Test
//	public void insertNewChart() {
//		MediChart_VO mvo = new MediChart_VO(null, 6, null, "20230911", "03","031" , "살구 접종", "가나동물병원 방문", "merida@disney.com", null, null, null);
//		String m = service.insertNewChart(mvo);
//		assertNotNull(m);
//	}
	
//	@Test
//	public void selectAllChart() {
//		String medi_id = "elsa@disney.com";
//		List<PetsInfo_VO> lists = service.selectAllChart(medi_id);
//		assertNotNull(lists);
//		for (int i = 0; i < lists.size(); i++) {
//			System.out.println(lists.get(i));
//		}
//	}
	
//	@Test
//	public void selectPetChart() {
//		Map<String, Object> map = new HashMap<String, Object>(){{
//			put("medi_id","merida@disney.com");
//			put("pet_name","자두");
//		}};
//		List<MediChart_VO> lists = service.selectPetChart(map);
//		assertNotNull(lists);
//	}
	
//	@Test
	public void selectLChart() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("medi_id","merida@disney.com");
			put("medi_l","03");
		}};
		List<MediChart_VO> lists = service.selectLChart(map);
		assertNotNull(lists);
	}
	
//	@Test
	public void selectSChart() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("pet_owner", "merida@disney.com");
			put("medi_l", "03");
			put("medi_s", "031");
		}};
		List<PetsInfo_VO> lists = service.selectSChart(map);
		assertNotNull(lists);
	}
	
//	@Test
	public void selectOneChart() {
		String medi_num = "M7";
		PetsInfo_VO pvo = service.selectOneChart(medi_num);
		assertNotNull(pvo);
	}
	
//	@Test
	public void modifyChart() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("medi_content","접종 후 기운이 없어보여 병원방문. 큰 이상 없음(수정)");
			put("medi_num","M7");
		}};
		int n = service.modifyChart(map);
		assertEquals(n, 1);
	}
	
//	@Test
	public void deleteChart() {
		String medi_num = "M7";
		int n =  service.deleteChart(medi_num);
		assertEquals(n, 1);
	}
	
//	@Test
	public void selectAllMediCode() {
		List<MediCode_VO> lists = service.selectAllMediCode();
		assertNotNull(lists);;
	}
	
//	@Test
	public void searchMediName() {
		String medi_code = "00";
		MediCode_VO mvo = service.searchMediName(medi_code);
		assertNotNull(mvo);
	}

//	@Test
	public void selectAllChartPaging() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("pet_owner", "elsa@disney.com");
			put("first", 1);
			put("last", 4);
			put("pet_seq",5);
		}};
		
		List<PetsInfo_VO> lists = service.selectAllChartPaging(map);
		assertNotNull(lists);
		
		for (int i = 0; i < lists.size(); i++) {
			System.out.println(lists.get(i));
		}
	}
}
