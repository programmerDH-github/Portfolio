package com.bside.BSIDE.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.bside.BSIDE.contents.domain.AnswerDto;
import com.bside.BSIDE.contents.persistence.AnswerMapper;

@Service
public class AnswerServiceImpl implements AnswerService {
	private final AnswerMapper answerMapper;

    public AnswerServiceImpl(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    
    @Override
    public void selectedQuestion(int qNo, String aWriter) {
    	AnswerDto answerDto = new AnswerDto();
    	answerDto.setQNo(qNo);
        answerDto.setAWriter(aWriter);
        answerMapper.selectedQuestion(answerDto);
    }
    
    @Override
    public List<AnswerDto> getUnansweredAnswers() {
        return answerMapper.getUnansweredAnswers();
    }
    
    @Override
    public boolean saveAnswer(AnswerDto answerDto) {
    	return answerMapper.saveAnswer(answerDto);
    }
    
    @Override
    public void passAnswer(int qNo, String email) {
    	answerMapper.passAnswer(qNo, email);
    }
    
    @Override
    public void deleteUnanswer() {
    	answerMapper.deleteUnanswer();
    }
}
