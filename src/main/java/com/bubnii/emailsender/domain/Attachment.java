package com.bubnii.emailsender.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Attachment {
    private String content;
    private String type;
    private String filename;
    private String disposition;
    private String contentId;
}
