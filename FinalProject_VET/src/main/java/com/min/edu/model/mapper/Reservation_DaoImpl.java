package com.min.edu.model.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.Reservation_VO;

@Repository
public class Reservation_DaoImpl implements IReservation_Dao {

	@Autowired
	private SqlSessionTemplate session;
	
	private static final String NS = "com.min.edu.model.mapper.Reservation_DaoImpl.";

	@Override
	public Map<Object, Object> resrv_monthYNCount(Map<String, Object> map) {
		Map<Object, Object> inMap = new HashMap<Object, Object>();
		inMap.put("lists", session.selectList(NS+"resrv_monthYNCount",map));
		return inMap;
	}

	@Override
	public List<Reservation_VO> resrv_ResrvLists(String resrv_hops) {
		return session.selectList(NS+"resrv_ResrvLists",resrv_hops);
	}

	@Override
	public Reservation_VO resrv_detail(String resrv_num) {
		return session.selectOne(NS+"resrv_detail", resrv_num);
	}

	@Override
	public List<Reservation_VO> resrv_dayStatus(Map<String, Object> map) {
		return session.selectList(NS+"resrv_dayStatus", map);
	}

	@Override
	public int resrv_insert(Reservation_VO rvo) {
		return session.insert(NS+"resrv_insert", rvo);
	}

	@Override
	public int resrv_updateToY(String resrv_num) {
		return session.update(NS+"resrv_updateToY",resrv_num);
	}

	@Override
	public int resrv_updateToN(String resrv_num) {
		return session.update(NS+"resrv_updateToN",resrv_num);
	}
	
	@Override
	public int resrv_updateToX(String resrv_num) {
		return session.update(NS+"resrv_updateToX",resrv_num);
	}

	@Override
	public int resrv_delete(String resrv_num) {
		return session.delete(NS+"resrv_delete",resrv_num);
	}

	@Override
	public Hospital_VO resrv_reqPage(String hosp_id) {
		return session.selectOne(NS+"resrv_reqPage",hosp_id);
	}

	@Override
	public List<Reservation_VO> resrv_reqCal(String resrv_hops) {
		return session.selectList(NS+"resrv_reqCal", resrv_hops);
	}

	@Override
	public String resrv_resultNum() {
		return session.selectOne(NS+"resrv_resultNum");
	}

	@Override
	public List<Reservation_VO> resrv_scheduler() {
		return session.selectList(NS+"resrv_scheduler");
	}

	@Override
	public List<Reservation_VO> resrv_recordList(Map<String, Object> map) {
		return session.selectList(NS+"resrv_recordList",map);
	}

	@Override
	public int resrv_recordListCnt(Map<String, Object> map) {
		return session.selectOne(NS+"resrv_recordListCnt",map);
	}

	@Override
	public String hosp_name(String hosp_id) {
		return session.selectOne(NS+"hosp_name",hosp_id);
	}

	@Override
	public Hospital_VO hosp_runTime(String hosp_id) {
		return session.selectOne(NS+"hosp_runTime", hosp_id);
	}

	@Override
	public int resrv_detailModify(Reservation_VO rvo) {
		return session.update(NS+"resrv_detailModify",rvo);
	}

	


}
