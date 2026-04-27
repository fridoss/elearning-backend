/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.controller;
import com.elearning.backend.dto.*;
import com.elearning.backend.model.User;
import com.elearning.backend.security.JwtUtil;
import com.elearning.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
/**
 *
 * @author Frids
 */
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request) {

        if (userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest()
                .body("Email déjà utilisé");
        }

        User user = new User();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setPassword(
            passwordEncoder.encode(request.getPassword()));
        user.setRole("STUDENT");

        userService.saveUser(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(
            new AuthResponse(token, user.getRole(), 
                user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        return userService.getUserByEmail(request.getEmail())
            .filter(user -> passwordEncoder.matches(
                request.getPassword(), user.getPassword()))
            .map(user -> {
                String token = jwtUtil.generateToken(
                    user.getEmail());
                return ResponseEntity.ok(
                    new AuthResponse(token, user.getRole(),
                        user.getEmail()));
            })
            .orElse(ResponseEntity.badRequest()
                .build());
    }
}


