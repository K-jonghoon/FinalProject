package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IReservation_Dao;
import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.Reservation_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Reservation_ServiceImpl implements IReservation_Service {

	@Autowired
	private IReservation_Dao dao;

	@Override
	public Map<Object, Object> resrv_monthYNCount(Map<String, Object> map) {
		log.info("&&&&& Reservation_ServiceImpl resrv_monthYNCount 실행 &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.resrv_monthYNCount(map);
	}

	@Override
	public List<Reservation_VO> resrv_ResrvLists(String resrv_hops) {
		log.info("&&&&& Reservation_ServiceImpl resrv_ResrvLists &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_hops);
		return dao.resrv_ResrvLists(resrv_hops);
	}

	@Override
	public Reservation_VO resrv_detail(String resrv_num) {
		log.info("&&&&& Reservation_ServiceImpl resrv_detail &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
		return dao.resrv_detail(resrv_num);
	}

	@Override
	public List<Reservation_VO> resrv_dayStatus(Map<String, Object> map) {
		log.info("&&&&& Reservation_ServiceImpl resrv_dayStatus &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.resrv_dayStatus(map);
	}

	@Override
	public String resrv_insert(Reservation_VO rvo) {
		log.info("&&&&& Reservation_ServiceImpl resrv_insert &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", rvo);
		int n = dao.resrv_insert(rvo);
		String resrv_num = (n>0)?dao.resrv_resultNum():"";
		return resrv_num;
	}

	@Override
	public int resrv_updateToY(String resrv_num) {
		log.info("&&&&& Reservation_ServiceImpl resrv_updateToY &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
		return dao.resrv_updateToY(resrv_num);
	}

	@Override
	public int resrv_updateToN(String resrv_num) {
		log.info("&&&&& Reservation_ServiceImpl resrv_updateToN &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
		return dao.resrv_updateToN(resrv_num);
	}
	
	@Override
	public int resrv_updateToX(String resrv_num) {
		log.info("&&&&& Reservation_ServiceImpl resrv_updateToX &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
		return dao.resrv_updateToX(resrv_num);
	}

	@Override
	public int resrv_delete(String resrv_num) {
		log.info("&&&&& Reservation_ServiceImpl resrv_delete &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_num);
		//예약 거절로 인한 삭제시 사용자에게 거절 이벤트 구현
		return dao.resrv_delete(resrv_num);
	}

	@Override
	public Hospital_VO resrv_reqPage(String hosp_id) {
		log.info("&&&&& Reservation_ServiceImpl resrv_reqPage &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", hosp_id);
		return dao.resrv_reqPage(hosp_id);
	}

	@Override
	public List<Reservation_VO> resrv_reqCal(String resrv_hops) {
		log.info("&&&&& Reservation_ServiceImpl resrv_reqCal &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", resrv_hops);
		return dao.resrv_reqCal(resrv_hops);
	}

	@Override
	public void resrv_scheduleDelete() {
		log.info("&&&&& 스케줄러 작동 2시간 경과된 예약대기 목록 삭제 &&&&&");
		//예약대기 상태가 2시간 이상 경과된 예약목록 list
		List<Reservation_VO> resrv_wlists = dao.resrv_scheduler();
		if(resrv_wlists.isEmpty()) {
			return;
		}else {
			for (Reservation_VO rvo : resrv_wlists) {
				dao.resrv_delete(rvo.getResrv_num()); 
			}
		}
	}

	@Override
	public List<Reservation_VO> resrv_recordList(Map<String, Object> map) {
		log.info("&&&&& Reservation_ServiceImpl resrv_recordList &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.resrv_recordList(map);
	}

	@Override
	public int resrv_recordListCnt(Map<String, Object> map) {
		log.info("&&&&& Reservation_ServiceImpl resrv_recordListCnt &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.resrv_recordListCnt(map);
	}

	@Override
	public String hosp_name(String hosp_id) {
		log.info("&&&&& Reservation_ServiceImpl hosp_name &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", hosp_id);
		return dao.hosp_name(hosp_id);
	}

	@Override
	public Hospital_VO hosp_runTime(String hosp_id) {
		log.info("&&&&& Reservation_ServiceImpl hosp_runTime &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", hosp_id);
		return dao.hosp_runTime(hosp_id);
	}

	@Override
	public int resrv_detailModify(Reservation_VO rvo) {
		log.info("&&&&& Reservation_ServiceImpl hosp_runTime &&&&&");
		log.info("&&&&& 전달받은 파라미터 값 : {} &&&&&", rvo);
		return dao.resrv_detailModify(rvo);
	}

	


	
}
