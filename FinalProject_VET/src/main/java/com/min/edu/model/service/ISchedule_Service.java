package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.SchedBoard_VO;

public interface ISchedule_Service {
	
	public List<SchedBoard_VO> selectAllSchedule(String sche_id);
	
	public SchedBoard_VO selectOneSchedule(int sche_num);
	
	public int modifySchedule(Map<String, Object> map);
	
	public int deleteSchedule(int sche_num);
	
	public int insertNewSchedule(SchedBoard_VO svo);

	//예약 취소 -> 일반사용자 ID, 일정명, 일정시간, 일정날짜로 일정 삭제
	public int deleteRSV(Map<String, Object> map);
	
}
