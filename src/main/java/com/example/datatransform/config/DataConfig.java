package com.example.datatransform.config;

import com.example.datatransform.model.Label;
import com.example.datatransform.model.ThirdPartyKeyMapping;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "data")
public class DataConfig {
    private List<Label> labels;
    private List<ThirdPartyKeyMapping> mappings;
    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels)
    {
        this.labels = labels;
    }

    public List<ThirdPartyKeyMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<ThirdPartyKeyMapping> mappings) {
        this.mappings = mappings;
    }
}
