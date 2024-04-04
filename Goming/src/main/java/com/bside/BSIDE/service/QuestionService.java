package com.bside.BSIDE.service;

import java.util.List;

import com.bside.BSIDE.contents.domain.CountAnsweredQuestionsByMonthDto;
import com.bside.BSIDE.contents.domain.QuestionAndAnswerDto;
import com.bside.BSIDE.contents.domain.QuestionCountDto;
import com.bside.BSIDE.contents.domain.QuestionDto;

public interface QuestionService {
	void insertQuestion(QuestionDto questionDto);
	void updateQuestion(QuestionDto questionDto);
	void deleteQuestion(int qNo);
	QuestionDto getQuestionByPNO(int pNo);
	List<QuestionDto> getQuestionByCategory(String category);
	
	int countUnansweredQuestions(String writer);
	int countPassQuestions(String writer);
	int countAnsweredQuestionsThisMonth(String writer);
	int countAnsweredQuestionsToday(String writer);
	CountAnsweredQuestionsByMonthDto countAnsweredQuestionsByMonth(String email, int year, int month);
	int countAnsweredQuestionsByDay(String email, String date);
	List<QuestionCountDto> countAnsweredDatesInMonth(String email, String date); 
	
	List<QuestionAndAnswerDto> getQuestionsAndAnswersByMonthAndEmail(String email, String date);
	List<QuestionAndAnswerDto> getQuestionsAndAnswersByDayAndEmail(String email, String date);

	List<QuestionDto> selectListQuestion();
}
