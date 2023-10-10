package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.BookMark_VO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BookMark_DaoImpl implements IBookMark_Dao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private static final String NS = "com.min.edu.model.mapper.BookMark_DaoImpl.";
	
	@Override
	public int countBookmark(String bm_usersid) {
		return session.selectOne(NS+"countBookmark",bm_usersid);
	}

	@Override
	public List<BookMark_VO> selectAllBookmark(String bm_usersid) {
		return session.selectList(NS+"selectAllBookmark",bm_usersid);
	}

	@Override
	public int insertNewBookmark(BookMark_VO bvo) {
		return session.insert(NS+"insertNewBookmark",bvo);
	}

	@Override
	public int deleteBookmark(BookMark_VO bvo) {
		return session.delete(NS+"deleteBookmark",bvo);
	}

	@Override
	public int bookMarkYorN(Map<String, Object> map) {
		return session.selectOne(NS+"bookMarkYorN",map);
	}

}
