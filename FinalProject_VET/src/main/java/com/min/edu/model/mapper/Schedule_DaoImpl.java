package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.SchedBoard_VO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class Schedule_DaoImpl implements ISchedule_Dao {
	
	private static final String NS="com.min.edu.model.mapper.Schedule_DaoImpl.";
	
	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public List<SchedBoard_VO> selectAllSchedule(String sche_id) {
		return session.selectList(NS+"selectAllSchedule", sche_id);
	}

	@Override
	public SchedBoard_VO selectOneSchedule(int sche_num) {
		return session.selectOne(NS+"selectOneSchedule", sche_num);
	}

	@Override
	public int modifySchedule(Map<String, Object> map) {
		return session.update(NS+"modifySchedule", map);
	}

	@Override
	public int deleteSchedule(int sche_num) {
		return session.delete(NS+"deleteSchedule", sche_num);
	}

	@Override
	public int insertNewSchedule(SchedBoard_VO svo) {
		return session.insert(NS+"insertNewSchedule",svo);
	}

	@Override
	public int deleteRSV(Map<String, Object> map) {
		return session.delete(NS+"deleteRSV",map);
	}
}
