package com.bubnii.emailsender.util;

import com.bubnii.emailsender.configuration.MailSenderProperties;
import com.bubnii.emailsender.domain.EmailWithText;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class MailSenderUtility {

    public static HttpEntity<EmailWithText> buildHttpEntityForRequest(final EmailWithText request,
                                                                      final MailSenderProperties mailSenderProperties) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.add(AUTHORIZATION, mailSenderProperties.getApiToken());

        return new HttpEntity<>(request, headers);
    }
}
