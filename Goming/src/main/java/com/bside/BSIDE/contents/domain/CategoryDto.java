package com.bside.BSIDE.contents.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data	
public class CategoryDto {
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private int categoryId;
	
	@Schema(description = "카테고리 종류", example = "기억")
	private String categoryName;
}
