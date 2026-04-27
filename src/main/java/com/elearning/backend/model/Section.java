/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.FetchType;
import java.util.ArrayList;
/**
 *
 * @author Frids
 */
@Data
@Entity
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private Integer ordre;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

   // Retire @JsonIgnore et remplace par :
@JsonProperty
@OneToMany(mappedBy = "section", 
    cascade = CascadeType.ALL, 
    fetch = FetchType.LAZY)
private List<Lesson> lessons = new ArrayList<>();
}
    

