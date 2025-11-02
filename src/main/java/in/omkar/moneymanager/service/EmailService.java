// package in.omkar.moneymanager.service;

// import jakarta.mail.MessagingException;
// import jakarta.mail.internet.MimeMessage;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.core.io.ByteArrayResource;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.stereotype.Service;

// @Service
// @RequiredArgsConstructor
// public class EmailService {

//     private final JavaMailSender mailSender;

//     @Value("${spring.mail.properties.mail.smtp.from}")
//     private String fromEmail;

//     public void sendEmail(String to, String subject, String body) {
//         try {
//             SimpleMailMessage message = new SimpleMailMessage();
//             message.setFrom(fromEmail);
//             message.setTo(to);
//             message.setSubject(subject);
//             message.setText(body);
//             mailSender.send(message);
//         }catch (Exception e) {
//             throw new RuntimeException(e.getMessage());
//         }
//     }

//     public void sendEmailWithAttachment(String to, String subject, String body, byte[] attachment, String filename) throws MessagingException {
//         MimeMessage message = mailSender.createMimeMessage();
//         MimeMessageHelper helper = new MimeMessageHelper(message, true);
//         helper.setFrom(fromEmail);
//         helper.setTo(to);
//         helper.setSubject(subject);
//         helper.setText(body);
//         helper.addAttachment(filename, new ByteArrayResource(attachment));
//         mailSender.send(message);
//     }
// }

package in.omkar.moneymanager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class EmailService {

    @Value("${Brevo_Api_Key}")
    private String apiKey;

    @Value("${BREVO_FROM_EMAIL}")
    private String fromEmail;

    public void sendEmail(String to, String subject, String body) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.brevo.com/v3/smtp/email";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", apiKey);

            Map<String, Object> payload = new HashMap<>();
            payload.put("sender", Map.of("email", fromEmail));
            payload.put("to", List.of(Map.of("email", to)));
            payload.put("subject", subject);
            payload.put("htmlContent", "<html><body>" + body + "</body></html>");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
            restTemplate.postForEntity(url, entity, String.class);

        } catch (Exception e) {
            System.err.println("❌ Email sending failed via Brevo API: " + e.getMessage());
        }
    }

    public void sendEmailWithAttachment(String to, String subject, String body, byte[] attachment, String filename) {
        // Optional: Brevo API supports attachments, but this is kept simple for now.
        sendEmail(to, subject, body + "\n\n(Attachment sending not supported in this version.)");
    }
}

