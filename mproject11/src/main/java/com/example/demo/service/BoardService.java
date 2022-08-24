package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.dao.*;
import com.example.demo.dto.*;
import com.example.demo.dto.BoardDto.*;
import com.example.demo.entity.*;
import com.example.demo.exception.*;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	@Value("${zproject.pagesize}")
	private Integer pagesize;

	public Board write(BoardDto.Write dto, String loginId) {
		Board board = dto.toEntity().addWriter(loginId);
		boardDao.save(board);
		return board;
	}
	
	// 서비스는 업무로직(비즈니스 로직)이 있는 곳
	// 글읽기 : 글이 없으면 409(BNFE). 글이 있고 글쓴이이면 조회수 증가
	public BoardDto.Read read(Integer bno, String loginId) {
		BoardDto.Read dto = boardDao.findById(bno).orElseThrow(()->new BoardNotFoundException());
		if(loginId!=null && dto.getWriter().equals(loginId)==false) {
			boardDao.update(Board.builder().bno(bno).readCnt(1).build());
			dto.setReadCnt(dto.getReadCnt()+1);
		}
		return dto;
	}
	
	// 글목록 : 글이 없으면 빈 목록
	public BoardDto.Page list(Integer pageno, String writer) {
		Integer totalcount = boardDao.count(writer);
		Integer start = (pageno-1) * pagesize + 1;
		Integer end = start * pagesize - 1;
		
		Map<String,Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("writer", writer);
		return new Page(pageno,pagesize,totalcount,boardDao.findAll(map));
	}
	
	// 글변경 : 실패 - 글이 없다(BoardNotFoundException), 글쓴이가 아니다(JobFailException)
	public void update(BoardDto.Update dto, String loginId) {
		String writer = boardDao.findWriterById(dto.getBno()).orElseThrow(()->new BoardNotFoundException());
		if(writer.equals(loginId)==false)
			throw new JobFailException("변경 권한이 없습니다");
		boardDao.update(dto.toEntity());
	}
	
	// 글삭제 : 실패 - 글이 없다(BNFE), 글쓴이가 아니다(JFE)
	public void delete(Integer bno, String loginId) {
		String writer = boardDao.findWriterById(bno).orElseThrow(()->new BoardNotFoundException());
		if(writer.equals(loginId)==false)
			throw new JobFailException("변경 권한이 없습니다");
		boardDao.deleteById(bno);
	}
}
