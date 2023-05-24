package com.bside.BSIDE.service;

import java.util.List;

import com.bside.BSIDE.contents.domain.QuestionDto;

public interface QuestionService {
	void insertQuestion(QuestionDto questionDto);
	QuestionDto getQuestionByPNO(int pNo);
	List<QuestionDto> getQuestionByCategory(String category);
	
	int countUnansweredQuestions();
	int countAnsweredQuestionsThisMonth();
	int countAnsweredQuestionsToday();
	int countAnsweredQuestionsByMonth(int year, int month);
}
