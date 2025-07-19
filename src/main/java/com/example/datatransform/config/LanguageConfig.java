package com.example.datatransform.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class LanguageConfig {

    private final List<String> supportedLanguages;
    private final String defaultLanguage ;

    public LanguageConfig(@Value("${app.supported-languages}") String supportedLanguages,@Value("${app.default-language}") String defaultLanguage) {
        this.supportedLanguages = Arrays.asList(supportedLanguages.split(","));
        this.defaultLanguage = defaultLanguage;

    }


    public  List<String> getSupportedLanguages() {
        return supportedLanguages;
    }
    public String getDefaultLanguage() {
        return defaultLanguage;
    }
}