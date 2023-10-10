package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.edu.model.mapper.IBoard_Dao;
import com.min.edu.vo.AnimalCode_VO;
import com.min.edu.vo.AnimalPart_VO;
import com.min.edu.vo.PetsInfo_VO;
import com.min.edu.vo.QuestBoard_VO;
import com.min.edu.vo.ReplyBoard_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Board_ServiceImpl implements IBoard_Service {

	@Autowired
	private IBoard_Dao dao;
	
	@Override
	public List<QuestBoard_VO> selectQuest() {
		log.info("&&&&& Board_ServiceImpl selectQuest &&&&&");
		return dao.selectQuest();
	}

	@Override
	public List<QuestBoard_VO> selectCodeQuest(Map<String, Object> map) {
		log.info("&&&&& Board_ServiceImpl selectCodeQuest 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.selectCodeQuest(map);
	}

	@Override
	public List<QuestBoard_VO> selectPartQuest(Map<String, Object> map) {
		log.info("&&&&& Board_ServiceImpl selectPartQuest 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.selectPartQuest(map);
	}

	@Override
	public List<QuestBoard_VO> selectAllBoard() {
		log.info("&&&&& Board_ServiceImpl selectAllBoard &&&&&");
		return dao.selectAllBoard();
	}

	@Override
	public List<QuestBoard_VO> selectReportBoard() {
		log.info("&&&&& Board_ServiceImpl selectReportBoard &&&&&");
		return dao.selectReportBoard();
	}

	@Override
	public List<QuestBoard_VO> selectOneBoard(String seq) {
		log.info("&&&&& Board_ServiceImpl selectOneBoard 전달받은 파라미터 값 : {} &&&&&", seq);
		
		return (List<QuestBoard_VO>) dao.selectOneBoard(seq);
	}
	

	@Override
	public String insertQuest(QuestBoard_VO vo) {
		log.info("&&&&& Board_ServiceImpl insertQuest 전달받은 파라미터 값 : {} &&&&&", vo);
		int n =dao.insertQuest(vo);
		String m = (n>0)?dao.getMaxSeq():"";
		return m;
	}

	@Override
	public int updateFastQuest(String seq) {
		log.info("&&&&& Board_ServiceImpl updateFastQuest 전달받은 파라미터 값 : {} &&&&&", seq);
		return dao.updateFastQuest(seq);
	}

	@Override
	public int modifyQuest(Map<String, Object> map) {
		log.info("&&&&& Board_ServiceImpl modifyQuest 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.modifyQuest(map);
	}

	@Override
	public int reportQuest(String seq) {
		log.info("&&&&& Board_ServiceImpl reportQuest 전달받은 파라미터 값 : {} &&&&&", seq);
		return dao.reportQuest(seq);
	}

	@Override
	public int deleteQuest(String seq) {
		log.info("&&&&& Board_ServiceImpl deleteQuest 전달받은 파라미터 값 : {} &&&&&", seq);
		return dao.deleteQuest(seq);
	}

	@Override
	public int insertReply(ReplyBoard_VO vo) {
		log.info("&&&&& Board_ServiceImpl insertReply 전달받은 파라미터 값 : {} &&&&&", vo);
		return dao.insertReply(vo);
	}

	@Override
	public int modifyReply(Map<String, Object> map) {
		log.info("&&&&& Board_ServiceImpl modifyReply 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.modifyReply(map);
	}

	@Override
	public int deleteReply(String seq) {
		log.info("&&&&& Board_ServiceImpl deleteReply 전달받은 파라미터 값 : {} &&&&&", seq);
		return dao.deleteReply(seq);
	}

	@Override
	public int reportReply(String seq) {
		log.info("&&&&& Board_ServiceImpl reportReply 전달받은 파라미터 값 : {} &&&&&", seq);
		return dao.reportReply(seq);
	}

	@Override
	public int chooseReply(String seq) {
		log.info("&&&&& Board_ServiceImpl chooseReply 전달받은 파라미터 값 : {} &&&&&", seq);
		return dao.chooseReply(seq);
	}

	@Override
	public int countReply(String id) {
		log.info("&&&&& Board_ServiceImpl countReply 전달받은 파라미터 값 : {} &&&&&", id);
		return dao.countReply(id);
	}

	@Override
	public int countChosenReply(String id) {
		log.info("&&&&& Board_ServiceImpl countChosenReply 전달받은 파라미터 값 : {} &&&&&", id);
		return dao.countChosenReply(id);
	}

	@Override
	public int calChoiceRate(String id) {
		log.info("&&&&& Board_ServiceImpl calChoiceRate 전달받은 파라미터 값 : {} &&&&&", id);
		return dao.calChoiceRate(id);
	}

	@Override
	public List<ReplyBoard_VO> selectReply(String seq) {
		return dao.selectReply(seq);
	}

	@Override
	public List<Users_VO> selectQuestUsers(Map<String, Object> map) {
		
		return dao.selectQuestUsers(map);
	}

	@Override
	public List<QuestBoard_VO> selectUsersBoard(String qst_id) {
		return dao.selectUsersBoard(qst_id);
	}

	@Override
	public List<QuestBoard_VO> selectFastBoard(String qst_id) {
		return dao.selectFastBoard(qst_id);
	}
	
	@Override
	public List<QuestBoard_VO> getAllBoardPage(Map<String, Object> map) {
		log.info("BoardServiceImpl 전체글 조회 페이징");
		log.info("BoardServiceImpl 전달 받은 페이지 권한 및 범위 {}", map);
		return dao.getAllBoardPage(map);
	}

	@Override
	public int getAllBoardCount(Map<String, Object> map) {
		log.info("BoardServiceImpl 전체글 조회 카운트");
		log.info("BoardServiceImpl 전달 받은 페이지 권한 및 범위 {}", map);
		return dao.getAllBoardCount(map);
	}
	


	
	


}
