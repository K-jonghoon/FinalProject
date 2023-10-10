package com.min.edu.model.mapper;


import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.AnimalCode_VO;
import com.min.edu.vo.AnimalPart_VO;
import com.min.edu.vo.QuestBoard_VO;
import com.min.edu.vo.ReplyBoard_VO;
import com.min.edu.vo.Users_VO;

@Repository
public class Board_DaoImpl implements IBoard_Dao {
	
	private static final String NS = "com.min.edu.model.mapper.Board_DaoImpl.";
	
	@Autowired
	SqlSessionTemplate session;
	

	@Override
	public List<QuestBoard_VO> selectQuest() {
		return session.selectList(NS+"selectQuest");
	}

	@Override
	public List<QuestBoard_VO> selectCodeQuest(Map<String, Object> map) {
		return session.selectList(NS+"selectCodeQuest", map);
	}

	@Override
	public List<QuestBoard_VO> selectPartQuest(Map<String, Object> map) {
		return session.selectList(NS+"selectPartQuest", map);
	}

	@Override
	public List<QuestBoard_VO> selectAllBoard() {
		return session.selectList(NS+"selectAllBoard");
	}

	@Override
	public List<QuestBoard_VO> selectReportBoard() {
		return session.selectList(NS+"selectReportBoard");
	}
	
	@Override
	public List<QuestBoard_VO> selectOneBoard(String seq) {
		return session.selectList(NS+"selectOneBoard", seq);
	}
	
	@Override
	public int insertQuest(QuestBoard_VO vo) {
		return session.insert(NS+"insertQuest", vo);
	}
	
	@Override
	public String getMaxSeq() {
		return session.selectOne(NS+"getMaxSeq");
	}
	
	@Override
	public int updateFastQuest(String seq) {
		return session.update(NS+"updateFastQuest", seq);
	}

	@Override
	public int modifyQuest(Map<String, Object> map) {
		return session.update(NS+"modifyQuest", map);
	}

	@Override
	public int reportQuest(String seq) {
		return session.update(NS+"reportQuest", seq);
	}

	@Override
	public int deleteQuest(String seq) {
		return session.update(NS+"deleteQuest", seq);
	}

	@Override
	public int insertReply(ReplyBoard_VO vo) {
		return session.insert(NS+"insertReply", vo);
	}


	@Override
	public int modifyReply(Map<String, Object> map) {
		return session.update(NS+"modifyReply", map);
	}

	@Override
	public int deleteReply(String seq) {
		return session.update(NS+"deleteReply", seq);
	}

	@Override
	public int reportReply(String seq) {
		return session.update(NS+"reportReply", seq);
	}

	@Override
	public int chooseReply(String seq) {
		return session.update("chooseReply", seq);
	}
	
	@Override
	public int countReply(String id) {
		return session.selectOne(NS+"countReply", id);
	}

	@Override
	public int countChosenReply(String id) {
		return session.selectOne(NS+"countChosenReply", id);
	}

	@Override
	public int calChoiceRate(String id) {
		return session.selectOne(NS+"calChoiceRate", id);
	}

	@Override
	public List<ReplyBoard_VO> selectReply(String seq) {
		return session.selectList(NS+"selectReply", seq);
	}

	@Override
	public List<Users_VO> selectQuestUsers(Map<String, Object> map) {
		return session.selectList(NS+"selectQuestUsers", map);
	}

	@Override
	public AnimalCode_VO selectAnimalCode(String anm_code) {
		return session.selectOne(NS+"selectAnimalCode", anm_code);
	}

	@Override
	public AnimalPart_VO selectAnimalPart(String part_code) {
		return session.selectOne(NS+"selectAnimalPart", part_code);
	}

	@Override
	public List<QuestBoard_VO> selectUsersBoard(String qst_id) {
		return session.selectList(NS+"selectUsersBoard",qst_id);
	}

	@Override
	public List<QuestBoard_VO> selectFastBoard(String qst_id) {
		return session.selectList(NS+"selectFastBoard",qst_id);
	}

	@Override
	public List<QuestBoard_VO> getAllBoardPage(Map<String, Object> map) {
		return session.selectList(NS+"getAllBoardPage", map);
	}

	@Override
	public int getAllBoardCount(Map<String, Object> map) {
		return session.selectOne(NS+"getAllBoardCount", map);
	}



	
	
}
