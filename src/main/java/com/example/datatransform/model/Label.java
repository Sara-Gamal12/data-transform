package com.example.datatransform.model;



public class Label {
    private final int id;
    private final String labelEn;
    private final String labelAr;


    public Label(int id, String labelEn, String labelAr) {
        this.id = id;
        this.labelEn = labelEn;
        this.labelAr = labelAr;
    }

    public int getId() {
        return id;
    }

    public String getLabel(String langCode) {
        return switch (langCode) {
            case "en" -> labelEn;
            case "ar" -> labelAr;
            default -> labelEn;
        };

    }
}
