package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.ISchedule_Dao;
import com.min.edu.vo.SchedBoard_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Schedule_ServiceImpl implements ISchedule_Service {
	
	@Autowired
	private ISchedule_Dao dao;
	
	@Override
	public List<SchedBoard_VO> selectAllSchedule(String sche_id) {
		log.info("&&&&& Schedule_ServiceImpl selectAllSchedule &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", sche_id);
		return dao.selectAllSchedule(sche_id);
	}

	@Override
	public SchedBoard_VO selectOneSchedule(int sche_num) {
		log.info("&&&&& Schedule_ServiceImpl selectOneSchedule &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", sche_num);
		return dao.selectOneSchedule(sche_num);
	}

	@Override
	public int modifySchedule(Map<String, Object> map) {
		log.info("&&&&& Schedule_ServiceImpl modifySchedule &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.modifySchedule(map);
	}

	@Override
	public int deleteSchedule(int sche_num) {
		log.info("&&&&& Schedule_ServiceImpl modifySchedule &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", sche_num);
		return dao.deleteSchedule(sche_num);
	}

	@Override
	public int insertNewSchedule(SchedBoard_VO svo) {
		log.info("&&&&& Schedule_ServiceImpl insertNewSchedule &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", svo);
		return dao.insertNewSchedule(svo);
	}

	@Override
	public int deleteRSV(Map<String, Object> map) {
		log.info("&&&&& Schedule_ServiceImpl insertNewSchedule &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.deleteRSV(map);
	}

}
