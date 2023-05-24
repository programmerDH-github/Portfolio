package com.bside.BSIDE.contents.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.contents.domain.CategoryDto;
import com.bside.BSIDE.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @CategoryController
 * @작성자 DongHun
 * @일자 2023.04.27.
 **/

@RestController
@RequestMapping("/category")
public class CategoryController {
	private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/select")
    @Operation(summary = "카테고리 조회")
    public ResponseEntity<CategoryDto> getRandomCategory(@RequestParam(value = "userId") int userId) {
    	CategoryDto  category = categoryService.getRandomCategory(userId);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
