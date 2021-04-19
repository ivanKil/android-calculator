package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView screen;
    Calculator calc;
    private static String KEY_CALCULATOR = "calc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        screen = findViewById(R.id.tv_display);
        final ScrollView scrollScreen = findViewById(R.id.scroll_screen);
        calc = new Calculator();
        for (int id : new Integer[]{R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
                R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn_div,
                R.id.btn_mul, R.id.btn_min, R.id.btn_plus, R.id.btn_point, R.id.btn_equals,
                R.id.btn_del, R.id.btn_clear}) {
            final Button but = ((Button) findViewById(id));
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calc.addSymbol(but.getText().toString());
                    screen.setText(calc.getText());
                    scrollScreen.fullScroll(View.FOCUS_DOWN);
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
        calc = (Calculator) instanceState.getParcelable(KEY_CALCULATOR);
        screen.setText(calc.getText());
    }

}
