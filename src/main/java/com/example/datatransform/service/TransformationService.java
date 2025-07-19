package com.example.datatransform.service;
import com.example.datatransform.model.Label;
import com.example.datatransform.model.ThirdPartyKeyMapping;
import com.example.datatransform.repository.DataRepository;
import com.example.datatransform.utils.InputParserUtils;
import com.example.datatransform.utils.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service

public class TransformationService {
   private final DataRepository dataRepository;
    public TransformationService(DataRepository dataRepository) {
         this.dataRepository = dataRepository;
    }


    public String transform(String rawInput, String source, String languageCode) {

        Map<String, String> parsedData = InputParserUtils.parseRawInput(rawInput);

        List<ThirdPartyKeyMapping> mappings = dataRepository.getMappings();
        List<Label> labels = dataRepository.getLabels();

        Map<String, String> localizedKeys = new HashMap<>();
        Map<Integer, Label> labelMap = labels.stream()
                .collect(Collectors.toMap(Label::getId, l -> l));

        for (ThirdPartyKeyMapping mapping : mappings) {
            if (mapping.getThirdPartySource().equalsIgnoreCase( source)) {
                int labelId = mapping.getLabelId();
                Label label = labelMap.get(labelId);
                if (label != null) {
                    System.out.println(mapping.getThirdPartyKey());
                    localizedKeys.put(mapping.getThirdPartyKey(), label.getLabel(languageCode));
                }
            }
        }
        MessageBuilder messageBuilder = new MessageBuilder();
        for(Map.Entry<String, String> entry : parsedData.entrySet()) {
            String displayKey = localizedKeys.getOrDefault(entry.getKey(), entry.getKey());
            String value = entry.getValue();
            messageBuilder.addLine(displayKey, value);
            }
        return messageBuilder.build();

    }

}