package com.example.demo.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JobFailException extends RuntimeException{
	String message;
}
