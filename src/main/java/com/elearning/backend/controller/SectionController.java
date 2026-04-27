/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.controller;

import com.elearning.backend.model.Lesson;
import com.elearning.backend.model.Section;
import com.elearning.backend.repository.LessonRepository;
import com.elearning.backend.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sections")
@CrossOrigin(origins = "http://localhost:3000")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/course/{courseId}")
    public List<Section> getSectionsByCourse(
            @PathVariable Long courseId) {
        List<Section> sections = sectionService
            .getSectionsByCourse(courseId);
        sections.forEach(section -> {
            List<Lesson> lessons = lessonRepository
                .findBySectionId(section.getId());
            section.setLessons(lessons);
        });
        return sections;
    }

    @PostMapping
    public Section createSection(
            @RequestBody Section section) {
        return sectionService.saveSection(section);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(
            @PathVariable Long id) {
        sectionService.deleteSection(id);
        return ResponseEntity.ok().build();
    }
}