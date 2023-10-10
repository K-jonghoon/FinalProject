package com.min.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.min.edu.model.mapper.IPayment_Dao;

import com.min.edu.model.service.IChosen_Service;
import com.min.edu.vo.Chosen_VO;

import com.min.edu.model.service.IMap_Service;
import com.min.edu.model.service.INotice_Service;
import com.min.edu.model.service.IPayment_Service;
import com.min.edu.model.service.IUsers_Service;
import com.min.edu.vo.AnimalConn_VO;
import com.min.edu.vo.MediConn_VO;

import com.min.edu.vo.NoticeBoard_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import retrofit2.http.GET;

@Controller
@Slf4j
public class Users_Controller {

	@Autowired
	private IUsers_Service service;
	
	@Autowired
	private IPayment_Service payment_service;
	
	@Autowired
	private INotice_Service notice_service;
	
	@Autowired
	private IChosen_Service chosen_service;
	
	//로그인 페이지로 이동
  
  @GetMapping(path = "/loginForm.do")
	public String loginForm() {
		log.info("&&&&& Users_Controller 메인화면 -> 로그인페이지 &&&&&");
		return "users_loginForm";
	}
	
	//로그인 실행
	@RequestMapping(path = "/login.do")
	public String login(@RequestParam Map<String, Object> map, HttpSession session,
									  HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller login 로그인 처리 {} &&&&&", map);
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			Users_VO loginVo = service.loginUser(map);
			
			if(loginVo != null) {
				session.setAttribute("loginVo", loginVo);
				int point = payment_service.selectAllPnt((String)loginVo.getUsers_id());
				session.setAttribute("point", point);
				session.setMaxInactiveInterval(1800);
				
				if(((String)loginVo.getUsers_auth()).equals("H")) {
					PrintWriter out;
					out = response.getWriter();
					out.println("<script>location.href='./resrv_Select.do';</script>");
					out.flush();
					return null;
				}else if(((String)loginVo.getUsers_auth()).equals("A")){
					PrintWriter out;
					out = response.getWriter();
					out.println("<script>alert('관리자 계정입니다'); location.href='./adminPage.do';</script>");
					out.flush();
					return null;
				}else {
					PrintWriter out;
					out = response.getWriter();
					out.println("<script>location.href='./main.do';</script>");
					out.flush();
					return null;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.println("<script>alert('로그인 정보가 없습니다');location.href='./loginForm.do';</script>");
		out.flush();
		return null;
	}
	
	
	//로그인 성공 후 메인 페이지로 이동 - 공지사항 확인
	@GetMapping(path="/main.do")
	public String main(Model model) {
		log.info("&&&&& Users_Controller 로그인 성공 -> 메인페이지 &&&&&");
		List<NoticeBoard_VO> notice_list = notice_service.selectNotice();
		model.addAttribute("notice_list", notice_list);
		
		List<Chosen_VO> chsn_list = chosen_service.chosen_rank();
		model.addAttribute("chsn_list", chsn_list);
		return "main";
	}
	
	//로그아웃 버튼 클릭 시 세션 삭제 후 로그인 페이지로 이동
	@GetMapping(path="/logout.do")
	public String logout(HttpSession session) {
		log.info("&&&&& Users_Controller 로그아웃 호출 -> 로그인페이지 &&&&&");
		session.invalidate();	
		return "redirect:/loginForm.do";
	}
	
	//관리자 계정 접속 후 관리자 페이지로 이동
	@GetMapping(path="/adminPage.do")
	public String adminUserList(Model model) {
		log.info("&&&&& Users_Controller 메인페이지 -> 관리자페이지 &&&&&");
		
		List<Users_VO> lists = service.selectAllUsers();
		model.addAttribute("listsVo", lists);
		
		return "users_adminPage";
	}
	
	//회원 아이디 클릭 시 회원 정보 상세 페이지로 이동
	@GetMapping(path="/selectUserDetail.do")
	public String selectUserDetail (@RequestParam("users_id") String id,
									HttpSession session, Model model,
									HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller 관리자페이지 -> 회원상세조회페이지 {} {} &&&&&", session.getAttribute("loginVo"));
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		
		List<Users_VO> usersDetail = service.selectUserDetail(id);
		Users_VO hospDetail = service.selectHospitalDetail(id);
		
		String auth = usersDetail.get(0).getUsers_auth();

		if(loginVo != null && loginVo.getUsers_auth().equals("A")) {
		
				if(auth.equals("A")||auth.equals("U")) {
					model.addAttribute("usersDetail", usersDetail);
					return "users_selectUserDetail";
				
				} else if (auth.equals("A")||auth.equals("H")){
					model.addAttribute("hospDetail", hospDetail);
					return "users_selectUserDetail";
				}
		
				return "users_selectUserDetail";
		
		} else {

			PrintWriter out = response.getWriter();
			out.println("<script>alert('관리자만 접근할 수 있는 페이지입니다.');location.href='./loginForm.do';</script>");
			out.flush();
			return null;
		}
					
	}
	
	//회원 아이디 검색 기능
	@PostMapping(path="/adminPage.do", produces="application/text; charset=UTF-8;")
	@ResponseBody
	public String searchUser (String keyword, HttpSession session,
							  HttpServletResponse response){
		log.info("&&&&& Users_Controller 관리자페이지 권한별 조회, 상태별 조회, 회원 검색 ajax 처리 {} {} &&&&&", session.getAttribute("loginVo"), keyword);
		response.setContentType("text/html; charset=UTF-8");
		
		List<Users_VO> searchList =  service.searchUsers(keyword);
		
		if(searchList.size()==0) {
				return "";
			} else {
				Gson gson = new Gson();
				String searchUserList = gson.toJson(searchList);
				return searchUserList;
			}
		}
	
	//권한별 조회
	@PostMapping(path="/adminPageAuth.do", produces="application/text; charset=UTF-8;")
	@ResponseBody
	public String selectAuth(String auth, HttpSession session,
							 HttpServletResponse response) {
		log.info("&&&&& Users_Controller 관리자페이지 권한별 조회 ajax처리 {} &&&&&", auth);
		response.setContentType("text/html; charset=UTF-8;");
		
		System.out.println(auth);
		
		List<Users_VO> selectAuth = service.selectUsersAuth(auth);
		
		Gson gson = new Gson();
		String selectAuthList = gson.toJson(selectAuth);
		
		return selectAuthList;
		
	}
	
	//상태별 조회
	@PostMapping(path="/adminPageStatus.do", produces="application/text; charset=UTF-8;")
	@ResponseBody
	public String selectStatus(String status, HttpSession session,
							   HttpServletResponse response) {
		log.info("&&&&& Users_Controller 관리자페이지 상태별 조회 ajax처리 {} &&&&&", status);
		response.setContentType("text/html; charset=UTF-8;");
		
		System.out.println(status);
		
		List<Users_VO> selectStatus = service.selectUsersStatus(status);
		
		Gson gson = new Gson();
		String selectStatusList = gson.toJson(selectStatus);
		
		return selectStatusList;
	}
	
	//회원가입(약관 동의 페이지로 이동)
	@GetMapping(path = "/insertUsers.do")
	public String insertUsers() {
		log.info("&&&&& Users_Controller loginForm ->  insertUsers 페이지 이동 &&&&&");
		return "users_insertUsersStepOne";
	}
	
	//회원가입(약관 동의 후 정보입력 페이지로 이동)
	@GetMapping(path = "/insertStepTwo.do")
	public String insertStepTwo() {
		log.info("&&&&& Users_Controller insertUsers-> insertStepTwo 페이지 이동 &&&&&");
		return "users_insertUsersStepTwo";
	}
	
	
	//아이디 중복검사
	@GetMapping(path="/duplication.do")
	public String duplication() {
		log.info("&&&&& Users_Controller duplication 아이디 중복검사 팝업 오픈 &&&&&");
		return "users_duplication";
	}
	
	
	//아이디(이메일) 중복검사 처리를 위한 rest 처리
	@PostMapping(path="/duplicationAjax.do")
	@ResponseBody
	public String duplicationAjax (String checkEmail) {
		log.info("&&&&& Users_Controller duplicationAjax 아이디 중복 확인 {} &&&&&", checkEmail);
		int n = service.duplicationId(checkEmail);
		return (n>0)?"true":"false";
	}
	
	//회원가입(일반사용자)
	@PostMapping(path = "/signUp.do")
	public String insertUsersTwo(@RequestParam Map<String, Object> map, Model model) {
		log.info("&&&&& Users_Controller insertStepTwo 회원가입 후 insertStepThree 페이지 이동 &&&&&");
		
		int n = service.insertUser(map);
		model.addAttribute("signUpVo",map);
		return (n>=1)?"users_insertUsersStepThree":"redirect:/main.do";
		
	}
	
	//회원정보 추가입력(일반사용자)
	@PostMapping(path="/addUserInfo.do")
	public String addInfo(@RequestParam  Map<String, Object> map,
							HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller insertStepThree 추가정보 입력 후 메인페이지 이동  &&&&&");
		response.setContentType("text/html; charset=UTF-8");
		
		String addr = (String)map.get("addr");
		String addrDetail = (String)map.get("addrDetail");
		
		String users_addr = addr+" "+addrDetail;
		System.out.println(users_addr);
		
		map.put("users_addr", users_addr);
		
		boolean isc = service.addInfo(map);
		
		if(isc==true) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('추가정보 등록이 완료되었습니다.');location.href='./main.do';</script>");
			out.flush();
			return null;
		} else {
			return "addInfo";
		}
		
		
	}
	
	//회원가입 페이지 이동 (병원관계자)
	@GetMapping(path = "/insertHospStepTwo.do")
	public String insertHospStepTwo() {
		log.info("&&&&& Users_Controller insertUsers->users_insertHospStepTwo 병원 관계자 페이지 이동 &&&&&");
		return "users_insertHospStepTwo";
	}
	
	//회원가입(병원관계자)
	@PostMapping(path = "/signUpHosp.do")
	public String insertHospTwo(@RequestParam Map<String, Object> map, Model model, HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller insertStepTwo 회원가입 후 insertStepThree 페이지 이동 {} &&&&&", map);
		response.setContentType("text/html; charset=UTF-8");
		
		String hosp_id = (String)map.get("users_id");
		map.put("hosp_id", hosp_id);
		
		String hosp_name = (String)map.get("users_name");
		map.put("hosp_name", hosp_name);
		
		String addr = (String)map.get("addr");
		String addrDetail = (String)map.get("addrDetail");
		String users_addr = addr+" "+addrDetail;
		System.out.println(users_addr);
		map.put("users_addr", users_addr);
		
		String openTime = (String)map.get("hosp_openTime");
		String closeTime = (String)map.get("hosp_closeTime");
		System.out.println("여는시간 : " + openTime+ "닫는 시간 : " + closeTime);
		String hosp_time = "{\"open\":\""+openTime+"\", \"close\":\""+closeTime+"\"}";
		map.put("hosp_time", hosp_time);
		
		String hosp_off = (String)map.get("hosp_off");
		map.put("hosp_off", hosp_off);
		
		String medi_code = (String)map.get("medi_code");
		map.put("medi_code", medi_code);
		
		String anm_code = (String)map.get("anm_code");
		map.put("anm_code", anm_code);
		
		System.out.println(map);
		
		boolean isc = service.insertHosp(map);
		int n = service.insertHospAnicode(map);
		int m = service.insertHospMedicode(map);
		
		model.addAttribute("signUpVo",map);
		
		if (isc==true && n != 0 && m != 0) {
			return "users_insertHospStepThree";
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 값을 입력하셨습니다. 다시 입력해주세요.');location.href='./insertHospStepTwo.do';</script>");
			out.flush();
			return null;
		}
			
	}

	//회원 탈퇴 페이지 이동
	@GetMapping(path = "/resignUser.do")
	public String resignUserPage(HttpSession session, Model model) {
		log.info("&&&&& Users_Controller 회원 탈퇴 페이지로 이동 {} &&&&&");
		Users_VO loginVo = (Users_VO)session.getAttribute("users_id");
		model.addAttribute("loginVo", loginVo);
		return "users_resignUser";
	}
	
	
	//회원 탈퇴
	@PostMapping(path = "/resignUser.do")
	@ResponseBody
	public String resignUser(@RequestParam Map<String, Object> map, HttpSession session,
							 HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller 회원 탈퇴 페이지로 이동 {} &&&&&", map);
		response.setContentType("text/html; charset=UTF-8");
		
		String users_id = (String)map.get("users_id");
		String users_pw = (String)map.get("users_pw");
		System.out.println(users_id +"   "+ users_pw);

		Users_VO vo = new Users_VO();
		vo.setUsers_id(users_id);
		vo.setUsers_pw(users_pw);
	
		int n = service.resignUser(vo);
		
		if(n == 1 ) {
			session.invalidate();
			PrintWriter out = response.getWriter();
			out.println("<script>alert('탈퇴 완료되었습니다.');location.href='./main.do';</script>");
			out.flush();
			return null;
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 값을 입력하셨습니다. 다시 입력해주세요.');location.href='./resignUser.do';</script>");
			out.flush();
			return null;
		}
		
	}

	//일반사용자의 마이페이지 -> 내정보관리 -> 조회 및 수정 화면 이동
	//loginVo가 null일 때 로그인 요청하는 기능 추가
	@GetMapping(value = "/selectOneDetail.do")
	public String selectOneDetail(HttpSession session, Model model, HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller 회원 상세정보 페이지로 이동&&&&&");
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
		
		if(loginVo != null) {
		String users_id = loginVo.getUsers_id();
		List<Users_VO> lists = service.selectUserDetail(users_id);
		model.addAttribute("lists",lists);
		
		return "users_detail";
		
		} else {
			
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스입니다.');location.href='./loginForm.do';</script>");
			out.flush();
			return null;
			
		}
	}
	
	//회원정보수정(일반사용자) 페이지 이동
	@GetMapping(path = "/updateUser.do")
	public String updateUserPage(HttpSession session, Model model,
								 HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller 회원 정보 수정 페이지로 이동 {} {} &&&&&", session, model);
		response.setContentType("text/html; charset=UTF-8");
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		
		if(loginVo != null) {
			session.setAttribute("loginVo", loginVo);
	
			String users_id = loginVo.getUsers_id();
			List<Users_VO> lists = service.selectUserDetail(users_id);
			model.addAttribute("lists", lists);
			
		return "users_updateUser";
		
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스입니다.');location.href='./loginForm.do';</script>");
			out.flush();
			return null;
		}
		
	}
	
	//회원정보수정(일반사용자)
	@PostMapping(path = "/updateUser.do")
	@ResponseBody
	public String updateUser(@RequestParam Map<String, Object> map, HttpSession session, Model model,
							 HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller 회원 정보 수정 서비스 실행 {} &&&&&");
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		Users_VO uVo = new Users_VO();
		
		if(loginVo != null) {
		
			uVo.setUsers_name((String)map.get("users_name")); 
			uVo.setUsers_tel((String)map.get("users_tel")); 
			uVo.setUsers_addr((String)map.get("users_addr"));
			uVo.setUsers_subtel((String)map.get("users_subtel"));
			
			uVo.setUsers_id((String)map.get("users_id"));			
			
			int n = service.updateUser(uVo);
			
			if(n == 1) {
				
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정 완료되었습니다.');location.href='./selectOneDetail.do';</script>");
				out.flush();
				return null;
				
			} else {
				
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 정보를 입력하셨습니다. 다시 입력해주세요.');location.href='./updateUser.do';</script>");
				out.flush();
				return null;
			}
		
		} else { 	
		
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스입니다.');location.href='./loginForm.do';</script>");
			out.flush();
			return null;
			
		}
	}
	
	//회원정보수정페이지 이동(병원 관계자)
	@GetMapping(path = "/updateHosp.do")
	public String updateHospPage(HttpSession session, Model model,
								 HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller 병원 정보 수정페이지로 이동 {} &&&&&", session);
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		System.out.println(loginVo);
				
		if(loginVo != null) {
			session.setAttribute("loginVo", loginVo);
			
			String users_id = loginVo.getUsers_id();
			System.out.println(users_id);
			
			Users_VO hosp_info = service.selectHospitalDetail(users_id);
			System.out.println(hosp_info);
			
			model.addAttribute("hosp_info", hosp_info);
			
		return "users_updateHosp";
		
		} else {
			
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스입니다.');location.href='./loginForm.do';</script>");
			out.flush();
			return null;
			
		}
		
	}
	
	//회원정보수정(병원 관계자)
	@PostMapping(path = "/updateHosp.do")
	@ResponseBody
	public String updateHospital(@RequestParam Map<String, Object> map, Model model,
								 HttpSession session, HttpServletResponse response) throws IOException {
		log.info("&&&&& Users_Controller updateHospital 병원정보수정 {} &&&&&", map);
		response.setContentType("text/html; charset=UTF-8");
		
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		log.info("찍어보자 {} " , map);
		
		if(loginVo != null) {
			
			Users_VO updateHospVo = new Users_VO();
			
			String hosp_id = (String)map.get("users_id");
			map.put("hosp_id", hosp_id);
			updateHospVo.setUsers_id(hosp_id);
			
			String hosp_name = (String)map.get("users_name");
			map.put("hosp_name", hosp_name);
			updateHospVo.setUsers_name(hosp_name);
			
			String addr = (String)map.get("users_addr");
			map.put("users_addr", addr);
			updateHospVo.setUsers_addr(addr);
			
			String hosp_tel = (String)map.get("users_tel");
			map.put("users_tel", hosp_tel);
			updateHospVo.setUsers_tel(hosp_tel);
			
			String hosp_subtel = (String)map.get("users_subtel");
			map.put("users_subtel", hosp_subtel);
			updateHospVo.setUsers_subtel(hosp_subtel);
			
			String openTime = (String)map.get("hosp_openTime");
			String closeTime = (String)map.get("hosp_closeTime");
			System.out.println("여는시간 : " + openTime+ "닫는 시간 : " + closeTime);
			String hosp_time = "{\"open\":\""+openTime+"\", \"close\":\""+closeTime+"\"}";
			map.put("hosp_time", hosp_time);
			
			String medi_code = (String)map.get("medi_code");
			map.put("medi_code", medi_code);
			
			String anm_code = (String)map.get("anm_code");
			map.put("anm_code", anm_code);
			
			int n = service.deleteHospAnicode(map);
			int m = service.deleteHospMedicode(map);
			
			int o = service.insertHospAnicode(map);
			int p = service.insertHospMedicode(map);
			
			int q = service.updateUser(updateHospVo);
			int r = service.updateHosp(map);
			
			
			if(n+m+o+p+q+r<=6) {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('정보 수정이 완료되었습니다.');location.href='./resrv_Select.do';</script>");
				out.flush();
				return null;
				
			} else if (n+m+o+p+q+r==0) {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 정보입니다. 다시 입력해주세요.');location.href='./updateHosp.do';</script>");
				out.flush();
				return null;
				
			} else {
				
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 정보입니다. 다시 입력해주세요.');location.href='./updateHosp.do';</script>");
				out.flush();
				return null;
				
			}
			
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스입니다.');location.href='./loginForm.do';</script>");
			out.flush();
			return null;
		}
		
		
		
	}
	
	
	//아이디 찾기 페이지 이동
	@GetMapping(path = "/findIdWindow.do")
	public String findIdPage () {
		log.info("&&&&& Users_Controller 아이디 찾기 페이지로 이동 &&&&&");
		return "users_findId";
	}
	
	
	//아이디 찾기 Ajax 실행 결과
	@PostMapping(path = "/findId.do")
	@ResponseBody
	public String findId(String users_name, String users_tel) {
		log.info("&&&&& Users_Controller 아이디 찾기 ajax 실행 결과 {} {}  &&&&&", users_name, users_tel);
		Users_VO uVo =  new Users_VO();
		
		uVo.setUsers_name(users_name);
		uVo.setUsers_tel(users_tel);
		
		System.out.println("이름 : " + users_name + " 전화번호 : " + users_tel);
		
		Users_VO vo = service.findId(uVo);
		
		if(vo==null) {
			return "false";
		} else {
			String users_id = vo.getUsers_id();
			return users_id;
		}
	}
	

	//비밀번호 찾기 팝업 오픈
		@GetMapping(path = "/findPwWindow.do")
		public String findPwPageOne () {
			log.info("&&&&& Users_Controller 비밀번호 찾기 페이지로 이동 &&&&&");
			return "users_findPw";
		}
	
	//병원 마이페이지에서 진료답변보기 기능
    @GetMapping(value = "/hosp_rpy.do")
	public String hosp_rpy(HttpSession session, Model model) {
		Users_VO loginVo = (Users_VO)session.getAttribute("loginVo");
		Chosen_VO cvo = chosen_service.rpy_cnt_chsn(loginVo.getUsers_id());
		model.addAttribute("cvo", cvo);
		return "hosp_reply";
	}
	
  
  	//비밀번호 변경 페이지 이동
  	@GetMapping(path ="/modifyPw.do")
 	public String findPwPageTwo () {
  		log.info("&&&&& Users_Controller 비밀번호 찾기 페이지로 이동 {} &&&&&");
  		return "users_changePw";
  	}
  	
  	//비밀번호 변경
  	@PostMapping(path ="/modifyPw.do")
  	public String findPw(@RequestParam Map<String, Object> map, HttpServletResponse response) throws IOException {
  		
  		Users_VO uVo = new Users_VO();
  		String id = (String)map.get("users_id");
  		String pw = (String)map.get("users_pw");
  		
  		System.out.println(id);
  		System.out.println(pw);
  		
  		uVo.setUsers_id(id);
  		uVo.setUsers_pw(pw);
  		
  		System.out.println(uVo);
  		int n = service.findPw(uVo);
  		
  		if(n==1) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호 수정이 완료되었습니다.');window.close();</script>");
			out.flush();
			return null;
			
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 정보입니다. 다시 입력해주세요.');location.href='./modifyPw.do';</script>");
			out.flush();
			return null;
		}
  	}

}