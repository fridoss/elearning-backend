/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.elearning.backend.repository;
import com.elearning.backend.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 *
 * @author Frids
 */
public interface LessonRepository  extends JpaRepository<Lesson, Long> {
  List<Lesson> findBySectionId(Long sectionId);
}
