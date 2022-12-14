package com.example.school20;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

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
    private String day_week;
    private String name_pol;
    private String class_pol;
    private String symbol_pol;
    private String prof_pol;
    private LinearLayout easter;
    private int east = 0;
    private LinearLayout layout;
    private LinearLayout lay2;
    private LinearLayout lay3;
    private LinearLayout lay_sw;
    private ImageView im1;
    static final int GALLERY_REQUEST = 1;

    private Button task;

    private LinearLayout lin;

    private Button next;
    private Button back;

    private LinearLayout menuBot;
    private ImageButton mHome;
    private ImageButton mTime;
    private ImageButton mSet;
    private ImageButton mProf;

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
        easter = findViewById(R.id.easter);
        layout = findViewById(R.id.a);
        lay2 = findViewById(R.id.lay_main_rasp);
        lay3 = findViewById(R.id.lay_main_inf);
        lay_sw = findViewById(R.id.lay_main_sw);
        im1 = findViewById(R.id.im_one);
        task = findViewById(R.id.home_task);
        lin = findViewById(R.id.idid);
        next = findViewById(R.id.but_next);
        back = findViewById(R.id.but_back);


        menuBot = findViewById(R.id.menu_bottom);
        mHome = findViewById(R.id.menu_home);
        mTime = findViewById(R.id.menu_timetable);
        mSet = findViewById(R.id.menu_settings);
        mProf = findViewById(R.id.menu_profile);
        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RaspActivity.class);
                startActivity(intent);
            }
        });
        mSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });



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
                day_mode();
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                nigth_mode();
                break;
        }

        if (sharedPreferences.getBoolean("night_mode", false) == true) {
            nigth_mode();
        }
        else {
            day_mode();
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
                    case "??????????????????????":
                        day_week = "??????????????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                }
                raspZ();
                return true;
            }
            public boolean onSwipeLeft() {
                switch (day_week) {
                    case "??????????????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????????????":
                        day_week = "??????????????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                }
                raspZ();
                return true;
            }
            public boolean onSwipeBottom() {
                return true;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (day_week) {
                    case "??????????????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????????????":
                        day_week = "??????????????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                }
                raspZ();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (day_week) {
                    case "??????????????????????":
                        day_week = "??????????????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                    case "??????????????????????":
                        day_week = "??????????????";
                        rasp();
                        date.setText(day_week);
                        break;
                }
                raspZ();
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
                            "?????????????? " + a + " ??????", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (east == 5) {
                    Intent intent = new Intent(MainActivity.this, EasterActivity.class);
                    startActivity(intent);
                    east = 0;
                }
            }
        });


        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(day_week.equals("??????????????????????")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "????????????????!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(day_week);
                    String tas = "";
                    if(!u1.getText().toString().equals("")&& !u1.getText().toString().equals("----")
                    &&!sharedPreferences.getString("home" + u1.getText().toString(), "").equals("")) {
                        tas = u1.getText().toString() + " - " +
                                sharedPreferences.getString("home" + u1.getText().toString(), "") + "\n";
                    }
                    if (!u1.getText().toString().equals(u2.getText().toString()) && !u2.getText().toString().equals("----")
                            &&!u2.getText().toString().equals("")&&!sharedPreferences.getString("home" + u2.getText().toString(), "").equals("")) {
                        tas = tas + u2.getText().toString() + " - " +
                                sharedPreferences.getString("home" + u2.getText().toString(), "") + "\n";
                    }
                    if (!u2.getText().toString().equals(u3.getText().toString()) && !u3.getText().toString().equals("----")
                            &&!u3.getText().toString().equals("")&&!sharedPreferences.getString("home" + u3.getText().toString(), "").equals("")) {
                        tas = tas + u3.getText().toString() + " - " +
                                sharedPreferences.getString("home" + u3.getText().toString(), "") + "\n";
                    }
                    if (!u3.getText().toString().equals(u4.getText().toString()) && !u4.getText().toString().equals("----")
                            &&!u4.getText().toString().equals("")&&!sharedPreferences.getString("home" + u4.getText().toString(), "").equals("")) {
                        tas = tas + u4.getText().toString() + " - " +
                                sharedPreferences.getString("home" + u4.getText().toString(), "") + "\n";
                    }
                    if (!u4.getText().toString().equals(u5.getText().toString()) && !u5.getText().toString().equals("----")
                            &&!u5.getText().toString().equals("")&&!sharedPreferences.getString("home" + u5.getText().toString(), "").equals("")) {
                        tas = tas + u5.getText().toString() + " - " +
                                sharedPreferences.getString("home" + u5.getText().toString(), "") + "\n";
                    }
                    if (!u5.getText().toString().equals(u6.getText().toString()) && !u6.getText().toString().equals("----")
                            &&!u6.getText().toString().equals("")&&!sharedPreferences.getString("home" + u6.getText().toString(), "").equals("")) {
                        tas = tas + u6.getText().toString() + " - " +
                                sharedPreferences.getString("home" + u6.getText().toString(), "") + "\n";
                    }
                    if (!u6.getText().toString().equals(u7.getText().toString()) && !u7.getText().toString().equals("----")
                            &&!u7.getText().toString().equals("")&&!sharedPreferences.getString("home" + u7.getText().toString(), "").equals("")) {
                        tas = tas + u7.getText().toString() + " - " +
                                sharedPreferences.getString("home" + u7.getText().toString(), "") + "\n";
                    }
                    if (!u7.getText().toString().equals(u8.getText().toString()) && !u8.getText().toString().equals("----")
                            &&!u8.getText().toString().equals("")&&!sharedPreferences.getString("home" + u8.getText().toString(), "").equals("")) {
                        tas = tas + u8.getText().toString() + " - " +
                                sharedPreferences.getString("home" + u8.getText().toString(), "") + "\n";
                    }
                    if (sharedPreferences.getString("home" + u1.getText().toString(), "").equals("")&&sharedPreferences.getString("home" + u2.getText().toString(), "").equals("")
                    &&sharedPreferences.getString("home" + u3.getText().toString(), "").equals("")&&sharedPreferences.getString("home" + u4.getText().toString(), "").equals("")
                    &&sharedPreferences.getString("home" + u5.getText().toString(), "").equals("")&&sharedPreferences.getString("home" + u6.getText().toString(), "").equals("")
                    &&sharedPreferences.getString("home" + u7.getText().toString(), "").equals("")&&sharedPreferences.getString("home" + u8.getText().toString(), "").equals("")) {
                        tas = "???????????? ???? ???????????? ???";
                    }
                    builder.setMessage(tas);
                    builder.setCancelable(false)
                            .setPositiveButton("????", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    builder.setNeutralButton("?????? ???????????????? ????", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("???????????????????? ?????????????????? ??????????????");
                            builder.setMessage("?????? ???????????????????? ?????????????????? ?????????????? ?????????????? ???? ???????????????? ????????????????????????" +
                                    " ????????????????. ?? ?????????????????????? ???????? ?? ???????????? \"???????????????? ??????????????\" ???????????????? ??????????????, ?????????? ???????? ?????????????? ???????????? \"??????????????????\"" +
                                    ". \n?????? ??????????????????????????, ?????????????? ?????????? ????????????????, ???????????????? ?????????? ???? ?????????????? ?? ?????????????? ??????????, ?????????? ???????? ?????? ???? ???????????? ???????????? \"??????????????????\". " +
                                    "?????????????????????? ???? ?????????? - 2 147 483 647 ????????????????");
                            builder.setNegativeButton("????", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
        u1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                home_d(u1.getText().toString());
            }
        });
        u2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_d(u2.getText().toString());
            }
        });
        u3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_d(u3.getText().toString());
            }
        });
        u4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_d(u4.getText().toString());
            }
        });
        u5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_d(u5.getText().toString());
            }
        });
        u6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_d(u6.getText().toString());
            }
        });
        u7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_d(u7.getText().toString());
            }
        });
        u8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_d(u8.getText().toString());
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((Switch) v).isChecked();
                if (checked) {
                    z1.setText(sharedPreferences.getString("rasp_bell_abb1", ""));
                    z2.setText(sharedPreferences.getString("rasp_bell_abb2", ""));
                    z3.setText(sharedPreferences.getString("rasp_bell_abb3", ""));
                    z4.setText(sharedPreferences.getString("rasp_bell_abb4", ""));
                    z5.setText(sharedPreferences.getString("rasp_bell_abb5", ""));
                    z6.setText(sharedPreferences.getString("rasp_bell_abb6", ""));
                    z7.setText(sharedPreferences.getString("rasp_bell_abb7", ""));
                    z8.setText(sharedPreferences.getString("rasp_bell_abb8", ""));
                }
                else {
                    switch (day_week) {
                        case "??????????????":
                            z1.setText(sharedPreferences.getString("rasp_bell_sat1", ""));
                            z2.setText(sharedPreferences.getString("rasp_bell_sat2", ""));
                            z3.setText(sharedPreferences.getString("rasp_bell_sat3", ""));
                            z4.setText(sharedPreferences.getString("rasp_bell_sat4", ""));
                            z5.setText(sharedPreferences.getString("rasp_bell_sat5", ""));
                            z6.setText(sharedPreferences.getString("rasp_bell_sat6", ""));
                            z7.setText(sharedPreferences.getString("rasp_bell_sat7", ""));
                            z8.setText(sharedPreferences.getString("rasp_bell_sat8", ""));
                            break;
                        default:
                            z1.setText(sharedPreferences.getString("rasp_bell_def1", ""));
                            z2.setText(sharedPreferences.getString("rasp_bell_def2", ""));
                            z3.setText(sharedPreferences.getString("rasp_bell_def3", ""));
                            z4.setText(sharedPreferences.getString("rasp_bell_def4", ""));
                            z5.setText(sharedPreferences.getString("rasp_bell_def5", ""));
                            z6.setText(sharedPreferences.getString("rasp_bell_def6", ""));
                            z7.setText(sharedPreferences.getString("rasp_bell_def7", ""));
                            z8.setText(sharedPreferences.getString("rasp_bell_def8", ""));
                            break;
                    }
                }
            }});

        boolean inet = isNetworkConnected();
        if (inet == true) {
            String urlT = "https://api.npoint.io/86de4c9a1714afd58caa";
            new getUrlData1().execute(urlT);
            new getUrlData().execute(urlT);
            new getUrlData3().execute(urlT);
        }
        String previouslyEncodedImage = sharedPreferences.getString("im_data", "");
        String previouslyEncodedImageBg = sharedPreferences.getString("bg_im_data", "");
        String imageData = sharedPreferences.getString("im_url_data", "");
        if( !previouslyEncodedImageBg.equals("") ){
            byte[] b = Base64.decode(previouslyEncodedImageBg, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            layout.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }
        if( !previouslyEncodedImage.equals("") ){
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            im1.setImageBitmap(bitmap);
        } else if (!imageData.equals("")) {
            Glide.with(MainActivity.this).load(imageData).into(im1);
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
                            "???????????? ???????????????? ????????????????????", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "???????????? JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }
            String pr = "";
            String symbol = "";
            if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                symbol = "a";
            else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                symbol = "t";
            else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                symbol = "s";
            else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                symbol = "b";
            else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                symbol = "v";
            else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                symbol = "g";
            else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                symbol = "d";
            else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                symbol = "e";
            if (prof_pol.equals("tech"))
                pr = "t";
            else if (prof_pol.equals("social"))
                pr = "s";
            else if (prof_pol.equals("not"))
                pr = "n";
            try {
                String str_obj = "";
                String les = "";
                if (class_pol.equals("11")) {
                    str_obj = "rasp" + class_pol + symbol + pr;
                } else {
                    str_obj = "rasp" + class_pol + symbol;
                }
                JSONObject jsonObject = new JSONObject(result);
                if (sharedPreferences.getString("verson", "").equals(jsonObject.getJSONObject(str_obj).getString("ver"))
                && sharedPreferences.getString("verson_bells", "").equals(jsonObject.getJSONObject("rasp_bells").getString("ver"))) {
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
                builder.setTitle("?????????? ???? ????????????!")
                        .setMessage("?????? ???????????????????? ???????????????????? ???????????? ???????????? ???????????? ????????????????????????")
                        .setCancelable(false)
                        .setPositiveButton("????????????????", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/school_2_0"));
                                startActivity(intent4);
                            }
                        });
                builder.setNegativeButton("??????????", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                jsonException.printStackTrace();
                return;
            }
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "????????????????...", Toast.LENGTH_SHORT);
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
            i = 0;
            while(i<8) {
                i++;
                String str_z = "rasp_bell_def" + i;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString(str_z, jsonObject.getJSONObject("rasp_bells").getJSONObject("default").getString(Integer.toString(i)));
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            i = 0;
            while(i<8) {
                i++;
                String str_z = "rasp_bell_sat" + i;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString(str_z, jsonObject.getJSONObject("rasp_bells").getJSONObject("saturday").getString(Integer.toString(i)));
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            i = 0;
            while(i<8) {
                i++;
                String str_z = "rasp_bell_abb" + i;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    editor.putString(str_z, jsonObject.getJSONObject("rasp_bells").getJSONObject("abbreviated").getString(Integer.toString(i)));
                    editor.putString("verson_bells", jsonObject.getJSONObject("rasp_bells").getString("ver"));
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rasp();
            raspZ();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "???????????????????? ??????????????????", Toast.LENGTH_SHORT);
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
                            "???????????? ???????????????? ????????????????????", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "???????????? JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }

            try {
                JSONObject jsonObject = new JSONObject(result);
                if (!jsonObject.getJSONObject("connect").getString("down").equals("false")) {
                String app = jsonObject.getJSONObject("connect").getString("app");
                String ver = jsonObject.getJSONObject("connect").getString("ver");
                String versionName = BuildConfig.VERSION_NAME;
                if (!ver.equals(versionName)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("???????????????? ????????????????????!")
                            .setCancelable(false)
                            .setPositiveButton("??????????????", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(app));
                                    startActivity(intent4);
                                }
                            });
                            builder.setNegativeButton("??????????", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                }}

            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "???????????? JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }
        }JSONObject jsonObject = null;
    }

    private class getUrlData2 extends AsyncTask<String, String, String> { //???????????? ?????????????? ????????????????????
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
                            "???????????? ???????????????? ????????????????????", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "???????????? JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }

            try {
                JSONObject jsonObject = new JSONObject(result);
                if (!jsonObject.getJSONObject("connect").getString("down").equals("false")) {
                String app = jsonObject.getJSONObject("connect").getString("app");
                String ver = jsonObject.getJSONObject("connect").getString("ver");
                String versionName = BuildConfig.VERSION_NAME;
                if (!ver.equals(versionName)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("???????????????? ????????????????????!")
                            .setCancelable(false)
                            .setPositiveButton("??????????????", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(app));
                                    startActivity(intent4);
                                }
                            });
                    builder.setNegativeButton("??????????", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "?????????????????????? ?????????????????? ????????????", Toast.LENGTH_SHORT);
                    toast.show();
                } }
            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "???????????? JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }
        }JSONObject jsonObject = null;
    }

    private class getUrlData3 extends AsyncTask<String, String, String> { //?????????? ??????????????????
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
                            "???????????? ???????????????? ????????????????????", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "???????????? JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }

            try {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                JSONObject jsonObject = new JSONObject(result);
                String num = jsonObject.getJSONObject("mes").getString("number");
                String class_n = jsonObject.getJSONObject("mes").getString("class_n");
                String class_s = jsonObject.getJSONObject("mes").getString("class_s");
                String all = jsonObject.getJSONObject("mes").getString("all");
                String alltime = jsonObject.getJSONObject("mes").getString("alltime");
                String tytle = jsonObject.getJSONObject("mes").getString("tytle");
                String text = jsonObject.getJSONObject("mes").getString("text");
                String but_one = jsonObject.getJSONObject("mes").getString("but_1");
                String but_one_url = jsonObject.getJSONObject("mes").getString("but_1_url");
                String but_two = jsonObject.getJSONObject("mes").getString("but_2");
                String but_two_url = jsonObject.getJSONObject("mes").getString("but_2_url");
                String but_three = jsonObject.getJSONObject("mes").getString("but_3");
                String but_three_url = jsonObject.getJSONObject("mes").getString("but_3_url");

                String symbol = "";
                if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                    symbol = "a";
                else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                    symbol = "t";
                else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                    symbol = "s";
                else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                    symbol = "b";
                else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                    symbol = "v";
                else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                    symbol = "g";
                else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                    symbol = "d";
                else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
                    symbol = "e";

                if (!num.equals(sharedPreferences.getString("mes_num", "")) || alltime.equals("true")) {
                    editor.putString("mes_num", jsonObject.getJSONObject("mes").getString("number"));
                    editor.commit();

                    if ((class_n.equals(class_pol) && (class_s.equals(symbol) || class_s.equals("-"))) || all.equals("true")) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(tytle).setMessage(text).setCancelable(false);
                        builder.setPositiveButton(but_one, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (!but_one_url.equals("")) {
                                Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(but_one_url));
                                startActivity(intent4); }
                            }
                        });
                        builder.setNegativeButton(but_two, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (!but_two_url.equals("")) {
                                Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(but_two_url));
                                startActivity(intent4);}
                            }
                        });
                    builder.setNeutralButton(but_three, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (!but_two_url.equals("")) {
                                Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(but_three_url));
                                startActivity(intent4);}
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }}
                else {
                }
            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "???????????? JSON", Toast.LENGTH_SHORT);
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
                day_week = "??????????????????????";
                break;
            case Calendar.MONDAY:
                day_week = "??????????????????????";
                break;
            case Calendar.TUESDAY:
                day_week = "??????????????";
                break;
            case Calendar.WEDNESDAY:
                day_week = "??????????";
                break;
            case Calendar.THURSDAY:
                day_week = "??????????????";
                break;
            case Calendar.FRIDAY:
                day_week = "??????????????";
                break;
            case Calendar.SATURDAY:
                day_week = "??????????????";
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
                finish();
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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        if (s.isChecked() == true) {
            z1.setText(sharedPreferences.getString("rasp_bell_abb1", ""));
            z2.setText(sharedPreferences.getString("rasp_bell_abb2", ""));
            z3.setText(sharedPreferences.getString("rasp_bell_abb3", ""));
            z4.setText(sharedPreferences.getString("rasp_bell_abb4", ""));
            z5.setText(sharedPreferences.getString("rasp_bell_abb5", ""));
            z6.setText(sharedPreferences.getString("rasp_bell_abb6", ""));
            z7.setText(sharedPreferences.getString("rasp_bell_abb7", ""));
            z8.setText(sharedPreferences.getString("rasp_bell_abb8", ""));
            return;
        }
        switch (day_week) {
            case "??????????????":
                z1.setText(sharedPreferences.getString("rasp_bell_sat1", ""));
                z2.setText(sharedPreferences.getString("rasp_bell_sat2", ""));
                z3.setText(sharedPreferences.getString("rasp_bell_sat3", ""));
                z4.setText(sharedPreferences.getString("rasp_bell_sat4", ""));
                z5.setText(sharedPreferences.getString("rasp_bell_sat5", ""));
                z6.setText(sharedPreferences.getString("rasp_bell_sat6", ""));
                z7.setText(sharedPreferences.getString("rasp_bell_sat7", ""));
                z8.setText(sharedPreferences.getString("rasp_bell_sat8", ""));
                break;
            default:
                z1.setText(sharedPreferences.getString("rasp_bell_def1", ""));
                z2.setText(sharedPreferences.getString("rasp_bell_def2", ""));
                z3.setText(sharedPreferences.getString("rasp_bell_def3", ""));
                z4.setText(sharedPreferences.getString("rasp_bell_def4", ""));
                z5.setText(sharedPreferences.getString("rasp_bell_def5", ""));
                z6.setText(sharedPreferences.getString("rasp_bell_def6", ""));
                z7.setText(sharedPreferences.getString("rasp_bell_def7", ""));
                z8.setText(sharedPreferences.getString("rasp_bell_def8", ""));
                break;
        }
        }

    void rasp() {
        String pr = "";
        String symbol = "";
        if (symbol_pol.equals("??") || symbol_pol.equals("??"))
            symbol = "a";
        else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
            symbol = "t";
        else if (symbol_pol.equals("??") || symbol_pol.equals("??"))
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
            case "??????????????????????":
                u1.setText("?????????");
                u2.setText("");
                u3.setText("");
                u4.setText("");
                u5.setText("");
                u6.setText("");
                u7.setText("");
                u8.setText("");
                break;
            case "??????????????????????":
                u1.setText(sharedPreferences.getString("pn1_" + str, ""));
                u2.setText(sharedPreferences.getString("pn2_" + str, ""));
                u3.setText(sharedPreferences.getString("pn3_" + str, ""));
                u4.setText(sharedPreferences.getString("pn4_" + str, ""));
                u5.setText(sharedPreferences.getString("pn5_" + str, ""));
                u6.setText(sharedPreferences.getString("pn6_" + str, ""));
                u7.setText(sharedPreferences.getString("pn7_" + str, ""));
                u8.setText(sharedPreferences.getString("pn8_" + str, ""));
                break;
            case "??????????????":
                u1.setText(sharedPreferences.getString("vt1_" + str, ""));
                u2.setText(sharedPreferences.getString("vt2_" + str, ""));
                u3.setText(sharedPreferences.getString("vt3_" + str, ""));
                u4.setText(sharedPreferences.getString("vt4_" + str, ""));
                u5.setText(sharedPreferences.getString("vt5_" + str, ""));
                u6.setText(sharedPreferences.getString("vt6_" + str, ""));
                u7.setText(sharedPreferences.getString("vt7_" + str, ""));
                u8.setText(sharedPreferences.getString("vt8_" + str, ""));
                break;
            case "??????????":
                u1.setText(sharedPreferences.getString("sr1_" + str, ""));
                u2.setText(sharedPreferences.getString("sr2_" + str, ""));
                u3.setText(sharedPreferences.getString("sr3_" + str, ""));
                u4.setText(sharedPreferences.getString("sr4_" + str, ""));
                u5.setText(sharedPreferences.getString("sr5_" + str, ""));
                u6.setText(sharedPreferences.getString("sr6_" + str, ""));
                u7.setText(sharedPreferences.getString("sr7_" + str, ""));
                u8.setText(sharedPreferences.getString("sr8_" + str, ""));
                break;
            case "??????????????":
                u1.setText(sharedPreferences.getString("ch1_" + str, ""));
                u2.setText(sharedPreferences.getString("ch2_" + str, ""));
                u3.setText(sharedPreferences.getString("ch3_" + str, ""));
                u4.setText(sharedPreferences.getString("ch4_" + str, ""));
                u5.setText(sharedPreferences.getString("ch5_" + str, ""));
                u6.setText(sharedPreferences.getString("ch6_" + str, ""));
                u7.setText(sharedPreferences.getString("ch7_" + str, ""));
                u8.setText(sharedPreferences.getString("ch8_" + str, ""));
                break;
            case "??????????????":
                u1.setText(sharedPreferences.getString("pt1_" + str, ""));
                u2.setText(sharedPreferences.getString("pt2_" + str, ""));
                u3.setText(sharedPreferences.getString("pt3_" + str, ""));
                u4.setText(sharedPreferences.getString("pt4_" + str, ""));
                u5.setText(sharedPreferences.getString("pt5_" + str, ""));
                u6.setText(sharedPreferences.getString("pt6_" + str, ""));
                u7.setText(sharedPreferences.getString("pt7_" + str, ""));
                u8.setText(sharedPreferences.getString("pt8_" + str, ""));
                break;
            case "??????????????":
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

    private void nigth_mode() {
        z1.setTextColor(getResources().getColor(R.color.gray));
        z2.setTextColor(getResources().getColor(R.color.gray));
        z3.setTextColor(getResources().getColor(R.color.gray));
        z4.setTextColor(getResources().getColor(R.color.gray));
        z5.setTextColor(getResources().getColor(R.color.gray));
        z6.setTextColor(getResources().getColor(R.color.gray));
        z7.setTextColor(getResources().getColor(R.color.gray));
        z8.setTextColor(getResources().getColor(R.color.gray));

        u1.setTextColor(getResources().getColor(R.color.white));
        u2.setTextColor(getResources().getColor(R.color.white));
        u3.setTextColor(getResources().getColor(R.color.white));
        u4.setTextColor(getResources().getColor(R.color.white));
        u5.setTextColor(getResources().getColor(R.color.white));
        u6.setTextColor(getResources().getColor(R.color.white));
        u7.setTextColor(getResources().getColor(R.color.white));
        u8.setTextColor(getResources().getColor(R.color.white));

        person_name.setTextColor(getResources().getColor(R.color.white));
        person_class.setTextColor(getResources().getColor(R.color.white));
        person_symbol.setTextColor(getResources().getColor(R.color.white));

        date.setTextColor(getResources().getColor(R.color.white));
        date.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.nigth_three));
        lay2.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.nigth_one));
        lay3.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.nigth_two));

        s.setTextColor(getResources().getColor(R.color.white));
        lay_sw.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.nigth_three));
        layout.setBackgroundColor(getResources().getColor(R.color.nigth_mode));

        task.setTextColor(getResources().getColor(R.color.white));
        next.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.nigth_three));
        back.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.nigth_three));
        next.setTextColor(getResources().getColor(R.color.white));
        back.setTextColor(getResources().getColor(R.color.white));

        menuBot.setBackgroundColor(getResources().getColor(R.color.menu_night));
        mHome.setImageResource(R.drawable.menu_home);
        mTime.setImageResource(R.drawable.menu_timetable);
        mSet.setImageResource(R.drawable.menu_settings);
        mProf.setImageResource(R.drawable.menu_profile);
    }

    private void day_mode() {
        z1.setTextColor(getResources().getColor(R.color.gray));
        z2.setTextColor(getResources().getColor(R.color.gray));
        z3.setTextColor(getResources().getColor(R.color.gray));
        z4.setTextColor(getResources().getColor(R.color.gray));
        z5.setTextColor(getResources().getColor(R.color.gray));
        z6.setTextColor(getResources().getColor(R.color.gray));
        z7.setTextColor(getResources().getColor(R.color.gray));
        z8.setTextColor(getResources().getColor(R.color.gray));

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

        date.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.shape_rect_three));
        lay2.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.shape_rect));
        lay3.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.shape_rect_two));

        s.setTextColor(getResources().getColor(R.color.black));

        layout.setBackgroundResource(R.drawable.background_main);
        lay_sw.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.shape_rect_three));

        task.setTextColor(getResources().getColor(R.color.black));
        next.setBackgroundColor(getResources().getColor(R.color.white));
        back.setBackgroundColor(getResources().getColor(R.color.white));
        next.setTextColor(getResources().getColor(R.color.black));
        back.setTextColor(getResources().getColor(R.color.black));

        menuBot.setBackgroundColor(getResources().getColor(R.color.menu_day));
        mHome.setImageResource(R.drawable.menu_home_day);
        mTime.setImageResource(R.drawable.menu_timetable_day);
        mSet.setImageResource(R.drawable.menu_settings_day);
        mProf.setImageResource(R.drawable.menu_profile_day);
    }

    void home_d(String num) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(num.equals("") || num.equals("----") || num.equals("?????????")){
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(num);

        final EditText input = new EditText(MainActivity.this);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        input.setText(sharedPreferences.getString("home"+num, ""));
        input.setHint("???????????????? ??????????????");
        builder.setView(input);

        builder.setPositiveButton("??????????????????", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor.putString("home"+num, input.getText().toString());
                editor.commit();
            }
        });
        builder.setNegativeButton("????????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
