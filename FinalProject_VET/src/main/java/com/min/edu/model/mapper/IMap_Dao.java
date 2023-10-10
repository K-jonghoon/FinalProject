package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.AnimalConn_VO;
import com.min.edu.vo.MapRegion_VO;
import com.min.edu.vo.MediConn_VO;
import com.min.edu.vo.NationwideHospital_VO;
import com.min.edu.vo.Users_VO;

public interface IMap_Dao {

	//Map에 전달될 병원회원 리스트
	public List<Users_VO> hosp_user(String auth);
	
	//병원 상세조회
	public Users_VO hosp_detail(String users_id);
	
	//병원 상세조회 진료동물
	public List<AnimalConn_VO> hosp_anm(String hosp_id);
	
	//병원 상세조회 진료과목
	public List<MediConn_VO> hosp_mediDept(String hosp_id);
	
	//지도의 마커찍힌 주소 가져와서 병원의 아이디 호출
	public String map_reqAddr(String addr);
	
	//전국 시/도
	public List<MapRegion_VO> map_Sido();
	
	//전국 시/군/구
	public List<MapRegion_VO> map_SiGunGu(String si_do);
	
	//지역별 동물병원 리스트
	public List<NationwideHospital_VO> map_RegionData(Map<String, Object> map);
}
