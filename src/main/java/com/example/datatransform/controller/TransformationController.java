package com.example.datatransform.controller;
import com.example.datatransform.config.LanguageConfig;
import com.example.datatransform.service.strategy.TransformationStrategy;
import com.example.datatransform.service.TransformationStrategyFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
@RestController
@RequestMapping("/api/transform")

public class TransformationController {


    @Autowired
    private LanguageConfig languageConfig;
    private final TransformationStrategyFactory strategyFactory;


    public TransformationController(LanguageConfig languageConfig, TransformationStrategyFactory strategyFactory) {
        this.languageConfig = languageConfig;
        this.strategyFactory = strategyFactory;
    }

    @PostMapping(consumes = "text/plain", produces = "text/plain")
    @Operation(
            summary = "Transform third-party data",
            description = "Parses raw key-value data and localizes it using predefined mappings and labels",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
//                            mediaType = "text/plain",
                            examples =
                            @ExampleObject(
                                    name = "Localized English Output",
                                    value = """
                                            Client Name : John Doe,
                                            Mobile Number : 123456789
                                            total_amount : 1000
                                            due_date : 2021-12-07 12:00:00
                                            """,
                                    summary = "Processed English result"
                            )

                    )

            ),
            parameters = {
                    @Parameter(name = "source", example = "party1", description = "Third-party source identifier"),
                    @Parameter(name = "language", example = "en", description = "Language code (e.g. 'en' or 'ar')")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = "text/plain",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Localized English Output",
                                                    value = """
                                                            Client Name : John Doe
                                                            Mobile Number : 123456789
                                                            total_amount : 1000
                                                            due_date : 2021-12-07 12:00:00
                                                            """,
                                                    summary = "Processed English result"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid input or unsupported language")
            }
    )
    public ResponseEntity<String> transformData(@RequestBody   String rawData,
                                                @RequestParam (required = false)  String source,

                                                @RequestParam (required = false) String language)
    {
        if(source == null)
            return ResponseEntity.badRequest().body("Source parameter is required.");

        String finalLang=(language == null || !languageConfig.getSupportedLanguages().contains(language.toLowerCase().trim()))
                ?languageConfig.getDefaultLanguage()
                :language;



            TransformationStrategy strategy = strategyFactory.getStrategy(source.trim());
            if (strategy == null)
                return ResponseEntity.badRequest().body("Unsupported source: " + source);
            String result = strategy.transform(rawData, finalLang);
            if (language!=null && !language.equalsIgnoreCase(finalLang))
                result = "Language not supported, defaulting to " + finalLang + ":\n" + result;

            return ResponseEntity.ok(result);


    }


}