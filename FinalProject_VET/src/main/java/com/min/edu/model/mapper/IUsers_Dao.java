package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.Users_VO;

public interface IUsers_Dao {

	// 전체 회원 조회
	public List<Users_VO> selectAllUsers();

	// 권한별 회원조회
	public List<Users_VO> selectUsersAuth(String users_auth);

	//상태별 회원 조회
	public List<Users_VO> selectUsersStatus(String users_status);

	//아이디로 회원 검색
	public List<Users_VO> searchUsers(String keyword);

	//회원상세조회 - 일반사용자
	public List<Users_VO> selectUserDetail(String users_id);

	//회원상세조회 - 병원관계자
	public Users_VO selectHospitalDetail(String users_id);
	
	//로그인
	public Users_VO loginUser(Map<String, Object> map);
	
	//회원 가입(일반사용자)
	public int insertUser(Map<String, Object> map);
	
	//이메일 중복확인(일반사용자)
	public int duplicationId(String email);
	
	//추가정보입력(일반사용자)
	public int addInfoUser(Map<String, Object> map);
	
	//반려동물정보입력
	public int insertPetInfo(Map<String, Object> map);
	
	//회원 가입(병원관계자)
	public int insertHospInfo(Map<String, Object> map);
	
	//추가정보입력(병원관계자)
	public int insertHospInfoDetail(Map<String, Object> map);
	
	//추가정보입력(병원관계자2)
	public int insertHospAnicode(Map<String, Object> map);
	
	//추가정보입력(병원관계자3)
	public int insertHospMedicode(Map<String, Object> map);
	
	//회원 탈퇴
	public int resignUser(Users_VO uVo);
	
	//회원정보수정(일반사용자)
	public int updateUser(Users_VO uVo);

	//회원정보수정(병원관계자)
	public int updateHosp(Map<String, Object> map);
	
	//회원정보수정(병원관계자)2 - 기존 정보 삭제 후 재입력
	public int deleteHospAnicode(Map<String, Object> map);
	
	//회원정보수정(병원관계자)3 - 기존 정보 삭제 후 재입력
	public int deleteHospMedicode(Map<String, Object> map);
	
	//아이디 찾기
	public Users_VO findId(Users_VO uVo);
	
	//비밀번호 변경
	public int findPw(Users_VO uVo);
		
}
