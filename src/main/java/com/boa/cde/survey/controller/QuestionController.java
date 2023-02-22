package com.boa.cde.survey.controller;

import com.boa.cde.survey.domain.Question;
import com.boa.cde.survey.dto.QuestionDto;
import com.boa.cde.survey.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public List<QuestionDto> getAllQuestionsWithAnswerOptions() {
        List<Question> questions = questionService.findAllQuestionsWithAnswerOptions();
        List<QuestionDto> questionDtos = questions.stream()
                .map(question -> new QuestionDto(question))
                .collect(Collectors.toList());
        return questionDtos;
    }

}

