package com.example.calculator.settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.calculator.R;

public class Settings {
    private static final String THEME_PREF_NAME = "ThemePref";
    private static final String THEME_PREF_KEY = "Theme";
    private SharedPreferences pref;

    public static void setSavedTheme(Activity view) {
        int themeId = view.getSharedPreferences(THEME_PREF_NAME, Context.MODE_PRIVATE).getInt(THEME_PREF_KEY, R.style.AppTheme);
        if (themeId != -1)
            view.setTheme(themeId);
    }


    public Settings(Context context) {
        pref = context.getSharedPreferences(THEME_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setTheme(int theme) {
        pref.edit().putInt(THEME_PREF_KEY, theme).apply();
    }

    public int getTheme() {
        return pref.getInt(THEME_PREF_KEY, -1);
    }

}
