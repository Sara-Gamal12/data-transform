package com.example.datatransform.service.strategy;

import com.example.datatransform.service.TransformationService;
import org.springframework.stereotype.Component;

@Component("party2")
public class Party2TransformationStrategy implements TransformationStrategy {
    private final TransformationService transformationService;
    public  Party2TransformationStrategy(TransformationService transformationService) {
        this.transformationService = transformationService;
    }
    @Override
    public String transform(String rawInput, String lang) {
        return transformationService.transform(rawInput, "party2", lang);
    }

}
