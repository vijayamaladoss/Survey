package com.boa.cde.survey.repository;

import com.boa.cde.survey.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Query("SELECT  c FROM Category c LEFT JOIN FETCH c.questions q LEFT JOIN q.answerOptions GROUP BY c.id")
//    List<Category> findAllCategoriesWithQuestionsAndAnswerOptions();
}
