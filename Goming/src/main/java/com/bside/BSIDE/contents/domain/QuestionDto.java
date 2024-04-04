package com.bside.BSIDE.contents.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionDto {
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private int qNo;
	
	@Schema(description = "질문 내용", example = "나의 친구들이 가지고 있는 공통점이 있다면 무엇인가요?")
	private String qQuestion;

	@Schema(description = "카테고리", example = "취향")
	private String qCategory;

	@Schema(description = "질문 작성자", example = "donghun@naver.com")
	private String qWriter;
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String qCreatedAt;
	
	public int getQNo() {
	    return this.qNo;
	}
}
