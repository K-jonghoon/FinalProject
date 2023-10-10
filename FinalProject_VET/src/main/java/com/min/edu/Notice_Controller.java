package com.min.edu;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.min.edu.model.service.INotice_Service;
import com.min.edu.vo.NoticeBoard_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Notice_Controller {
	
	@Autowired
	private INotice_Service service;
	
	//공지사항 목록 조회
	@GetMapping(path="/notice.do")
	public String notice (Model model, HttpSession session) {
		log.info("&&&&& Notice_Controller 메인화면 -> 공지사항 페이지로 이동 &&&&&");
		Users_VO loginVo =  (Users_VO)session.getAttribute("loginVo");
				
		List<NoticeBoard_VO> lists = service.selectNotice();

		model.addAttribute("noticeLists", lists);
		session.setAttribute("loginVo", loginVo);
		return "noti_main";
		
	}
	
	//공지사항 상세 조회
	@GetMapping(path="/noticeDetail.do")
	public String noticeDetailPage(@RequestParam String noti_seq, Model model, HttpSession session,
									HttpServletResponse response) throws IOException {
		log.info("&&&&& Notice_Controller 공지사항 목록 -> 공지사항 상세 페이지로 이동 &&&&&");
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		NoticeBoard_VO nVo = service.selectNoticeDetail(noti_seq);
		
		session.setAttribute("loginVo", loginVo);
		model.addAttribute("noticeDetail", nVo);
		
		return "noti_detail";
		
	}
	
	//공지사항 수정 페이지로 이동
	@GetMapping(path="/modifyNotice.do")
	public String modifyNoticePage(String noti_seq, HttpSession session, Model model, HttpServletResponse response) throws IOException {
		log.info("&&&&& Notice_Controller 공지사항 수정페이지로 이동 {} &&&&&", noti_seq);
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		NoticeBoard_VO nVo = service.selectNoticeDetail(noti_seq);
		
		if(loginVo != null &&  loginVo.getUsers_auth().equals("A"))  {
			session.setAttribute("loginVo", loginVo);
			model.addAttribute("noticeDetail", nVo);
			return "noti_modify";
		} else { 
			PrintWriter out = response.getWriter();
			out.println("<script>alert('관리자만 접근이 가능한 페이지입니다.');location.href='./loginForm.do';</script>");
			out.flush();
			return null;
		}
	}
	
	//공지사항 수정
	@PostMapping(path="/modifyNotice.do")
	public String modifyNotice (@RequestParam Map<String, Object> map, HttpSession session, Model model,
								HttpServletResponse response) throws IOException {
		log.info("&&&&& Notice_Controller 공지사항 수정 {} &&&&&", map);
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		NoticeBoard_VO nVo = new NoticeBoard_VO();
		
		if(loginVo != null &&  loginVo.getUsers_auth().equals("A"))  {
			session.setAttribute("loginVo", loginVo);
			
			nVo.setNoti_title((String)map.get("noti_title"));
			nVo.setNoti_content((String)map.get("noti_content"));
			nVo.setNoti_seq((String)map.get("noti_seq"));
	
			int n = service.modifyNotice(nVo);
			String seq = nVo.getNoti_seq();
			
			if(n==1) {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정이 완료되었습니다.');location.href='./noticeDetail.do?noti_seq="+seq+"';</script>");
				out.flush();
				return null;
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('관리자만 접근이 가능한 페이지입니다.');location.href='./loginForm.do';</script>");
				out.flush();
				return null;
			}
			
		} else { 
			PrintWriter out = response.getWriter();
			out.println("<script>alert('관리자만 접근이 가능한 페이지입니다.');location.href='./loginForm.do';</script>");
			out.flush();
			return null;
		}
		
	}
	
	//공지사항 입력 페이지로 이동
	@GetMapping(path = "/insertNotice.do")
	public String insertNoticePage(HttpSession session, HttpServletResponse response) throws IOException {
		log.info("&&&&& Notice_Controller 공지사항 작성 페이지로 이동 {} &&&&&", session);
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		
		if(loginVo != null && loginVo.getUsers_auth().equals("A")) {
			session.setAttribute("loginVo", loginVo);
			return "noti_insert";
			
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('관리자만 접근이 가능한 페이지입니다.');location.href='./loginForm.do';</script>");
			out.flush();
			return null;
		}
		
	}
	
	//공지사항 입력
	@PostMapping(path = "/insertNotice.do")
	@ResponseBody
	public String insertNotice(@RequestParam Map<String, Object> map,
								HttpSession session, HttpServletResponse response) throws IOException {
		log.info("&&&&& Notice_Controller 공지사항 작성 {} &&&&&", session);
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		NoticeBoard_VO nVo = new NoticeBoard_VO();
		
		String auth = loginVo.getUsers_auth();
		String id = loginVo.getUsers_id();
		System.out.println(auth);
		
		if(loginVo != null && loginVo.getUsers_auth().equals("A")) {
			
			session.setAttribute("loginVo", loginVo);
			
			nVo.setNoti_auth(auth);
			nVo.setNoti_id(id);
			nVo.setNoti_title((String)map.get("noti_title"));
			nVo.setNoti_content((String)map.get("noti_content"));
			
			int n = service.insertNotice(nVo);
			
			if(n==1) {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('공지글이 작성되었습니다.');location.href='./notice.do';</script>");
				out.flush();
				return null;
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 요청입니다.');location.href='./insertNotice.do';</script>");
				out.flush();
				return null;
			}
			
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('관리자만 접근이 가능한 페이지입니다.');location.href='./insertNotice.do';</script>");
			out.flush();
			return null;
		}
		
	}
	
	//공지사항 삭제
	@GetMapping(path="/deleteNotice.do")
	public String deleteNoticePage(HttpSession session, Model model, String noti_seq, HttpServletResponse response) throws IOException {
		log.info("&&&&& Notice_Controller 공지사항 삭제로 이동 {} &&&&&", noti_seq);
		response.setContentType("text/html; charset=UTF-8");
		
		int n = service.deleteNotice(noti_seq);
		
		if(n == 1) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제 완료되었습니다.');location.href='./notice.do';</script>");
			out.flush();
			return null;
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 요청입니다.');location.href='./noticeDetail.do?noti_seq="+noti_seq+"';</script>");
			out.flush();
			return null;
		}
		
		
	}
	

}
