package com.bside.BSIDE.service;

import org.springframework.stereotype.Service;

import com.bside.BSIDE.contents.domain.UserCategoryDto;
import com.bside.BSIDE.contents.persistence.UserCategoryMapper;

@Service
public class UserCategoryServiceImpl implements UserCategoryService{
	private final UserCategoryMapper userCategoryMapper;

    public UserCategoryServiceImpl(UserCategoryMapper userCategoryMapper) {
        this.userCategoryMapper = userCategoryMapper;
    }

    @Override
    public void resetUserCategories() {
        userCategoryMapper.resetUserCategories();
    }
    
    @Override
    public void insertUserCategory(UserCategoryDto userCategoryDto) {
    	userCategoryMapper.insertUserCategory(userCategoryDto);
    }
}
