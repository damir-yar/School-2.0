package com.example.school20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
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
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView person_name;
    private TextView person_class;
    private TextView person_symbol;

    private TextView date;

    private TextView z1;
    private TextView z2;
    private TextView z3;
    private TextView z4;
    private TextView z5;
    private TextView z6;
    private TextView z7;
    private TextView z8;
    private TextView u1;
    private TextView u2;
    private TextView u3;
    private TextView u4;
    private TextView u5;
    private TextView u6;
    private TextView u7;
    private TextView u8;

    private Switch s;
    private Button back;
    private Button next;

    private String day_week;
    private String name_pol;
    private String class_pol;
    private String symbol_pol;
    private String prof_pol;

    private Button easter;
    private int east = 0;

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("School 2.0");

        person_name = findViewById(R.id.person_name);
        person_class = findViewById(R.id.person_class);
        person_symbol = findViewById(R.id.person_symbol);
        date = findViewById(R.id.date);
        z1 = findViewById(R.id.time_one);
        z2 = findViewById(R.id.time_two);
        z3 = findViewById(R.id.time_three);
        z4 = findViewById(R.id.time_four);
        z5 = findViewById(R.id.time_five);
        z6 = findViewById(R.id.time_six);
        z7 = findViewById(R.id.time_seven);
        z8 = findViewById(R.id.time_eight);
        u1 = findViewById(R.id.les_one);
        u2 = findViewById(R.id.les_two);
        u3 = findViewById(R.id.les_three);
        u4 = findViewById(R.id.les_four);
        u5 = findViewById(R.id.les_five);
        u6 = findViewById(R.id.les_six);
        u7 = findViewById(R.id.les_seven);
        u8 = findViewById(R.id.les_eight);
        s = findViewById(R.id.switch_s);
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
        easter = findViewById(R.id.easter);
        layout = findViewById(R.id.a);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        name_pol = sharedPreferences.getString("name", "unknown");
        class_pol = sharedPreferences.getString("class", "unknown");
        symbol_pol = sharedPreferences.getString("symbol", "unknown");
        prof_pol = sharedPreferences.getString("prof", "unknown");

        if (name_pol.equals("unknown") || class_pol.equals("unknown") || symbol_pol.equals("unknown")
        || name_pol.equals("") || class_pol.equals("") || symbol_pol.equals("")){
            Intent intent = new Intent(MainActivity.this, RegistrActivity.class);
            startActivity(intent);
        }

        person_name.setText(name_pol);
        person_class.setText(class_pol);
        person_symbol.setText(symbol_pol);

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                z1.setTextColor(getResources().getColor(R.color.black));
                z2.setTextColor(getResources().getColor(R.color.black));
                z3.setTextColor(getResources().getColor(R.color.black));
                z4.setTextColor(getResources().getColor(R.color.black));
                z5.setTextColor(getResources().getColor(R.color.black));
                z6.setTextColor(getResources().getColor(R.color.black));
                z7.setTextColor(getResources().getColor(R.color.black));
                z8.setTextColor(getResources().getColor(R.color.black));

                u1.setTextColor(getResources().getColor(R.color.black));
                u2.setTextColor(getResources().getColor(R.color.black));
                u3.setTextColor(getResources().getColor(R.color.black));
                u4.setTextColor(getResources().getColor(R.color.black));
                u5.setTextColor(getResources().getColor(R.color.black));
                u6.setTextColor(getResources().getColor(R.color.black));
                u7.setTextColor(getResources().getColor(R.color.black));
                u8.setTextColor(getResources().getColor(R.color.black));

                person_name.setTextColor(getResources().getColor(R.color.black));
                person_class.setTextColor(getResources().getColor(R.color.black));
                person_symbol.setTextColor(getResources().getColor(R.color.black));

                date.setTextColor(getResources().getColor(R.color.black));

                next.setTextColor(getResources().getColor(R.color.black));
                back.setTextColor(getResources().getColor(R.color.black));
                back.setBackgroundColor(getResources().getColor(R.color.white));
                next.setBackgroundColor(getResources().getColor(R.color.white));

                s.setTextColor(getResources().getColor(R.color.black));

                layout.setBackgroundResource(R.drawable.background_main);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                z1.setTextColor(getResources().getColor(R.color.black));
                z2.setTextColor(getResources().getColor(R.color.black));
                z3.setTextColor(getResources().getColor(R.color.black));
                z4.setTextColor(getResources().getColor(R.color.black));
                z5.setTextColor(getResources().getColor(R.color.black));
                z6.setTextColor(getResources().getColor(R.color.black));
                z7.setTextColor(getResources().getColor(R.color.black));
                z8.setTextColor(getResources().getColor(R.color.black));

                u1.setTextColor(getResources().getColor(R.color.black));
                u2.setTextColor(getResources().getColor(R.color.black));
                u3.setTextColor(getResources().getColor(R.color.black));
                u4.setTextColor(getResources().getColor(R.color.black));
                u5.setTextColor(getResources().getColor(R.color.black));
                u6.setTextColor(getResources().getColor(R.color.black));
                u7.setTextColor(getResources().getColor(R.color.black));
                u8.setTextColor(getResources().getColor(R.color.black));

                person_name.setTextColor(getResources().getColor(R.color.black));
                person_class.setTextColor(getResources().getColor(R.color.black));
                person_symbol.setTextColor(getResources().getColor(R.color.black));

                date.setTextColor(getResources().getColor(R.color.white));

                next.setTextColor(getResources().getColor(R.color.white));
                next.setBackgroundColor(getResources().getColor(R.color.black));
                back.setTextColor(getResources().getColor(R.color.white));
                back.setBackgroundColor(getResources().getColor(R.color.black));

                s.setTextColor(getResources().getColor(R.color.white));

                layout.setBackgroundColor(getResources().getColor(R.color.black));

                break;
        }

        clend();
        date.setText(day_week);
        rasp();

        layout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public boolean onSwipeTop() {
                Intent intent2 = new Intent(MainActivity.this, RaspActivity.class);
                startActivity(intent2);
                return true;
            }
            public boolean onSwipeRight() {
                switch (day_week) {
                    case "Понедельник":
                        day_week = "Воскресенье";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Вторник":
                        day_week = "Понедельник";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Среда":
                        day_week = "Вторник";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Четверг":
                        day_week = "Среда";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Пятница":
                        day_week = "Четверг";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Суббота":
                        day_week = "Пятница";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Воскресенье":
                        day_week = "Суббота";
                        rasp();
                        date.setText(day_week);
                        break;
                }
                sOnClick();
                return true;
            }
            public boolean onSwipeLeft() {
                switch (day_week) {
                    case "Понедельник":
                        day_week = "Вторник";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Вторник":
                        day_week = "Среда";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Среда":
                        day_week = "Четверг";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Четверг":
                        day_week = "Пятница";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Пятница":
                        day_week = "Суббота";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Суббота":
                        day_week = "Воскресенье";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Воскресенье":
                        day_week = "Понедельник";
                        rasp();
                        date.setText(day_week);
                        break;
                }
                sOnClick();
                return true;
            }
            public boolean onSwipeBottom() {
                return true;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (day_week) {
                    case "Понедельник":
                        day_week = "Воскресенье";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Вторник":
                        day_week = "Понедельник";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Среда":
                        day_week = "Вторник";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Четверг":
                        day_week = "Среда";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Пятница":
                        day_week = "Четверг";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Суббота":
                        day_week = "Пятница";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Воскресенье":
                        day_week = "Суббота";
                        rasp();
                        date.setText(day_week);
                        break;
                }
                sOnClick();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (day_week) {
                    case "Понедельник":
                        day_week = "Вторник";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Вторник":
                        day_week = "Среда";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Среда":
                        day_week = "Четверг";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Четверг":
                        day_week = "Пятница";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Пятница":
                        day_week = "Суббота";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Суббота":
                        day_week = "Воскресенье";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "Воскресенье":
                        day_week = "Понедельник";
                        rasp();
                        date.setText(day_week);
                        break;
                }
                sOnClick();
            }
        });

        raspZ();
        rasp();

        easter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (east < 5) {
                    east += 1;
                    int a = 5-east;
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Осталось " + a + " шага", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (east == 5) {
                    Intent intent = new Intent(MainActivity.this, EasterActivity.class);
                    startActivity(intent);
                    east = 0;
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
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            try {
                JSONObject jsonObject = new JSONObject(result);
                if(!jsonObject.getJSONObject("record").getJSONObject("connect").getString("cn").equals("true")) {
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
                } catch (JSONException jsonException) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка JSON", Toast.LENGTH_SHORT);
                    toast.show();
                    jsonException.printStackTrace();
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
                } catch (JSONException jsonException) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка JSON", Toast.LENGTH_SHORT);
                    toast.show();
                    jsonException.printStackTrace();
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
                } catch (JSONException jsonException) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка JSON", Toast.LENGTH_SHORT);
                    toast.show();
                    jsonException.printStackTrace();
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
                } catch (JSONException jsonException) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка JSON", Toast.LENGTH_SHORT);
                    toast.show();
                    jsonException.printStackTrace();
                }
            }
            JSONObject jsonObject = null;
        }
    }

    private class getUrlData1 extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
        }

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
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (!jsonObject.getJSONObject("record").getJSONObject("connect").getString("cn").equals("true")) {
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

            try {
                JSONObject jsonObject = new JSONObject(result);
                String app = jsonObject.getJSONObject("record").getJSONObject("connect").getString("app");
                Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(app));
                startActivity(intent4);
            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ошибка JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }
        }JSONObject jsonObject = null;
    }

    void clend() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day){
            case Calendar.SUNDAY:
                day_week = "Воскресенье";
                break;
            case Calendar.MONDAY:
                day_week = "Понедельник";
                break;
            case Calendar.TUESDAY:
                day_week = "Вторник";
                break;
            case Calendar.WEDNESDAY:
                day_week = "Среда";
                break;
            case Calendar.THURSDAY:
                day_week = "Четверг";
                break;
            case Calendar.FRIDAY:
                day_week = "Пятница";
                break;
            case Calendar.SATURDAY:
                day_week = "Суббота";
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about:
                Intent intent1 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_update_app:
                boolean inet = isNetworkConnected();
                if (inet == true) {
                    String urlT = "https://api.jsonbin.io/v3/b/6341bce12b3499323bd7c899";
                    new getUrlData1().execute(urlT);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Нет подключения к интернету", Toast.LENGTH_SHORT);
                    toast.show();
                }
                return true;
            case R.id.action_update:
                boolean inet1 = isNetworkConnected();
                if (inet1 == true) {
                    String url1T = "https://api.jsonbin.io/v3/b/6341bce12b3499323bd7c899";
                    new getUrlData().execute(url1T);
                    clend();
                    rasp();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Нет подключения к интернету", Toast.LENGTH_SHORT);
                    toast.show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener (Context ctx){
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public boolean onSwipeRight() {
            return false;
        }

        public boolean onSwipeLeft() {
            return false;
        }

        public boolean onSwipeTop() {
            return false;
        }

        public boolean onSwipeBottom() {
            return false;
        }
    }

    void raspZ() {
        z1.setText(R.string.rasp_z1_default);
        z2.setText(R.string.rasp_z2_default);
        z3.setText(R.string.rasp_z3_default);
        z4.setText(R.string.rasp_z4_default);
        z5.setText(R.string.rasp_z5_default);
        z6.setText(R.string.rasp_z6_default);
        z7.setText(R.string.rasp_z7_default);
        z8.setText(R.string.rasp_z8_default);
        sOnClick();
        }

    void sOnClick() {
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((Switch) v).isChecked();
                if (checked) {
                    z1.setText(R.string.rasp_z1_s);
                    z2.setText(R.string.rasp_z2_s);
                    z3.setText(R.string.rasp_z3_s);
                    z4.setText(R.string.rasp_z4_s);
                    z5.setText(R.string.rasp_z5_s);
                    z6.setText(R.string.rasp_z6_s);
                    z7.setText(R.string.rasp_z7_s);
                    z8.setText(R.string.rasp_z8_s);
                }
                else {
                    z1.setText(R.string.rasp_z1_default);
                    z2.setText(R.string.rasp_z2_default);
                    z3.setText(R.string.rasp_z3_default);
                    z4.setText(R.string.rasp_z4_default);
                    z5.setText(R.string.rasp_z5_default);
                    z6.setText(R.string.rasp_z6_default);
                    z7.setText(R.string.rasp_z7_default);
                    z8.setText(R.string.rasp_z8_default);
                }
            }});
    }

    void rasp() {
        if (class_pol.equals("11") && (symbol_pol.equals("а") || symbol_pol.equals("А")) && prof_pol.equals("tech")) {
            rasp11at();
        }
        else if (class_pol.equals("11") && (symbol_pol.equals("а") || symbol_pol.equals("А")) && prof_pol.equals("social")) {
            rasp11as();
        }
        else if (class_pol.equals("10") && (symbol_pol.equals("т") || symbol_pol.equals("Т"))) {
            rasp10t();
        }
        else if (class_pol.equals("10") && (symbol_pol.equals("с") || symbol_pol.equals("С"))) {
            rasp10s();
        }
        else {
            u1.setText("Класс не найден");
            u2.setText("Напиши");
            u3.setText("разработчику");
            u4.setText("для добавления");
            u5.setText("расписания");
            u6.setText("твоего класса");
            u7.setText("(Три точки, пункт");
            u8.setText("О приложении)");
        }
    }

    void rasp11at() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        switch (day_week) {
            case "Воскресенье":
                u1.setText("☺☺☺");
                u2.setText("");
                u3.setText("");
                u4.setText("");
                u5.setText("");
                u6.setText("");
                u7.setText("");
                u8.setText("");
                break;
            case "Понедельник":
                u1.setText(sharedPreferences.getString("pn1_11at", ""));
                u2.setText(sharedPreferences.getString("pn2_11at", ""));
                u3.setText(sharedPreferences.getString("pn3_11at", ""));
                u4.setText(sharedPreferences.getString("pn4_11at", ""));
                u5.setText(sharedPreferences.getString("pn5_11at", ""));
                u6.setText(sharedPreferences.getString("pn6_11at", ""));
                u7.setText(sharedPreferences.getString("pn7_11at", ""));
                u8.setText(sharedPreferences.getString("pn8_11at", ""));
                break;
            case "Вторник":
                u1.setText(sharedPreferences.getString("vt1_11at", ""));
                u2.setText(sharedPreferences.getString("vt2_11at", ""));
                u3.setText(sharedPreferences.getString("vt3_11at", ""));
                u4.setText(sharedPreferences.getString("vt4_11at", ""));
                u5.setText(sharedPreferences.getString("vt5_11at", ""));
                u6.setText(sharedPreferences.getString("vt6_11at", ""));
                u7.setText(sharedPreferences.getString("vt7_11at", ""));
                u8.setText(sharedPreferences.getString("vt8_11at", ""));
                break;
            case "Среда":
                u1.setText(sharedPreferences.getString("sr1_11at", ""));
                u2.setText(sharedPreferences.getString("sr2_11at", ""));
                u3.setText(sharedPreferences.getString("sr3_11at", ""));
                u4.setText(sharedPreferences.getString("sr4_11at", ""));
                u5.setText(sharedPreferences.getString("sr5_11at", ""));
                u6.setText(sharedPreferences.getString("sr6_11at", ""));
                u7.setText(sharedPreferences.getString("sr7_11at", ""));
                u8.setText(sharedPreferences.getString("sr8_11at", ""));
                break;
            case "Четверг":
                u1.setText(sharedPreferences.getString("ch1_11at", ""));
                u2.setText(sharedPreferences.getString("ch2_11at", ""));
                u3.setText(sharedPreferences.getString("ch3_11at", ""));
                u4.setText(sharedPreferences.getString("ch4_11at", ""));
                u5.setText(sharedPreferences.getString("ch5_11at", ""));
                u6.setText(sharedPreferences.getString("ch6_11at", ""));
                u7.setText(sharedPreferences.getString("ch7_11at", ""));
                u8.setText(sharedPreferences.getString("ch8_11at", ""));
                break;
            case "Пятница":
                u1.setText(sharedPreferences.getString("pt1_11at", ""));
                u2.setText(sharedPreferences.getString("pt2_11at", ""));
                u3.setText(sharedPreferences.getString("pt3_11at", ""));
                u4.setText(sharedPreferences.getString("pt4_11at", ""));
                u5.setText(sharedPreferences.getString("pt5_11at", ""));
                u6.setText(sharedPreferences.getString("pt6_11at", ""));
                u7.setText(sharedPreferences.getString("pt7_11at", ""));
                u8.setText(sharedPreferences.getString("pt8_11at", ""));
                break;
            case "Суббота":
                u1.setText(sharedPreferences.getString("sb1_11at", ""));
                u2.setText(sharedPreferences.getString("sb2_11at", ""));
                u3.setText(sharedPreferences.getString("sb3_11at", ""));
                u4.setText(sharedPreferences.getString("sb4_11at", ""));
                u5.setText(sharedPreferences.getString("sb5_11at", ""));
                u6.setText(sharedPreferences.getString("sb6_11at", ""));
                u7.setText(sharedPreferences.getString("sb7_11at", ""));
                u8.setText(sharedPreferences.getString("sb8_11at", ""));
                break;
        }
    }

    void rasp11as() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        switch (day_week) {
            case "Воскресенье":
                u1.setText("☺☺☺");
                u2.setText("");
                u3.setText("");
                u4.setText("");
                u5.setText("");
                u6.setText("");
                u7.setText("");
                u8.setText("");
                break;
            case "Понедельник":
                u1.setText(sharedPreferences.getString("pn1_11as", ""));
                u2.setText(sharedPreferences.getString("pn2_11as", ""));
                u3.setText(sharedPreferences.getString("pn3_11as", ""));
                u4.setText(sharedPreferences.getString("pn4_11as", ""));
                u5.setText(sharedPreferences.getString("pn5_11as", ""));
                u6.setText(sharedPreferences.getString("pn6_11as", ""));
                u7.setText(sharedPreferences.getString("pn7_11as", ""));
                u8.setText(sharedPreferences.getString("pn8_11as", ""));
                break;
            case "Вторник":
                u1.setText(sharedPreferences.getString("vt1_11as", ""));
                u2.setText(sharedPreferences.getString("vt2_11as", ""));
                u3.setText(sharedPreferences.getString("vt3_11as", ""));
                u4.setText(sharedPreferences.getString("vt4_11as", ""));
                u5.setText(sharedPreferences.getString("vt5_11as", ""));
                u6.setText(sharedPreferences.getString("vt6_11as", ""));
                u7.setText(sharedPreferences.getString("vt7_11as", ""));
                u8.setText(sharedPreferences.getString("vt8_11as", ""));
                break;
            case "Среда":
                u1.setText(sharedPreferences.getString("sr1_11as", ""));
                u2.setText(sharedPreferences.getString("sr2_11as", ""));
                u3.setText(sharedPreferences.getString("sr3_11as", ""));
                u4.setText(sharedPreferences.getString("sr4_11as", ""));
                u5.setText(sharedPreferences.getString("sr5_11as", ""));
                u6.setText(sharedPreferences.getString("sr6_11as", ""));
                u7.setText(sharedPreferences.getString("sr7_11as", ""));
                u8.setText(sharedPreferences.getString("sr8_11as", ""));
                break;
            case "Четверг":
                u1.setText(sharedPreferences.getString("ch1_11as", ""));
                u2.setText(sharedPreferences.getString("ch2_11as", ""));
                u3.setText(sharedPreferences.getString("ch3_11as", ""));
                u4.setText(sharedPreferences.getString("ch4_11as", ""));
                u5.setText(sharedPreferences.getString("ch5_11as", ""));
                u6.setText(sharedPreferences.getString("ch6_11as", ""));
                u7.setText(sharedPreferences.getString("ch7_11as", ""));
                u8.setText(sharedPreferences.getString("ch8_11as", ""));
                break;
            case "Пятница":
                u1.setText(sharedPreferences.getString("pt1_11as", ""));
                u2.setText(sharedPreferences.getString("pt2_11as", ""));
                u3.setText(sharedPreferences.getString("pt3_11as", ""));
                u4.setText(sharedPreferences.getString("pt4_11as", ""));
                u5.setText(sharedPreferences.getString("pt5_11as", ""));
                u6.setText(sharedPreferences.getString("pt6_11as", ""));
                u7.setText(sharedPreferences.getString("pt7_11as", ""));
                u8.setText(sharedPreferences.getString("pt8_11as", ""));
                break;
            case "Суббота":
                u1.setText(sharedPreferences.getString("sb1_11as", ""));
                u2.setText(sharedPreferences.getString("sb2_11as", ""));
                u3.setText(sharedPreferences.getString("sb3_11as", ""));
                u4.setText(sharedPreferences.getString("sb4_11as", ""));
                u5.setText(sharedPreferences.getString("sb5_11as", ""));
                u6.setText(sharedPreferences.getString("sb6_11as", ""));
                u7.setText(sharedPreferences.getString("sb7_11as", ""));
                u8.setText(sharedPreferences.getString("sb8_11as", ""));
                break;
        }
    }

    void rasp10t() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        switch (day_week) {
            case "Воскресенье":
                u1.setText("☺☺☺");
                u2.setText("");
                u3.setText("");
                u4.setText("");
                u5.setText("");
                u6.setText("");
                u7.setText("");
                u8.setText("");
                break;
            case "Понедельник":
                u1.setText(sharedPreferences.getString("pn1_10t", ""));
                u2.setText(sharedPreferences.getString("pn2_10t", ""));
                u3.setText(sharedPreferences.getString("pn3_10t", ""));
                u4.setText(sharedPreferences.getString("pn4_10t", ""));
                u5.setText(sharedPreferences.getString("pn5_10t", ""));
                u6.setText(sharedPreferences.getString("pn6_10t", ""));
                u7.setText(sharedPreferences.getString("pn7_10t", ""));
                u8.setText(sharedPreferences.getString("pn8_10t", ""));
                break;
            case "Вторник":
                u1.setText(sharedPreferences.getString("vt1_10t", ""));
                u2.setText(sharedPreferences.getString("vt2_10t", ""));
                u3.setText(sharedPreferences.getString("vt3_10t", ""));
                u4.setText(sharedPreferences.getString("vt4_10t", ""));
                u5.setText(sharedPreferences.getString("vt5_10t", ""));
                u6.setText(sharedPreferences.getString("vt6_10t", ""));
                u7.setText(sharedPreferences.getString("vt7_10t", ""));
                u8.setText(sharedPreferences.getString("vt8_10t", ""));
                break;
            case "Среда":
                u1.setText(sharedPreferences.getString("sr1_10t", ""));
                u2.setText(sharedPreferences.getString("sr2_10t", ""));
                u3.setText(sharedPreferences.getString("sr3_10t", ""));
                u4.setText(sharedPreferences.getString("sr4_10t", ""));
                u5.setText(sharedPreferences.getString("sr5_10t", ""));
                u6.setText(sharedPreferences.getString("sr6_10t", ""));
                u7.setText(sharedPreferences.getString("sr7_10t", ""));
                u8.setText(sharedPreferences.getString("sr8_10t", ""));
                break;
            case "Четверг":
                u1.setText(sharedPreferences.getString("ch1_10t", ""));
                u2.setText(sharedPreferences.getString("ch2_10t", ""));
                u3.setText(sharedPreferences.getString("ch3_10t", ""));
                u4.setText(sharedPreferences.getString("ch4_10t", ""));
                u5.setText(sharedPreferences.getString("ch5_10t", ""));
                u6.setText(sharedPreferences.getString("ch6_10t", ""));
                u7.setText(sharedPreferences.getString("ch7_10t", ""));
                u8.setText(sharedPreferences.getString("ch8_10t", ""));
                break;
            case "Пятница":
                u1.setText(sharedPreferences.getString("pt1_10t", ""));
                u2.setText(sharedPreferences.getString("pt2_10t", ""));
                u3.setText(sharedPreferences.getString("pt3_10t", ""));
                u4.setText(sharedPreferences.getString("pt4_10t", ""));
                u5.setText(sharedPreferences.getString("pt5_10t", ""));
                u6.setText(sharedPreferences.getString("pt6_10t", ""));
                u7.setText(sharedPreferences.getString("pt7_10t", ""));
                u8.setText(sharedPreferences.getString("pt8_10t", ""));
                break;
            case "Суббота":
                u1.setText(sharedPreferences.getString("sb1_10t", ""));
                u2.setText(sharedPreferences.getString("sb2_10t", ""));
                u3.setText(sharedPreferences.getString("sb3_10t", ""));
                u4.setText(sharedPreferences.getString("sb4_10t", ""));
                u5.setText(sharedPreferences.getString("sb5_10t", ""));
                u6.setText(sharedPreferences.getString("sb6_10t", ""));
                u7.setText(sharedPreferences.getString("sb7_10t", ""));
                u8.setText(sharedPreferences.getString("sb8_10t", ""));
                break;
        }
    }

    void rasp10s() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        switch (day_week) {
            case "Воскресенье":
                u1.setText("☺☺☺");
                u2.setText("");
                u3.setText("");
                u4.setText("");
                u5.setText("");
                u6.setText("");
                u7.setText("");
                u8.setText("");
                break;
            case "Понедельник":
                u1.setText(sharedPreferences.getString("pn1_10s", ""));
                u2.setText(sharedPreferences.getString("pn2_10s", ""));
                u3.setText(sharedPreferences.getString("pn3_10s", ""));
                u4.setText(sharedPreferences.getString("pn4_10s", ""));
                u5.setText(sharedPreferences.getString("pn5_10s", ""));
                u6.setText(sharedPreferences.getString("pn6_10s", ""));
                u7.setText(sharedPreferences.getString("pn7_10s", ""));
                u8.setText(sharedPreferences.getString("pn8_10s", ""));
                break;
            case "Вторник":
                u1.setText(sharedPreferences.getString("vt1_10s", ""));
                u2.setText(sharedPreferences.getString("vt2_10s", ""));
                u3.setText(sharedPreferences.getString("vt3_10s", ""));
                u4.setText(sharedPreferences.getString("vt4_10s", ""));
                u5.setText(sharedPreferences.getString("vt5_10s", ""));
                u6.setText(sharedPreferences.getString("vt6_10s", ""));
                u7.setText(sharedPreferences.getString("vt7_10s", ""));
                u8.setText(sharedPreferences.getString("vt8_10s", ""));
                break;
            case "Среда":
                u1.setText(sharedPreferences.getString("sr1_10s", ""));
                u2.setText(sharedPreferences.getString("sr2_10s", ""));
                u3.setText(sharedPreferences.getString("sr3_10s", ""));
                u4.setText(sharedPreferences.getString("sr4_10s", ""));
                u5.setText(sharedPreferences.getString("sr5_10s", ""));
                u6.setText(sharedPreferences.getString("sr6_10s", ""));
                u7.setText(sharedPreferences.getString("sr7_10s", ""));
                u8.setText(sharedPreferences.getString("sr8_10s", ""));
                break;
            case "Четверг":
                u1.setText(sharedPreferences.getString("ch1_10s", ""));
                u2.setText(sharedPreferences.getString("ch2_10s", ""));
                u3.setText(sharedPreferences.getString("ch3_10s", ""));
                u4.setText(sharedPreferences.getString("ch4_10s", ""));
                u5.setText(sharedPreferences.getString("ch5_10s", ""));
                u6.setText(sharedPreferences.getString("ch6_10s", ""));
                u7.setText(sharedPreferences.getString("ch7_10s", ""));
                u8.setText(sharedPreferences.getString("ch8_10s", ""));
                break;
            case "Пятница":
                u1.setText(sharedPreferences.getString("pt1_10s", ""));
                u2.setText(sharedPreferences.getString("pt2_10s", ""));
                u3.setText(sharedPreferences.getString("pt3_10s", ""));
                u4.setText(sharedPreferences.getString("pt4_10s", ""));
                u5.setText(sharedPreferences.getString("pt5_10s", ""));
                u6.setText(sharedPreferences.getString("pt6_10s", ""));
                u7.setText(sharedPreferences.getString("pt7_10s", ""));
                u8.setText(sharedPreferences.getString("pt8_10s", ""));
                break;
            case "Суббота":
                u1.setText(sharedPreferences.getString("sb1_10s", ""));
                u2.setText(sharedPreferences.getString("sb2_10s", ""));
                u3.setText(sharedPreferences.getString("sb3_10s", ""));
                u4.setText(sharedPreferences.getString("sb4_10s", ""));
                u5.setText(sharedPreferences.getString("sb5_10s", ""));
                u6.setText(sharedPreferences.getString("sb6_10s", ""));
                u7.setText(sharedPreferences.getString("sb7_10s", ""));
                u8.setText(sharedPreferences.getString("sb8_10s", ""));
                break;
        }
    }
}