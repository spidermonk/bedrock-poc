package com.xcalibur.bedrock.models;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.bedrock.BedrockClient;
import software.amazon.awssdk.services.bedrock.model.FoundationModelSummary;
import software.amazon.awssdk.services.bedrock.model.ListFoundationModelsRequest;
import software.amazon.awssdk.services.bedrock.model.ListFoundationModelsResponse;

import java.util.List;

@RestController
@RequestMapping("bedrock/models")
public class BedrockModelController {

    private final BedrockClient bedrockClient;

    public BedrockModelController(BedrockClient bedrockClient) {
        this.bedrockClient = bedrockClient;
    }

    @GetMapping("")
    public List<FoundationModel> getBedrockModels() {

        ListFoundationModelsResponse response = bedrockClient.listFoundationModels(ListFoundationModelsRequest.builder().build());
        return response.modelSummaries().stream().map(this::map).toList();
    }

    @GetMapping("/provider/{provider}")
    public List<FoundationModel> getBedrockModelsByProvider(@PathVariable("provider") String provider) {

        ListFoundationModelsRequest request = ListFoundationModelsRequest.builder().byProvider(provider).build();
        ListFoundationModelsResponse response = bedrockClient.listFoundationModels(request);
        return response.modelSummaries().stream().map(this::map).toList();
    }

    private FoundationModel map(FoundationModelSummary m) {
        return FoundationModel.builder().arn(m.modelArn()).id(m.modelId())
                .name(m.modelName()).provider(m.providerName()).inferenceTypes(m.inferenceTypesSupportedAsStrings())
                .inputModalities(m.inputModalitiesAsStrings()).outputModalities(m.outputModalitiesAsStrings()).customizationsSupported(m.customizationsSupportedAsStrings())
                .status(m.modelLifecycle().statusAsString()).streamingSupported(m.responseStreamingSupported()).build();
    }
}
