package com.bside.BSIDE.service;

import java.util.List;

import com.bside.BSIDE.contents.domain.AnswerDto;

public interface AnswerService {
	void selectedQuestion(int qNo, String aWriter);
	List<AnswerDto> getUnansweredAnswers();
	void saveAnswer(AnswerDto answerDto);
}
