package com.xcalibur.bedrock.models;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.bedrock.BedrockClient;
import software.amazon.awssdk.services.bedrock.model.ListFoundationModelsRequest;
import software.amazon.awssdk.services.bedrock.model.ListFoundationModelsResponse;

import java.util.List;

@RestController
public class BedrockModelController {

    private final BedrockClient bedrockClient;

    public BedrockModelController(BedrockClient bedrockClient) {
        this.bedrockClient = bedrockClient;
    }

    @GetMapping("bedrock/models")
    public List<FoundationModel> getBedrockModels() {

        ListFoundationModelsResponse response = bedrockClient.listFoundationModels(ListFoundationModelsRequest.builder().build());
        return response.modelSummaries().stream().map(m ->
                        new FoundationModel(m.modelArn(), m.modelId(), m.modelName(), m.providerName()))
                .toList();

    }
}
