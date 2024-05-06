package com.bubnii.emailsender;

import com.bubnii.emailsender.configuration.MailSenderProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
		classes = MailSenderApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class MailSenderApplicationTests {
	@Autowired
	MailSenderProperties mailSenderProperties;

	@Test
	void contextLoads() {
		assertNotNull(mailSenderProperties);
	}

}
