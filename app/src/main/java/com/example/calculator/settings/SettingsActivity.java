package com.example.calculator.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.R;
import com.example.calculator.mainscreen.ui.CalculatorActivity;

public class SettingsActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.setSavedTheme(this);
        setContentView(R.layout.activity_settings);
        init();
    }

    private void init() {
        settings = new Settings(getApplicationContext());
        View.OnClickListener selectorTheme = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.radio_theme1)
                    settings.setTheme(R.style.AppTheme);
                else if (v.getId() == R.id.radio_theme2)
                    settings.setTheme(R.style.AppTheme2);
                startActivity(new Intent(SettingsActivity.this, CalculatorActivity.class));
            }
        };
        findViewById(R.id.radio_theme1).setOnClickListener(selectorTheme);
        findViewById(R.id.radio_theme2).setOnClickListener(selectorTheme);
        radioGroup = findViewById(R.id.radioGroup);
        setRadioSelect(settings.getTheme());
    }

    public void setRadioSelect(int themeId) {
        if (themeId == R.style.AppTheme || themeId == -1)
            radioGroup.check(R.id.radio_theme1);
        else if (themeId == R.style.AppTheme2)
            radioGroup.check(R.id.radio_theme2);
    }
}