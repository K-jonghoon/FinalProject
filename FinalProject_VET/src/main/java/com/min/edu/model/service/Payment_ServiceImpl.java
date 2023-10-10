package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.edu.model.mapper.IPayment_Dao;
import com.min.edu.vo.Payment_VO;
import com.min.edu.vo.Point_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Payment_ServiceImpl implements IPayment_Service {
	
	@Autowired
	IPayment_Dao dao;
	
	@Override
	public List<Payment_VO> selectAllPayment(String pay_id) {
		log.info("&&&&& Payment_ServiceImpl selectAllPayment &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", pay_id);
		return dao.selectAllPayment(pay_id);
	}
	
	@Override
	public Payment_VO selectOnePayment(String pay_num) {
		log.info("&&&&& Payment_ServiceImpl selectOnePayment &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", pay_num);
		return dao.selectOnePayment(pay_num);
	}

	@Override
	public int insertNewPayment(Map<String, Object> map) {
		log.info("&&&&& Payment_ServiceImpl insertNewPayment &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.insertNewPayment(map);
	}

	//결제완료시 결제금액만큼 포인트 적립 transaction
	@Transactional(readOnly = true)
	@Override
	public int insertPaymentPoint(Map<String, Object> map) {
		log.info("&&&&& Payment_ServiceImpl insertPaymentPoint &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		int m = dao.insertNewPayment(map);
		int n = dao.insertNewPnt(map);
		return (m>0 || n>0)?1:0;
	}

	@Override
	public int canclePayment(String imp_uid) {
		log.info("&&&&& Payment_ServiceImpl canclePayment &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", imp_uid);
		return dao.canclePayment(imp_uid);
	}

	@Override
	public int insertNewPnt(Map<String, Object> map) {
		log.info("&&&&& Payment_ServiceImpl insertNewPnt &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", map);
		return dao.insertNewPnt(map);
	}
	
	@Override
	public int usePntOnResrv(String pnt_id) {
		log.info("&&&&& Payment_ServiceImpl usePntOnResrv &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", pnt_id);
		return dao.usePntOnResrv(pnt_id);
	}

	@Override
	public int usePntOnBoard(String pnt_id) {
		log.info("&&&&& Payment_ServiceImpl usePntOnBoard &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", pnt_id);
		return dao.usePntOnBoard(pnt_id);
	}

	@Override
	public int selectAllPnt(String pnt_id) {
		log.info("&&&&& Payment_ServiceImpl selectAllPnt &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", pnt_id);
		int point = dao.selectAllPnt(pnt_id);
		return (point > 0)?point:0;
	}

	@Override
	public List<Point_VO> selectPntList(String pnt_id) {
		log.info("&&&&& Payment_ServiceImpl selectPntList &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", pnt_id);
		return dao.selectPntList(pnt_id);
	}

	@Override
	public Payment_VO searchMID(String imp_uid) {
		log.info("&&&&& Payment_ServiceImpl searchMID &&&&&");
		log.info("&&&&& 전달받은 파라미터값 : {} &&&&&", imp_uid);
		return dao.searchMID(imp_uid);
	}

	

}
