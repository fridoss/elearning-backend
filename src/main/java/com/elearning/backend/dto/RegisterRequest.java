/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.dto;
import lombok.Data;

@Data
/**
 *
 * @author Frids
 */
public class RegisterRequest {
    
    private String nom;
    private String prenom;
    private String email;
    private String password;

}
