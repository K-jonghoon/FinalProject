package com.min.edu.model.service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.min.edu.model.mapper.IMediChart_Dao;
import com.min.edu.vo.FileBoard_VO;
import com.min.edu.vo.MediChart_VO;
import com.min.edu.vo.MediCode_VO;
import com.min.edu.vo.PetsInfo_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MediChart_ServiceImpl implements IMediChart_Service {
	
	@Autowired
	private IMediChart_Dao dao;
	
	@Override
	public int countPet(String id) {
		log.info("&&&&& MediChart_ServiceImpl countPet 전달받은 파라미터값 : {} &&&&&", id);
		return dao.countPet(id);
	}

	@Override
	public List<PetsInfo_VO> searchPet(String id) {
		log.info("&&&&& MediChart_ServiceImpl searchPet 전달받은 파라미터값 : {} &&&&&", id);
		return dao.searchPet(id);
	}

	@Override
	public int insertNewPet(PetsInfo_VO pvo) {
		log.info("&&&&& MediChart_ServiceImpl insertNewPet 전달받은 파라미터값 : {} &&&&&", pvo);
		return dao.insertNewPet(pvo);
	}

	@Override
	public int deletePet(int pet_seq) {
		log.info("&&&&& MediChart_ServiceImpl deletePet 전달받은 파라미터값 : {} &&&&&", pet_seq);
		return dao.deletePet(pet_seq);
	}
	
	@Override
	public String insertNewChart(MediChart_VO mvo) {
		log.info("&&&&& MediChart_ServiceImpl insertNewChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", mvo);
		int n = dao.insertNewChart(mvo);
		String m = (n>0)?dao.getMaxSeq():"";
		return m;
	}

//	@Override
//	public List<PetsInfo_VO> selectAllChart(String medi_id) {
//		log.info("&&&&& MediChart_ServiceImpl selectAllChart &&&&&");
//		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", medi_id);
//		return dao.selectAllChart(medi_id);
//	}

	@Override
	public List<PetsInfo_VO> selectPetChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectAllChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.selectPetChart(map);
	}

	@Override
	public List<MediChart_VO> selectLChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectLChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.selectLChart(map);
	}

	@Override
	public List<PetsInfo_VO> selectSChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectSChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.selectSChart(map);
	}

	@Override
	public PetsInfo_VO selectOneChart(String medi_num) {
		log.info("&&&&& MediChart_ServiceImpl selectOneChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", medi_num);
		return dao.selectOneChart(medi_num);
	}

	@Override
	public int modifyChart(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl modifyChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.modifyChart(map);
	}

	@Override
	public int deleteChart(String medi_num) {
		log.info("&&&&& MediChart_ServiceImpl deleteChart &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", medi_num);
		return dao.deleteChart(medi_num);
	}

	@Override
	public List<MediCode_VO> selectAllMediCode() {
		log.info("&&&&& MediChart_ServiceImpl selectAllMediCode &&&&&");
		return dao.selectAllMediCode();
	}

	@Override
	public MediCode_VO searchMediName(String medi_code) {
		log.info("&&&&& MediChart_ServiceImpl searchMediName &&&&&");
		return dao.searchMediName(medi_code);
	}

	@Override
	public String getDetail(String medi_num) {
		log.info("&&&&& MediChart_ServiceImpl getDetail &&&&&");
		return dao.getDetail(medi_num);
	}

	@Override
	public List<PetsInfo_VO> selectAllChartPaging(Map<String, Object> map) {
		log.info("&&&&& MediChart_ServiceImpl selectAllChartPaging &&&&&");
		return dao.selectAllChartPaging(map);
	}

	@Override
	public int chartAllCount(String medi_id) {
		log.info("&&&&& MediChart_ServiceImpl chartAllCount &&&&&");
		return dao.chartAllCount(medi_id);
	}

}
