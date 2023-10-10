package com.test.edu;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.service.IBookMark_Service;
import com.min.edu.vo.BookMark_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BookMark_JUnitTest {

	@Autowired
	private IBookMark_Service service;

//	@Test
	public void countBookmark() {
		String bm_usersid = "elsa@disney.com";
		int n = service.countBookmark(bm_usersid);
		
		assertEquals(n, 1);
	}

//	@Test
	public void selectAllBookmark() {
		String bm_usersid = "elsa@disney.com";
		List<BookMark_VO> lists = service.selectAllBookmark(bm_usersid);
		assertNotNull(lists);
	}
}
