/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {

    // Dossier où les fichiers seront sauvegardés
    private final String uploadDir = "uploads/";

    @PostMapping("/pdf")
    public ResponseEntity<Map<String, String>> uploadPdf(
            @RequestParam("file") MultipartFile file) {
        try {
            // Créer le dossier s'il n'existe pas
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            // Nom unique pour éviter les conflits
            String fileName = System.currentTimeMillis() 
                + "_" + file.getOriginalFilename()
                    .replaceAll("[^a-zA-Z0-9._-]", "_");
            
            // Sauvegarder le fichier
            Path path = Paths.get(uploadDir + fileName);
            Files.write(path, file.getBytes());

            // Retourner l'URL d'accès
            String fileUrl = "http://localhost:8080/api/files/" + fileName;
            
            Map<String, String> response = new HashMap<>();
            response.put("url", fileUrl);
            response.put("fileName", fileName);
            
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Erreur upload : " + e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }
}