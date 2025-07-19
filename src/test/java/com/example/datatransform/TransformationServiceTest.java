package com.example.datatransform;

import com.example.datatransform.service.TransformationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransformationServiceTest {
    @Autowired
    private  TransformationService service;

    @Test
    public void testTransformDataEN(){
        String input = """
            client_name : John Doe
        mobile_number : 123456789
        total_amount : 1000
        """;

        String expectedOutput = """
            Client Name : John Doe
        Mobile Number : 123456789
        total_amount : 1000
        """;
        String result = service.transform(input, "party1", "en");
        assertEquals( result.trim(),expectedOutput.trim()) ;


    }
    @Test
    public void testTransformDataAR(){
         String input = """
        client_name : أحمد
        mobile_number : 01000000000
        """;

        String expectedOutput = """
        اسم العميل : أحمد
        رقم الموبايل : 01000000000
        """;
        String result = service.transform(input, "party1","ar");
        assertEquals( result.trim(),expectedOutput.trim()) ;
    }
    @Test
    public void testMalformedInput() {
        String input = """
            client_name : John Doe
            mobile_number : 123456789
            invalidlinewithoutcolon
        """;


        String result = service.transform(input, "party1","en");
        assertTrue(result.contains("Client Name : John Doe"));
        assertTrue(result.contains("Mobile Number : 123456789"));
        assertFalse(result.contains("invalidlinewithoutcolon"));

    }




}
