package com.bside.BSIDE.contents.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bside.BSIDE.contents.domain.AnswerDto;

@Mapper
public interface AnswerMapper {
	void selectedQuestion(AnswerDto answerDto);
	List<AnswerDto> getUnansweredAnswers();
	boolean saveAnswer(AnswerDto answerDto);
	void passAnswer(int qNo, String email);
	void deleteUnanswer();
}
