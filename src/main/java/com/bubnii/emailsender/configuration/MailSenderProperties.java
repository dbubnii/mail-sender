package com.bubnii.emailsender.configuration;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@ConfigurationProperties("mail-sender")
public class MailSenderProperties {
    String apiUrl;
    String apiToken;
}
