package com.min.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.min.edu.model.service.ISchedule_Service;
import com.min.edu.model.service.IUsers_Service;
import com.min.edu.vo.SchedBoard_VO;
import com.min.edu.vo.ScheduleCalendar_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Schedule_Controller {

	@Autowired
	private ISchedule_Service service;
	
	@GetMapping(value = "/selectAllSchedule.do")
	public String schedule_calendar() {
		 log.info("&&&&& 마이페이지 -> 일반사용자 일정조회 &&&&&");
		 
		 return "schedule_main";
	}
	
	@GetMapping(value = "/fullCalendar_sche.do")
	public void fullCalendar(HttpSession session,HttpServletResponse response) throws IOException {
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String sche_id = loginVo.getUsers_id();
		
		List<ScheduleCalendar_VO> resultlist = new ArrayList<>();
		
		List<SchedBoard_VO> scheduleList = service.selectAllSchedule(sche_id);
		for(int i=0; i< scheduleList.size(); i++) {
			ScheduleCalendar_VO svo = new ScheduleCalendar_VO();
			svo.setTitle(scheduleList.get(i).getSche_title());
			svo.setSche_num(scheduleList.get(i).getSche_num());
			svo.setStart(scheduleList.get(i).getSche_date());
			resultlist.add(svo);
		}

		Gson gson = new Gson();
		String json = gson.toJson(resultlist);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		
	}
	
	@PostMapping(value = "/selectOneSchedule.do")
	@ResponseBody
	public Map<String, Object> selectOneSchedule(String sche_num, Model model) {
		log.info("&&&&& 일정캘린더 -> 일정 상세조회 &&&&&");
		log.info("&&&&& Schedule_Controller selectOneSchedule 전달받은 parameter값 : {} &&&&&", sche_num);
		
		int n = Integer.parseInt(sche_num); 
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		SchedBoard_VO svo = service.selectOneSchedule(n);
		map.put("svo", svo);
		return map;
	}
	
	@PostMapping(value = "/insertNewSchedule.do")
	public String insertNewSchedule(@RequestParam Map<String, Object> map, HttpSession session) {
		log.info("&&&&& Schedule_Controller insertNewSchedule 전달받은 parameter값 : {} &&&&&", map);
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		String sche_id = loginVo.getUsers_id();
		
		String sche_title = (String) map.get("sche_title");
		String sche_date = (String) map.get("sche_date");
		String sche_content = (String) map.get("sche_content");
		String sche_hour = (String) map.get("sche_hour");
		String sche_minute = (String) map.get("sche_minute");
		
		if(sche_hour == null || sche_minute == null || sche_content == null) {
			sche_hour = "";
			sche_minute = "";
			sche_content = "";
		}
		SchedBoard_VO svo = new SchedBoard_VO(0, sche_id, sche_date, sche_title, sche_content, sche_hour, sche_minute);
		
		int n = service.insertNewSchedule(svo);
		
		
		return (n>0)?"redirect:/selectAllSchedule.do":"";
	}
	
	@PostMapping(value = "/modifySchedule.do")
	@ResponseBody
	public Map<String, Object> modifySchedule(@RequestParam Map<String, Object> map, HttpServletResponse response) throws IOException {
		log.info("&&&&& Schedule_Controller modifySchedule 전달받은 parameter값 : {} &&&&&", map);
		
		String sche_title = (String) map.get("sche_title");
		String sche_content = (String)map.get("sche_content");
		String sche_number = (String) map.get("sche_num");
		int sche_num = Integer.parseInt(sche_number);
		
		Map<String, Object> inMap = new HashMap<String, Object>(){{
			put("sche_title",sche_title);
			put("sche_content",sche_content);
			put("sche_num",sche_num);
		}};
		
		int n = service.modifySchedule(inMap);
		if(n>0) {
			SchedBoard_VO svo = service.selectOneSchedule(sche_num);
			Map<String, Object> list = new HashMap<String, Object>(){{
				put("list", svo);
			}};
			return list;
		}else {
			return null;
		}
	}
	
	
	@PostMapping(value = "/deleteSchedule.do")
	@ResponseBody
	public int deleteSchedule(String sche_num, HttpServletResponse response) throws IOException {
		log.info("&&&&& Schedule_Controller deleteSchedule 전달받은 parameter값 : {} &&&&&", sche_num);
		int delete_num = Integer.parseInt(sche_num);
		int n = service.deleteSchedule(delete_num);
		
		if(n>0) {
			return n;
		}else {
			return 0;
		}
		
	}
	
}