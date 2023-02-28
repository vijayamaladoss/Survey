package com.boa.cde.survey.service.impl;


import com.boa.cde.survey.dto.AnswerOptionDTO;
import com.boa.cde.survey.dto.CategoryDTO;
import com.boa.cde.survey.dto.QuestionDTO;
import com.boa.cde.survey.entity.AnswerOption;
import com.boa.cde.survey.entity.Category;
import com.boa.cde.survey.entity.Question;
import com.boa.cde.survey.repository.AnswerOptionRepository;
import com.boa.cde.survey.repository.CategoryRepository;
import com.boa.cde.survey.repository.QuestionRepository;
import com.boa.cde.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SurveySerivceImpl implements SurveyService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerOptionRepository answerRepository;

    @Override
    @Transactional
    public QuestionDTO createQuestion(Question question) {
        return new QuestionDTO(questionRepository.save(question));
    }

    @Override
    @Transactional
    public QuestionDTO createQuestionWithDependentAnswerOption(Question question, String answerOptionName) {
        AnswerOption option = answerRepository.findByAnswerText(answerOptionName);
        question.setDependentAnswerOption(option);
        return new QuestionDTO(questionRepository.save(question));
    }

    @Override
    public CategoryDTO createCategory(Category category) {
        return new CategoryDTO(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public List<QuestionDTO> getAllQuestionsAndAnswerOptionsForCategory(Long categoryID) {
        Category category = categoryRepository.findById(categoryID).get();
        return getQuestionDTOListForCategory(category);
    }

    @Override
    @Transactional
    public List<CategoryDTO> getAllQuestionAndAnswerOptions() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category catEntity : categoryList){
            CategoryDTO categoryDTO = new CategoryDTO(catEntity);
            List<QuestionDTO> questionDTOList = getQuestionDTOListForCategory(catEntity);
            categoryDTO.setQuestions(questionDTOList);
            categoryDTOList.add(categoryDTO);

        }
        return categoryDTOList;

    }

    private List<QuestionDTO> getQuestionDTOListForCategory(Category catEntity) {
        List<Question> questionList = questionRepository.findByCategory(catEntity);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question : questionList){
            QuestionDTO questionDTO = new QuestionDTO(question);
            List<AnswerOption> answerOptionList = answerRepository.findByQuestion(question);
            List<AnswerOptionDTO> answerOptionDTOS = answerOptionList.stream().
                                    map(answerOption -> new AnswerOptionDTO(answerOption)).collect(Collectors.toList());
            questionDTO.setAnswerOptions(answerOptionDTOS);
            if(question.getDependentAnswerOption() != null){
                questionDTO.setDependentAnswerOptionDto(question.getDependentAnswerOption());
            }
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }



}
