package com.boa.cde.survey.repository;

import com.boa.cde.survey.domain.AnswerOption;
import com.boa.cde.survey.domain.Category;
import com.boa.cde.survey.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(Category category);
    List<Question> findByDependentAnswerOption(AnswerOption answerOption);

    @Query("SELECT q FROM Question q JOIN q.category c")
    List<Question> findAllQuestions();

    //Eagerly g
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.answerOptions")
    List<Question> findAllQuestionsWithAnswerOptions();
}