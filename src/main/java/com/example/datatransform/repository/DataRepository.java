

package com.example.datatransform.repository;

import com.example.datatransform.config.DataConfig;
import com.example.datatransform.model.Label;
import com.example.datatransform.model.ThirdPartyKeyMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataRepository {

    private final DataConfig dataConfig;

    public DataRepository(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

    public List<Label> getLabels() {
        return dataConfig.getLabels();
    }

    public List<ThirdPartyKeyMapping> getMappings() {

        return dataConfig.getMappings();
    }
}
