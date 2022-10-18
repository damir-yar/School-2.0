package com.example.school20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
import java.util.concurrent.TimeUnit;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        boolean inet = isNetworkConnected();
        if (inet == true) {
            String urlT = "https://api.npoint.io/86de4c9a1714afd58caa";
            new getUrlData1().execute(urlT);
            new getUrlData().execute(urlT);
        }
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
                    return;
                }
            } catch (JSONException jsonException) {
                Log.e("MYAPP", "unexpected JSON exception", jsonException);
                jsonException.printStackTrace();
            }
            String str_obj1 = "";
            String les1 = "";
            if (class_pol.equals("11")) {
                str_obj1 = "rasp" + class_pol + symbol + pr;
            } else {
                str_obj1 = "rasp" + class_pol + symbol;
            }
            les1 = "pn4";
            try {
                JSONObject jsonObject = new JSONObject(result);
                String s = jsonObject.getJSONObject(str_obj1).getString(les1);

            } catch (JSONException jsonException) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Класс не найден!\nДля добавления расписания твоего класса напиши разработчику")
                        .setCancelable(false)
                        .setPositiveButton("Написать", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/school_2_0"));
                                startActivity(intent4);
                            }
                        });
                builder.setNegativeButton("Позже", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                jsonException.printStackTrace();
                return;
            }
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "Загрузка...", Toast.LENGTH_SHORT);
            toast1.show();
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
                    editor.putString("verson", jsonObject.getJSONObject(str_obj).getString("ver"));
                    editor.commit();
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rasp();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Расписание обновлено", Toast.LENGTH_SHORT);
            toast.show();
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
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (!jsonObject.getJSONObject("connect").getString("cn").equals("true")) {
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
                String app = jsonObject.getJSONObject("connect").getString("app");
                String ver = jsonObject.getJSONObject("connect").getString("ver");
                String versionName = BuildConfig.VERSION_NAME;
                if (!ver.equals(versionName)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Доступно обновление!")
                            .setCancelable(false)
                            .setPositiveButton("Скачать", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(app));
                                    startActivity(intent4);
                                }
                            });
                            builder.setNegativeButton("Позже", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                }
            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ошибка JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }
        }JSONObject jsonObject = null;
    }

    private class getUrlData2 extends AsyncTask<String, String, String> {
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
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (!jsonObject.getJSONObject("connect").getString("cn").equals("true")) {
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
                String app = jsonObject.getJSONObject("connect").getString("app");
                String ver = jsonObject.getJSONObject("connect").getString("ver");
                String versionName = BuildConfig.VERSION_NAME;
                if (!ver.equals(versionName)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Доступно обновление!")
                            .setCancelable(false)
                            .setPositiveButton("Скачать", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(app));
                                    startActivity(intent4);
                                }
                            });
                    builder.setNegativeButton("Позже", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Установлена последняя версия", Toast.LENGTH_SHORT);
                    toast.show();
                }
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
            case R.id.action_update:
                boolean inet = isNetworkConnected();
                if (inet == true) {
                    String urlT = "https://api.npoint.io/86de4c9a1714afd58caa";
                    new getUrlData2().execute(urlT);
                    new getUrlData().execute(urlT);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Нет подключения к интернету", Toast.LENGTH_SHORT);
                    toast.show();
                }
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about:
                Intent intent1 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent1);
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
        String str = "";
        String les = "";

        if (class_pol.equals("11")) {
            str = class_pol + symbol + pr;
        } else {
            str = class_pol + symbol;
        }
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
                u1.setText(sharedPreferences.getString("pn1_" + str, ""));
                u2.setText(sharedPreferences.getString("pn2_" + str, ""));
                u3.setText(sharedPreferences.getString("pn3_" + str, ""));
                u4.setText(sharedPreferences.getString("pn4_" + str, ""));
                u5.setText(sharedPreferences.getString("pn5_" + str, ""));
                u6.setText(sharedPreferences.getString("pn6_" + str, ""));
                u7.setText(sharedPreferences.getString("pn7_" + str, ""));
                u8.setText(sharedPreferences.getString("pn8_" + str, ""));
                break;
            case "Вторник":
                u1.setText(sharedPreferences.getString("vt1_" + str, ""));
                u2.setText(sharedPreferences.getString("vt2_" + str, ""));
                u3.setText(sharedPreferences.getString("vt3_" + str, ""));
                u4.setText(sharedPreferences.getString("vt4_" + str, ""));
                u5.setText(sharedPreferences.getString("vt5_" + str, ""));
                u6.setText(sharedPreferences.getString("vt6_" + str, ""));
                u7.setText(sharedPreferences.getString("vt7_" + str, ""));
                u8.setText(sharedPreferences.getString("vt8_" + str, ""));
                break;
            case "Среда":
                u1.setText(sharedPreferences.getString("sr1_" + str, ""));
                u2.setText(sharedPreferences.getString("sr2_" + str, ""));
                u3.setText(sharedPreferences.getString("sr3_" + str, ""));
                u4.setText(sharedPreferences.getString("sr4_" + str, ""));
                u5.setText(sharedPreferences.getString("sr5_" + str, ""));
                u6.setText(sharedPreferences.getString("sr6_" + str, ""));
                u7.setText(sharedPreferences.getString("sr7_" + str, ""));
                u8.setText(sharedPreferences.getString("sr8_" + str, ""));
                break;
            case "Четверг":
                u1.setText(sharedPreferences.getString("ch1_" + str, ""));
                u2.setText(sharedPreferences.getString("ch2_" + str, ""));
                u3.setText(sharedPreferences.getString("ch3_" + str, ""));
                u4.setText(sharedPreferences.getString("ch4_" + str, ""));
                u5.setText(sharedPreferences.getString("ch5_" + str, ""));
                u6.setText(sharedPreferences.getString("ch6_" + str, ""));
                u7.setText(sharedPreferences.getString("ch7_" + str, ""));
                u8.setText(sharedPreferences.getString("ch8_" + str, ""));
                break;
            case "Пятница":
                u1.setText(sharedPreferences.getString("pt1_" + str, ""));
                u2.setText(sharedPreferences.getString("pt2_" + str, ""));
                u3.setText(sharedPreferences.getString("pt3_" + str, ""));
                u4.setText(sharedPreferences.getString("pt4_" + str, ""));
                u5.setText(sharedPreferences.getString("pt5_" + str, ""));
                u6.setText(sharedPreferences.getString("pt6_" + str, ""));
                u7.setText(sharedPreferences.getString("pt7_" + str, ""));
                u8.setText(sharedPreferences.getString("pt8_" + str, ""));
                break;
            case "Суббота":
                u1.setText(sharedPreferences.getString("sb1_" + str, ""));
                u2.setText(sharedPreferences.getString("sb2_" + str, ""));
                u3.setText(sharedPreferences.getString("sb3_" + str, ""));
                u4.setText(sharedPreferences.getString("sb4_" + str, ""));
                u5.setText(sharedPreferences.getString("sb5_" + str, ""));
                u6.setText(sharedPreferences.getString("sb6_" + str, ""));
                u7.setText(sharedPreferences.getString("sb7_" + str, ""));
                u8.setText(sharedPreferences.getString("sb8_" + str, ""));
                break;
        }
    }
}
