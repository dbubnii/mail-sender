package com.bubnii.emailsender.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {
    private String email;
    private String name;
}
