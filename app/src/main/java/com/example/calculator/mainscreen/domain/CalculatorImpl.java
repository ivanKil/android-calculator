package com.example.calculator.mainscreen.domain;

import android.os.Parcel;

import java.math.BigDecimal;
import java.math.MathContext;

public class CalculatorImpl implements Calculator {
    private String operand1 = "";
    private String operand2 = "";
    private String operation = "";
    private boolean err = false;

    public CalculatorImpl() {
    }


    public CalculatorImpl(Parcel in) {
        operand1 = in.readString();
        operand2 = in.readString();
        operation = in.readString();
        err = in.readByte() != 0;
    }

    public static final Creator<com.example.calculator.mainscreen.domain.CalculatorImpl> CREATOR = new Creator<com.example.calculator.mainscreen.domain.CalculatorImpl>() {
        @Override
        public CalculatorImpl createFromParcel(Parcel in) {
            return new CalculatorImpl(in);
        }

        @Override
        public CalculatorImpl[] newArray(int size) {
            return new CalculatorImpl[size];
        }
    };

    @Override
    public boolean addText(String text) {
        if (text != null) {
            char[] array = text.toCharArray();
            for (char symb : array) {
                if (!"1234567890./*-+=".contains(String.valueOf(symb))) {
                    setError(ErrCalc.PARSE_TEXT);
                    return false;
                }
            }
            for (char symb : array) {
                addSymbol(String.valueOf(symb));
            }
            return true;
        }
        return false;
    }

    public void addSymbol(String symbol) {
        if (err) {
            clear();
            err = false;
        }
        if ("<-".equals(symbol)) {
            del();
            return;
        }
        if ("C".equals(symbol)) {
            clear();
            return;
        }
        if ("/*-+=".contains(symbol)) {
            if ("".equals(operand1)) {
                operand1 = "0";
            }
            if (!"".equals(operand2)) {
                calculate();
            }
            if (!err && !"=".equals(symbol)) {
                operation = symbol;
            }
            return;
        }

        if (operation.equals("")) {
            if (!".".equals(symbol) || !operand1.contains("."))
                operand1 = operand1 + symbol;
        } else {
            if (!".".equals(symbol) || !operand2.contains("."))
                operand2 = operand2 + symbol;
        }
    }

    public String getText() {
        return operand1 + operation + operand2;
    }

    private void calculate() {
        switch (operation) {
            case "/":
                if ("0".equals(operand2)) {
                    err = true;
                    setError(ErrCalc.DIV_0);
                    break;
                }
                operand1 = new BigDecimal(operand1).divide(new BigDecimal(operand2), new MathContext(10)).toString();
                break;
            case "*":
                operand1 = new BigDecimal(operand1).multiply(new BigDecimal(operand2)).toString();
                break;
            case "+":
                operand1 = new BigDecimal(operand1).add(new BigDecimal(operand2)).toString();
                break;
            case "-":
                operand1 = new BigDecimal(operand1).subtract(new BigDecimal(operand2)).toString();
                break;
        }
        operation = "";
        operand2 = "";
    }

    private void setError(ErrCalc errCalc) {
        err = true;
        operand1 = errCalc.getText();
        operation = "";
        operand2 = "";
    }

    private void del() {
        if (!"".equals(operand2)) {
            operand2 = operand2.substring(0, operand2.length() - 1);
        } else if (!operation.equals("")) {
            operation = "";
        } else if (!"".equals(operand1)) {
            operand1 = operand1.substring(0, operand1.length() - 1);
        }
    }

    private void clear() {
        operand2 = "";
        operation = "";
        operand1 = "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(operand1);
        dest.writeString(operand2);
        dest.writeString(operation);
        dest.writeByte((byte) (err ? 1 : 0));
    }
}