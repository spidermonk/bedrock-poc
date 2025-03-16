package com.xcalibur.bedrock.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class FoundationModel {

    private String arn;
    private String id;
    private String name;
    private String provider;
    private List<String> inferenceTypes;
    private List<String> inputModalities;
    private List<String> outputModalities;
    private List<String> customizationsSupported;
    private String status;
    private Boolean streamingSupported;
}
