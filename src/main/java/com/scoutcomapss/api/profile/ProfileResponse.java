package com.scoutcomapss.api.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author : Kanchana Kalansooriya
 * @since 3/3/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private String fullName;
    private String email;
    private LocalDate dob;
    private String district;
    private String gender;
    private String mobNumber;
    private String school;
    private String instructor_name;
}
