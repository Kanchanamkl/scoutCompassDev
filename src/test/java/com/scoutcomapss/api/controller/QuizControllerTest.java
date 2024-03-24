package com.scoutcomapss.api.controller;

import com.scoutcomapss.api.requirement.quiz.Question;
import com.scoutcomapss.api.requirement.quiz.QuizQuestionService;
import com.scoutcomapss.api.requirement.quiz.QuizController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class QuizControllerTest {

    @Mock
    private QuizQuestionService quizQuestionService;

    @InjectMocks
    private QuizController quizController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllQuestions() {
        // Given
        List<Question> expectedQuestions = new ArrayList<>();

        Question question1 = new Question(1L,1,1,"QUESTION_TEXT1","ANS1","ANS2","ANS3","ANS4","CORRECT_ANS");
        expectedQuestions.add(question1);

        // When
        when(quizQuestionService.getAllQuestions()).thenReturn(expectedQuestions);
        ResponseEntity<?> responseEntity = quizController.getAllQuestions();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedQuestions, responseEntity.getBody());
        verify(quizQuestionService, times(1)).getAllQuestions();
    }

    @Test
    void testGetQuestions() {
        // Given
        int awardId = 1;
        int requirementId = 1;
        List<Question> expectedQuestions = new ArrayList<>();
        Question question1 = new Question(1L,1,1,"QUESTION_TEXT1","ANS1","ANS2","ANS3","ANS4","CORRECT_ANS");

        expectedQuestions.add(question1);


        // When
        when(quizQuestionService.getQuestionsByAwardIdAndRequirementId(awardId, requirementId)).thenReturn(expectedQuestions);
        ResponseEntity<?> responseEntity = quizController.getQuestions(awardId, requirementId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedQuestions, responseEntity.getBody());
        verify(quizQuestionService, times(1)).getQuestionsByAwardIdAndRequirementId(awardId, requirementId);
    }
}
