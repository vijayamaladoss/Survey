package com.boa.cde.survey.service;

import com.boa.cde.survey.entity.AnswerOption;
import com.boa.cde.survey.entity.Category;
import com.boa.cde.survey.entity.Question;
import com.boa.cde.survey.repository.AnswerOptionRepository;
import com.boa.cde.survey.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

    public Question createQuestionWithDependentAnswerOption(Question question, String answerOptionName){
        AnswerOption option = answerOptionRepository.findByAnswerText(answerOptionName);
        question.setDependentAnswerOption(option);
        return questionRepository.save(question);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAllQuestions();
    }

    public List<Question> getAllQuestionsByCategory(Category category) {
        return questionRepository.findByCategory(category);
    }

    public List<Question> getDependentQuestionsByAnswerOption(AnswerOption answerOption) {
        return questionRepository.findByDependentAnswerOption(answerOption);
    }

    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }

    public List<Question> findAllQuestionsWithAnswerOptions() {
        return questionRepository.findAllQuestionsWithAnswerOptions();
    }

    public List<Question> findAllQuestions() {
        return questionRepository.findAllQuestions();
    }


}