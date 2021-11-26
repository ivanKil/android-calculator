package com.example.calculator.mainscreen.domain;

public enum ErrCalc {
    DIV_0("ERR_DIV_0"), PARSE_TEXT("PARSE_TEXT");
    private final String text;

    ErrCalc(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}