/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.elearning.backend.repository;
import com.elearning.backend.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 *
 * @author Frids
 */
public interface EnrollmentRepository  extends JpaRepository<Enrollment, Long>{
 List<Enrollment> findByUserId(Long userId);
    Boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}
