/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.controller;

import com.elearning.backend.model.Lesson;
import com.elearning.backend.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@CrossOrigin(origins = "http://localhost:3000")
public class LessonController {

    @Autowired
    private LessonService lessonService;
    @Autowired
private com.elearning.backend.repository
    .LessonRepository lessonRepository;

    @GetMapping("/section/{sectionId}")
    public List<Lesson> getLessonsBySection(
            @PathVariable Long sectionId) {
        return lessonService
            .getLessonsBySection(sectionId);
    }

    @PostMapping
    public Lesson createLesson(
            @RequestBody Lesson lesson) {
        return lessonService.saveLesson(lesson);
    }
    @GetMapping("/{id}")
public ResponseEntity<Lesson> getLessonById(
        @PathVariable Long id) {
    return lessonRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(
            @PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.ok().build();
    }
  @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
    lessonRepository.deleteById(id);
    return ResponseEntity.ok().build();
}  
}