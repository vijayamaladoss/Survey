package com.boa.cde.survey.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String questionText;

    @Column(nullable = false)
    private Boolean hasQuestionDependent;

    private String choiceRenderType;

    @Column(nullable = false)
    private Boolean isScoring;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ToString.Exclude
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerOption> answerOptions = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private AnswerOption dependentAnswerOption;

}
