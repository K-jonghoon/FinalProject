package com.min.edu.model.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.Chosen_VO;

@Repository
public class Chosen_DaoImpl implements IChosen_Dao {

	@Autowired
	private SqlSessionTemplate session;
	
	private static final String NS = "com.min.edu.model.mapper.Chosen_DaoImpl.";

	@Override
	public List<Chosen_VO> chosen_rank() {
		return session.selectList(NS+"chosen_rank");
	}

	@Override
	public Chosen_VO rpy_cnt_chsn(String rpy_id) {
		return session.selectOne(NS+"rpy_cnt_chsn",rpy_id);
	}
	
}
