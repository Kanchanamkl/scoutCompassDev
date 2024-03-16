package com.scoutcomapss.api.requirement.quiz;

/**
 * @author : Kanchana Kalansooriya
 * @since 3/6/2024
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table(name = "_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private Integer awardId;
    private Integer requirementId;
    private String questionText;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;


}
