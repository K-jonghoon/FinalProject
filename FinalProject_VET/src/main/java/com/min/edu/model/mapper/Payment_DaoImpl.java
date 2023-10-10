package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.Payment_VO;
import com.min.edu.vo.Point_VO;

@Repository
public class Payment_DaoImpl implements IPayment_Dao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private static final String NS = "com.min.edu.model.mapper.Payment_DaoImpl.";

	@Override
	public List<Payment_VO> selectAllPayment(String pay_id) {
		return session.selectList(NS+"selectAllPayment",pay_id);
	}

	@Override
	public Payment_VO selectOnePayment(String pay_num) {
		return session.selectOne(NS+"selectOnePayment",pay_num);
	}
	
	@Override
	public int insertNewPayment(Map<String, Object> map) {
		return session.insert(NS+"insertNewPayment",map);
	}

	@Override
	public int canclePayment(String imp_uid) {
		return session.update(NS+"canclePayment",imp_uid);
	}

	@Override
	public int insertNewPnt(Map<String, Object> map) {
		return session.insert(NS+"insertNewPnt",map);
	}

	@Override
	public int usePntOnResrv(String pnt_id) {
		return session.insert(NS+"usePntOnResrv",pnt_id);
	}

	@Override
	public int usePntOnBoard(String pnt_id) {
		return session.insert(NS+"usePntOnBoard",pnt_id);
	}

	@Override
	public int selectAllPnt(String pnt_id) {
		return session.selectOne(NS+"selectAllPnt",pnt_id);
	}

	@Override
	public List<Point_VO> selectPntList(String pnt_id) {
		return session.selectList(NS+"selectPntList",pnt_id);
	}

	@Override
	public Payment_VO searchMID(String imp_uid) {
		return session.selectOne(NS+"searchMID",imp_uid);
	}

	

}
