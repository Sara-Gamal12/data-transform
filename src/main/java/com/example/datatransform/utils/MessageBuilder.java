package com.example.datatransform.utils;


public class MessageBuilder {
    private final StringBuilder sb= new StringBuilder();

    public void addLine(String label, String value) {
        sb.append(label).append(" : ").append(value).append("\n");
    }


    public String build() {
        return sb.toString();
    }
}
