package com.test.edu;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.service.IChosen_Service;
import com.min.edu.vo.Chosen_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Chosen_JUnitTest {

	@Autowired
	private IChosen_Service service;
	
	@Test
	public void chosen_rankTest() {
		List<Chosen_VO> lists = service.chosen_rank();
		System.out.println(lists);
		assertNotNull(lists);
	}
	
//	@Test
	public void rpy_cnt_chsn() {
		Chosen_VO cvo = service.rpy_cnt_chsn("gana@naver.com");
		System.out.println(cvo);
		assertNotNull(cvo);
	}

}
