package com.example.datatransform.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class InputParserUtils {
    static     public Map<String, String> parseRawInput(String rawInput) {
        Map<String, String> result = new LinkedHashMap<>();
        String[] lines = rawInput.split("\\n");
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                result.put(parts[0].trim(), parts[1].trim());
            } else {
                System.err.println("Skipping malformed line: " + line);
            }
        }
        return result;
    }

}
