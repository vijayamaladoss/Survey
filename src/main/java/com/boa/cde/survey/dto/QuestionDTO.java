package com.boa.cde.survey.dto;

import com.boa.cde.survey.entity.AnswerOption;
import com.boa.cde.survey.entity.Question;

import java.util.List;
import java.util.stream.Collectors;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
public class QuestionDTO {

    private Long id;
    private String text;
    private Boolean hasQuestionDependent;
    private String choiceRenderType;
    private Boolean isScoring;

    private List<AnswerOptionDTO> answerOptions;
    private AnswerOptionDTO dependentAnswerOptionDto;

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.text = question.getQuestionText();
        this.hasQuestionDependent = question.getHasQuestionDependent();
        this.choiceRenderType = question.getChoiceRenderType();
        this.isScoring = question.getIsScoring();
//        this.answerOptions = question.getAnswerOptions().stream()
//                .map(answerOption -> new AnswerOptionDTO(answerOption))
//                .collect(Collectors.toList());
    }

    public void setDependentAnswerOptionDto(AnswerOption answerOption) {
        AnswerOptionDTO ansDTO = new AnswerOptionDTO(answerOption);
        this.dependentAnswerOptionDto = ansDTO;
    }

    // getters and setters

}