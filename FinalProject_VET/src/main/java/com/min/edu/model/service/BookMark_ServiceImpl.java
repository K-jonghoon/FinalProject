package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IBookMark_Dao;
import com.min.edu.vo.BookMark_VO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookMark_ServiceImpl implements IBookMark_Service {
	
	@Autowired
	private IBookMark_Dao dao;
	
	@Override
	public int countBookmark(String bm_usersid) {
		log.info("&&&&& BookMark_ServiceImpl countBookmark 전달받은 파라미터 값 : {} &&&&&", bm_usersid);
		return dao.countBookmark(bm_usersid);
	}

	@Override
	public List<BookMark_VO> selectAllBookmark(String bm_usersid) {
		log.info("&&&&& BookMark_ServiceImpl selectAllBookmark 전달받은 파라미터 값 : {} &&&&&", bm_usersid);
		return dao.selectAllBookmark(bm_usersid);
	}

	@Override
	public int insertNewBookmark(BookMark_VO bvo) {
		log.info("&&&&& BookMark_ServiceImpl insertNewBookmark 전달받은 파라미터 값 : {} &&&&&", bvo);
		return dao.insertNewBookmark(bvo);
	}

	@Override
	public int deleteBookmark(BookMark_VO bvo) {
		log.info("&&&&& BookMark_ServiceImpl deleteBookmark 전달받은 파라미터 값 : {} &&&&&", bvo);
		return dao.deleteBookmark(bvo);
	}

	@Override
	public int bookMarkYorN(Map<String, Object> map) {
		log.info("&&&&& BookMark_ServiceImpl deleteBookmark 전달받은 파라미터 값 : {} &&&&&", map);
		return dao.bookMarkYorN(map);
	}

}
