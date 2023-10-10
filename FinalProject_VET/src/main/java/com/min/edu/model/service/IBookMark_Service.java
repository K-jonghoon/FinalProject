package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.BookMark_VO;

public interface IBookMark_Service {
	
	public int countBookmark(String bm_usersid);
	
	public List<BookMark_VO> selectAllBookmark(String bm_usersid);
	
	public int insertNewBookmark(BookMark_VO bvo);
	
	public int deleteBookmark(BookMark_VO bvo);
	
	public int bookMarkYorN(Map<String, Object> map);

}
