package com.example.school20;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegistrActivity extends AppCompatActivity {

    private EditText reg_name;
    private EditText reg_class;
    private EditText reg_symbol;
    private RadioButton prof_not;
    private RadioButton prof_tech;
    private RadioButton prof_social;
    private Button but;

    private String prof = "unknow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);

        reg_name = findViewById(R.id.reg_name);
        reg_class = findViewById(R.id.reg_class);
        reg_symbol = findViewById(R.id.reg_symbol);

        prof_not = findViewById(R.id.reg_prof_not);
        prof_tech = findViewById(R.id.reg_prof_tech);
        prof_social = findViewById(R.id.reg_prof_social);

        but = findViewById(R.id.button);

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                reg_name.setTextColor(WHITE);
                reg_class.setTextColor(WHITE);
                reg_symbol.setTextColor(WHITE);
                prof_social.setTextColor(WHITE);
                prof_tech.setTextColor(WHITE);
                prof_not.setTextColor(WHITE);
                but.setBackgroundColor(WHITE);
                but.setTextColor(BLACK);
                break;
        }

        prof_not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prof = "not";
            }
        });
        prof_tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prof = "tech";
            }
        });
        prof_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prof = "social";
            }
        });

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reg_name.getText().toString().equals("") || reg_class.getText().toString().equals("") || reg_symbol.getText().toString().equals("") || prof.equals("unknow")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Заполни все поля", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RegistrActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", reg_name.getText().toString());
                    editor.putString("class", reg_class.getText().toString());
                    editor.putString("symbol", reg_symbol.getText().toString());
                    editor.putString("prof", prof);
                    editor.putString("ddd", "1");
                    editor.commit();
                    Intent intent = new Intent(RegistrActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}