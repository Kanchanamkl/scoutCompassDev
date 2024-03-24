package com.scoutcomapss.api.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoutRegisterRequest {

  private String scoutFirstName;
  private String scoutLastname;
  private String scoutEmail;
  private LocalDate scoutDob;
  private String scoutGender;
  private String scoutMobNum;
  private String scoutSchool;
  private String scoutDistrict;
  private String scoutPassword;
  private String instructorId;

}
