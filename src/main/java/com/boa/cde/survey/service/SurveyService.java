package com.boa.cde.survey.service;


import com.boa.cde.survey.dto.CategoryDTO;
import com.boa.cde.survey.dto.QuestionDTO;
import com.boa.cde.survey.entity.Category;
import com.boa.cde.survey.entity.Question;

import java.util.List;

public interface SurveyService {

public List<QuestionDTO> getAllQuestionsAndAnswerOptionsForCategory(Long categoryID);

public List<CategoryDTO> getAllQuestionAndAnswerOptions();

public QuestionDTO createQuestion(Question question);

public QuestionDTO createQuestionWithDependentAnswerOption(Question question, String answerOptionName);

public CategoryDTO createCategory(Category category);


}
