package com.min.edu;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.model.service.IBookMark_Service;
import com.min.edu.vo.BookMark_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;
import retrofit2.http.GET;

@Controller
@Slf4j
public class BookMark_Controller {
	
	@Autowired
	IBookMark_Service service;
	
	@PostMapping(value = "/insertNewBookmark.do")
	@ResponseBody
	public String insertNewBookmark(HttpSession session, HttpServletResponse response, String bm_hospid) throws IOException {
		log.info("&&&&& BookMark_Controller insertNewBookmark 전달받은 파라미터 값 : {} &&&&&", bm_hospid);
		Users_VO loginVo= (Users_VO) session.getAttribute("loginVo");
		String bm_usersid = loginVo.getUsers_id();
		
		int cnt = service.countBookmark(bm_usersid);
		if(cnt < 3) {
			BookMark_VO bvo = new BookMark_VO();
			bvo.setBm_hospid(bm_hospid);
			bvo.setBm_usersid(bm_usersid);
			int n = service.insertNewBookmark(bvo);
			return (n>0)?"true":"false";
		}else{
			return "false";
		}
	}
	
	@GetMapping(value = "/selectAllBookmark.do")
	public String selectAllBookmark(String bm_usersid,Model model) {
		log.info("&&&&& BookMark_Controller insertNewBookmark 전달받은 파라미터 값 : {} &&&&&", bm_usersid);
		
		List<BookMark_VO> lists = service.selectAllBookmark(bm_usersid);
		model.addAttribute("lists", lists);
		return "bookmark_allList";
	}
	
	@PostMapping(value = "/bookMarkYorN.do")
	@ResponseBody
	public String bookMarkYorN(String bm_hospid, HttpSession session) {
		log.info("&&&&& BookMark_Controller bookMarkYorN 전달받은 파라미터 값 : {} &&&&&", bm_hospid);
		Users_VO loginVo= (Users_VO) session.getAttribute("loginVo");
		
		if(loginVo == null) {
			return "redirect:/select_HospDetail.do?hosp_id="+bm_hospid;
		}else {
			String bm_usersid = loginVo.getUsers_id();
			
			@SuppressWarnings("serial")
			Map<String, Object> map = new HashMap<String, Object>(){{
				put("bm_usersid", bm_usersid);
				put("bm_hospid", bm_hospid);
			}};
			int cnt = service.bookMarkYorN(map);
			return (cnt>0)?"true":"false";
		}
	}
	
	@PostMapping(value = "/deleteBookmark.do")
	@ResponseBody
	public String deleteBookmark(String bm_hospid, HttpSession session) {
		log.info("&&&&& BookMark_Controller deleteBookmark 전달받은 파라미터 값 : {} &&&&&", bm_hospid);
		Users_VO loginVo= (Users_VO) session.getAttribute("loginVo");
		String bm_usersid = loginVo.getUsers_id();
		
		BookMark_VO bvo = new BookMark_VO();
		bvo.setBm_hospid(bm_hospid);
		bvo.setBm_usersid(bm_usersid);
		int n = service.deleteBookmark(bvo);
		
		return (n>0)?"true":"false";
	}
	
}
