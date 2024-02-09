package com.scoutcomapss.api.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String instructLastName;
    private String instructEmail;
    private Date instructDob;
    private String instructGender;
    private String instructMobNum;
    private String instructSchool;
    private String instructPassword;
    private boolean isEnable;

}
