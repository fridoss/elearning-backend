/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.elearning.backend.repository;
import com.elearning.backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 *
 * @author Frids
 */
public interface CourseRepository  extends JpaRepository<Course, Long>{
 List<Course> findByInstructorId(Long instructorId);
}
