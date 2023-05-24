package com.bside.BSIDE.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bside.BSIDE.contents.domain.CategoryDto;
import com.bside.BSIDE.contents.persistence.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService{
	private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
    
    @Override
    public CategoryDto getRandomCategory(int userId) {
        return categoryMapper.getRandomCategory(userId);
    }
}
