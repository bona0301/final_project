package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDao;
import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public Board write(BoardDto.Write dto, String loginId) {
		Board board = dto.toEntity().addWriter(loginId);
		boardDao.save(board);
		return board;
	}
}
