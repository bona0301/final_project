package com.example.demo.controller.advice;

import java.sql.*;

import javax.validation.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.*;

@RestControllerAdvice
public class BoardControllerAdvice {
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<String> sQLException(SQLException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(BoardNotFoundException.class)
	public ResponseEntity<String> BNFExption() {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("게시물을 찾을 수 없습니다");
	}
	
	@ExceptionHandler(JobFailException.class)
	public ResponseEntity<String> JFExption(JobFailException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(e.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> constraintViolationException() {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("데이터가 누락되었습니다");
	}
}