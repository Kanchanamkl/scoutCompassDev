package com.scoutcomapss.api.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_scout")
public class Scout implements UserDetails {

  @Id
  @GeneratedValue
  private Integer scoutId;
  private String scoutFirstName;
  private String scoutLastname;
  private String scoutEmail;
  private Date  scoutDob;
  private String scoutGender;
  private String scoutMobNum;
  private String scoutSchool;
  private String scoutPassword;
  private String instructorId;

  @Enumerated(EnumType.STRING)
  private Role role;
  private Boolean enabled ;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return scoutPassword;
  }

  @Override
  public String getUsername() {
    return scoutEmail;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


}
