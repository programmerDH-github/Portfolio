package com.bside.BSIDE.contents.persistence;

import com.bside.BSIDE.contents.domain.CategoryDto;

public interface CategoryMapper {
	CategoryDto getRandomCategory(String email);
	int selectCaterogyCount(String email);
	CategoryDto getCategory(String category);
}
