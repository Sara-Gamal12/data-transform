package com.example.datatransform;

import com.example.datatransform.service.TransformationStrategyFactory;
import com.example.datatransform.service.strategy.TransformationStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransformationStrategyTest {

    @Autowired
    private TransformationStrategyFactory strategyFactory;

    @Test
    void testTransToEnglishParty1() {
        TransformationStrategy strategy = strategyFactory.getStrategy("party1");
        String input = """
                client_name : John Doe
                mobile_number : 123456789
                """;

        String result = strategy.transform(input, "en");
        assertTrue(result.contains("Client Name : John Doe"));
        assertTrue(result.contains("Mobile Number : 123456789"));
    }

    @Test
    void testTransToEnglishParty2() {
        TransformationStrategy strategy = strategyFactory.getStrategy("party2");
        String input = """
                customer_name : John Doe
                ClientMobile : 123456789
                """;

        String result = strategy.transform(input, "en");
        assertTrue(result.contains("Client Name : John Doe"));
        assertTrue(result.contains("Mobile Number : 123456789"));
    }




}
