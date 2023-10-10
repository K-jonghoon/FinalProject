package com.min.edu.model.mapper;


import java.util.List;
import java.util.Map;

import com.min.edu.vo.AnimalCode_VO;
import com.min.edu.vo.AnimalPart_VO;
import com.min.edu.vo.QuestBoard_VO;
import com.min.edu.vo.ReplyBoard_VO;
import com.min.edu.vo.Users_VO;

public interface IBoard_Dao {
	
	public List<QuestBoard_VO> selectQuest();
	
	public List<QuestBoard_VO> selectCodeQuest(Map<String, Object> map);
	
	public List<QuestBoard_VO> selectPartQuest(Map<String, Object> map);

	public List<QuestBoard_VO> selectAllBoard();
	
	public List<QuestBoard_VO> selectReportBoard();
	
	public List<QuestBoard_VO> selectOneBoard(String seq);
	
	public List<ReplyBoard_VO> selectReply(String seq);
	
	public String getMaxSeq();
	
	public int insertQuest(QuestBoard_VO vo);
	
	public int updateFastQuest(String seq);
	
	public int modifyQuest(Map<String, Object> map);
	
	public int reportQuest(String seq);
	
	public int deleteQuest(String seq);
	
	public int insertReply(ReplyBoard_VO vo);
	
	public int modifyReply(Map<String, Object> map);
	
	public int deleteReply(String seq);

	public int reportReply(String seq);
	
	public int chooseReply(String seq);
	
	public int countReply(String id);
	
	public int countChosenReply(String id);
	
	public int calChoiceRate(String id);

	public List<Users_VO> selectQuestUsers(Map<String, Object> map);

	AnimalCode_VO selectAnimalCode(String anm_code);

	AnimalPart_VO selectAnimalPart(String part_code);

	public List<QuestBoard_VO> selectUsersBoard(String qst_id);
	
	public List<QuestBoard_VO> selectFastBoard(String qst_id);

	public List<QuestBoard_VO> getAllBoardPage(Map<String, Object> map);
	
	public int getAllBoardCount(Map<String, Object> map);

	
	
	
	
	
	
	
	
	
	

}
