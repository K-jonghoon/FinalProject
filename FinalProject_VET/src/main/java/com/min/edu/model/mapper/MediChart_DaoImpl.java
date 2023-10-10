package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.FileBoard_VO;
import com.min.edu.vo.MediChart_VO;
import com.min.edu.vo.MediCode_VO;
import com.min.edu.vo.PetsInfo_VO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MediChart_DaoImpl implements IMediChart_Dao {
	
	private static final String NS= "com.min.edu.model.mapper.MediChart_DaoImpl.";
	
	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public int countPet(String id) {
		return session.selectOne(NS+"countPet",id);
	}

	@Override
	public List<PetsInfo_VO> searchPet(String id) {
		return session.selectList(NS+"searchPet",id);
	}

	@Override
	public int insertNewPet(PetsInfo_VO pvo) {
		return session.insert(NS+"insertNewPet",pvo);
	}

	@Override
	public int deletePet(int pet_seq) {
		return session.delete(NS+"deletePet",pet_seq);
	}

	@Override
	public int insertNewChart(MediChart_VO mvo) {
		return session.insert(NS+"insertNewChart", mvo);
	}

//	@Override
//	public List<PetsInfo_VO> selectAllChart(String pet_owner) {
//		return session.selectList(NS+"selectAllChart",pet_owner);
//	}

	@Override
	public List<PetsInfo_VO> selectPetChart(Map<String, Object> map) {
		return session.selectList(NS+"selectPetChart",map);
	}

	@Override
	public List<MediChart_VO> selectLChart(Map<String, Object> map) {
		return session.selectList(NS+"selectLChart", map);
	}

	@Override
	public List<PetsInfo_VO> selectSChart(Map<String, Object> map) {
		return session.selectList(NS+"selectSChart", map);
	}

	@Override
	public PetsInfo_VO selectOneChart(String medi_num) {
		return session.selectOne(NS+"selectOneChart", medi_num);
	}

	@Override
	public int modifyChart(Map<String, Object> map) {
		return session.update(NS+"modifyChart", map);
	}

	@Override
	public int deleteChart(String medi_num) {
		return session.delete(NS+"deleteChart",medi_num);
	}

	@Override
	public List<MediCode_VO> selectAllMediCode() {
		return session.selectList(NS+"selectAllMediCode");
	}

	@Override
	public String getMaxSeq() {
		return session.selectOne(NS+"getMaxSeq");
	}

	@Override
	public MediCode_VO searchMediName(String medi_code) {
		return session.selectOne(NS+"searchMediName", medi_code);
	}

	@Override
	public String getDetail(String medi_num) {
		return session.selectOne(NS+"getDetail", medi_num);
	}

	@Override
	public List<PetsInfo_VO> selectAllChartPaging(Map<String, Object> map) {
		return session.selectList(NS+"selectAllChartPaging",map);
	}

	@Override
	public int chartAllCount(String medi_id) {
		return session.selectOne(NS+"chartAllCount",medi_id);
	}

}
