package com.bside.BSIDE.contents.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class QuestionListOutDto {
    @Schema(description = "현재 페이지 번호", example = "1", nullable = false)
    private Integer pageNo;

    @Schema(description = "페이지당 들어갈 컬럼 수", example = "20", nullable = false)
    private Integer pageSize;

    @Schema(description = "질문 contents", nullable = true)
    private List<QuestionDto> grid;
}
