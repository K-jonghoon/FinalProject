package com.test.edu;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.min.edu.model.mapper.IBoard_Dao;
import com.min.edu.model.service.IBoard_Service;
import com.min.edu.vo.QuestBoard_VO;
import com.min.edu.vo.ReplyBoard_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class Board_JUnitTest {
	
	@Autowired
	private IBoard_Dao dao;
	@Autowired
	private IBoard_Service service;
	@Autowired
	private ApplicationContext context;
	

//	@Before
	public void db_Test() {
		SqlSessionTemplate session = context.getBean("sqlSessionTemplate", SqlSessionTemplate.class);
		assertNotNull(session);
	}
	
//	@Test
	public void selectQuest() {
		List<QuestBoard_VO> lists = dao.selectQuest();
		assertNotNull(lists);
	}
	
//	@Test
	public void selectCodeQuest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qst_species", "D");
		List<QuestBoard_VO> lists = dao.selectCodeQuest(map);
		assertNotNull(lists);
	}
	
//	@Test
	public void selectPartQuest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qst_species", "D");
		map.put("qst_part", "10");
		List<QuestBoard_VO> lists = dao.selectPartQuest(map);
		assertNotNull(lists);
	}
	
//	@Test
	public void selectAllBoard() {
		List<QuestBoard_VO> lists = dao.selectAllBoard();
		assertNotNull(lists);
	}
	
//	@Test
	public void selectReportBoard() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qst_status", "R");
		map.put("rpy_status", "R");
		List<QuestBoard_VO> lists = dao.selectReportBoard();
		assertNotNull(lists);
	}
	
//	@Test
//	public void selectOneBoard() {
//		List<QuestBoard_VO> lists = service.selectOneBoard("Q2");
//		assertNotNull(lists);
//	}
	
//	@Test
	public void insertQuest() {
		QuestBoard_VO vo = new QuestBoard_VO();
		vo.setQst_seq("Q10");
		vo.setQst_id("carrot@gmail.com");
		vo.setQst_species("O");
		vo.setQst_part("08");
		vo.setQst_title("TESTTITLE");
		vo.setQst_content("TESTCONTENT");
		
		String n = service.insertQuest(vo);
		assertEquals(1, n);
	}
	
//	@Test
	public void updateFastQuest() {
		int n = service.updateFastQuest("Q10");
		assertEquals(1, n);
	}
	
//	@Test
	public void modifyQuest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qst_seq","Q10");
//		map.put("qst_content", "수정테스트");
		map.put("qst_content", "오늘 아침에 다람쥐가 뺨을 때리면서 깨우길래 이게 간이 배 밖으로 나왔구나 싶었어요. 야구방망이를 챙겨들고 날아가는 다람쥐를 얼른 따라갔는데, 화장실로 들어가더라고요. 그래서 집에 있던 친구에게 문 밖을 지키라고 당부하고선 화장실 문을 열었는데, 아니 제 하늘다람쥐가 세면대에서 배 밖으로 쏟아진 간을 씻고 있는 거에요. 너무 아파보였어요. 지금은 새장에 넣어둿는데 어떻게 해야할까요? 다시 줏어넣기에는 간이 너무 커다래요.");
//		map.put("qst_species", "D");
		map.put("qst_species", "O");
//		map.put("qst_part", "01");
		map.put("qst_part", "08");
		int n = service.modifyQuest(map);
		assertEquals(n, 1);
	}
	
//	@Test
	public void deleteQuest() {
		String qst_seq = "Q10";
		int n = service.deleteQuest(qst_seq);
		assertEquals(n, 1);
	}
		
//	@Test
	public void insertReply() {
		ReplyBoard_VO vo = new ReplyBoard_VO();
		vo.setRpy_seq("R14");
		vo.setRpy_id("juchu@naver.com");
		vo.setRpy_ref("Q10");
		vo.setRpy_content("내부 장기가 공기 중에 오래 노출되면 외부오염과 더불어 신체에 심각한 문제가 생길 수 있습니다. 가까운 병원에 방문하여 치료해주세요.");
		
		int n = service.insertReply(vo);
		assertEquals(1, n);
	}
	
//	@Test
	public void modifyReply() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("rpy_seq", "R14");
//			put("rpy_content", "답글수정테스트");
			put("rpy_content", "내부 장기가 공기 중에 오래 노출되면 외부오염과 더불어 신체에 심각한 문제가 생길 수 있습니다. 가까운 병원에 방문하여 치료해주세요.");
		}};
		int n = service.modifyReply(map);
		assertEquals(n, 1);
	}
	
//	@Test
	public void deleteReply() {
		String rpy_seq = "R14";
		int n = service.deleteReply(rpy_seq);
		assertEquals(n, 1);
	}
	
//	@Test
	public void reportReply() {
		String rpy_seq = "R14";
		int n = service.reportReply(rpy_seq);
		assertEquals(n, 1);
	}
	
//	@Test
	public void chooseReply() {
		String rpy_seq = "R14";
		int n = service.chooseReply(rpy_seq);
		assertEquals(n, 1);
	}
	
//	@Test
	public void countReply() {
		String id = "gana@naver.com";
		int n = service.countReply(id);
		assertEquals(n, 3);
	}
	
//	@Test
	public void countChosenReply() {
		String id = "gana@naver.com";
		int n = service.countChosenReply(id);
		assertEquals(n, 2);
	}
	
//	@Test
	public void calChoiceRate() {
		String id = "gana@naver.com";
		int n = service.calChoiceRate(id);
		assertEquals(n, 66);
	}
	
	
	
}
