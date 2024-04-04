package com.bside.BSIDE.contents.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.contents.domain.AnswerDto;
import com.bside.BSIDE.contents.domain.CategoryDto;
import com.bside.BSIDE.contents.domain.QuestionDto;
import com.bside.BSIDE.contents.domain.UserCategoryDto;
import com.bside.BSIDE.service.AnswerService;
import com.bside.BSIDE.service.CategoryService;
import com.bside.BSIDE.service.QuestionService;
import com.bside.BSIDE.service.UserCategoryService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @CategoryController
 * @작성자 DongHun
 * @일자 2023.04.27.
 **/

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
	private final CategoryService categoryService;
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserCategoryService userCategoryService;

    @Autowired
    public CategoryController(CategoryService categoryService, QuestionService questionService, AnswerService answerService, UserCategoryService userCategoryService) {
        this.categoryService = categoryService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.userCategoryService = userCategoryService;
    }
    
    @GetMapping("/select")
    @Operation(summary = "카테고리 조회")
    public ResponseEntity<?> getRandomCategory(@RequestParam(value = "email") String email) {
    	CategoryDto category = categoryService.getRandomCategory(email);
    	
    	int count = selectCaterogyCount(email);
    	
    	if(count == 3) {
    		String message = String.format("오늘의 카테고리 선택을 모두 진행하였습니다. ");
    		return ResponseEntity.ok(message);
    	}
    	
        if (category != null) {
        	System.out.println(category.getCategoryName());        	
        	
        	List<QuestionDto> questions = getQuestionByCategory(category.getCategoryName());      	
        	
        	
            return ResponseEntity.ok(questions);
        } else {
        	String message = String.format("3개의 카테고리를 모두 뽑았습니다.");
            return ResponseEntity.ok(message);
        }
        
    }
    
    /* 질문 조회 */
    public List<QuestionDto> getQuestionByCategory(String category) {
    	System.out.println("getQuestionByCategory");
    	// 오늘 생성된 답변되지 않은 질문 조회
        List<AnswerDto> unansweredAnswers = answerService.getUnansweredAnswers();
        
        // 만약 오늘 생성된 답변되지 않은 질문이 있다면 해당 질문 출력
        if (!unansweredAnswers.isEmpty()) {
            List<QuestionDto> unansweredQuestions = new ArrayList<>();
            for (AnswerDto answer : unansweredAnswers) {
                QuestionDto question = questionService.getQuestionByPNO(answer.getQNo());
                unansweredQuestions.add(question);
                
            }
            return unansweredQuestions;
        }
        
        System.out.println(questionService.getQuestionByCategory(category));
        // 답변되지 않은 질문이 없다면 전체 질문 조회        
        return questionService.getQuestionByCategory(category);
    }
    
    /* 카테고리 저장 */
    public void insertUserCategory(@RequestBody UserCategoryDto userCategoryDto) {
        userCategoryService.insertUserCategory(userCategoryDto);
    }
    
    /* UserCategory 에 저장된 개수 */
    public Integer selectCaterogyCount(@RequestParam(value = "email") String email) {
    	Integer count = categoryService.selectCaterogyCount(email);
    	return count;
    }
    
}
