package com.example.datatransform;

import com.example.datatransform.config.DataConfig;
import com.example.datatransform.repository.DataRepository;
import com.example.datatransform.service.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties

public class DataTransformApplication  implements CommandLineRunner {
    @Autowired
    private TransformationService transformationService;

    public static void main(String[] args) {
        SpringApplication.run(DataTransformApplication.class, args);
    }
    @Override
    public void run(String... args) {

        String rawInput = """
                client_id : 28608251600337
                client_name : yyy
                mobile_number : 1025097591
                due_date : 7/12/2021 12:00:00 AM
                installment_amount : 2529
                Insurance Allow amount : 0
                min_amount : 10
                other_fees : 0
                penalty_amount : 3755
                remaining_amount_of_loan : 55638
                remaining_no_installments : 22
                total_amount : 6284
                """;

        String language = "en";
        String source = "party1";

        String result = transformationService.transform(rawInput, source, language);

        System.out.println("===== DEMO TRANSFORMATION OUTPUT =====");
        System.out.println(result);
        System.out.println("======================================");
    }


}
