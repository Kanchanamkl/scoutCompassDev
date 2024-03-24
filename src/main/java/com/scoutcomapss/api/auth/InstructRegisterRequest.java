package com.scoutcomapss.api.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author : Kanchana Kalansooriya
 * @since 2/6/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructRegisterRequest {

    private String instructFirstName;
    private String instructLastname;
    private String instructEmail;
    private String instructPassword;
    private LocalDate instructDob;
    private String instructGender;
    private String instructMobNum;
    private String instructSchool;
    private String instructDistrict;
    private String instructWarrantId;
    private boolean isEnable;

}
