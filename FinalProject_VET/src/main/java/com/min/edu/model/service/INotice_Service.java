package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.NoticeBoard_VO;

public interface INotice_Service {
	
	//전체 공지 조회
	public List<NoticeBoard_VO> selectNotice();

	//삭제공지 포함 전체 공지 조회
	public List<NoticeBoard_VO> selectAllNotice();
	
	//상세 공지 조회
	public NoticeBoard_VO selectNoticeDetail(String noti_seq);
	
	//공지 등록
	public int insertNotice(NoticeBoard_VO nVo);
	
	//공지 수정
	public int modifyNotice(NoticeBoard_VO nVo);
	
	//공지 삭제
	public int deleteNotice(String noti_seq);
}
