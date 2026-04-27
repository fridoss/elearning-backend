/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.service;

import com.elearning.backend.model.Certificate;
import com.elearning.backend.model.Course;
import com.elearning.backend.model.User;
import com.elearning.backend.repository.CertificateRepository;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    public List<Certificate> getCertificatesByUser(
            Long userId) {
        return certificateRepository
            .findByUserId(userId);
    }

    public Certificate saveCertificate(
            Certificate certificate) {
        return certificateRepository
            .save(certificate);
    }

    public byte[] generateCertificatePdf(
            User user, Course course) {
        try {
            ByteArrayOutputStream baos = 
                new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Titre
            document.add(new Paragraph()
                .add(new Text("CERTIFICAT DE RÉUSSITE")
                    .setFontSize(28)
                    .setBold()
                    .setFontColor(ColorConstants.BLUE))
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(50));

            // Séparateur
            document.add(new Paragraph("_".repeat(60))
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(ColorConstants.BLUE));

            // Texte principal
            document.add(new Paragraph()
                .add(new Text(
                    "\nCeci certifie que\n\n")
                    .setFontSize(14))
                .setTextAlignment(TextAlignment.CENTER));

            // Nom de l'étudiant
            document.add(new Paragraph()
                .add(new Text(
                    user.getPrenom() + " " + user.getNom())
                    .setFontSize(24)
                    .setBold()
                    .setFontColor(ColorConstants.DARK_GRAY))
                .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph()
                .add(new Text(
                    "\na complété avec succès le cours\n\n")
                    .setFontSize(14))
                .setTextAlignment(TextAlignment.CENTER));

            // Titre du cours
            document.add(new Paragraph()
                .add(new Text(course.getTitre())
                    .setFontSize(20)
                    .setBold()
                    .setFontColor(ColorConstants.BLUE))
                .setTextAlignment(TextAlignment.CENTER));

            // Date
            String date = LocalDateTime.now()
                .format(DateTimeFormatter
                    .ofPattern("dd/MM/yyyy"));
            document.add(new Paragraph()
                .add(new Text("\n\nDate : " + date)
                    .setFontSize(12))
                .setTextAlignment(TextAlignment.CENTER));

            // Séparateur bas
            document.add(new Paragraph("_".repeat(60))
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(ColorConstants.BLUE)
                .setMarginTop(30));

            document.add(new Paragraph()
                .add(new Text("eLearning Platform")
                    .setFontSize(12)
                    .setItalic()
                    .setFontColor(ColorConstants.GRAY))
                .setTextAlignment(TextAlignment.CENTER));

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException(
                "Erreur génération PDF", e);
        }
    }
}