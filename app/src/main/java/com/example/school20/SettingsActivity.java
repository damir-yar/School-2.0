package com.example.school20;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private Button rename;
    private Button custom;
    private Button about;

    private Switch bl_mode;

    private boolean switchState;

    private LinearLayout layout;

    private LinearLayout menuBot;
    private ImageButton mHome;
    private ImageButton mTime;
    private ImageButton mSet;
    private ImageButton mProf;

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
        about = findViewById(R.id.about);
        bl_mode = findViewById(R.id.sw_black_mode);

        layout = findViewById(R.id.lay_set);

        bl_mode.setChecked(switchState);

        menuBot = findViewById(R.id.menu_bottom);
        mHome = findViewById(R.id.menu_home);
        mTime = findViewById(R.id.menu_timetable);
        mSet = findViewById(R.id.menu_settings);
        mProf = findViewById(R.id.menu_profile);
        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, RaspActivity.class);
                startActivity(intent);
            }
        });
        mSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

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

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
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
            nigth_mode();
        }
        else {
            day_mode();
        }
    }

    private void nigth_mode() {
        rename.setTextColor(getResources().getColor(R.color.white));
        custom.setTextColor(getResources().getColor(R.color.white));
        bl_mode.setTextColor(getResources().getColor(R.color.white));
        about.setTextColor(getResources().getColor(R.color.white));
        layout.setBackgroundColor(getResources().getColor(R.color.nigth_mode));

        rename.setCompoundDrawablesWithIntrinsicBounds(R.drawable.settings_rename_night, 0, 0, 0);
        custom.setCompoundDrawablesWithIntrinsicBounds(R.drawable.settings_redesign_night, 0, 0, 0);
        about.setCompoundDrawablesWithIntrinsicBounds(R.drawable.settings_about_night, 0, 0, 0);
        bl_mode.setCompoundDrawablesWithIntrinsicBounds(R.drawable.settings_night_night, 0, 0, 0);

        menuBot.setBackgroundColor(getResources().getColor(R.color.menu_night));
        mHome.setImageResource(R.drawable.menu_home);
        mTime.setImageResource(R.drawable.menu_timetable);
        mSet.setImageResource(R.drawable.menu_settings);
        mProf.setImageResource(R.drawable.menu_profile);
    }

    private void day_mode() {
        rename.setTextColor(getResources().getColor(R.color.black));
        custom.setTextColor(getResources().getColor(R.color.black));
        bl_mode.setTextColor(getResources().getColor(R.color.black));
        about.setTextColor(getResources().getColor(R.color.black));
        layout.setBackgroundColor(getResources().getColor(R.color.white));

        rename.setCompoundDrawablesWithIntrinsicBounds(R.drawable.settings_rename, 0, 0, 0);
        custom.setCompoundDrawablesWithIntrinsicBounds(R.drawable.settings_redesign, 0, 0, 0);
        about.setCompoundDrawablesWithIntrinsicBounds(R.drawable.settings_about, 0, 0, 0);
        bl_mode.setCompoundDrawablesWithIntrinsicBounds(R.drawable.settings_night, 0, 0, 0);

        menuBot.setBackgroundColor(getResources().getColor(R.color.menu_day));
        mHome.setImageResource(R.drawable.menu_home_day);
        mTime.setImageResource(R.drawable.menu_timetable_day);
        mSet.setImageResource(R.drawable.menu_settings_day);
        mProf.setImageResource(R.drawable.menu_profile_day);
    }
}