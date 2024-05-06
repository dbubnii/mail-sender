package com.bubnii.emailsender.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Attachment {
    private String content;
    private String type;
    private String filename;
    private String disposition;
    @JsonProperty("content_id")
    private String contentId;
}
