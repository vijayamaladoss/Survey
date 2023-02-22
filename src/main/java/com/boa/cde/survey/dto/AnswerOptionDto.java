package com.boa.cde.survey.dto;

import com.boa.cde.survey.domain.AnswerOption;

public class AnswerOptionDto {

    private Long id;
    private String text;

    public AnswerOptionDto(AnswerOption answerOption) {
        this.id = answerOption.getId();
        this.text = answerOption.getText();
    }

    // getters and setters

}