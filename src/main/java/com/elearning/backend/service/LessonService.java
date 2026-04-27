/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.service;
import com.elearning.backend.model.Lesson;
import com.elearning.backend.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
/**
 *
 * @author Frids
 */
public class LessonService {
    
    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> getLessonsBySection(
            Long sectionId) {
        return lessonRepository
            .findBySectionId(sectionId);
    }

    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

}
