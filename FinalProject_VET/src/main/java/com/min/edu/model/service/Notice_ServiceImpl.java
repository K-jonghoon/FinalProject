package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.INotice_Dao;
import com.min.edu.vo.NoticeBoard_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Notice_ServiceImpl implements INotice_Service {

	@Autowired
	private INotice_Dao dao;
	
	@Override
	public List<NoticeBoard_VO> selectNotice() {
		log.info("&&&&& Users_ServiceImpl selectNotice &&&&&");
		return dao.selectNotice();
	}

	@Override
	public List<NoticeBoard_VO> selectAllNotice() {
		log.info("&&&&& Users_ServiceImpl selectAllNotice &&&&&");
		return dao.selectAllNotice();
	}

	@Override
	public NoticeBoard_VO selectNoticeDetail(String noti_seq) {
		log.info("&&&&& Users_ServiceImpl selectNoticeDetail &&&&&");
		return dao.selectNoticeDetail(noti_seq);
	}

	@Override
	public int insertNotice(NoticeBoard_VO nVo) {
		log.info("&&&&& Users_ServiceImpl insertNotice &&&&&");
		return dao.insertNotice(nVo);
	}

	@Override
	public int modifyNotice(NoticeBoard_VO nVo) {
		log.info("&&&&& Users_ServiceImpl modifyNotice &&&&&");
		return dao.modifyNotice(nVo);
	}

	@Override
	public int deleteNotice(String noti_seq) {
		log.info("&&&&& Users_ServiceImpl deleteNotice &&&&&");
		return dao.deleteNotice(noti_seq);
	}

}
