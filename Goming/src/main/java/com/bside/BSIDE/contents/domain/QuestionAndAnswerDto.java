package com.bside.BSIDE.contents.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionAndAnswerDto {
	
	@Schema(description = "질문 내용", example = "가장 좋아하는 계절은?")
	private String question;
	
	@Schema(description = "답변 내용", example = "겨울")
    private String answer;
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String date;
	
	@Schema(description = "카테고리 이름", example = "여행")
    private String category;
}
