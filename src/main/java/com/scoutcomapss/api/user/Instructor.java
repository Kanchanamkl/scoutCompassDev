package com.scoutcomapss.api.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author : Kanchana Kalansooriya
 * @since 2/5/2024
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_instructor")
public class Instructor implements UserDetails {

    @Id
    @GeneratedValue
    private Integer instructId;
    private String instructFirstName;
    private String instructLastname;
    private String instructEmail;
    private Date instructDob;
    private String instructGender;
    private String instructMobNum;
    private String instructSchool;
    private String instructPassword;
    private boolean isEnable;

    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean enabled;

    @OneToMany(cascade =CascadeType.ALL)
    List<Scout> scoutDetails =  new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
