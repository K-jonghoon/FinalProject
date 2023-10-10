package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IMap_Dao;
import com.min.edu.vo.AnimalConn_VO;
import com.min.edu.vo.MapRegion_VO;
import com.min.edu.vo.MediConn_VO;
import com.min.edu.vo.NationwideHospital_VO;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Map_ServiceImpl implements IMap_Service {

	@Autowired
	private IMap_Dao dao;

	@Override
	public List<Users_VO> hosp_user(String auth) {
		log.info("&&&&& Map_ServiceImpl hosp_user &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",auth);
		return dao.hosp_user(auth);
	}


	@Override
	public Users_VO hosp_detail(String users_id) {
		log.info("&&&&& Map_ServiceImpl hosp_detail &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",users_id);
		return dao.hosp_detail(users_id);
	}

	@Override
	public List<AnimalConn_VO> hosp_anm(String hosp_id) {
		log.info("&&&&& Map_ServiceImpl hosp_anm &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",hosp_id);
		return dao.hosp_anm(hosp_id);
	}

	@Override
	public List<MediConn_VO> hosp_mediDept(String hosp_id) {
		log.info("&&&&& Map_ServiceImpl hosp_mediDept &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",hosp_id);
		return dao.hosp_mediDept(hosp_id);
	}

	@Override
	public String map_reqAddr(String addr) {
		log.info("&&&&& Map_ServiceImpl map_reqAddr &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",addr);
		return dao.map_reqAddr(addr);
	}

	@Override
	public List<MapRegion_VO> map_Sido() {
		log.info("&&&&& Map_ServiceImpl map_Sido &&&&&");
		return dao.map_Sido();
	}

	@Override
	public List<MapRegion_VO> map_SiGunGu(String si_do) {
		log.info("&&&&& Map_ServiceImpl map_SiGunGu &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",si_do);
		return dao.map_SiGunGu(si_do);
	}


	@Override
	public List<NationwideHospital_VO> map_RegionData(Map<String, Object> map) {
		log.info("&&&&& Map_ServiceImpl map_RegionData &&&&&");
		log.info("&&&&& 전달된 파라미터 값 : {} &&&&&",map);
		return dao.map_RegionData(map);
	}

	
	
}
