package com.min.edu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.min.edu.model.service.IMap_Service;
import com.min.edu.vo.AnimalConn_VO;
import com.min.edu.vo.MapRegion_VO;
import com.min.edu.vo.MediConn_VO;
import com.min.edu.vo.NationwideHospital_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Slf4j
public class Map_Controller {

	@Autowired
	private IMap_Service service;
	
	@GetMapping(value = "/map.do")
	public String map_vet(Model model) {
		log.info("&&&&& 헤더 -> 동물병원찾기 페이지 이동 &&&&&");
		List<MapRegion_VO> sidoList = service.map_Sido();
		model.addAttribute("sidoList", sidoList);
		return "map_main";
	}
	
	// 시/도 호출
	@PostMapping(value = "/siGunGuLists.do")
	@ResponseBody
	public String siGunGuLists(String si_do) {
		log.info("&&&&& Map_Controller siGunGuLists &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", si_do);
		List<MapRegion_VO> sigunguList = service.map_SiGunGu(si_do);
		Gson gson = new Gson();
		String json = gson.toJson(sigunguList);
		return json;
	}
	
	/*
	 *  시/군/구 호출 후 전국 동물병원 현황 데이터에서 해당 지역의 주소 및 사업장명 호출
	 */
	@PostMapping(value = "/nationwideHosp.do")
	@ResponseBody
	public String nationwideHosp(@RequestParam Map<String, Object> map) {
		log.info("&&&&& Map_Controller siGunGuLists &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", map);
		@SuppressWarnings("serial")
		Map<String, Object> inMap = new HashMap<String, Object>(){{
			if(map.get("si_do").equals("세종특별자치시")) {
				put("si_do", map.get("si_do"));
				put("si_gun_gu", "");
			}else {
				put("si_do", map.get("si_do"));
				put("si_gun_gu", map.get("si_gun_gu"));
			}
			
		}};
		
		List<NationwideHospital_VO> hospLists = service.map_RegionData(inMap);
		Gson gson = new Gson();
		String json = gson.toJson(hospLists);
		return json;
	}
	
	@PostMapping(value = "/getHospUser.do")
	@ResponseBody
	public String getHospUser(String users_auth) {
		log.info("&&&&& Map_Controller getHospUser &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", users_auth);
		List<Users_VO> list = service.hosp_user(users_auth);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
	
	
	@GetMapping(value = "/select_HospDetail.do")
	public String select_HospDetail(@RequestParam String hosp_id, Model model) {
		log.info("&&&&& Map_Controller select_HospDetail &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", hosp_id);
		Users_VO hosp_info = service.hosp_detail(hosp_id);
		List<AnimalConn_VO> anm_lists = service.hosp_anm(hosp_id);
		List<MediConn_VO> medi_lists = service.hosp_mediDept(hosp_id);
		model.addAttribute("hosp_info", hosp_info);
		model.addAttribute("anm_lists", anm_lists);
		model.addAttribute("medi_lists", medi_lists);
		return "hosp_detail";
	}
	
	@PostMapping(value = "/getHosp_id.do")
	@ResponseBody
	public String req_Addr(String users_addr) {
		log.info("&&&&& Map_Controller req_Addr &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", users_addr);
		String hosp_id = service.map_reqAddr(users_addr.substring(3));
		return hosp_id;
	}
	
	
}
