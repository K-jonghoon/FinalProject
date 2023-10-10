package com.min.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.min.edu.model.service.IMap_Service;
import com.min.edu.model.service.IPayment_Service;
import com.min.edu.model.service.IReservation_Service;
import com.min.edu.model.service.ISchedule_Service;
import com.min.edu.vo.AnimalConn_VO;
import com.min.edu.vo.FullCalendar_VO;
import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.MediConn_VO;
import com.min.edu.vo.Paging_VO;
import com.min.edu.vo.Reservation_VO;
import com.min.edu.vo.ResrvList_VO;
import com.min.edu.vo.SchedBoard_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Reservation_Controller {

   @Autowired
   private IReservation_Service service;
   
   @Autowired
   private IPayment_Service pay_service;
   
   @Autowired
   private ISchedule_Service sche_service;
   
   @Autowired
   private IMap_Service map_service;
   
   @Autowired
   private JavaMailSender mailSender;
   
   @GetMapping(value = "/resrv_Select.do")
   public String resrv_Select(HttpSession session, Model model) throws JsonMappingException, JsonProcessingException {
      log.info("&&&&& 병원관계자 로그인 -> 병원 마이페이지 이동 &&&&&");
      log.info("&&&&& 병원정보 호출 &&&&&");
      Users_VO user_info = (Users_VO)session.getAttribute("loginVo");
      String user_id = user_info.getUsers_id();
      Users_VO hosp_info = map_service.hosp_detail(user_id);
      String hosp_time = hosp_info.getHospital_vo().get(0).getHosp_time();
      hosp_time = hosp_time.replaceAll(" ", "");
      
      ObjectMapper objectMapper = new ObjectMapper();
      TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
      Map<String, Object> map =  objectMapper.readValue(hosp_time, typeReference);
      System.out.println(map);
      
      model.addAttribute("hosp_time", map);
      List<AnimalConn_VO> anm_lists = map_service.hosp_anm(user_id);
      List<MediConn_VO> medi_lists = map_service.hosp_mediDept(user_id);
      model.addAttribute("hosp_info", hosp_info);
      model.addAttribute("anm_lists", anm_lists);
      model.addAttribute("medi_lists", medi_lists);
      return "hosp_resrvMNG";
   }
   
   @PostMapping(value = "/resrv_monthCount.do")
   @ResponseBody
   public Map<Object, Object> resrv_MonthCount(HttpSession session, String yyyy, Model model) {
      log.info("&&&&& Reservation_Controller resrv_monthCount 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", yyyy);
      // 받아올 값
      Users_VO hosp_id = (Users_VO) session.getAttribute("loginVo");
      
      @SuppressWarnings("serial")
	Map<String, Object> map = new HashMap<String, Object>(){{
         put("yyyy",yyyy);
         put("RESRV_HOPS",hosp_id.getUsers_id());
      }};
      
      Map<Object, Object> resultMap = service.resrv_monthYNCount(map);
      System.out.println(resultMap.get("lists")); 
      return resultMap;
   }
   
   @GetMapping(value = "/fullCalendar.do")
   public void fullCalendar(HttpServletResponse response, HttpSession session) throws IOException {
      log.info("&&&&& Reservation_Controller fullCalendar 호출 &&&&&");
      Users_VO User_id = (Users_VO) session.getAttribute("loginVo");
      String hosp_id = User_id.getUsers_id();
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", hosp_id);
      
      List<FullCalendar_VO> resultList = new ArrayList<>();
      List<Reservation_VO> resrvList = service.resrv_ResrvLists(hosp_id);
      
      for (Reservation_VO rvo : resrvList) {
         FullCalendar_VO fvo = new FullCalendar_VO();
         fvo.setTitle(rvo.getResrv_name());
         fvo.setStart(rvo.getResrv_visit());
         fvo.setResrv_num(rvo.getResrv_num());
         resultList.add(fvo);
      }
      Gson gson = new Gson();
      String json = gson.toJson(resultList);
      PrintWriter out = response.getWriter();
      out.print(json);
      out.flush();
      out.close();
   }
   
   @GetMapping(value = "/resrv_detail.do")
   public String resrv_detail(String resrv_num, Model model) {
      log.info("&&&&& Reservation_Controller resrv_detail 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
      Reservation_VO rvo = service.resrv_detail(resrv_num);
      model.addAttribute("resrv_detail", rvo);
      return "resrv_detail";
   }
   
   @GetMapping(value = "/resrv_detailAjax.do")
   @ResponseBody
   public String resrv_detailAjax(String resrv_num, Model model) {
	   log.info("&&&&& Reservation_Controller resrv_detail 호출 &&&&&");
	   log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
	   Reservation_VO rvo = service.resrv_detail(resrv_num);
	   Gson gson = new Gson();
	   String json = gson.toJson(rvo);
	   return json;
   }
   
   @PostMapping(value = "/resrv_waitLists.do")
   @ResponseBody
   public String resrv_waitList(HttpSession session, String resrv_status) {
      log.info("&&&&& Reservation_Controller resrv_detail 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_status);
      
      Users_VO User_id = (Users_VO)session.getAttribute("loginVo");
      String hosp_id = User_id.getUsers_id();
      
    @SuppressWarnings("serial")
	Map<String, Object> map = new HashMap<String, Object>(){{
         put("resrv_hops", hosp_id);
         put("resrv_status", resrv_status);
      }};
      List<Reservation_VO> lists = service.resrv_dayStatus(map);
      Gson gson = new Gson();
      String json = gson.toJson(lists);
      return json;
   }
   
   /*
    * 일반 사용자의 예약을 승인처리하는 컨트롤러 메소드
    */
   @PostMapping(value = "/resrv_confirm.do")
   @ResponseBody
   public String resrv_confirm(String resrv_num) {
      log.info("&&&&& Reservation_Controller resrv_confirm 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
      int n = service.resrv_updateToY(resrv_num);
      Reservation_VO rvo = service.resrv_detail(resrv_num);
      String hospitalName = service.hosp_name(rvo.getResrv_hops());
      String sche_id = rvo.getResrv_userid();
      String sche_date = rvo.getResrv_visit();
      String sche_title = "진료예약";
      String sche_content = hospitalName;
      String sche_hour = rvo.getResrv_time();
      String sche_minute = "00";
      //예약 확정 메일 전송
      String subject = "[퍼펫트케어] 예약이 확정되었습니다.";
	  String content = "퍼펫트케어에서 신청하신 예약이 확정되었습니다.\n"
				      +"예약번호는 " + resrv_num + " 입니다.\n"
					  +"방문날짜는 " + sche_date + " 입니다.\n"
					  +"자세한 예약내역은 퍼펫트케어 홈페이지를 방문해주세요.\n"
					  +"\n퍼펫트케어를 이용해주셔서 감사합니다.";
	  String from = "likeFirstTime1010@gmail.com";
	  String to = sche_id;
	  try {
	    	MimeMessage mail = mailSender.createMimeMessage();
	    	MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content);
			mailSender.send(mail);
			} catch (Exception e) {
				e.printStackTrace();
			}
	  //일반 사용자 일정에 추가
      SchedBoard_VO svo = new SchedBoard_VO();
      svo.setSche_id(sche_id);
      svo.setSche_date(sche_date);
      svo.setSche_title(sche_title);
      svo.setSche_content(sche_content);
      svo.setSche_hour(sche_hour);
      svo.setSche_minute(sche_minute);
      sche_service.insertNewSchedule(svo);
   	  return (n>0)?"confirm":"false";
   }
   
   /*
    * 일반 사용자의 예약을 거절처리하는 컨트롤러 메소드
    */
   @PostMapping(value = "/resrv_refuse.do")
   @ResponseBody
   public String resrv_refuse(String resrv_num, String user_id, HttpSession session) {
      log.info("&&&&& Reservation_Controller resrv_refuse 호출 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", user_id);

      @SuppressWarnings("serial")
      Map<String, Object> map = new HashMap<String, Object>(){{
		   put("pnt_id",user_id);
		   put("pnt_point",3000);
	   }};
	   int n = service.resrv_updateToX(resrv_num);
	   int m = pay_service.insertNewPnt(map);
	   int point = pay_service.selectAllPnt(user_id);
	   System.out.println(point);
	   session.setAttribute("point", point);
	   
	   Reservation_VO resrv_vo = service.resrv_detail(resrv_num);
	   String send_userID = resrv_vo.getResrv_userid();
	   String subject = "[퍼펫트케어] 예약이 거절되었습니다.";
	   String content = "퍼펫트케어에서 신청하신 예약이 거절되었습니다.\n"
				+"예약이 거절되어 예약금 3000포인트가 반환되었습니다.\n"
				+"\n퍼펫트케어를 이용해주셔서 감사합니다.";
	   String from = "likeFirstTime1010@gmail.com";
	   String to = send_userID;
	   
	   try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content);
			mailSender.send(mail);
	   } catch (Exception e) {
			e.printStackTrace();
	   }
	   return (n>0 && m>0)?"resrv_refuse":"false";
   }
   
   @GetMapping(value = "/resrv_requestPage.do")
   public String resrv_requestPage(HttpSession session, HttpServletResponse response, String resrv_hops, Model model) throws IOException{
      log.info("&&&&& 예약신청 페이지 이동 &&&&&");
      log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_hops);
      response.setContentType("text/html; charset=UTF-8;");
      Users_VO user_vo = (Users_VO) session.getAttribute("loginVo");
      System.out.println("#####"+user_vo);
      session.setAttribute("resrv_hops", resrv_hops);
      String hosp_name = service.hosp_name(resrv_hops);
      model.addAttribute("hosp_name", hosp_name);
      PrintWriter out = response.getWriter();
      if(user_vo != null) {
          if(resrv_hops != null) {
        	  Hospital_VO hosp_info = service.resrv_reqPage(resrv_hops);
        	  int point = pay_service.selectAllPnt(user_vo.getUsers_id());
        	  model.addAttribute("point", point);
        	  if(hosp_info != null) {
        		  model.addAttribute("hosp_time", hosp_info.getHosp_time());
                  model.addAttribute("user_vo", user_vo);
                  return "resrv_requestPage";  
        	  }else {
        		  Hospital_VO hosp_runTime = service.hosp_runTime(resrv_hops);
//        		  Gson gson = new Gson();
//        		  String hosp_run = gson.toJson(hosp_runTime.getHosp_time());
        		  model.addAttribute("hosp_runTime", hosp_runTime.getHosp_time());
        		  model.addAttribute("user_vo", user_vo);
        		  return "resrv_requestPage";
        	  }
               
          }else {
        	  out.print("<script>alert('병원을 다시 선택해주세요');location.href='./map.do'</script>");
        	  out.flush();
        	  out.close();
        	  return null;
          }
      }else {
    	  out.print("<script>alert('로그인 후 이용해주세요');location.href='./loginForm.do'</script>");
    	  out.flush();
    	  out.close();
    	  return null;
      }
      
      
   }
   
   @GetMapping(value = "/resrv_requestAjax.do")
   @ResponseBody
   public void resrv_requestAjax(HttpServletResponse response, HttpSession session, Model model) throws IOException {
	   log.info("&&&&& Reservation_Controller resrv_requestAjax 호출 &&&&&");
	   String resrv_hops = (String)session.getAttribute("resrv_hops");
	   System.out.println("###############"+resrv_hops);
       List<FullCalendar_VO> resultList = new ArrayList<>();
       
       List<Reservation_VO> rLists = service.resrv_reqCal(resrv_hops);
      
       Hospital_VO hosp_info = service.resrv_reqPage(resrv_hops);
//       System.out.println(rLists);
       for (Reservation_VO rvo : rLists) {
    	FullCalendar_VO fvo = new FullCalendar_VO();
		fvo.setStart(rvo.getResrv_visit());
		fvo.setResrv_num(rvo.getResrv_num());
		fvo.setHosp_time(hosp_info.getHosp_time());
		resultList.add(fvo);
       }
       Gson gson = new Gson();
       String json = gson.toJson(resultList);
       PrintWriter out = response.getWriter();
       out.print(json);
       out.flush();
       out.close();
   }
   
   @PostMapping(value = "/resrv_insert.do")
   public String resrv_insert(
		   		@RequestParam Map<String, Object> resrv_map, HttpServletResponse response, 
		   		HttpSession session, Model model) throws IOException {
	   log.info("&&&&& Reservation_Controller resrv_insert &&&&&");
	   log.info("&&&&& 전달받은 파라미터 {} &&&&&", resrv_map);
	   response.setContentType("text/html; charset=UTF-8;");
	   
	   String hosp = (String)resrv_map.get("resrv_hops");
	   String visit = (String)resrv_map.get("resrv_visit");
	   String time = ((String)resrv_map.get("resrv_time")).substring(0,2);
	   String name = (String)resrv_map.get("resrv_name");
	   String tel = (String)resrv_map.get("resrv_tel");
	   String memo = (String)resrv_map.get("resrv_memo");
	   String user_id = (String)resrv_map.get("resrv_userid");
	   
	   int wholePnt =  pay_service.selectAllPnt(user_id);
	   if(wholePnt >= 3000) {
		   Reservation_VO resrv_vo = new Reservation_VO();
		   resrv_vo.setResrv_hops(hosp);
		   resrv_vo.setResrv_visit(visit);
		   resrv_vo.setResrv_time(time);
		   resrv_vo.setResrv_name(name);
		   resrv_vo.setResrv_tel(tel);
		   resrv_vo.setResrv_memo(memo);
		   resrv_vo.setResrv_userid(user_id);
		   String resrv_num = service.resrv_insert(resrv_vo);
		   pay_service.usePntOnResrv(user_id); 
		   int point = pay_service.selectAllPnt(user_id);
		   session.setAttribute("point", point);
		   return "redirect:/resrv_detail.do?resrv_num="+resrv_num;
	   }else {
		   PrintWriter out = response.getWriter();
	       out.print("<script>alert('예약금 결제 포인트가 부족합니다. 포인트를 충전해주세요'); "
	       				+ "window.opener.location.href='./goPayment.do';"
	       				+ "window.close();</script>");
	       out.flush();
	       out.close();
	       return null;
	   }
	   
   }
   
   @GetMapping(value = "/resrv_recordList.do")
   public String resrv_recordList(String resrv_userid, Model model, HttpSession session,
		   @RequestParam(required = false, defaultValue = "1")String page
		   ) {
	   log.info("&&&&& Reservation_Controller resrv_recordList &&&&&");
	   log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&",resrv_userid);
	   log.info("&&&&& 전달된 페이지 : {} &&&&&", page);
	   
	   Paging_VO pVo = null;
		if(session.getAttribute("row")==null) {
			pVo = new Paging_VO();
			session.setAttribute("row", pVo);
		} 
		else {
			pVo = (Paging_VO)session.getAttribute("row");
		}
		log.info("----------------현재페이지 : {}", page);
		int selectPage = Integer.parseInt(page);
		log.info("----------------선택된페이지 : {}", selectPage);
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("resrv_userid", resrv_userid);
		
		pVo.setTotalCount(service.resrv_recordListCnt(inMap)); //총 게시물의 개수
		pVo.setCountList(5); //출력될 게시글의 개수
		pVo.setCountPage(5); // 화면에 몇 개의 페이지를 보여줄 건지 (페이지 그룹)
		pVo.setTotalPage(pVo.getTotalCount()); // 총 페이지의 개수
		pVo.setPage(selectPage); // 화면에서 선택된 페이지 번호
		pVo.setStagePage(selectPage); // 페이지 그룹의 시작 번호
		pVo.setEndPage(pVo.getCountPage()); // 끝 번호
		
		//페이징처리된 결과를 가지고 옴
		// 현재 페이지 * 한 페이지의 글 개수 row - (한 페이지의 글 개수 row -1) : 1*5 - (5-1) = 1
		inMap.put("first", pVo.getPage()*pVo.getCountList()-(pVo.getCountList()-1));
		// 현재 페이지 * 한 페이지의 글 개수 row
		inMap.put("last", pVo.getPage()*pVo.getCountList());
		
	   List<Reservation_VO> list = service.resrv_recordList(inMap);
	   List<ResrvList_VO> hosp_lists = new ArrayList<ResrvList_VO>();
	   for (int i = 0; i < list.size(); i++) {
		   ResrvList_VO rvo = new ResrvList_VO();
		   String hospName = service.hosp_name(list.get(i).getResrv_hops());
		   rvo.setResrv_num(list.get(i).getResrv_num());
		   rvo.setResrv_hops(hospName);
		   rvo.setResrv_visit(list.get(i).getResrv_visit());
		   rvo.setResrv_time(list.get(i).getResrv_time());
		   rvo.setResrv_name(list.get(i).getResrv_name());
		   rvo.setResrv_status(list.get(i).getResrv_status());
		   rvo.setResrv_memo(list.get(i).getResrv_memo());
		   hosp_lists.add(rvo);
	   }
	   
	   model.addAttribute("hosp_lists", hosp_lists);
	   model.addAttribute("page", pVo);
	   return "users_resrvRecord";
   }
   
   @PostMapping(value = "/resrv_detailModify.do")
   @ResponseBody
   public String resrv_detailModify(@RequestParam Map<String, Object> map, HttpServletResponse response) throws IOException {
	   log.info("&&&&& Reservation_Controller resrv_detailModify &&&&&");
	   log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&",map);
	   response.setContentType("text/html; charset=UTF-8;");
	  
	   Reservation_VO rvo = new Reservation_VO();
	   rvo.setResrv_num((String) map.get("resrv_num"));
	   rvo.setResrv_visit((String)map.get("resrv_visit"));
	   String resrv_time = (String)map.get("resrv_time");
	   rvo.setResrv_time(resrv_time.substring(0,2));
	   rvo.setResrv_name((String)map.get("resrv_name"));
	   rvo.setResrv_tel((String)map.get("resrv_tel"));
	   if(map.get("resrv_memo") != null) {
		   rvo.setResrv_memo((String)map.get("resrv_memo"));
	   }else {
		   rvo.setResrv_memo("");
	   }
	   int n = service.resrv_detailModify(rvo);
	   if(n>0) {
		   return "true";
	   }else {
		   PrintWriter out = response.getWriter();
		   out.print("<script>alert('수정 실패했습니다.'); location.href='./resrv_Select.do'</script>");
		   out.flush();
		   out.close();
		   return null;
	   }
   }
   
   /*
    * 일반사용자 예약취소를 처리하는 컨트롤러 메소드
    */
   @PostMapping(value = "/resrv_userCancel.do")
   @ResponseBody
   public String resrv_userCancel(@RequestParam Map<String, Object> map,HttpSession session, HttpServletResponse response) throws IOException {
	   log.info("&&&&& Reservation_Controller resrv_userCancel &&&&&");
	   log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&",map);
	   Users_VO uvo = (Users_VO)session.getAttribute("loginVo");
	   response.setContentType("text/html; charsest=UTF-8;");
	   //유저 아이디
	   String user_id = uvo.getUsers_id();
	   
	   if(user_id != null) {
		   int n = service.resrv_updateToN((String) map.get("resrv_num"));
		   
		   @SuppressWarnings("serial")
		   Map<String, Object> inMap = new HashMap<String, Object>(){{
			   put("pnt_id",user_id);
			   put("pnt_point",3000);
		   }};
		   int m = pay_service.insertNewPnt(inMap);
		   int point = pay_service.selectAllPnt(user_id);
		   session.setAttribute("point", point);
		   
		   @SuppressWarnings("serial")
		Map<String, Object> delMap = new HashMap<String, Object>(){{
			   put("sche_id",user_id);
			   put("sche_date",(String)map.get("resrv_visit"));
			   put("sche_hour",(String)map.get("resrv_time"));
			   put("sche_title","진료예약");
		   }};
		   int del_sche = sche_service.deleteRSV(delMap);
		   
		   return (n>0 && m>0 && del_sche>0)?"true":"false";
	   }else {
		   PrintWriter out = response.getWriter();
		   out.print("<script>alert('아이디 정보가 없습니다. 다시 로그인해주세요.'); location.href='./loginForm.do'</script>");
		   out.flush();
		   out.close();
		   return null;
	   }
	   
   }
}