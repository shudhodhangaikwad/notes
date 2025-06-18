package com.jsiders.notes.sevice.impl;

import com.jsiders.notes.entity.NoteUser;
import com.jsiders.notes.repository.NoteUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class NoteUserService implements UserDetailsService {

    private NoteUserRepository repository;

    private PasswordEncoder passwordEncoder;


    public NoteUser createUser(NoteUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Encoded Password: {}", user.getPassword());

        return repository.save(user);
    }

    @Cacheable(value = "users", key = "#username")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading User By Username: {}", username);
        NoteUser noteUser = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return User
                .withUsername(noteUser.getUsername())
                .password(noteUser.getPassword())
                .authorities(new SimpleGrantedAuthority(noteUser.getRole()))
                .build();

    }


}
