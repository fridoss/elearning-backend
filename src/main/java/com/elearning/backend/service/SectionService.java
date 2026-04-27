/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.service;
import com.elearning.backend.model.Section;
import com.elearning.backend.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
/**
 *
 * @author Frids
 */
public class SectionService {
    
     @Autowired
    private SectionRepository sectionRepository;

    public List<Section> getSectionsByCourse(
            Long courseId) {
        return sectionRepository
            .findByCourseId(courseId);
    }

    public Section saveSection(Section section) {
        return sectionRepository.save(section);
    }

    public void deleteSection(Long id) {
        sectionRepository.deleteById(id);
    }

}
