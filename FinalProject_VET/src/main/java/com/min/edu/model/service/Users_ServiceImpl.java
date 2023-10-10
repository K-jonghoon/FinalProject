package com.min.edu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.edu.model.mapper.IUsers_Dao;
import com.min.edu.vo.Users_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Users_ServiceImpl implements IUsers_Service {

	@Autowired
	private IUsers_Dao dao;

	@Override
	public List<Users_VO> selectAllUsers() {
		log.info("&&&&& Users_ServiceImpl selectAllUsers &&&&&");
		return dao.selectAllUsers();
	}

	@Override
	public List<Users_VO> selectUsersAuth(String users_auth) {
		log.info("&&&&& Users_ServiceImpl selectUsersAuth 전달받은 파라미터 값 : {} &&&&&", users_auth);
		return dao.selectUsersAuth(users_auth);
	}

	@Override
	public List<Users_VO> selectUsersStatus(String users_status) {
		log.info("&&&&& Users_ServiceImpl selectUsersStatus 전달받은 파라미터 값 : {} &&&&&", users_status);
		return dao.selectUsersStatus(users_status);
	}

	@Override
	public List<Users_VO> searchUsers(String keyword) {
		log.info("&&&&& Users_ServiceImpl searchUsers 전달받은 파라미터 값 : {} &&&&&", keyword);
		return dao.searchUsers(keyword);
	}

	@Override
	public List<Users_VO> selectUserDetail(String users_id) {
		log.info("&&&&& Users_ServiceImpl selectUserDetail 전달받은 파라미터 값 : {} &&&&&", users_id);
		return dao.selectUserDetail(users_id);
	}

	@Override
	public Users_VO selectHospitalDetail(String users_id) {
		log.info("&&&&& Users_ServiceImpl selectHospitalDetail 전달받은 파라미터 값 : {} &&&&&", users_id);
		return dao.selectHospitalDetail(users_id);
	}

	@Override
	public Users_VO loginUser(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl loginUser 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.loginUser(map);
	}

	@Override
	public int insertUser(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl insertUser 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.insertUser(map);
	}
	
	@Override
	public int duplicationId(String email) {
		log.info("&&&&& Users_ServiceImpl duplicationId 전달받은 파라미터 값 : {} &&&&&", email);
		return dao.duplicationId(email);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean addInfo(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl addInfo 추가정보입력 : {} &&&&&", map);
		int n = dao.addInfoUser(map);
		int m = dao.insertPetInfo(map);
		return (n+m)>0?true:false;
	}

	@Transactional(readOnly = true)
	@Override
	public boolean insertHosp(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl insertHosp 병원회원가입 : {} &&&&&", map);
		int n = dao.insertHospInfo(map);
		int m = dao.insertHospInfoDetail(map);
		return (n+m)>0?true:false;
	}
	
	@Override
	public int insertHospAnicode(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl insertHospAnicode 병원진료동물입력 : {} &&&&&", map);
		return dao.insertHospAnicode(map);
	}
	
	@Override
	public int insertHospMedicode(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl insertHospMedicode 병원진료과목입력 : {} &&&&&", map);
		return dao.insertHospMedicode(map);
	}
	
	
	@Override
	public int resignUser(Users_VO uVo) {
		log.info("&&&&& Users_ServiceImpl resignUser 회원탈퇴 : {} &&&&&", uVo);
		return dao.resignUser(uVo);
	}
	
	@Override
	public int updateUser(Users_VO uVo) {
		log.info("&&&&& Users_ServiceImpl updateUser 회원정보수정(일반사용자) : {} &&&&&", uVo);
		return dao.updateUser(uVo);
	}
	
	
	@Override
	public int updateHosp(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl updateHospital 회원정보수정(병원관계자) 1 : {} &&&&&", map);
		return dao.updateHosp(map);
	}
	
	@Override
	public int deleteHospAnicode(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl deleteHospAnicode 회원정보수정(병원관계자) 진료동물 : {} &&&&&", map);
		return dao.deleteHospAnicode(map);
	}
	
	@Override
	public int deleteHospMedicode(Map<String, Object> map) {
		log.info("&&&&& Users_ServiceImpl deleteHospMedicode 회원정보수정(병원관계자) 진료과목 : {} &&&&&", map);
		return dao.deleteHospMedicode(map);
	}
	
	@Override
	public Users_VO findId(Users_VO uVo) {
		log.info("&&&&& Users_ServiceImpl findId 아이디 찾기 : {} &&&&&", uVo);
		return dao.findId(uVo);
	}
	
	@Override
	public int findPw(Users_VO uVo) {
		log.info("&&&&& Users_ServiceImpl findPw 비밀번호 변경 : {} &&&&&", uVo);
		return dao.findPw(uVo);
	}
}
