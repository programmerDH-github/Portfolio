package com.bside.BSIDE.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bside.BSIDE.contents.domain.QuestionDto;
import com.bside.BSIDE.contents.persistence.QuestionMapper;

@Service
public class QuestionServiceImpl implements QuestionService {

	private final QuestionMapper questionMapper;

	public QuestionServiceImpl(QuestionMapper questionMapper) {
		this.questionMapper = questionMapper;
	}

	@Override
	public void insertQuestion(QuestionDto questionDto) {
		questionMapper.insertQuestion(questionDto);
	}
	
	@Override
    public List<QuestionDto> getQuestionByCategory(String category) {
        return questionMapper.getQuestionByCategory(category);
    }
	
	@Override
    public QuestionDto getQuestionByPNO(int pNo) {
        return questionMapper.getQuestionByPNO(pNo);
    }
	

	@Override
	public int countUnansweredQuestions() {
		return questionMapper.countUnansweredQuestions();
	}

	@Override
	public int countAnsweredQuestionsThisMonth() {
		return questionMapper.countAnsweredQuestionsThisMonth();
	}

	@Override
	public int countAnsweredQuestionsToday() {
		return questionMapper.countAnsweredQuestionsToday();
	}

	@Override
	public int countAnsweredQuestionsByMonth(int year, int month) {
		return questionMapper.countAnsweredQuestionsByMonth(year, month);
	}	
	
}
