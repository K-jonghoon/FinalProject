package com.min.edu.model.service;

import java.util.List;

import com.min.edu.vo.Chosen_VO;

public interface IChosen_Service {

	public List<Chosen_VO> chosen_rank();
	
	public Chosen_VO rpy_cnt_chsn(String rpy_id);
	
}
