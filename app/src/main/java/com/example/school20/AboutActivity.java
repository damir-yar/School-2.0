package com.example.school20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;
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
import java.util.concurrent.TimeUnit;

public class AboutActivity extends AppCompatActivity {

    private TextView about;
    private String txt;
    private LinearLayout lay;

    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private TextView t6;
    private TextView t7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("О приложении");

        about = findViewById(R.id.about);
        lay = findViewById(R.id.layout);

        t1 = findViewById(R.id.txt_one);
        t2 = findViewById(R.id.txt_two);
        t3 = findViewById(R.id.txt_three);
        t4 = findViewById(R.id.txt_four);
        t5 = findViewById(R.id.txt_five);
        t6 = findViewById(R.id.txt_six);
        t7 = findViewById(R.id.txt_seven);

        String urlT = "https://api.npoint.io/86de4c9a1714afd58caa";
        Toast toast1 = Toast.makeText(getApplicationContext(),
                "Загрузка...", Toast.LENGTH_SHORT);
        toast1.show();
        new getUrlData2().execute(urlT);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AboutActivity.this);
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
                txt = jsonObject.getJSONObject("connect").getString("text");

            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ошибка JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }
            about.setText(txt);
        }JSONObject jsonObject = null;
    }

    private void nigth_mode() {
        lay.setBackgroundColor(getResources().getColor(R.color.nigth_mode));
        t1.setTextColor(getResources().getColor(R.color.white));
        t2.setTextColor(getResources().getColor(R.color.white));
        t3.setTextColor(getResources().getColor(R.color.white));
        t4.setTextColor(getResources().getColor(R.color.white));
        t5.setTextColor(getResources().getColor(R.color.white));
        t6.setTextColor(getResources().getColor(R.color.white));
        t7.setTextColor(getResources().getColor(R.color.white));
        about.setTextColor(getResources().getColor(R.color.white));
    }
    private void day_mode() {
        lay.setBackgroundColor(getResources().getColor(R.color.white));
    }
}