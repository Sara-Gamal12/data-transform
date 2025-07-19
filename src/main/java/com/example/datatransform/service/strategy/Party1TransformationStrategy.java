package com.example.datatransform.service.strategy;

import com.example.datatransform.service.TransformationService;
import org.springframework.stereotype.Component;

@Component("party1")
public class Party1TransformationStrategy implements TransformationStrategy {
    private final TransformationService transformationService;
    public  Party1TransformationStrategy(TransformationService transformationService) {
        this.transformationService = transformationService;
    }
    @Override
    public String transform(String rawInput, String lang) {
        return transformationService.transform(rawInput, "party1", lang);
    }

}
