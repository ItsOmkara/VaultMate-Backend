package in.omkar.moneymanager.controller;

import in.omkar.moneymanager.entity.ContactEntity;
import in.omkar.moneymanager.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

    @Autowired
    private ContactRepository contactRepo;

    @PostMapping
    public ResponseEntity<String> saveMessage(@RequestBody ContactEntity message) {
        contactRepo.save(message);
        System.out.println("Received: " + message);
        // TODO: Save to DB or send email
        return ResponseEntity.ok("Message received successfully");
    }
}
