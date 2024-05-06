package com.bubnii.emailsender.service;

import com.bubnii.emailsender.domain.EmailResponse;
import com.bubnii.emailsender.domain.EmailWithText;

public interface MailSender {
    EmailResponse sendEmail(EmailWithText request);
}
