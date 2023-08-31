package com.example.jpa.extra.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AirInput {
    private String sido;

    public String getSearchSido() {
        return sido != null ? sido : "";
    }
}
