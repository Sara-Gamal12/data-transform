package com.example.datatransform.service;


import com.example.datatransform.service.strategy.TransformationStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TransformationStrategyFactory {

    private final Map<String, TransformationStrategy> strategyMap;

    public TransformationStrategyFactory(Map<String, TransformationStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public TransformationStrategy getStrategy(String sourceKey) {

        return strategyMap.get(sourceKey.toLowerCase());

    }
}
