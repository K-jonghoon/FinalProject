package com.test.edu;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.service.IUsers_Service;
import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.Users_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Users_JUnitTest {
	
	@Autowired
	private IUsers_Service service;
	
	@Test
	public void test() {

//		List<Users_VO> lists = service.selectAllUsers();

//		String auth = "U";
//		List<Users_VO> lists = service.selectUsersAuth(auth);

//		String status = "N";
//		List<Users_VO> lists = service.selectUsersStatus(status);
		
//		String keyword = "d";
//		List<Users_VO> lists = service.searchUsers(keyword);
		
//		String userId = "elsa@disney.com";
//		List<Users_VO> lists = service.selectUserDetail(userId);

//		String hospitalId = "gunu@naver.com";
//		Users_VO vo = service.selectHospitalDetail(hospitalId);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("users_id", "elsa@disney.com");
//		map.put("users_pw", "elsa1234");
//		Users_VO vo = service.loginUser(map);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("users_id", "ariel1234@disney.com");
//		map.put("users_pw", "ariel1234");
//		map.put("users_name", "박아리엘");
//		map.put("users_tel", "0318268826");
		
		
//		int n = service.duplicationId("elsa@disney.com");
//		assertEquals(1, n);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		map.put("users_id","chara@naver.com");
//		map.put("users_pw","chara1234");
//		map.put("users_name","차라동물병원");
//		map.put("users_addr","경기도 양주시 백석읍 꿈나무로299 동화2차상가 1층");
//		map.put("users_tel","0314568978");
//		map.put("users_crn","7895645789");
//
//		map.put("hosp_id", "chara@naver.com");
//		map.put("hosp_name", "차라동물병원");
//		map.put("hosp_time", "{\"open\":\"09\", \"close\":\"18\"}");
//		map.put("hosp_off", "월");
//		map.put("hosp_park", "Y");
//		map.put("hosp_etc", "고양이 전문 병원 입니다.");
//		map.put("hosp_mng", "G");
		
//		Users_VO vo = new Users_VO();
//		vo.setUsers_id("sana1@naver.com");
//		vo.setUsers_pw("1234");
		
//		vo.setUsers_name("김당망");
//		vo.setUsers_tel("01012341234");
//		vo.setUsers_addr("");
//		vo.setUsers_subtel("");
//		vo.setUsers_id("sarang@gmail.com");
		
//		int n = service.updateUser(vo);
		
//		Users_VO uVo = service.findId(vo);
//		System.out.println(uVo);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("medi_code", "00");
//		map.put("anm_code", "B");
//		map.put("hosp_id", "panananan@naver.com");
//		
//		int n = service.deleteHospAnicode(map);
//		int m = service.deleteHospMedicode(map);
		
//		assertEquals(1, n);
//		assertEquals(1, m);
		
		Users_VO uVo = new Users_VO();
		uVo.setUsers_id("panananan@naver.com");
		uVo.setUsers_pw("789456");
		int n = service.findPw(uVo);
		
		assertEquals(1, n);
		
	}

}
