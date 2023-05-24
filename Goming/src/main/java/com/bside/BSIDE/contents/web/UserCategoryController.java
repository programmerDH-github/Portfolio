package com.bside.BSIDE.contents.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.contents.domain.UserCategoryDto;
import com.bside.BSIDE.service.UserCategoryService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @UserCategoryController
 * @작성자 DongHun
 * @일자 2023.04.28.
 **/

@RestController
@RequestMapping("/user-categories")
public class UserCategoryController {
    private final UserCategoryService userCategoryService;

    public UserCategoryController(UserCategoryService userCategoryService) {
        this.userCategoryService = userCategoryService;
    }
    
    @PostMapping("/insert")
    @Operation(summary = "userCategory 저장")
    public void insertUserCategory(@RequestBody UserCategoryDto userCategoryDto) {
        userCategoryService.insertUserCategory(userCategoryDto);
    }
    
    @PostMapping("/reset")
    @Operation(summary = "리셋")
    public ResponseEntity<String> resetUserCategories() {
        userCategoryService.resetUserCategories();
        return ResponseEntity.ok("User categories reset successful.");
    }
    
    /*    
//  매일 자정에 userCategory DB 리셋 
  @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
  public void resetUserCategories() {
      userCategoryService.resetUserCategories();
      System.out.println("User categories reset successful.");
  }
*/
    
}
