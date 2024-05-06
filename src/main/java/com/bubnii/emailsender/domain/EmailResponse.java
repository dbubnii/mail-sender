package com.bubnii.emailsender.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EmailResponse {
    private boolean success;
    @JsonProperty("message_ids")
    private List<String> messageIds;
}
