package com.boa.cde.survey.dto;

import com.boa.cde.survey.domain.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionDto {

    private Long id;
    private String text;
    private List<AnswerOptionDto> answerOptions;

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.text = question.getText();
        this.answerOptions = question.getAnswerOptions().stream()
                .map(answerOption -> new AnswerOptionDto(answerOption))
                .collect(Collectors.toList());
    }

    // getters and setters

}