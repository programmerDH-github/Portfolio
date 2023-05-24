package com.bside.BSIDE.service;

import java.util.List;

import com.bside.BSIDE.contents.domain.CategoryDto;

public interface CategoryService {
	CategoryDto getRandomCategory(int userId);
}
