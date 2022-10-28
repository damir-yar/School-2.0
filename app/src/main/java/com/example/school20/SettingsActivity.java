package com.example.school20;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private Button rename;
    private Button custom;

    private Switch bl_mode;

    private boolean switchState;

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Настройки");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switchState = sharedPreferences.getBoolean("night_mode", false);

        rename = findViewById(R.id.rename);
        custom = findViewById(R.id.custom);
        bl_mode = findViewById(R.id.sw_black_mode);

        layout = findViewById(R.id.lay_set);

        bl_mode.setChecked(switchState);

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                day_mode();
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                nigth_mode();
                bl_mode.setChecked(true);
                break;
        }
        if (sharedPreferences.getBoolean("night_mode", false) == true) {
            nigth_mode();
        }
        else {
            day_mode();
        }



        rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, RegistrActivity.class);
                startActivity(intent);
            }
        });

        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, CustomActivity.class);
                startActivity(intent);
            }
        });
        nigth();
        bl_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    editor.putBoolean("night_mode", bl_mode.isChecked());
                    editor.commit();
                    nigth();

            }});
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void nigth() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        switchState = sharedPreferences.getBoolean("night_mode", false);
        if (switchState == true) {
            rename.setTextColor(getResources().getColor(R.color.white));
            custom.setTextColor(getResources().getColor(R.color.white));
            bl_mode.setTextColor(getResources().getColor(R.color.white));
            layout.setBackgroundColor(getResources().getColor(R.color.nigth_mode));
        }
        else {
            rename.setTextColor(getResources().getColor(R.color.black));
            custom.setTextColor(getResources().getColor(R.color.black));
            bl_mode.setTextColor(getResources().getColor(R.color.black));
            layout.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    private void nigth_mode() {
        rename.setTextColor(getResources().getColor(R.color.white));
        custom.setTextColor(getResources().getColor(R.color.white));
        bl_mode.setTextColor(getResources().getColor(R.color.white));
        layout.setBackgroundColor(getResources().getColor(R.color.nigth_mode));
    }

    private void day_mode() {
        rename.setTextColor(getResources().getColor(R.color.black));
        custom.setTextColor(getResources().getColor(R.color.black));
        bl_mode.setTextColor(getResources().getColor(R.color.black));
        layout.setBackgroundColor(getResources().getColor(R.color.white));
    }
}