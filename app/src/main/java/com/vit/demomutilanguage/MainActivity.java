package com.vit.demomutilanguage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_LANG = "PREF_LANG";
    public static final String LANG_VI = "vi";
    public static final String LANG_EN = "en";

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getPreferences(MODE_PRIVATE);

        checkChoosedLang();

        findViewById(R.id.button_en).setOnClickListener(v -> setLocale(LANG_EN));
        findViewById(R.id.button_vi).setOnClickListener(v -> setLocale(LANG_VI));
    }

    private void checkChoosedLang() {
        if (!mSharedPreferences.getString(PREF_LANG, "").equals("")) {
            findViewById(R.id.button_en).setVisibility(View.INVISIBLE);
            findViewById(R.id.button_vi).setVisibility(View.INVISIBLE);
        }
    }

    public void setLocale(String lang) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PREF_LANG, lang);
        editor.commit();

        Locale locale = new Locale(lang);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        createConfigurationContext(configuration);
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        startActivity(new Intent(this, MainActivity.class));
    }
}
