package com.boa.cde.survey.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answerText;

    private Boolean isDefault;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

}
