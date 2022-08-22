package com.example.demo.dto;

import java.time.*;
import java.util.*;

import javax.validation.constraints.*;

import com.example.demo.entity.*;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;

// 입력용 DTO의 경우 입력값 검증을 수행(Java Validataion)
// 출력용 DTO의 경우 Swagger를 통해 필드의 역할을 설명하겠다

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class BoardDto {
	@Data
	@Schema(description = "글 목록 DTO")
	public static class ForList {
		@Schema(description = "글번호", example="123")
		private Integer bno;
		@Schema(description = "글제목")
		private String title;
		@Schema(description = "글쓴이")
		private String writer;
		@Schema(description = "작성시간", example="2022-10-20")
		private LocalDateTime writeTime;
		@Schema(description = "조회수", example="10")
		private Integer readCnt;
		@Schema(description = "댓글수", example="20")
		private Integer commentCnt;
	}
	
	@Data
	@Schema(description = "글 목록과 페이징 정보를 제공하는 DTO")
	public static class Page {
		@Schema(description = "페이지 번호", example="1")
		private Integer pageno;
		@Schema(description = "페이지 크기", example="10")
		private Integer pagesize;
		@Schema(description = "글 개수", example="123")
		private Integer totalcount;
		@Schema(description = "글 목록", example="ForList 객체 배열")
		private Collection<ForList> boardList;
	}
	
	@Data
	@Schema(description = "글 출력 DTO")
	public static class Read {
		@Schema(description = "글번호", example="123")
		private Integer bno;
		@Schema(description = "글제목")
		private String title;
		@Schema(description = "글내용")
		private String content;
		@Schema(description = "글쓴이")
		private String writer;
		@Schema(description = "작성시간", example="2022")
		private LocalDateTime writeTime;
		@Schema(description = "조회수", example="10")
		private Integer readCnt;
		@Schema(description = "좋아요 개수", example="10")
		private Integer goodCnt;
		@Schema(description = "싫어요 개수", example="10")
		private Integer badCnt;
		@Schema(description = "댓글수", example="10")
		private Integer commentCnt;
		@Schema(description = "댓글 목록", example="Comment 객체 배열")
		private List<Comment> comments;
	}
	
	@Data
	@Builder
	public static class Write {
		@NotEmpty(message="제목은 필수입력입니다")
		private String title;
		@NotEmpty(message="내용은 필수입력입니다")
		private String content;
		public Board toEntity() {
			return Board.builder().title(title).content(content).build();
		}
	}
	
	@Data
	@Builder
	public static class Update {
		@NotNull(message="글번호는 필수입력입니다")
		private Integer bno;
		@NotEmpty(message="제목은 필수입력입니다")
		private String title;
		@NotEmpty(message="내용은 필수입력입니다")
		private String content;
		public Board toEntity() {
			return Board.builder().title(title).content(content).bno(bno).build();
		}
	}
}