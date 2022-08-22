package com.example.demo.entity;

import java.time.*;

import lombok.*;

@Getter
@Builder
@ToString
public class Board {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime writeTime;
	private Integer readCnt;
	private Integer goodCnt;
	private Integer badCnt;
	private Integer commentCnt;
	
	// 글쓴이를 추가하는 메소드
	// 필드를 변경하는 애매한 의미를 가진 setter 대신 명확한 역할을 가진 메소드를 사용 
	public Board addWriter(String loginId) {
		this.writer = loginId;
		return this;
	}
}