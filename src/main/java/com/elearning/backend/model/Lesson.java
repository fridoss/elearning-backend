/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.model;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Data
@Entity
@Table(name = "lessons")
/**
 *
 * @author Frids
 */
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String type;
    // VIDEO / PDF / TEXTE

    @Column(columnDefinition = "TEXT")
    private String contenu;

    private Integer duree;

    @Column(nullable = false)
    private Integer ordre;

   @ManyToOne
@JoinColumn(name = "section_id")
@JsonIgnoreProperties({"lessons", "course"})
private Section section;
}
    

