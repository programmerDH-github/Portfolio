package com.bside.BSIDE.contents.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionCountDto {
	@Schema(description = "질문 개수", example = "4")
	private int count;
	
	@Schema(description = "날짜", example = "1993-08-04")
	private String date;
}
