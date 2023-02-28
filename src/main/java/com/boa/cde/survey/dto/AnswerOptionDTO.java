package com.boa.cde.survey.dto;

import com.boa.cde.survey.entity.AnswerOption;
import lombok.*;

@Getter
@Setter
public class AnswerOptionDTO {

    private Long id;
    private String text;
    private Boolean isDefault;

    public AnswerOptionDTO(AnswerOption answerOption) {
        this.id = answerOption.getId();
        this.text = answerOption.getAnswerText();
        this.isDefault = answerOption.getIsDefault();
    }

    // getters and setters

}