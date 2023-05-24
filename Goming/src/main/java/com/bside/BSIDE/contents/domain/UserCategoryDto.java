package com.bside.BSIDE.contents.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCategoryDto {
	@Schema(description = "유저 아이디", example = "1")
    private int userId;
	
	@Schema(description = "카테고리 아이디", example = "2")
    private int categoryId;
}
