package com.bubnii.emailsender.service;

import com.bubnii.emailsender.TestUtility;
import com.bubnii.emailsender.configuration.MailSenderProperties;
import com.bubnii.emailsender.domain.EmailResponse;
import com.bubnii.emailsender.domain.EmailWithText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@Tag("Integration")
@SpringBootTest
public class MailSenderServiceImplIntegrationTest {
    @Autowired
    private MailSenderServiceImpl mailSenderService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private MailSenderProperties mailSenderProperties;

    @BeforeEach
    void setUp() {
        when(mailSenderProperties.getApiUrl()).thenReturn("https://stoplight.io/mocks/railsware/mailtrap-api-docs/93404133/api/send");
        when(mailSenderProperties.getApiToken()).thenReturn("Bearer b731b3e4948dd5e3fd1a3758371b6213");
    }

    @Test
    void sendEmail_Successful() {
        EmailWithText emailRequest = TestUtility.buildEmailWithText();
        EmailResponse expectedResponse = EmailResponse.builder().success(true)
                .messageIds(List.of(UUID.randomUUID().toString()))
                .build();

        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(EmailResponse.class)))
                .thenReturn(expectedResponse);

        EmailResponse actualResponse = mailSenderService.sendEmail(emailRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).postForObject(anyString(), any(HttpEntity.class), eq(EmailResponse.class));
    }
}
