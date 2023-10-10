package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.Users_VO;

@Repository
public class Users_DaoImpl implements IUsers_Dao {
	
	private static final String NS = "com.min.edu.model.mapper.Users_DaoImpl.";
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Users_VO> selectAllUsers() {
		return session.selectList(NS+"selectAllUsers");
	}

	@Override
	public List<Users_VO> selectUsersAuth(String users_auth) {
		return session.selectList(NS+"selectUsersAuth", users_auth);
	}

	@Override
	public List<Users_VO> selectUsersStatus(String users_status) {
		return session.selectList(NS+"selectUsersStatus", users_status);
	}

	@Override
	public List<Users_VO> searchUsers(String keyword) {
		return session.selectList(NS+"searchUsers", keyword);
	}

	@Override
	public List<Users_VO> selectUserDetail(String users_id) {
		return session.selectList(NS+"selectUserDetail", users_id);
	}

	@Override
	public Users_VO selectHospitalDetail(String users_id) {
		return session.selectOne(NS+"selectHospitalDetail",users_id);
	}

	@Override
	public Users_VO loginUser(Map<String, Object> map) {
		return session.selectOne(NS+"loginUser", map);
	}

	@Override
	public int insertUser(Map<String, Object> map) {
		return session.insert(NS+"insertUser", map);
	}

	@Override
	public int duplicationId(String email) {
		return session.selectOne(NS+"duplicationId", email);
	}

	@Override
	public int addInfoUser(Map<String, Object> map) {
		return session.update(NS+"addInfoUser", map);
	}

	@Override
	public int insertPetInfo(Map<String, Object> map) {
		return session.insert(NS+"insertPetInfo", map);
	}

	@Override
	public int insertHospInfo(Map<String, Object> map) {
		return session.insert(NS+"insertHospInfo", map);
	}

	@Override
	public int insertHospInfoDetail(Map<String, Object> map) {
		return session.insert(NS+"insertHospInfoDetail",map);
	}
	
	@Override
	public int insertHospAnicode(Map<String, Object> map) {
		return session.insert(NS+"insertHospAnicode", map);
	}

	@Override
	public int insertHospMedicode(Map<String, Object> map) {
		return session.insert(NS+"insertHospMedicode", map);
	}

	@Override
	public int resignUser(Users_VO uVo) {
		return session.update(NS+"resignUser", uVo);
	}

	@Override
	public int updateUser(Users_VO uVo) {
		return session.update(NS+"updateUser", uVo);
	}
		
	@Override
	public int updateHosp(Map<String, Object> map) {
		return session.update(NS+"updateHospitalDetail", map);
	}
	
	@Override
	public int deleteHospAnicode(Map<String, Object> map) {
		return session.delete(NS+"deleteHospAnicode", map);
	}
	
	@Override
	public int deleteHospMedicode(Map<String, Object> map) {
		return session.delete(NS+"deleteHospMedicode", map);
	}
	
	@Override
	public Users_VO findId(Users_VO uVo) {
		return session.selectOne(NS+"findId", uVo);
	}
	
	@Override
	public int findPw(Users_VO uVo) {
		return session.update(NS+"findPw", uVo);
	}
}
