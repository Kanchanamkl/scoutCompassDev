package com.scoutcomapss.api.auth;

import com.scoutcomapss.api.auth.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegisterRequest {
    private String adminEmail;
    private String adminPassword;
    Role role;
}

