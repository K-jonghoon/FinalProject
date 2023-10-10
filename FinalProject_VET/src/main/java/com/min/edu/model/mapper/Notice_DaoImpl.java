package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.NoticeBoard_VO;

@Repository
public class Notice_DaoImpl implements INotice_Dao {
	
	private static final String NS = "com.min.edu.model.mapper.Notice_DaoImpl.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<NoticeBoard_VO> selectNotice() {
		return sqlSession.selectList(NS+"selectNotice");
	}

	@Override
	public List<NoticeBoard_VO> selectAllNotice() {
		return sqlSession.selectList(NS+"selectAllNotice");
	}

	@Override
	public NoticeBoard_VO selectNoticeDetail(String noti_seq) {
		return sqlSession.selectOne(NS+"selectNoticeDetail", noti_seq);
	}

	@Override
	public int insertNotice(NoticeBoard_VO nVo) {
		return sqlSession.insert(NS+"insertNotice", nVo);
	}

	@Override
	public int modifyNotice(NoticeBoard_VO nVo) {
		return sqlSession.update(NS+"modifyNotice", nVo);
	}

	@Override
	public int deleteNotice(String noti_seq) {
		return sqlSession.delete(NS+"deleteNotice", noti_seq);
	}

}
