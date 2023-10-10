package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.Hospital_VO;
import com.min.edu.vo.Reservation_VO;

public interface IReservation_Service {

	//월별 예약확정/예약취소 건수 조회
	public Map<Object, Object> resrv_monthYNCount(Map<String, Object> map);
	
	//해당 월에 등록된 예약자 명단
	public List<Reservation_VO> resrv_ResrvLists(String resrv_hops);
	
	//예약 상세정보를 조회
	public Reservation_VO resrv_detail(String resrv_num);
	
	//일별 예약상태에 따른 조회
	public List<Reservation_VO> resrv_dayStatus(Map<String, Object> map);
	
	
	//예약대기(W)에서 예약확정(Y) 상태로 변경
	public int resrv_updateToY(String resrv_num);
	
	//예약확정(Y) 상태에서 예약취소(N) 상태로 변경
	public int resrv_updateToN(String resrv_num);
	
	//예약대기(W)에서 예약거절(X) 상태로 변경
	public int resrv_updateToX(String resrv_num);
	
	//예약 신청화면에 가져올 병원의 운영정보
	public Hospital_VO resrv_reqPage(String hosp_id);
	
	//예약을 거절/취소한 경우 예약목록에서 삭제
	public int resrv_delete(String resrv_num);
	
	//진료예약 등록
	public String resrv_insert(Reservation_VO rvo);
	
	//사용자 예약신청 시 해당 병원의 예약확정 리스트 (오늘날짜부터 출력됨)
	public List<Reservation_VO> resrv_reqCal(String	resrv_hops);
	
	// 스케쥴러를 통해 대기 2시간 초과되는 예약 삭제
	public void resrv_scheduleDelete();
	
	//사용자 예약신청내역 조회
	public List<Reservation_VO> resrv_recordList(Map<String, Object> map);
	
	//사용자 예약신청내역 카운트(페이징)
	public int resrv_recordListCnt(Map<String, Object> map);
	
	public String hosp_name(String hosp_id);
	
	//예약없는 병원 운영시간,휴무일 가져오기
	public Hospital_VO hosp_runTime(String hosp_id);
	
	//예약수정
	public int resrv_detailModify(Reservation_VO rvo);
	
}
