package com.bubnii.emailsender.service;

import com.bubnii.emailsender.configuration.MailSenderProperties;
import com.bubnii.emailsender.domain.EmailResponse;
import com.bubnii.emailsender.domain.EmailWithText;
import com.bubnii.emailsender.exception.WrongRequestFormatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.bubnii.emailsender.util.MailSenderUtility.buildHttpEntityForRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSender {
    private final RestTemplate restTemplate;
    private final MailSenderProperties mailSenderProperties;

    @Override
    public EmailResponse sendEmail(final EmailWithText request) {
        final HttpEntity<EmailWithText> entity = buildHttpEntityForRequest(request, mailSenderProperties);

        EmailResponse emailResponse;
        try {
            emailResponse = restTemplate.postForObject(mailSenderProperties.getApiUrl(), entity, EmailResponse.class);
            log.info("Email sent successfully. Response {}", emailResponse);
        } catch (HttpClientErrorException e) {
            log.error("Email sending failed {}", e.getMessage(), e);
            throw new WrongRequestFormatException(e.getMessage());
        }

        return emailResponse;
    }

}
