package com.example.jpa.extra.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OpenApiResultResponse {
    private OpenApiResultResponseHeader header;
    private OpenApiResultResponseBody body;
}
