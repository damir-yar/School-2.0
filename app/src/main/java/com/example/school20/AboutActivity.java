package com.example.school20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("О приложении");

        about = findViewById(R.id.about);

        String urlT = "https://api.npoint.io/86de4c9a1714afd58caa";
        new getUrlData2().execute(urlT);


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
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Загрузка...", Toast.LENGTH_SHORT);
            toast.show();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            about.setText(txt);
        }JSONObject jsonObject = null;
    }
}