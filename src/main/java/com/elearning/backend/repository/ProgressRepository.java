/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.elearning.backend.repository;
import com.elearning.backend.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author Frids
 */
public interface ProgressRepository extends JpaRepository<Progress, Long> {
 List<Progress> findByUserId(Long userId);
    Optional<Progress> findByUserIdAndLessonId(
        Long userId, Long lessonId);
}
