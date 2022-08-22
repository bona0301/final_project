package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BoardDao;
import com.example.demo.entity.Board;

// JUnit은 단위(unit) 테스트 도구 - 메소드의 동작을 확인
@SpringBootTest
public class BoardDaoTest {
	@Autowired
	private BoardDao boardDao;

	// Test 케이스 1. dao 생성 -> null이 아님
	//@Test
	public void initTest() {
		assertNotNull(boardDao);
	}
	
	// insert, delete, update의 실행 결과는 변경된 행의 개수
	// Test 케이스 2. save : Board -> 결과값이 1이다
	
	// Transaction : 일련의 sql을 모아서 하나의 작업으로 지정
	//			     함께 commit되거나 rollback 되어야 한다(일부분만 동작할 수 없다)
	@Transactional
	//@Test
	public void saveTest() {
		Board board = Board.builder().title("xxx").content("yyy").writer("spring").build();
		assertEquals(1, boardDao.save(board));
	}
	
	// Test 케이스 3. count : count -> 개수를 수동으로 확인해서 assert한다
	//@Test
	public void countTest() {
		assertEquals(15, boardDao.count(null));
	}
	
	// Test 케이스 4. findAll : 글이 14개있다. 11~14까지 4개를 읽어오자
	//@Test
	public void findAllTest() {
		assertEquals(5, boardDao.findAll(null, 11, 15).size());
	}
	
	// Test 케이스 5. (내용,제목), 조회수, 좋아요, 싫어요에 대해 값을 주면 update -> 결과가 0이 아니다
	@Transactional
	//@Test
	public void updateTest() {
		assertNotEquals(0, boardDao.update(Board.builder().bno(7).readCnt(1).build()));
		assertNotEquals(0, boardDao.update(Board.builder().bno(7).goodCnt(1).build()));
		assertNotEquals(0, boardDao.update(Board.builder().bno(7).commentCnt(15).build()));
		assertNotEquals(0, boardDao.update(Board.builder().bno(7).title("변경").content("변경").build()));
	}
	
	// 바람직하지 않은 결과도 테스트해야 한다
	// Test 케이스 6. 6번글을 읽으면 비어있다, 7번글을 읽으면 존재한다
	//@Test
	public void findByIdTest() {
		assertEquals(true, boardDao.findById(6).isEmpty());
		assertEquals(true, boardDao.findById(7).isPresent());
	}
		
	// Test 케이스 7. 6번 글이 없다, 7번 글의 글쓴이는 있다
	//@Test
	public void findWriterTest() {
		assertEquals(true, boardDao.findWriterById(6).isEmpty());
		assertEquals(true, boardDao.findWriterById(7).isPresent());
	}
		
	// Test 케이스 8. 1번 글의 삭제 결과는 0, 2번글의 삭제 결과는 1
	@Transactional
	@Test
	public void deleteByIdTest() {
		assertEquals(0, boardDao.deleteById(6));
		assertEquals(1, boardDao.deleteById(7));
	}
}
