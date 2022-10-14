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
import java.util.concurrent.TimeUnit;

public class RegistrActivity extends AppCompatActivity {

    private EditText reg_name;
    private EditText reg_class;
    private EditText reg_symbol;
    private RadioButton prof_not;
    private RadioButton prof_tech;
    private RadioButton prof_social;
    private Button but;

    private String day_week;
    private String name_pol;
    private String class_pol;
    private String symbol_pol;
    private String prof_pol;

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
                    editor.commit();
                    name_pol = sharedPreferences.getString("name", "unknown");
                    class_pol = sharedPreferences.getString("class", "unknown");
                    symbol_pol = sharedPreferences.getString("symbol", "unknown");
                    prof_pol = sharedPreferences.getString("prof", "unknown");
                    String urlT = "https://api.jsonbin.io/v3/b/6341bce12b3499323bd7c899";
                    new getUrlData().execute(urlT);
                }
            }
        });

    }
    private class getUrlData extends AsyncTask<String, String, String> {
        protected void onPreExecute(){ }
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null)
                    buffer.append(line).append("\n");

                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RegistrActivity.this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            try {
                JSONObject jsonObject = new JSONObject(result);
                if(!jsonObject.getJSONObject("record").getJSONObject("connect").getString("cn").equals("true")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка сервера", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ошибка JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }
            if (class_pol.equals("11") && (symbol_pol.equals("а") || symbol_pol.equals("А")) && prof_pol.equals("tech")) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString("pn1_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pn1"));
                    editor.putString("pn2_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pn2"));
                    editor.putString("pn3_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pn3"));
                    editor.putString("pn4_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pn4"));
                    editor.putString("pn5_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pn5"));
                    editor.putString("pn6_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pn6"));
                    editor.putString("pn7_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pn7"));
                    editor.putString("pn8_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pn8"));

                    editor.putString("vt1_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("vt1"));
                    editor.putString("vt2_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("vt2"));
                    editor.putString("vt3_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("vt3"));
                    editor.putString("vt4_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("vt4"));
                    editor.putString("vt5_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("vt5"));
                    editor.putString("vt6_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("vt6"));
                    editor.putString("vt7_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("vt7"));
                    editor.putString("vt8_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("vt8"));

                    editor.putString("sr1_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sr1"));
                    editor.putString("sr2_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sr2"));
                    editor.putString("sr3_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sr3"));
                    editor.putString("sr4_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sr4"));
                    editor.putString("sr5_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sr5"));
                    editor.putString("sr6_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sr6"));
                    editor.putString("sr7_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sr7"));
                    editor.putString("sr8_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sr8"));

                    editor.putString("ch1_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("ch1"));
                    editor.putString("ch2_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("ch2"));
                    editor.putString("ch3_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("ch3"));
                    editor.putString("ch4_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("ch4"));
                    editor.putString("ch5_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("ch5"));
                    editor.putString("ch6_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("ch6"));
                    editor.putString("ch7_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("ch7"));
                    editor.putString("ch8_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("ch8"));

                    editor.putString("pt1_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pt1"));
                    editor.putString("pt2_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pt2"));
                    editor.putString("pt3_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pt3"));
                    editor.putString("pt4_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pt4"));
                    editor.putString("pt5_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pt5"));
                    editor.putString("pt6_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pt6"));
                    editor.putString("pt7_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pt7"));
                    editor.putString("pt8_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("pt8"));

                    editor.putString("sb1_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sb1"));
                    editor.putString("sb2_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sb2"));
                    editor.putString("sb3_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sb3"));
                    editor.putString("sb4_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sb4"));
                    editor.putString("sb5_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sb5"));
                    editor.putString("sb6_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sb6"));
                    editor.putString("sb7_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sb7"));
                    editor.putString("sb8_11at", jsonObject.getJSONObject("record").getJSONObject("rasp11at").getString("sb8"));
                    editor.commit();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Расписание обновлено", Toast.LENGTH_SHORT);
                    toast.show();
                    TimeUnit.SECONDS.sleep(3);
                    Intent intent = new Intent(RegistrActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (JSONException jsonException) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка JSON", Toast.LENGTH_SHORT);
                    toast.show();
                    jsonException.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (class_pol.equals("11") && (symbol_pol.equals("а") || symbol_pol.equals("А")) && prof_pol.equals("social")){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString("pn1_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pn1"));
                    editor.putString("pn2_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pn2"));
                    editor.putString("pn3_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pn3"));
                    editor.putString("pn4_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pn4"));
                    editor.putString("pn5_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pn5"));
                    editor.putString("pn6_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pn6"));
                    editor.putString("pn7_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pn7"));
                    editor.putString("pn8_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pn8"));

                    editor.putString("vt1_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("vt1"));
                    editor.putString("vt2_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("vt2"));
                    editor.putString("vt3_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("vt3"));
                    editor.putString("vt4_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("vt4"));
                    editor.putString("vt5_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("vt5"));
                    editor.putString("vt6_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("vt6"));
                    editor.putString("vt7_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("vt7"));
                    editor.putString("vt8_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("vt8"));

                    editor.putString("sr1_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sr1"));
                    editor.putString("sr2_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sr2"));
                    editor.putString("sr3_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sr3"));
                    editor.putString("sr4_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sr4"));
                    editor.putString("sr5_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sr5"));
                    editor.putString("sr6_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sr6"));
                    editor.putString("sr7_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sr7"));
                    editor.putString("sr8_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sr8"));

                    editor.putString("ch1_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("ch1"));
                    editor.putString("ch2_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("ch2"));
                    editor.putString("ch3_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("ch3"));
                    editor.putString("ch4_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("ch4"));
                    editor.putString("ch5_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("ch5"));
                    editor.putString("ch6_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("ch6"));
                    editor.putString("ch7_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("ch7"));
                    editor.putString("ch8_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("ch8"));

                    editor.putString("pt1_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pt1"));
                    editor.putString("pt2_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pt2"));
                    editor.putString("pt3_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pt3"));
                    editor.putString("pt4_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pt4"));
                    editor.putString("pt5_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pt5"));
                    editor.putString("pt6_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pt6"));
                    editor.putString("pt7_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pt7"));
                    editor.putString("pt8_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("pt8"));

                    editor.putString("sb1_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sb1"));
                    editor.putString("sb2_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sb2"));
                    editor.putString("sb3_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sb3"));
                    editor.putString("sb4_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sb4"));
                    editor.putString("sb5_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sb5"));
                    editor.putString("sb6_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sb6"));
                    editor.putString("sb7_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sb7"));
                    editor.putString("sb8_11as", jsonObject.getJSONObject("record").getJSONObject("rasp11as").getString("sb8"));
                    editor.commit();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Расписание обновлено", Toast.LENGTH_SHORT);
                    toast.show();
                    TimeUnit.SECONDS.sleep(3);
                    Intent intent = new Intent(RegistrActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (JSONException jsonException) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка JSON", Toast.LENGTH_SHORT);
                    toast.show();
                    jsonException.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(class_pol.equals("10") && (symbol_pol.equals("т") || symbol_pol.equals("Т"))) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString("pn1_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pn1"));
                    editor.putString("pn2_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pn2"));
                    editor.putString("pn3_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pn3"));
                    editor.putString("pn4_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pn4"));
                    editor.putString("pn5_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pn5"));
                    editor.putString("pn6_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pn6"));
                    editor.putString("pn7_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pn7"));
                    editor.putString("pn8_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pn8"));

                    editor.putString("vt1_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("vt1"));
                    editor.putString("vt2_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("vt2"));
                    editor.putString("vt3_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("vt3"));
                    editor.putString("vt4_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("vt4"));
                    editor.putString("vt5_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("vt5"));
                    editor.putString("vt6_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("vt6"));
                    editor.putString("vt7_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("vt7"));
                    editor.putString("vt8_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("vt8"));

                    editor.putString("sr1_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sr1"));
                    editor.putString("sr2_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sr2"));
                    editor.putString("sr3_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sr3"));
                    editor.putString("sr4_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sr4"));
                    editor.putString("sr5_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sr5"));
                    editor.putString("sr6_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sr6"));
                    editor.putString("sr7_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sr7"));
                    editor.putString("sr8_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sr8"));

                    editor.putString("ch1_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("ch1"));
                    editor.putString("ch2_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("ch2"));
                    editor.putString("ch3_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("ch3"));
                    editor.putString("ch4_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("ch4"));
                    editor.putString("ch5_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("ch5"));
                    editor.putString("ch6_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("ch6"));
                    editor.putString("ch7_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("ch7"));
                    editor.putString("ch8_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("ch8"));

                    editor.putString("pt1_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pt1"));
                    editor.putString("pt2_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pt2"));
                    editor.putString("pt3_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pt3"));
                    editor.putString("pt4_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pt4"));
                    editor.putString("pt5_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pt5"));
                    editor.putString("pt6_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pt6"));
                    editor.putString("pt7_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pt7"));
                    editor.putString("pt8_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("pt8"));

                    editor.putString("sb1_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sb1"));
                    editor.putString("sb2_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sb2"));
                    editor.putString("sb3_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sb3"));
                    editor.putString("sb4_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sb4"));
                    editor.putString("sb5_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sb5"));
                    editor.putString("sb6_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sb6"));
                    editor.putString("sb7_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sb7"));
                    editor.putString("sb8_10t", jsonObject.getJSONObject("record").getJSONObject("rasp10t").getString("sb8"));
                    editor.commit();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Расписание обновлено", Toast.LENGTH_SHORT);
                    toast.show();
                    TimeUnit.SECONDS.sleep(3);
                    Intent intent = new Intent(RegistrActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (JSONException jsonException) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка JSON", Toast.LENGTH_SHORT);
                    toast.show();
                    jsonException.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(class_pol.equals("10") && (symbol_pol.equals("с") || symbol_pol.equals("С"))) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString("pn1_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pn1"));
                    editor.putString("pn2_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pn2"));
                    editor.putString("pn3_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pn3"));
                    editor.putString("pn4_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pn4"));
                    editor.putString("pn5_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pn5"));
                    editor.putString("pn6_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pn6"));
                    editor.putString("pn7_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pn7"));
                    editor.putString("pn8_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pn8"));

                    editor.putString("vt1_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("vt1"));
                    editor.putString("vt2_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("vt2"));
                    editor.putString("vt3_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("vt3"));
                    editor.putString("vt4_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("vt4"));
                    editor.putString("vt5_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("vt5"));
                    editor.putString("vt6_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("vt6"));
                    editor.putString("vt7_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("vt7"));
                    editor.putString("vt8_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("vt8"));

                    editor.putString("sr1_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sr1"));
                    editor.putString("sr2_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sr2"));
                    editor.putString("sr3_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sr3"));
                    editor.putString("sr4_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sr4"));
                    editor.putString("sr5_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sr5"));
                    editor.putString("sr6_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sr6"));
                    editor.putString("sr7_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sr7"));
                    editor.putString("sr8_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sr8"));

                    editor.putString("ch1_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("ch1"));
                    editor.putString("ch2_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("ch2"));
                    editor.putString("ch3_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("ch3"));
                    editor.putString("ch4_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("ch4"));
                    editor.putString("ch5_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("ch5"));
                    editor.putString("ch6_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("ch6"));
                    editor.putString("ch7_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("ch7"));
                    editor.putString("ch8_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("ch8"));

                    editor.putString("pt1_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pt1"));
                    editor.putString("pt2_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pt2"));
                    editor.putString("pt3_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pt3"));
                    editor.putString("pt4_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pt4"));
                    editor.putString("pt5_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pt5"));
                    editor.putString("pt6_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pt6"));
                    editor.putString("pt7_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pt7"));
                    editor.putString("pt8_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("pt8"));

                    editor.putString("sb1_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sb1"));
                    editor.putString("sb2_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sb2"));
                    editor.putString("sb3_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sb3"));
                    editor.putString("sb4_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sb4"));
                    editor.putString("sb5_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sb5"));
                    editor.putString("sb6_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sb6"));
                    editor.putString("sb7_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sb7"));
                    editor.putString("sb8_10s", jsonObject.getJSONObject("record").getJSONObject("rasp10s").getString("sb8"));
                    editor.commit();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Расписание обновлено", Toast.LENGTH_SHORT);
                    toast.show();
                    TimeUnit.SECONDS.sleep(3);
                    Intent intent = new Intent(RegistrActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (JSONException jsonException) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка JSON", Toast.LENGTH_SHORT);
                    toast.show();
                    jsonException.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            JSONObject jsonObject = null;
        }
    }
}