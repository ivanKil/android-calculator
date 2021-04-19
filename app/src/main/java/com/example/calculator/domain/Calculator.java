package com.example.calculator.domain;

import android.os.Parcelable;

public interface Calculator extends Parcelable {
    void addSymbol(String text);

    String getText();
}
