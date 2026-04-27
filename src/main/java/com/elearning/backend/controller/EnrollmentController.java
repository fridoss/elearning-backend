/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.controller;
import com.elearning.backend.model.Enrollment;
import com.elearning.backend.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "http://localhost:3000")
/**
 *
 * @author Frids
 */
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/user/{userId}")
    public List<Enrollment> getEnrollmentsByUser(
            @PathVariable Long userId) {
        return enrollmentService.getEnrollmentsByUser(userId);
    }

    @PostMapping
    public Enrollment createEnrollment(
            @RequestBody Enrollment enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }

    @GetMapping("/check/{userId}/{courseId}")
    public ResponseEntity<Boolean> checkEnrollment(
            @PathVariable Long userId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(
            enrollmentService.isEnrolled(userId, courseId));
    }
}


