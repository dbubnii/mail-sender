package com.bubnii.emailsender.service;

import com.bubnii.emailsender.TestUtility;
import com.bubnii.emailsender.configuration.MailSenderProperties;
import com.bubnii.emailsender.domain.EmailResponse;
import com.bubnii.emailsender.domain.EmailWithText;
import com.bubnii.emailsender.exception.WrongRequestFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;

@Tag("Unit")
public class MailSenderServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private MailSenderProperties mailSenderProperties;

    private MailSenderServiceImpl unit;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        unit = new MailSenderServiceImpl(restTemplate, mailSenderProperties);
    }

    @Test
    void sendEmail_Successful() {
        EmailWithText emailRequest = TestUtility.buildEmailWithText();
        EmailResponse expectedResponse = EmailResponse.builder()
                .success(true)
                .messageIds(List.of(UUID.randomUUID().toString())).build();

        when(mailSenderProperties.getApiUrl()).thenReturn("https://stoplight.io/mocks/railsware/mailtrap-api-docs/93404133/api/send");
        when(mailSenderProperties.getApiToken()).thenReturn("Bearer b731b3e4948dd5e3fd1a3758371b6213");
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(EmailResponse.class)))
                .thenReturn(expectedResponse);

        EmailResponse actualResponse = unit.sendEmail(emailRequest);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void sendEmail_Failed() {
        EmailWithText emailRequest = TestUtility.buildWrongEmailWithText();

        when(mailSenderProperties.getApiUrl()).thenReturn("https://stoplight.io/mocks/railsware/mailtrap-api-docs/93404133/api/send");
        when(mailSenderProperties.getApiToken()).thenReturn("Bearer b731b3e4948dd5e3fd1a3758371b6213");
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(EmailResponse.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        assertThrows(WrongRequestFormatException.class, () -> unit.sendEmail(emailRequest));
    }
}
