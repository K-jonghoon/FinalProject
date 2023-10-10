package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.AnimalConn_VO;
import com.min.edu.vo.MapRegion_VO;
import com.min.edu.vo.MediConn_VO;
import com.min.edu.vo.NationwideHospital_VO;
import com.min.edu.vo.Users_VO;

@Repository
public class Map_DaoImpl implements IMap_Dao {

	@Autowired
	private SqlSessionTemplate session;
	
	private static final String NS = "com.min.edu.model.mapper.Map_DaoImpl.";

	@Override
	public List<Users_VO> hosp_user(String auth) {
		return session.selectList(NS+"hosp_user", auth);
	}

	@Override
	public Users_VO hosp_detail(String users_id) {
		return session.selectOne(NS+"hosp_detail",users_id);
	}

	@Override
	public List<AnimalConn_VO> hosp_anm(String hosp_id) {
		return session.selectList(NS+"hosp_anm",hosp_id);
	}

	@Override
	public List<MediConn_VO> hosp_mediDept(String hosp_id) {
		return session.selectList(NS+"hosp_mediDept", hosp_id);
	}

	@Override
	public String map_reqAddr(String addr) {
		return session.selectOne(NS+"map_reqAddr", addr);
	}

	@Override
	public List<MapRegion_VO> map_Sido() {
		return session.selectList(NS+"map_Sido");
	}

	@Override
	public List<MapRegion_VO> map_SiGunGu(String si_do) {
		return session.selectList(NS+"map_SiGunGu", si_do);
	}

	@Override
	public List<NationwideHospital_VO> map_RegionData(Map<String, Object> map) {
		return session.selectList(NS+"map_RegionData", map);
	}
	
}
