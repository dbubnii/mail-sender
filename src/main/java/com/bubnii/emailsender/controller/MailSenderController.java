package com.bubnii.emailsender.controller;

import com.bubnii.emailsender.domain.EmailResponse;
import com.bubnii.emailsender.domain.EmailWithText;
import com.bubnii.emailsender.service.MailSenderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailSenderController {
    private final MailSenderServiceImpl mailSenderService;

    @PostMapping("/api/send")
    public ResponseEntity<EmailResponse> sendMail(@RequestBody EmailWithText request) {
        log.info("Received email to send {}", request);
        final EmailResponse emailResponse = mailSenderService.sendEmail(request);

        return ResponseEntity.ok(emailResponse);
    }
}
