package com.bside.BSIDE.contents.persistence;

import java.util.List;

import com.bside.BSIDE.contents.domain.CategoryDto;

public interface CategoryMapper {
	CategoryDto getRandomCategory(int userId);
}
