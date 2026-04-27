/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.controller;

import com.elearning.backend.model.Certificate;
import com.elearning.backend.model.Course;
import com.elearning.backend.model.User;
import com.elearning.backend.repository.CourseRepository;
import com.elearning.backend.repository.UserRepository;
import com.elearning.backend.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@CrossOrigin(origins = "http://localhost:3000")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/user/{userId}")
    public List<Certificate> getCertificatesByUser(
            @PathVariable Long userId) {
        return certificateService
            .getCertificatesByUser(userId);
    }

    @PostMapping
    public ResponseEntity<Certificate> createCertificate(
            @RequestBody Certificate certificate) {
        certificate.setIssuedAt(LocalDateTime.now());
        Certificate saved = certificateService
            .saveCertificate(certificate);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/download/{userId}/{courseId}")
    public ResponseEntity<byte[]> downloadCertificate(
            @PathVariable Long userId,
            @PathVariable Long courseId) {

        User user = userRepository.findById(userId)
            .orElseThrow();
        Course course = courseRepository.findById(courseId)
            .orElseThrow();

        byte[] pdf = certificateService
            .generateCertificatePdf(user, course);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(
            "attachment",
            "certificat_" + course.getTitre() + ".pdf");

        return ResponseEntity.ok()
            .headers(headers)
            .body(pdf);
    }
}