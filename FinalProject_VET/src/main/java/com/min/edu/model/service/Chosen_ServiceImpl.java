package com.min.edu.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IChosen_Dao;
import com.min.edu.vo.Chosen_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Chosen_ServiceImpl implements IChosen_Service {

	@Autowired
	private IChosen_Dao dao;

	@Override
	public List<Chosen_VO> chosen_rank() {
		log.info("&&&&& Chosen_ServiceImpl chosen_rank 실행 &&&&&");
		return dao.chosen_rank();
	}

	@Override
	public Chosen_VO rpy_cnt_chsn(String rpy_id) {
		log.info("&&&&& Chosen_ServiceImpl rpy_cnt_chsn 실행 &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", rpy_id);
		return dao.rpy_cnt_chsn(rpy_id);
	}
	
	
}
