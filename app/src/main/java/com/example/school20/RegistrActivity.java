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
import android.util.Log;
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
                    String urlT = "https://api.npoint.io/86de4c9a1714afd58caa";
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
                if(!jsonObject.getJSONObject("connect").getString("cn").equals("true")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка сервера", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ошибка JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }
            String pr = "";
            String symbol = "";
            if (symbol_pol.equals("а") || symbol_pol.equals("А"))
                symbol = "a";
            else if (symbol_pol.equals("т") || symbol_pol.equals("Т"))
                symbol = "t";
            else if (symbol_pol.equals("с") || symbol_pol.equals("С"))
                symbol = "s";
            if (prof_pol.equals("tech"))
                pr = "t";
            else
                pr = "s";

            try {
                String str_obj = "";
                String les = "";
                if (class_pol.equals("11")) {
                    str_obj = "rasp" + class_pol + symbol + pr;
                } else {
                    str_obj = "rasp" + class_pol + symbol;
                }
                JSONObject jsonObject = new JSONObject(result);
                if (sharedPreferences.getString("verson", "").equals(jsonObject.getJSONObject(str_obj).getString("ver"))) {
                }
            } catch (JSONException jsonException) {
                Log.e("MYAPP", "unexpected JSON exception", jsonException);
                jsonException.printStackTrace();
            }

            int i = 0;
            while (i < 8) {
                i++;
                String str_put = "";
                String str_obj = "";
                String les = "";
                if (class_pol.equals("11")) {
                    str_put = "pn" + i + "_" + class_pol + symbol + pr;
                    str_obj = "rasp" + class_pol + symbol + pr;
                } else {
                    str_put = "pn" + i + "_" + class_pol + symbol;
                    str_obj = "rasp" + class_pol + symbol;
                }
                les = "pn" + i;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString(str_put, jsonObject.getJSONObject(str_obj).getString(les));
                    editor.commit();
                } catch (JSONException jsonException) {
                    Log.e("MYAPP", "unexpected JSON exception", jsonException);
                    jsonException.printStackTrace();
                }
            }
            i = 0;
            while (i < 8) {
                i++;
                String str_put = "";
                String str_obj = "";
                String les = "";
                if (class_pol.equals("11")) {
                    str_put = "vt" + i + "_" + class_pol + symbol + pr;
                    str_obj = "rasp" + class_pol + symbol + pr;
                } else {
                    str_put = "vt" + i + "_" + class_pol + symbol;
                    str_obj = "rasp" + class_pol + symbol;
                }
                les = "vt" + i;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString(str_put, jsonObject.getJSONObject(str_obj).getString(les));
                    editor.commit();
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
            i = 0;
            while (i < 8) {
                i++;
                String str_put = "";
                String str_obj = "";
                String les = "";
                if (class_pol.equals("11")) {
                    str_put = "sr" + i + "_" + class_pol + symbol + pr;
                    str_obj = "rasp" + class_pol + symbol + pr;
                } else {
                    str_put = "sr" + i + "_" + class_pol + symbol;
                    str_obj = "rasp" + class_pol + symbol;
                }
                les = "sr" + i;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString(str_put, jsonObject.getJSONObject(str_obj).getString(les));
                    editor.commit();
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
            i = 0;
            while (i < 8) {
                i++;
                String str_put = "";
                String str_obj = "";
                String les = "";
                if (class_pol.equals("11")) {
                    str_put = "ch" + i + "_" + class_pol + symbol + pr;
                    str_obj = "rasp" + class_pol + symbol + pr;
                } else {
                    str_put = "ch" + i + "_" + class_pol + symbol;
                    str_obj = "rasp" + class_pol + symbol;
                }
                les = "ch" + i;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString(str_put, jsonObject.getJSONObject(str_obj).getString(les));
                    editor.commit();
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
            i = 0;
            while (i < 8) {
                i++;
                String str_put = "";
                String str_obj = "";
                String les = "";
                if (class_pol.equals("11")) {
                    str_put = "pt" + i + "_" + class_pol + symbol + pr;
                    str_obj = "rasp" + class_pol + symbol + pr;
                } else {
                    str_put = "pt" + i + "_" + class_pol + symbol;
                    str_obj = "rasp" + class_pol + symbol;
                }
                les = "pt" + i;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString(str_put, jsonObject.getJSONObject(str_obj).getString(les));
                    editor.commit();
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
            i = 0;
            while (i < 8) {
                i++;
                String str_put = "";
                String str_obj = "";
                String les = "";
                if (class_pol.equals("11")) {
                    str_put = "sb" + i + "_" + class_pol + symbol + pr;
                    str_obj = "rasp" + class_pol + symbol + pr;
                } else {
                    str_put = "sb" + i + "_" + class_pol + symbol;
                    str_obj = "rasp" + class_pol + symbol;
                }
                les = "sb" + i;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString(str_put, jsonObject.getJSONObject(str_obj).getString(les));
                    editor.commit();
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
            Toast toast = Toast.makeText(getApplicationContext(),
                            "Загрузка...", Toast.LENGTH_SHORT);
                    toast.show();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(RegistrActivity.this, MainActivity.class);
                    startActivity(intent);
            JSONObject jsonObject = null;
        }
    }
}