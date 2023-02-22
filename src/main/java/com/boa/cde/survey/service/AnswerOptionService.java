package com.boa.cde.survey.service;

import com.boa.cde.survey.domain.AnswerOption;
import com.boa.cde.survey.repository.AnswerOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerOptionService {

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    public AnswerOption getAnswerOptionById(Long id) {
        return answerOptionRepository.findById(id).orElse(null);
    }

    public List<AnswerOption> getAllAnswerOptions() {
        return answerOptionRepository.findAll();
    }

    public AnswerOption saveAnswerOption(AnswerOption answerOption) {
        return answerOptionRepository.save(answerOption);
    }

    public void deleteAnswerOption(Long id) {
        answerOptionRepository.deleteById(id);
    }

}