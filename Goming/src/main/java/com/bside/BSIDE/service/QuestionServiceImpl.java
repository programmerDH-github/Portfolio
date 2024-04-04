package com.bside.BSIDE.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bside.BSIDE.contents.domain.CountAnsweredQuestionsByMonthDto;
import com.bside.BSIDE.contents.domain.QuestionAndAnswerDto;
import com.bside.BSIDE.contents.domain.QuestionCountDto;
import com.bside.BSIDE.contents.domain.QuestionDto;
import com.bside.BSIDE.contents.persistence.QuestionMapper;

@Service
public class QuestionServiceImpl implements QuestionService {

	private final QuestionMapper questionMapper;

	public QuestionServiceImpl(QuestionMapper questionMapper) {
		this.questionMapper = questionMapper;
	}

	@Override
	public void updateQuestion(QuestionDto questionDto) {
		questionMapper.updateQuestion(questionDto);
	}

	@Override
	public void deleteQuestion(int qNo) {
		questionMapper.deleteQuestion(qNo);
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
	public int countUnansweredQuestions(String writer) {
		return questionMapper.countUnansweredQuestions(writer);
	}
	
	@Override
	public int countPassQuestions(String writer) {
		return questionMapper.countPassQuestions(writer);
	}

	@Override
	public int countAnsweredQuestionsThisMonth(String writer) {
		return questionMapper.countAnsweredQuestionsThisMonth(writer);
	}

	@Override
	public int countAnsweredQuestionsToday(String writer) {
		return questionMapper.countAnsweredQuestionsToday(writer);
	}

	@Override
	public CountAnsweredQuestionsByMonthDto countAnsweredQuestionsByMonth(String email, int year, int month) {
	    return questionMapper.countAnsweredQuestionsByMonth(email, year, month);
	}
	
	@Override
	public int countAnsweredQuestionsByDay(String email, String date) {
	    return questionMapper.countAnsweredQuestionsByDay(email, date);
	}
	
	@Override
	public List<QuestionCountDto> countAnsweredDatesInMonth(String email, String date) {
		return questionMapper.countAnsweredDatesInMonth(email, date);
	}
	
	@Override
    public List<QuestionAndAnswerDto> getQuestionsAndAnswersByMonthAndEmail(String email, String date) {
        return questionMapper.getQuestionsAndAnswersByMonthAndEmail(email, date);
    }
	
	@Override
    public List<QuestionAndAnswerDto> getQuestionsAndAnswersByDayAndEmail(String email, String date) {
        return questionMapper.getQuestionsAndAnswersByDayAndEmail(email, date);
    }

	@Override
	public List<QuestionDto> selectListQuestion() {
		return questionMapper.selectListQuestion();
	}

}

