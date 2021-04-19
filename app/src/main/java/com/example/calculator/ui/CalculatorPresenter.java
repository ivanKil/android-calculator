package com.example.calculator.ui;

import com.example.calculator.domain.Calculator;

public class CalculatorPresenter {
    private CalculatorView view;
    private Calculator calculator;

    public void setView(CalculatorView view) {
        this.view = view;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
        view.setScreenText(calculator.getText());
    }

    public void onButtonClick(String text) {
        calculator.addSymbol(text);
        view.setScreenText(calculator.getText());
    }

    public void detachView() {
        view = null;
    }
}
