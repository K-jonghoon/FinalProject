package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.FileBoard_VO;
import com.min.edu.vo.MediChart_VO;
import com.min.edu.vo.MediCode_VO;
import com.min.edu.vo.PetsInfo_VO;

public interface IMediChart_Dao {

	public int countPet(String id);
	
	public List<PetsInfo_VO> searchPet(String id);
	
	public int insertNewPet(PetsInfo_VO pvo);
	
	public int deletePet(int pet_seq);
	
	public int insertNewChart(MediChart_VO mvo);
	
//	public List<PetsInfo_VO> selectAllChart(String pet_owner);
	
	public List<PetsInfo_VO> selectPetChart(Map<String, Object> map);
	
	public List<MediChart_VO> selectLChart(Map<String, Object> map);
	
	public List<PetsInfo_VO> selectSChart(Map<String,Object> map);
	
	public PetsInfo_VO selectOneChart(String medi_num);
	
	public int modifyChart(Map<String, Object> map);
	
	public int deleteChart(String medi_num);
	
	public List<MediCode_VO> selectAllMediCode();
	
	public String getMaxSeq();
	
	public MediCode_VO searchMediName(String medi_code);
	
	public String getDetail(String medi_num);
	
	public List<PetsInfo_VO> selectAllChartPaging(Map<String, Object> map);
	
	public int chartAllCount(String medi_id);
	
}
