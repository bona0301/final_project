package com.example.demo.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import springfox.documentation.annotations.ApiIgnore;

// 스프링 검증 활성화(자바 표준 검증보다 기능이 더 많다)
@Validated
// Rest만 처리하는 @Controller의 부분 집합
@RestController
public class BoardController {
	@Autowired
	private BoardService service;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="/board/new",produces=MediaType.TEXT_PLAIN_VALUE)
	@Operation(summary="5.글 작성", description="제목과 내용을 입력해 글을 작성")
	@ApiImplicitParams({
			@ApiImplicitParam(name="title",value="제목",required = true, dataTypeClass = String.class),
			@ApiImplicitParam(name="content",value="내용",required = true, dataTypeClass = String.class)
	})
	@ApiResponses({
		@ApiResponse(code=200,response=String.class,message ="글을 읽을 주소"),
		@ApiResponse(code=409,response=String.class,message="오류 메시지")
	})
	
	public ResponseEntity<String> write(@Valid BoardDto.Write dto, 
			BindingResult bindingResult, @ApiIgnore Principal principal){
		Board board = service.write(dto, principal.getName());
		return ResponseEntity.ok("/board/read?bno="+board.getBno());
	}
}
