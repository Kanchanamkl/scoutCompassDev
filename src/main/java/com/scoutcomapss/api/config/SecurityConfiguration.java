package com.scoutcomapss.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
            .csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/api/scoutcompass/auth/**").permitAll()
                    .requestMatchers("/api/scoutcompass/resource/**").permitAll()
                    .requestMatchers("/api/scoutcompass/profile/**").permitAll()
                    .requestMatchers("/api/scoutcompass/requirement/**").permitAll()
                    .requestMatchers("/api/scoutcompass/event/**").permitAll()
                    .requestMatchers("/api/scoutcompass/passing/**").permitAll()
                    //  .requestMatchers("/api/v1/test/**").permitAll()
                    // .requestMatchers("/api/v1/test/**").hasAuthority("ADMIN")
                    //  .requestMatchers(GET ,"/api/v1/demo-controller/**").permitAll()
                    .anyRequest().authenticated())
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
