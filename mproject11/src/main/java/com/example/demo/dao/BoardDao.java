package com.example.demo.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.dto.*;
import com.example.demo.entity.*;

// 스프링에서 DAO에 거는 어노테이션 : @Repository
@Mapper
public interface BoardDao {
	// 저장
	public Integer save(Board board);
	
	// 개수
	public Integer count(String writer);
	
	// 페이징
	public List<BoardDto.ForList> findAll(String writer, Integer start, Integer end);
	
	// 업데이트
	public Integer update(Board board);
	
	// 읽기
	public Optional<BoardDto.Read> findById(Integer bno);
	
	// 글 변경, 삭제 전에 글쓴이를 확인 -> 글쓴이가 없다면 글이 존재하지 않는다
	public Optional<String> findWriterById(Integer bno);
	
	// 삭제
	public Integer deleteById(Integer bno);
	
}





