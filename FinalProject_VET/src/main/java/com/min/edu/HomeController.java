package com.min.edu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping(value = "/userMyPage.do")
	public String userMyPage() {
		log.info("&&&&& 메인 -> 일반사용자 마이페이지 &&&&&");
		return "userMyPage";
	}

	@GetMapping(value = "/hospitalDetail.do")
	public String hospitalDetail() {
		return "hospitalDetail";
	}
  
}