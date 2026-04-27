/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.controller;

import com.elearning.backend.model.Progress;
import com.elearning.backend.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "http://localhost:3000")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @GetMapping("/user/{userId}")
    public List<Progress> getProgressByUser(
            @PathVariable Long userId) {
        return progressService
            .getProgressByUser(userId);
    }

    @PostMapping
    public Progress saveProgress(
            @RequestBody Progress progress) {
        return progressService
            .saveProgress(progress);
    }
}
