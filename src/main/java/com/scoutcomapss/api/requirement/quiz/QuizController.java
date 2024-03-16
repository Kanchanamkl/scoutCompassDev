package com.scoutcomapss.api.requirement.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Kanchana Kalansooriya
 * @since 3/6/2024
 */

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/scoutcompass/requirement")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class QuizController {
    private final QuizQuestionService quizQuestionService;

    @GetMapping("/questionList")
    public ResponseEntity<?> getAllQuestions() {
        List<Question> questionArrayList = quizQuestionService.getAllQuestions();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(questionArrayList);
    }

    @GetMapping("/questions")
    public ResponseEntity<?> getQuestions(  @RequestParam Integer awardId, @RequestParam Integer requirementId) {
        List<Question> questionArrayList = quizQuestionService.getQuestionsByAwardIdAndRequirementId(awardId,requirementId );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(questionArrayList);
    }

}
