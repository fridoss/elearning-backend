/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.elearning.backend.repository;

import com.elearning.backend.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnswerRepository
    extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);
}
