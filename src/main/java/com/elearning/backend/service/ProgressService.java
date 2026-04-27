/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.service;
import com.elearning.backend.model.Progress;
import com.elearning.backend.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
/**
 *
 * @author Frids
 */
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    public List<Progress> getProgressByUser(Long userId) {
        return progressRepository.findByUserId(userId);
    }

    public Progress saveProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    public Optional<Progress> getProgressByUserAndLesson(
            Long userId, Long lessonId) {
        return progressRepository
            .findByUserIdAndLessonId(userId, lessonId);
    }
}  


