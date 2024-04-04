package com.bside.BSIDE.contents.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CountAnsweredQuestionsByMonthDto {
    @Schema(description = "이메일", example = "programmer_h@naver.com")
    private String email;
    
    @Schema(description = "답변 개수", example = "8")
    private int count;
    
    @Schema(description = "날짜", example = "2023-05")
    private String date;
}
