package com.bside.BSIDE.contents.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bside.BSIDE.contents.domain.AnswerDto;
import com.bside.BSIDE.service.AnswerService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @AnswerController
 * @작성자 DongHun
 * @일자 2023.04.27.
 **/

@RestController
@RequestMapping("/answers")
public class AnswerController {
	private final AnswerService answerService;

	public AnswerController(AnswerService answerService) {
		this.answerService = answerService;
	}

	/* 선택된 질문을 보관하기 */
	@PostMapping("/selectedQuestion")
	@Operation(summary = "선택된 질문 보관")
	public void selectedQuestion(@RequestParam("qNo") int qNo, @RequestParam("aWriter") String aWriter) {
		answerService.selectedQuestion(qNo,aWriter);
	}
	
	/* 질문에 대한 답변 저장하기 */
	@PutMapping("/saveAnswer")
	@Operation(summary = "답변 저장")
	public ResponseEntity<String> saveAnswer(@RequestBody AnswerDto answerDto) {
        answerService.saveAnswer(answerDto);
        return ResponseEntity.ok("답변이 수정되었습니다.");
    }
}
