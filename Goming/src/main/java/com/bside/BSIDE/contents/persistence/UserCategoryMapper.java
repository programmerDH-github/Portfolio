package com.bside.BSIDE.contents.persistence;

import com.bside.BSIDE.contents.domain.UserCategoryDto;

public interface UserCategoryMapper {
	void resetUserCategories();
	void insertUserCategory(UserCategoryDto userCategoryDto);
}
