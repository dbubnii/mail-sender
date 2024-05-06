package com.bubnii.emailsender;

import com.bubnii.emailsender.domain.Address;
import com.bubnii.emailsender.domain.EmailWithText;

import java.util.List;

public class TestUtility {
    public static EmailWithText buildEmailWithText() {
        Address from = Address.builder().email("demomailtrap.com").name("Demo Mailtrap Test").build();
        Address to = Address.builder().email("bubniyo@gmail.com").build();

        return EmailWithText.builder()
                .from(from)
                .to(List.of(to))
                .subject("Test email")
                .text("Building test object")
                .category("Testing").build();
    }

    public static EmailWithText buildWrongEmailWithText() {
        Address to = Address.builder().email("bubniyo@gmail.com").build();

        return EmailWithText.builder()
                .to(List.of(to))
                .subject("Test email")
                .text("Building test object")
                .category("Testing").build();
    }
}
