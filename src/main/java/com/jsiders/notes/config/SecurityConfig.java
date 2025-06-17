package com.jsiders.notes.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@SecurityScheme(
        name = "basicAuth", // This will be used in @SecurityRequirement
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@Configuration
@EnableJpaAuditing
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) -> request
                .requestMatchers("/public/**").permitAll()
                .anyRequest()
                .authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session-> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("sid")
                .password(passwordEncoder().encode("sid"))
                //.authorities("ROLE_USER")
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin"))
               // .authorities("ROLE_ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    public RoleHierarchy roleHierarchy(){
        return RoleHierarchyImpl.fromHierarchy("ROLE_ADMIN > ROLE_USER");
    }


}
