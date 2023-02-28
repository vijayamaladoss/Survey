package com.boa.cde.survey.repository;

import com.boa.cde.survey.entity.AnswerOption;
import com.boa.cde.survey.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
    List<AnswerOption> findByQuestion(Question question);
    
    AnswerOption findByAnswerText(String answerText);
}