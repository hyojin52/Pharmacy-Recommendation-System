package com.example.roadview.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    
    @JsonProperty("place_name")
    private String placeName;
    @JsonProperty("distance")
    private double distance;

    @JsonProperty("address_name")
    private String addressName;
    @JsonProperty("y")
    private double latitude;
    @JsonProperty("x")
    private double longitude;
}
