package BackendSiadseUfps.siadse.controller;

import BackendSiadseUfps.siadse.service.implementations.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private SendEmailService emailService;

    @PostMapping("/email")
    public void sendEmailNotification(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
    }

}
