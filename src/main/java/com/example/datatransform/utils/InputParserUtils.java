package com.example.datatransform.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class InputParserUtils {
    static     public Map<String, String> parseRawInput(String rawInput) {
        Map<String, String> result = new LinkedHashMap<>();
        String[] lines = rawInput.split("\\n");
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length >= 2) {
                // Ensure we only take the first part as key and the rest as value
                String key = parts[0].trim();
                String value = String.join(":",  Arrays.copyOfRange(parts, 1, parts.length)).trim();
                result.put(key, value);

            } else {
                System.err.println("Skipping malformed line: " + line);
            }
        }
        return result;
    }

}
