package com.test.edu;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.service.INotice_Service;
import com.min.edu.vo.NoticeBoard_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Notice_JUnitTest {
	
	@Autowired
	private INotice_Service service;
	
	@Test
	public void test() {
//		List<NoticeBoard_VO> lists = service.selectNotice();

//		List<NoticeBoard_VO> lists = service.selectAllNotice();
		
//		NoticeBoard_VO nVo = service.selectNoticeDetail("N2");
		
//		NoticeBoard_VO nVo = new NoticeBoard_VO();
//		nVo.setNoti_auth("A");
//		nVo.setNoti_id("admin@gmail.com");
//		nVo.setNoti_seq("N5");
//		nVo.setNoti_title("공지사항5 수정수정");
//		nVo.setNoti_content("수정수정수정수정");
	
		String noti_seq = "N1";
		int n = service.deleteNotice(noti_seq);
				
//		assertNotNull(nVo);
		assertEquals("삭제성공", 1, n, 0);
		
	}

}
