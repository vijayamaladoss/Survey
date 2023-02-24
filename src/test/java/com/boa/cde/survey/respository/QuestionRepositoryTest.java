package com.boa.cde.survey.respository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.transaction.Transactional;

import com.boa.cde.survey.domain.AnswerOption;
import com.boa.cde.survey.domain.Category;
import com.boa.cde.survey.domain.Question;
import com.boa.cde.survey.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @Transactional
    public void testCreateQuestion() {
        // Create a new category
        Category category = new Category();
        category.setName("Test Category");

        // Create a new question
        Question question = new Question();
        question.setText("Test Question");
        question.setCategory(category);

        // Create a new answer option
        AnswerOption answerOption = new AnswerOption();
        answerOption.setText("Test Answer Option");
        answerOption.setQuestion(question);
        //answerOption.setDependentQuestion(true);

        // Save the question and answer option
        questionRepository.save(question);

        // Check that the question and answer option were saved correctly
        assertNotNull(question.getId());
        assertNotNull(answerOption.getId());
        assertEquals(question, answerOption.getQuestion());
    }

    @Test
    @Transactional
    public void testUpdateQuestion() {
        // Create a new question
        Question question = new Question();
        question.setText("Test Question");
        question = questionRepository.save(question);

        // Update the question
        question.setText("Updated Test Question");
        questionRepository.save(question);

        // Check that the question was updated correctly
        Question updatedQuestion = questionRepository.findById(question.getId()).orElse(null);
        assertNotNull(updatedQuestion);
        assertEquals("Updated Test Question", updatedQuestion.getText());
    }

    @Test
    @Transactional
    public void testDeleteQuestion() {
        // Create a new question
        Question question = new Question();
        question.setText("Test Question");
        question = questionRepository.save(question);

        // Delete the question
        questionRepository.delete(question);

        // Check that the question was deleted
        assertNull(questionRepository.findById(question.getId()).orElse(null));
    }

    @Test
    @Transactional
    public void testFindAllQuestions() {
        // Create some test data
        Question question1 = new Question();
        question1.setText("Test Question 1");
        questionRepository.save(question1);

        Question question2 = new Question();
        question2.setText("Test Question 2");
        questionRepository.save(question2);

        // Find all questions
        List<Question> questions = questionRepository.findAll();

        // Check that the correct number of questions were found
        assertEquals(2, questions.size());
        assertEquals(question1, questions.get(0));
        assertEquals(question2, questions.get(1));
    }

    @Test
    @Transactional
    public void testFindQuestionById() {
        // Create a new question
        Question question = new Question();
        question.setText("Test Question");
        question = questionRepository.save(question);

        // Find the question by ID
        Question foundQuestion = questionRepository.findById(question.getId()).orElse(null);

        // Check that the correct question was found
        assertNotNull(foundQuestion);
        assertEquals(question, foundQuestion);
    }
}