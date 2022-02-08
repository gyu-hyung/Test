package com.example.project03_rycyclerpager.recycler;

public class RecDTO {
    private int redId;
    private String textStr;

    public RecDTO(int redId, String textStr) {
        this.redId = redId;
        this.textStr = textStr;
    }

    public int getRedId() {
        return redId;
    }

    public void setRedId(int redId) {
        this.redId = redId;
    }

    public String getTextStr() {
        return textStr;
    }

    public void setTextStr(String textStr) {
        this.textStr = textStr;
    }
}
