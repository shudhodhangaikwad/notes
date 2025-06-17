package com.jsiders.notes.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class CustomAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User user= (User) securityContext.getAuthentication().getPrincipal();
        return Optional.of(user.getUsername());
    }
}
