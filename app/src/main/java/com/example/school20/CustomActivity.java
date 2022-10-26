package com.example.school20;

import static com.example.school20.MainActivity.GALLERY_REQUEST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CustomActivity extends AppCompatActivity {

    private ImageView ch_im1;
    private ImageView ch_im2;
    private ImageView ch_im3;
    private ImageView ch_im4;
    private ImageView ch_im5;
    private ImageView ch_im6;
    private ImageView ch_im7;
    private ImageView ch_im8;
    private ImageView ch_im9;
    private ImageView ch_im10;

    private Button but_ch_im1;
    private Button but_ch_im2;
    private Button but_ch_im3;
    private Button but_ch_im4;
    private Button but_ch_im5;
    private Button but_ch_im6;
    private Button but_ch_im7;
    private Button but_ch_im8;
    private Button but_ch_im9;
    private Button but_ch_im10;

    private HorizontalScrollView scr;
    private LinearLayout ln;

    private Button gl;
    private Button clear;

    private Button sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        setTitle("Кастомизация");

        ch_im1 = findViewById(R.id.choise_im1);
        ch_im2 = findViewById(R.id.choise_im2);
        ch_im3 = findViewById(R.id.choise_im3);
        ch_im4 = findViewById(R.id.choise_im4);
        ch_im5 = findViewById(R.id.choise_im5);
        ch_im6 = findViewById(R.id.choise_im6);
        ch_im7 = findViewById(R.id.choise_im7);
        ch_im8 = findViewById(R.id.choise_im8);
        ch_im9 = findViewById(R.id.choise_im9);
        ch_im10 = findViewById(R.id.choise_im10);

        but_ch_im1 = findViewById(R.id.but_im1);
        but_ch_im2 = findViewById(R.id.but_im2);
        but_ch_im3 = findViewById(R.id.but_im3);
        but_ch_im4 = findViewById(R.id.but_im4);
        but_ch_im5 = findViewById(R.id.but_im5);
        but_ch_im6 = findViewById(R.id.but_im6);
        but_ch_im7 = findViewById(R.id.but_im7);
        but_ch_im8 = findViewById(R.id.but_im8);
        but_ch_im9 = findViewById(R.id.but_im9);
        but_ch_im10 = findViewById(R.id.but_im10);

        scr = findViewById(R.id.scroll);
        ln = findViewById(R.id.two_but);

        sv = findViewById(R.id.save);

        gl = findViewById(R.id.gal);
        clear = findViewById(R.id.clr);

        scr.setVisibility(View.INVISIBLE);
        sv.setVisibility(View.INVISIBLE);
        ln.setVisibility(View.INVISIBLE);


                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(CustomActivity.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("url_im1_theme_5", "");
                editor.commit();
                ln.setVisibility(View.VISIBLE);
                gl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sv.setVisibility(View.VISIBLE);
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
                    }
                });
                boolean inet = isNetworkConnected();
                if (inet == true) {
                    String urlT = "https://api.npoint.io/86de4c9a1714afd58caa";
                    new getUrlDataImage().execute(urlT);
                    Toast toast1 = Toast.makeText(getApplicationContext(),
                            "Загрузка гифок...", Toast.LENGTH_SHORT);
                    toast1.show(); }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Нет подключения к интернету", Toast.LENGTH_SHORT);
                    toast.show(); }



        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(CustomActivity.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("im_url_data", "");
                editor.putString("im_data", "");
                editor.commit();
            }
        });

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomActivity.this, MainActivity.class);
                    startActivity(intent);
            }
        });

//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;

        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(CustomActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("im_data",encodedImage);
                    editor.putString("im_url_data", "");
                    editor.commit();
//                    Intent intent = new Intent(CustomActivity.this, MainActivity.class);
//                    startActivity(intent);
                }
        }
    }

    private class getUrlDataImage extends AsyncTask<String, String, String> {
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
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(CustomActivity.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("url_im1_theme_1", jsonObject.getJSONObject("image").getJSONObject("im1").getString("theme_1"));
                editor.putString("url_im1_theme_2", jsonObject.getJSONObject("image").getJSONObject("im1").getString("theme_2"));
                editor.putString("url_im1_theme_3", jsonObject.getJSONObject("image").getJSONObject("im1").getString("theme_3"));
                editor.putString("url_im1_theme_4", jsonObject.getJSONObject("image").getJSONObject("im1").getString("theme_4"));
                editor.putString("url_im1_theme_5", jsonObject.getJSONObject("image").getJSONObject("im1").getString("theme_5"));
                editor.putString("url_im1_theme_6", jsonObject.getJSONObject("image").getJSONObject("im1").getString("theme_6"));
                editor.putString("url_im1_theme_7", jsonObject.getJSONObject("image").getJSONObject("im1").getString("theme_7"));
                editor.putString("url_im1_theme_8", jsonObject.getJSONObject("image").getJSONObject("im1").getString("theme_8"));
                editor.putString("url_im1_theme_9", jsonObject.getJSONObject("image").getJSONObject("im1").getString("theme_9"));
                editor.putString("url_im1_theme_10", jsonObject.getJSONObject("image").getJSONObject("im1").getString("theme_10"));
                editor.putString("image_data", "");
                editor.commit();

                String imageData1 = sharedPreferences.getString("url_im1_theme_1", "");
                String imageData2 = sharedPreferences.getString("url_im1_theme_2", "");
                String imageData3 = sharedPreferences.getString("url_im1_theme_3", "");
                String imageData4 = sharedPreferences.getString("url_im1_theme_4", "");
                String imageData5 = sharedPreferences.getString("url_im1_theme_5", "");
                String imageData6 = sharedPreferences.getString("url_im1_theme_6", "");
                String imageData7 = sharedPreferences.getString("url_im1_theme_7", "");
                String imageData8 = sharedPreferences.getString("url_im1_theme_8", "");
                String imageData9 = sharedPreferences.getString("url_im1_theme_9", "");
                String imageData10 = sharedPreferences.getString("url_im1_theme_10", "");
                scr.setVisibility(View.VISIBLE);
                sv.setVisibility(View.VISIBLE);
                Glide.with(CustomActivity.this).load(imageData1).into(ch_im1);
                Glide.with(CustomActivity.this).load(imageData2).into(ch_im2);
                Glide.with(CustomActivity.this).load(imageData3).into(ch_im3);
                Glide.with(CustomActivity.this).load(imageData4).into(ch_im4);
                Glide.with(CustomActivity.this).load(imageData5).into(ch_im5);
                Glide.with(CustomActivity.this).load(imageData6).into(ch_im6);
                Glide.with(CustomActivity.this).load(imageData7).into(ch_im7);
                Glide.with(CustomActivity.this).load(imageData8).into(ch_im8);
                Glide.with(CustomActivity.this).load(imageData9).into(ch_im9);
                Glide.with(CustomActivity.this).load(imageData10).into(ch_im10);
                but_ch_im1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putString("im_url_data", imageData1);
                        editor.putString("im_data", "");
                        editor.commit();
                    }
                });
                but_ch_im2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putString("im_url_data", imageData2);
                        editor.putString("im_data", "");
                        editor.commit();
                    }
                });
                but_ch_im3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putString("im_url_data", imageData3);
                        editor.putString("im_data", "");
                        editor.commit();
                    }
                });
                but_ch_im4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putString("im_url_data", imageData4);
                        editor.putString("im_data", "");
                        editor.commit();
                    }
                });
                but_ch_im5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putString("im_url_data", imageData5);
                        editor.putString("im_data", "");
                        editor.commit();
                    }
                });
                but_ch_im6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putString("im_url_data", imageData6);
                        editor.putString("im_data", "");
                        editor.commit();
                    }
                });
                but_ch_im7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putString("im_url_data", imageData7);
                        editor.putString("im_data", "");
                        editor.commit();
                    }
                });
                but_ch_im8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putString("im_url_data", imageData8);
                        editor.putString("im_data", "");
                        editor.commit();
                    }
                });
                but_ch_im9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putString("im_url_data", imageData9);
                        editor.putString("im_data", "");
                        editor.commit();
                    }
                });
                but_ch_im10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putString("im_url_data", imageData10);
                        editor.putString("im_data", "");
                        editor.commit();
                    }
                });
            } catch (JSONException jsonException) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ошибка JSON", Toast.LENGTH_SHORT);
                toast.show();
                jsonException.printStackTrace();
            }
        }JSONObject jsonObject = null;
    }
        private boolean isNetworkConnected() {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
        }
}