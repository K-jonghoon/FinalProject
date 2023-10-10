//package com.min.edu;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.min.edu.model.service.IBoard_Service;
//import com.min.edu.vo.Paging_VO;
//import com.min.edu.vo.QuestBoard_VO;
//import com.min.edu.vo.Users_VO;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@Slf4j
//public class Paging_Controller {
//
//	@Autowired
//	private IBoard_Service service;
//	
//	@PostMapping(value = "/page.do")
//	public Map<String, Object> page(@RequestParam(name="page") int selectPage,
//									HttpSession session,
//									Model model){
//		log.info("RestPageController 게시판 페이지 REST 처리를 위한 page.do : {}", selectPage);
//		log.info("RestPageController page.do 에 Session 확인 : {}, {}", session.getAttribute("loginVo"), session.getAttribute("row"));
//		Users_VO loginVo = (Users_VO) session.getAttribute("loginVo");
//		Paging_VO pVo = (Paging_VO) session.getAttribute("row");
//		
//		pVo.setTotalCount(service.getAllBoardCount(new HashMap<String, Object>(){{
//			put("auth",loginVo.getUsers_auth());
//		}}));
//		
//		pVo.setCountList(5);
//		pVo.setCountPage(5);
//		pVo.setTotalPage(pVo.getTotalCount());
//		pVo.setPage(selectPage);
//		pVo.setStagePage(selectPage);
//		pVo.setEndPage(pVo.getCountPage());
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("first", pVo.getPage()*pVo.getCountList()-(pVo.getCountList()-1));
//		map.put("last", pVo.getPage()*pVo.getCountList());
//		map.put("auth", loginVo.getUsers_auth()); 
//		List<QuestBoard_VO> lists = service.getAllBoardPage(map);
//		
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("lists", lists);
//		result.put("row", pVo);
//		result.put("memId", loginVo.getUsers_id());
//		
////		return new HashMap<String, Object>(){{
////			put("check","return value");
////		}};
//		return result;
//	}
//}
