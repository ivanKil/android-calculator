package com.example.calculator.mainscreen.domain;

import android.os.Parcelable;

public interface Calculator extends Parcelable {
    void addSymbol(String text);

    boolean addText(String text);

    String getText();
}
