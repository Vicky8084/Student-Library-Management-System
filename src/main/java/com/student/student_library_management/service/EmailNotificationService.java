package com.student.student_library_management.service;

import com.student.student_library_management.model.Student;
import com.student.student_library_management.model.Librarian;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
public class EmailNotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.full-name}")
    private String adminName;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final String baseUrl = "http://localhost:8010";

    public void sendStudentRegistrationNotification(Student student) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            Context context = new Context();
            context.setVariable("adminName", adminName);
            context.setVariable("studentName", student.getName());
            context.setVariable("studentEmail", student.getEmail());
            context.setVariable("registrationNumber", student.getRegistrationNumber());
            context.setVariable("mobileNumber", student.getMobileNumber());
            context.setVariable("course", student.getCourse());
            context.setVariable("semester", student.getSemester());
            context.setVariable("bloodGroup", student.getBloodGroup().getDisplayValue());
            context.setVariable("cardNumber", student.getCard().getCardNumber());
            context.setVariable("approveUrl", baseUrl + "/admin/api/students/" + student.getId() + "/approve");
            context.setVariable("rejectUrl", baseUrl + "/admin/api/students/" + student.getId() + "/reject");

            String htmlContent = templateEngine.process("student-registration-email", context);

            helper.setTo(adminEmail);
            helper.setFrom(fromEmail);
            helper.setSubject("📚 New Student Registration: " + student.getName());
            helper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
            log.info("✅ Student registration email sent to admin for: {}", student.getName());

        } catch (Exception e) {
            log.error("❌ Failed to send student registration email: {}", e.getMessage());
        }
    }

    public void sendLibrarianRegistrationNotification(Librarian librarian) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            Context context = new Context();
            context.setVariable("adminName", adminName);
            context.setVariable("librarianName", librarian.getName());
            context.setVariable("librarianEmail", librarian.getEmail());
            context.setVariable("employeeId", librarian.getEmpId());
            context.setVariable("mobileNumber", librarian.getMobileNumber());
            context.setVariable("joiningDate", librarian.getJoiningDate());
            context.setVariable("approveUrl", baseUrl + "/admin/api/librarians/" + librarian.getId() + "/approve");
            context.setVariable("rejectUrl", baseUrl + "/admin/api/librarians/" + librarian.getId() + "/reject");

            String htmlContent = templateEngine.process("librarian-registration-email", context);

            helper.setTo(adminEmail);
            helper.setFrom(fromEmail);
            helper.setSubject("👤 New Librarian Registration: " + librarian.getName());
            helper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
            log.info("✅ Librarian registration email sent to admin for: {}", librarian.getName());

        } catch (Exception e) {
            log.error("❌ Failed to send librarian registration email: {}", e.getMessage());
        }
    }
}