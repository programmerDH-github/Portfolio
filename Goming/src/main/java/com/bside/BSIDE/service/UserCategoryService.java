package com.bside.BSIDE.service;

import com.bside.BSIDE.contents.domain.UserCategoryDto;

public interface UserCategoryService {
	void resetUserCategories();
	void insertUserCategory(UserCategoryDto userCategoryDto);
}
