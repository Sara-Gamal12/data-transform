package com.example.datatransform.model;

public class ThirdPartyKeyMapping {
    private int id;
    private final int labelId;
    private final String thirdPartyKey;

    private final String thirdPartySource;

    public ThirdPartyKeyMapping(int id, int labelId, String thirdPartyKey, String thirdPartySource) {
        this.id = id;
        this.labelId = labelId;
        this.thirdPartyKey = thirdPartyKey;
        this.thirdPartySource = thirdPartySource;
    }

    public int getLabelId() {
        return labelId;
    }

    public String getThirdPartyKey() {
        return thirdPartyKey;
    }

    public String getThirdPartySource() {
        return thirdPartySource;
    }
}
