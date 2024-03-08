package com.scoutcomapss.api.requirement.quiz;

/**
 * @author : Kanchana Kalansooriya
 * @since 3/6/2024
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuizQuestionService {
    @Autowired
    private  QuizQuestionRepository quizQuestionRepository;
    public List<Question> getAllQuestions() {
        List<Question> questionArrayList = quizQuestionRepository.findAll();
        return questionArrayList;

    }
    public List<Question> getQuestionsByAwardIdAndRequirementId(Integer awardId , Integer RequirementId) {
        List<Question> questionArrayList = quizQuestionRepository.findByAwardIdAndRequirementId(awardId,RequirementId);
        return questionArrayList;

    }
}
