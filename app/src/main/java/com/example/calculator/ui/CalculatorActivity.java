package com.example.calculator.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.R;
import com.example.calculator.domain.Calculator;
import com.example.calculator.domain.CalculatorImpl;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {
    private TextView screen;
    private Calculator calc;
    private CalculatorPresenter presenter;
    private ScrollView scrollScreen;

    private static String KEY_CALCULATOR = "calc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        screen = findViewById(R.id.tv_display);
        scrollScreen = findViewById(R.id.scroll_screen);
        presenter = new CalculatorPresenter();
        presenter.setView(this);
        if (savedInstanceState == null) {
            presenter.setCalculator(calc = new CalculatorImpl());
        }
        for (int id : new Integer[]{R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
                R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn_div,
                R.id.btn_mul, R.id.btn_min, R.id.btn_plus, R.id.btn_point, R.id.btn_equals,
                R.id.btn_del, R.id.btn_clear}) {
            final Button but = ((Button) findViewById(id));
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.onButtonClick(but.getText().toString());
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(KEY_CALCULATOR, calc);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        presenter.setCalculator(calc = (Calculator) instanceState.getParcelable(KEY_CALCULATOR));
    }

    public void setScreenText(String text) {
        screen.setText(text);
        scrollScreen.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}