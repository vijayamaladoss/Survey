package com.boa.cde.survey;

import com.boa.cde.survey.domain.AnswerOption;
import com.boa.cde.survey.domain.Category;
import com.boa.cde.survey.domain.Question;
import com.boa.cde.survey.service.AnswerOptionService;
import com.boa.cde.survey.service.CategoryService;
import com.boa.cde.survey.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class SurveyApplication implements CommandLineRunner {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerOptionService answerOptionService;

    public static void main(String[] args) {
        SpringApplication.run(SurveyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        Category category = new Category();
        category.setName("Architecture");
        Category result  = categoryService.createCategory(category);
        System.out.println("Category Created ==> " + result.getId());
        System.out.println("Category ToString ==> " + result.toString());

        Question question1 = Question.builder().category(category).text("What is Technology Stack?").
                build();
        AnswerOption option1 = AnswerOption.builder().text("Java").build();
        AnswerOption option2 = AnswerOption.builder().text("Dotnet").build();
        AnswerOption option3 = AnswerOption.builder().text("Python").build();

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
        //System.out.println("Option1 ToString ==> " + option1.toString());
        Long dependentAnswerJavaID = option1.getId();

        AnswerOption yesOpt = AnswerOption.builder().text("Yes").build();
        AnswerOption noOpt = AnswerOption.builder().text("No").build();
        Question question2 = Question.builder().category(category).text("Does the application use DMZ?").
                build();
        yesOpt.setQuestion(question2);
        noOpt.setQuestion(question2);
        List yesNoList = new ArrayList<AnswerOption>();
        Collections.addAll(yesNoList, yesOpt, noOpt);
        question2.setAnswerOptions(yesNoList);
        question2.setDependentAnswerOption(null);
        Question result3 = questionService.createQuestion(question2);
        System.out.println("Question2 result ID ==> " + result3.getId());
        System.out.println("Question2 ToString ==> " + result3.toString());

        Question question3 = Question.builder().category(category).text("What is Version of Java Used?").
                build();
        AnswerOption optionJ1 = AnswerOption.builder().text("Java 1.8").build();
        AnswerOption optionJ2 = AnswerOption.builder().text("Java 11").build();
        AnswerOption optionJ3 = AnswerOption.builder().text("Java 17").build();
        optionJ1.setQuestion(question3);
        optionJ2.setQuestion(question3);
        optionJ3.setQuestion(question3);
        List javaVersionList = new ArrayList<AnswerOption>();
        Collections.addAll(javaVersionList, yesOpt, noOpt);
        question3.setAnswerOptions(javaVersionList);
//        AnswerOption javaOption = answerOptionService.getAnswerOptionById(dependentAnswerJavaID);
//        question3.setDependentAnswerOption(javaOption);
        Question result4 = questionService.createQuestionWithDependentAnswerOption(question3, "Java");
        System.out.println("Question3 result ID ==> " + result4.getId());
        System.out.println("Question3 ToString ==> " + result4.toString());

        List<Question> questionList = questionService.getDependentQuestionsByAnswerOption(option1);
        System.out.println("QuestionList toString ==> " + questionList);






//        Question question3 = Question.builder().category(category).text("Does the application use NAS?").
//                answerOptions(yesNoList).dependentAnswerOption(null).build();
//        Question result4 = questionService.createQuestion(question3);
//        System.out.println("Question3 result ID ==> " + result4.getId());
//        System.out.println("Question3 ToString ==> " + result4.toString());




    }
}
