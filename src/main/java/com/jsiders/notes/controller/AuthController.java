package com.jsiders.notes.controller;

import com.jsiders.notes.dto.AuthRequest;
import com.jsiders.notes.dto.AuthResponse;
import com.jsiders.notes.entity.NoteUser;
import com.jsiders.notes.repository.NoteUserRepository;
import com.jsiders.notes.sevice.JwtService;
import com.jsiders.notes.sevice.impl.NoteUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final NoteUserService noteUserService;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<NoteUser> create(@RequestBody NoteUser noteUser) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(noteUserService.createUser(noteUser));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authentication);
        log.info("Is Authenticated {}", auth.isAuthenticated());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(jwtService.generateToken(authRequest.getUsername(), auth.getAuthorities()));

    }

}
