package com.bubnii.emailsender.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Builder
@Data
public class EmailWithText {
    private Address from;
    private List<Address> to;
    private List<Address> bcc;
    private List<Attachment> attachments;
    private Map<String, String> headers;
    @JsonProperty("custom_variables")
    private Map<String, String> customVariables;
    private String subject;
    private String text;
    private String html;
    private String category;
}
