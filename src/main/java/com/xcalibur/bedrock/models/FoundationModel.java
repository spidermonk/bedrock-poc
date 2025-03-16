package com.xcalibur.bedrock.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoundationModel {

    private String arn;
    private String id;
    private String name;
    private String provider;
}
