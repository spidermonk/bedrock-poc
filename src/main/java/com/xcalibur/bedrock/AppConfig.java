package com.xcalibur.bedrock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.bedrock.BedrockClient;

@Configuration
public class AppConfig {

    @Bean
    public BedrockClient bedrockClient() {
        return BedrockClient.builder().build();
    }
}
