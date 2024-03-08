package com.scoutcomapss.api.requirement.quiz;

/**
 * @author : Kanchana Kalansooriya
 * @since 3/6/2024
 */
import com.scoutcomapss.api.resource.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface QuizQuestionRepository extends JpaRepository<Question, Long> {


    @Query("SELECT q FROM Question q ")
    ArrayList<Question> findAllByQuestionId();

    ArrayList<Question> findByAwardIdAndRequirementId(Integer awardId, Integer requirementId);

}
