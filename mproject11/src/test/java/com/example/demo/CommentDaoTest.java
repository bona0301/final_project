package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CommentDao;
import com.example.demo.entity.Comment;

@SpringBootTest
public class CommentDaoTest {
	@Autowired
	private CommentDao commentDao;
	
	// save 테스트
	//@Test
	public void saveTest() {
		assertEquals(1, commentDao.save(Comment.builder().bno(1).content("yyy").writer("summer").build()));
	}
	
	// findByBno 테스트
	//@Test
	public void findByBnoTest() {
		assertEquals(0, commentDao.findByBno(7).size());
	}
	
	// findWriterByBno 테스트
	//@Test
	public void findWriterByBnoTest() {
		assertEquals(true, commentDao.findWriterById(300).isEmpty());
		assertEquals(true, commentDao.findWriterById(7).isPresent());
	}
	
	// deleteByCno 테스트
	@Transactional
	//@Test
	public void deleteByCnoTest() {
		assertEquals(1, commentDao.deleteByCno(7));
		assertEquals(0, commentDao.deleteByBno(300));
	}
	
	// deleteByBno 테스트
	@Transactional
	@Test
	public void deleteByBnosTest() {
		assertEquals(1, commentDao.deleteByCno(7));
		assertEquals(0, commentDao.deleteByBno(300));
	}
}
