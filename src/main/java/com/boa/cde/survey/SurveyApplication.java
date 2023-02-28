package com.boa.cde.survey;

import com.boa.cde.survey.dto.CategoryDTO;
import com.boa.cde.survey.dto.QuestionDTO;
import com.boa.cde.survey.entity.AnswerOption;
import com.boa.cde.survey.entity.Category;

import com.boa.cde.survey.entity.Question;
import com.boa.cde.survey.service.AnswerOptionService;
import com.boa.cde.survey.service.CategoryService;
import com.boa.cde.survey.service.QuestionService;
import com.boa.cde.survey.service.SurveyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class SurveyApplication implements CommandLineRunner {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerOptionService answerOptionService;

    @Autowired
    private SurveyService surveyService;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(SurveyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category category = new Category();
        category.setName("Prequiste");
        Category result  = categoryService.createCategory(category);
        System.out.println("Category Created ==> " + result.getId());
        System.out.println("Category ToString ==> " + result.toString());

        Question question1 = Question.builder().category(category).questionText("What is Technology Stack?").
                isScoring(Boolean.FALSE).hasQuestionDependent(Boolean.TRUE).build();
        AnswerOption option1 = AnswerOption.builder().answerText("Java").build();
        AnswerOption option2 = AnswerOption.builder().answerText("Dotnet").build();
        AnswerOption option3 = AnswerOption.builder().answerText("Python").build();

        option1.setQuestion(question1);
        option2.setQuestion(question1);
        option3.setQuestion(question1);
        List answerOptionList = new ArrayList<AnswerOption>();
        Collections.addAll(answerOptionList, option1, option2, option3);
        question1.setAnswerOptions(answerOptionList);
        question1.setDependentAnswerOption(null);
        Question result2 = questionService.createQuestion(question1);
        System.out.println("Question1 result ID== > " + result2.getId());
        System.out.println("Question1 ToString ==> " + result2.toString());

        Question question3 = Question.builder().category(category).questionText("What is Version of Java Used?").
                isScoring(Boolean.FALSE).hasQuestionDependent(Boolean.FALSE).build();
        AnswerOption optionJ1 = AnswerOption.builder().answerText("Java 1.8").isDefault(Boolean.TRUE).build();
        AnswerOption optionJ2 = AnswerOption.builder().answerText("Java 11").build();
        AnswerOption optionJ3 = AnswerOption.builder().answerText("Java 17").build();
        optionJ1.setQuestion(question3);
        optionJ2.setQuestion(question3);
        optionJ3.setQuestion(question3);
        List javaVersionList = new ArrayList<AnswerOption>();
        Collections.addAll(javaVersionList, optionJ1, optionJ2, optionJ3);
        question3.setAnswerOptions(javaVersionList);
        Question result4 = questionService.createQuestionWithDependentAnswerOption(question3, "Java");
        System.out.println("Question3 result ID ==> " + result4.getId());
        System.out.println("Question3 ToString ==> " + result4.toString());


        Category category2 = new Category();
        category2.setName("Architecture");
        Category catResult  = categoryService.createCategory(category2);
        System.out.println("Architecture Category Created ==> " + catResult.getId());
        System.out.println("Category ToString ==> " + catResult.toString());

        AnswerOption yesOpt = AnswerOption.builder().answerText("Yes").isDefault(Boolean.FALSE).build();
        AnswerOption noOpt = AnswerOption.builder().answerText("No").build();
        Question question2 = Question.builder().category(catResult).questionText("Does the application use DMZ?")
                .isScoring(Boolean.TRUE).hasQuestionDependent(Boolean.FALSE).build();
        yesOpt.setQuestion(question2);
        noOpt.setQuestion(question2);
        List yesNoList = new ArrayList<AnswerOption>();
        Collections.addAll(yesNoList, yesOpt, noOpt);
        question2.setAnswerOptions(yesNoList);
        question2.setDependentAnswerOption(null);
        Question result3 = questionService.createQuestion(question2);
        System.out.println("Question2 result ID ==> " + result3.getId());
        System.out.println("Question2 ToString ==> " + result3.toString());

        AnswerOption yesOpt1 = AnswerOption.builder().answerText("Yes").isDefault(Boolean.TRUE).build();
        AnswerOption noOpt1 = AnswerOption.builder().answerText("No").build();
        Question questionNAS = Question.builder().category(catResult).questionText("Does the application use NAS?")
                .isScoring(Boolean.TRUE).hasQuestionDependent(Boolean.FALSE).build();
        yesOpt1.setQuestion(questionNAS);
        noOpt1.setQuestion(questionNAS);
        List yesNoList1 = new ArrayList<AnswerOption>();
        Collections.addAll(yesNoList1, yesOpt1, noOpt1);
        questionNAS.setAnswerOptions(yesNoList1);
        questionNAS.setDependentAnswerOption(null);
        Question resultNAS = questionService.createQuestion(questionNAS);
        System.out.println("Question2 result ID ==> " + resultNAS.getId());
        System.out.println("Question2 ToString ==> " + resultNAS.toString());

        ObjectMapper mapper = new ObjectMapper();
        List<CategoryDTO> categoryDTOList = surveyService.getAllQuestionAndAnswerOptions();
        System.out.println(mapper.writeValueAsString(categoryDTOList));


    }
}
