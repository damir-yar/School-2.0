package com.example.school20;

import static android.graphics.Color.WHITE;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RaspActivity extends AppCompatActivity {

    private TextView u1_pn;
    private TextView u2_pn;
    private TextView u3_pn;
    private TextView u4_pn;
    private TextView u5_pn;
    private TextView u6_pn;
    private TextView u7_pn;
    private TextView u8_pn;

    private TextView u1_vt;
    private TextView u2_vt;
    private TextView u3_vt;
    private TextView u4_vt;
    private TextView u5_vt;
    private TextView u6_vt;
    private TextView u7_vt;
    private TextView u8_vt;

    private TextView u1_sr;
    private TextView u2_sr;
    private TextView u3_sr;
    private TextView u4_sr;
    private TextView u5_sr;
    private TextView u6_sr;
    private TextView u7_sr;
    private TextView u8_sr;

    private TextView u1_ch;
    private TextView u2_ch;
    private TextView u3_ch;
    private TextView u4_ch;
    private TextView u5_ch;
    private TextView u6_ch;
    private TextView u7_ch;
    private TextView u8_ch;

    private TextView u1_pt;
    private TextView u2_pt;
    private TextView u3_pt;
    private TextView u4_pt;
    private TextView u5_pt;
    private TextView u6_pt;
    private TextView u7_pt;
    private TextView u8_pt;

    private TextView u1_sb;
    private TextView u2_sb;
    private TextView u3_sb;
    private TextView u4_sb;
    private TextView u5_sb;
    private TextView u6_sb;
    private TextView u7_sb;
    private TextView u8_sb;


    private String name_pol;
    private String class_pol;
    private String symbol_pol;
    private String prof_pol;

    private TextView pn;
    private TextView vt;
    private TextView sr;
    private TextView ch;
    private TextView pt;
    private TextView sb;

    private LinearLayout r;
    private LinearLayout l1;
    private LinearLayout l2;
    private LinearLayout l3;
    private LinearLayout l4;
    private LinearLayout l5;
    private LinearLayout l6;
    private LinearLayout l7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rasp);
        setTitle("Расписание на неделю");

        u1_pn = findViewById(R.id.u1_pn);
        u2_pn = findViewById(R.id.u2_pn);
        u3_pn = findViewById(R.id.u3_pn);
        u4_pn = findViewById(R.id.u4_pn);
        u5_pn = findViewById(R.id.u5_pn);
        u6_pn = findViewById(R.id.u6_pn);
        u7_pn = findViewById(R.id.u7_pn);
        u8_pn = findViewById(R.id.u8_pn);

        u1_vt = findViewById(R.id.u1_vt);
        u2_vt = findViewById(R.id.u2_vt);
        u3_vt = findViewById(R.id.u3_vt);
        u4_vt = findViewById(R.id.u4_vt);
        u5_vt = findViewById(R.id.u5_vt);
        u6_vt = findViewById(R.id.u6_vt);
        u7_vt = findViewById(R.id.u7_vt);
        u8_vt = findViewById(R.id.u8_vt);

        u1_sr = findViewById(R.id.u1_sr);
        u2_sr = findViewById(R.id.u2_sr);
        u3_sr = findViewById(R.id.u3_sr);
        u4_sr = findViewById(R.id.u4_sr);
        u5_sr = findViewById(R.id.u5_sr);
        u6_sr = findViewById(R.id.u6_sr);
        u7_sr = findViewById(R.id.u7_sr);
        u8_sr = findViewById(R.id.u8_sr);

        u1_ch = findViewById(R.id.u1_ch);
        u2_ch = findViewById(R.id.u2_ch);
        u3_ch = findViewById(R.id.u3_ch);
        u4_ch = findViewById(R.id.u4_ch);
        u5_ch = findViewById(R.id.u5_ch);
        u6_ch = findViewById(R.id.u6_ch);
        u7_ch = findViewById(R.id.u7_ch);
        u8_ch = findViewById(R.id.u8_ch);

        u1_pt = findViewById(R.id.u1_pt);
        u2_pt = findViewById(R.id.u2_pt);
        u3_pt = findViewById(R.id.u3_pt);
        u4_pt = findViewById(R.id.u4_pt);
        u5_pt = findViewById(R.id.u5_pt);
        u6_pt = findViewById(R.id.u6_pt);
        u7_pt = findViewById(R.id.u7_pt);
        u8_pt = findViewById(R.id.u8_pt);

        u1_sb = findViewById(R.id.u1_sb);
        u2_sb = findViewById(R.id.u2_sb);
        u3_sb = findViewById(R.id.u3_sb);
        u4_sb = findViewById(R.id.u4_sb);
        u5_sb = findViewById(R.id.u5_sb);
        u6_sb = findViewById(R.id.u6_sb);
        u7_sb = findViewById(R.id.u7_sb);
        u8_sb = findViewById(R.id.u8_sb);

        pn = findViewById(R.id.pn);
        vt = findViewById(R.id.vt);
        sr = findViewById(R.id.sr);
        ch = findViewById(R.id.ch);
        pt = findViewById(R.id.pt);
        sb = findViewById(R.id.sb);

        r = findViewById(R.id.rrr);
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);
        l5 = findViewById(R.id.l5);
        l6 = findViewById(R.id.l6);
        l7 = findViewById(R.id.l7);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RaspActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        name_pol = sharedPreferences.getString("name", "unknown");
        class_pol = sharedPreferences.getString("class", "unknown");
        symbol_pol = sharedPreferences.getString("symbol", "unknown");
        prof_pol = sharedPreferences.getString("prof", "unknown");

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                u1_pn.setTextColor(getResources().getColor(R.color.black));
                u2_pn.setTextColor(getResources().getColor(R.color.black));
                u3_pn.setTextColor(getResources().getColor(R.color.black));
                u4_pn.setTextColor(getResources().getColor(R.color.black));
                u5_pn.setTextColor(getResources().getColor(R.color.black));
                u6_pn.setTextColor(getResources().getColor(R.color.black));
                u7_pn.setTextColor(getResources().getColor(R.color.black));
                u8_pn.setTextColor(getResources().getColor(R.color.black));

                u1_vt.setTextColor(getResources().getColor(R.color.black));
                u2_vt.setTextColor(getResources().getColor(R.color.black));
                u3_vt.setTextColor(getResources().getColor(R.color.black));
                u4_vt.setTextColor(getResources().getColor(R.color.black));
                u5_vt.setTextColor(getResources().getColor(R.color.black));
                u6_vt.setTextColor(getResources().getColor(R.color.black));
                u7_vt.setTextColor(getResources().getColor(R.color.black));
                u8_vt.setTextColor(getResources().getColor(R.color.black));

                u1_sr.setTextColor(getResources().getColor(R.color.black));
                u2_sr.setTextColor(getResources().getColor(R.color.black));
                u3_sr.setTextColor(getResources().getColor(R.color.black));
                u4_sr.setTextColor(getResources().getColor(R.color.black));
                u5_sr.setTextColor(getResources().getColor(R.color.black));
                u6_sr.setTextColor(getResources().getColor(R.color.black));
                u7_sr.setTextColor(getResources().getColor(R.color.black));
                u8_sr.setTextColor(getResources().getColor(R.color.black));

                u1_ch.setTextColor(getResources().getColor(R.color.black));
                u2_ch.setTextColor(getResources().getColor(R.color.black));
                u3_ch.setTextColor(getResources().getColor(R.color.black));
                u4_ch.setTextColor(getResources().getColor(R.color.black));
                u5_ch.setTextColor(getResources().getColor(R.color.black));
                u6_ch.setTextColor(getResources().getColor(R.color.black));
                u7_ch.setTextColor(getResources().getColor(R.color.black));
                u8_ch.setTextColor(getResources().getColor(R.color.black));

                u1_pt.setTextColor(getResources().getColor(R.color.black));
                u2_pt.setTextColor(getResources().getColor(R.color.black));
                u3_pt.setTextColor(getResources().getColor(R.color.black));
                u4_pt.setTextColor(getResources().getColor(R.color.black));
                u5_pt.setTextColor(getResources().getColor(R.color.black));
                u6_pt.setTextColor(getResources().getColor(R.color.black));
                u7_pt.setTextColor(getResources().getColor(R.color.black));
                u8_pt.setTextColor(getResources().getColor(R.color.black));

                u1_sb.setTextColor(getResources().getColor(R.color.black));
                u2_sb.setTextColor(getResources().getColor(R.color.black));
                u3_sb.setTextColor(getResources().getColor(R.color.black));
                u4_sb.setTextColor(getResources().getColor(R.color.black));
                u5_sb.setTextColor(getResources().getColor(R.color.black));
                u6_sb.setTextColor(getResources().getColor(R.color.black));
                u7_sb.setTextColor(getResources().getColor(R.color.black));
                u8_sb.setTextColor(getResources().getColor(R.color.black));

                pn.setTextColor(getResources().getColor(R.color.black));
                vt.setTextColor(getResources().getColor(R.color.black));
                sr.setTextColor(getResources().getColor(R.color.black));
                ch.setTextColor(getResources().getColor(R.color.black));
                pt.setTextColor(getResources().getColor(R.color.black));
                sb.setTextColor(getResources().getColor(R.color.black));

                r.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                u1_pn.setTextColor(getResources().getColor(R.color.black));
                u2_pn.setTextColor(getResources().getColor(R.color.black));
                u3_pn.setTextColor(getResources().getColor(R.color.black));
                u4_pn.setTextColor(getResources().getColor(R.color.black));
                u5_pn.setTextColor(getResources().getColor(R.color.black));
                u6_pn.setTextColor(getResources().getColor(R.color.black));
                u7_pn.setTextColor(getResources().getColor(R.color.black));
                u8_pn.setTextColor(getResources().getColor(R.color.black));

                u1_vt.setTextColor(getResources().getColor(R.color.black));
                u2_vt.setTextColor(getResources().getColor(R.color.black));
                u3_vt.setTextColor(getResources().getColor(R.color.black));
                u4_vt.setTextColor(getResources().getColor(R.color.black));
                u5_vt.setTextColor(getResources().getColor(R.color.black));
                u6_vt.setTextColor(getResources().getColor(R.color.black));
                u7_vt.setTextColor(getResources().getColor(R.color.black));
                u8_vt.setTextColor(getResources().getColor(R.color.black));

                u1_sr.setTextColor(getResources().getColor(R.color.black));
                u2_sr.setTextColor(getResources().getColor(R.color.black));
                u3_sr.setTextColor(getResources().getColor(R.color.black));
                u4_sr.setTextColor(getResources().getColor(R.color.black));
                u5_sr.setTextColor(getResources().getColor(R.color.black));
                u6_sr.setTextColor(getResources().getColor(R.color.black));
                u7_sr.setTextColor(getResources().getColor(R.color.black));
                u8_sr.setTextColor(getResources().getColor(R.color.black));

                u1_ch.setTextColor(getResources().getColor(R.color.black));
                u2_ch.setTextColor(getResources().getColor(R.color.black));
                u3_ch.setTextColor(getResources().getColor(R.color.black));
                u4_ch.setTextColor(getResources().getColor(R.color.black));
                u5_ch.setTextColor(getResources().getColor(R.color.black));
                u6_ch.setTextColor(getResources().getColor(R.color.black));
                u7_ch.setTextColor(getResources().getColor(R.color.black));
                u8_ch.setTextColor(getResources().getColor(R.color.black));

                u1_pt.setTextColor(getResources().getColor(R.color.black));
                u2_pt.setTextColor(getResources().getColor(R.color.black));
                u3_pt.setTextColor(getResources().getColor(R.color.black));
                u4_pt.setTextColor(getResources().getColor(R.color.black));
                u5_pt.setTextColor(getResources().getColor(R.color.black));
                u6_pt.setTextColor(getResources().getColor(R.color.black));
                u7_pt.setTextColor(getResources().getColor(R.color.black));
                u8_pt.setTextColor(getResources().getColor(R.color.black));

                u1_sb.setTextColor(getResources().getColor(R.color.black));
                u2_sb.setTextColor(getResources().getColor(R.color.black));
                u3_sb.setTextColor(getResources().getColor(R.color.black));
                u4_sb.setTextColor(getResources().getColor(R.color.black));
                u5_sb.setTextColor(getResources().getColor(R.color.black));
                u6_sb.setTextColor(getResources().getColor(R.color.black));
                u7_sb.setTextColor(getResources().getColor(R.color.black));
                u8_sb.setTextColor(getResources().getColor(R.color.black));


                pn.setTextColor(getResources().getColor(R.color.white));
                vt.setTextColor(getResources().getColor(R.color.white));
                sr.setTextColor(getResources().getColor(R.color.white));
                ch.setTextColor(getResources().getColor(R.color.white));
                pt.setTextColor(getResources().getColor(R.color.white));
                sb.setTextColor(getResources().getColor(R.color.white));

                r.setBackgroundColor(getResources().getColor(R.color.black));
                break;
        }

        if (class_pol.equals("11") && (symbol_pol.equals("а") || symbol_pol.equals("А")) && prof_pol.equals("tech"))
            rasp11at();
        else if (class_pol.equals("11") && (symbol_pol.equals("а") || symbol_pol.equals("А")) && prof_pol.equals("social"))
            rasp11as();
        else if (class_pol.equals("10") && (symbol_pol.equals("т") || symbol_pol.equals("Т")))
            rasp10t();
        else if (class_pol.equals("10") && (symbol_pol.equals("с") || symbol_pol.equals("С")))
            rasp10s();

    }

    void rasp11at() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RaspActivity.this);
        u1_pn.setText(sharedPreferences.getString("pn1_11at", ""));
        u2_pn.setText(sharedPreferences.getString("pn2_11at", ""));
        u3_pn.setText(sharedPreferences.getString("pn3_11at", ""));
        u4_pn.setText(sharedPreferences.getString("pn4_11at", ""));
        u5_pn.setText(sharedPreferences.getString("pn5_11at", ""));
        u6_pn.setText(sharedPreferences.getString("pn6_11at", ""));
        u7_pn.setText(sharedPreferences.getString("pn7_11at", ""));
        u8_pn.setText(sharedPreferences.getString("pn8_11at", ""));

        u1_vt.setText(sharedPreferences.getString("vt1_11at", ""));
        u2_vt.setText(sharedPreferences.getString("vt2_11at", ""));
        u3_vt.setText(sharedPreferences.getString("vt3_11at", ""));
        u4_vt.setText(sharedPreferences.getString("vt4_11at", ""));
        u5_vt.setText(sharedPreferences.getString("vt5_11at", ""));
        u6_vt.setText(sharedPreferences.getString("vt6_11at", ""));
        u7_vt.setText(sharedPreferences.getString("vt7_11at", ""));
        u8_vt.setText(sharedPreferences.getString("vt8_11at", ""));

        u1_sr.setText(sharedPreferences.getString("sr1_11at", ""));
        u2_sr.setText(sharedPreferences.getString("sr2_11at", ""));
        u3_sr.setText(sharedPreferences.getString("sr3_11at", ""));
        u4_sr.setText(sharedPreferences.getString("sr4_11at", ""));
        u5_sr.setText(sharedPreferences.getString("sr5_11at", ""));
        u6_sr.setText(sharedPreferences.getString("sr6_11at", ""));
        u7_sr.setText(sharedPreferences.getString("sr7_11at", ""));
        u8_sr.setText(sharedPreferences.getString("sr8_11at", ""));

        u1_ch.setText(sharedPreferences.getString("ch1_11at", ""));
        u2_ch.setText(sharedPreferences.getString("ch2_11at", ""));
        u3_ch.setText(sharedPreferences.getString("ch3_11at", ""));
        u4_ch.setText(sharedPreferences.getString("ch4_11at", ""));
        u5_ch.setText(sharedPreferences.getString("ch5_11at", ""));
        u6_ch.setText(sharedPreferences.getString("ch6_11at", ""));
        u7_ch.setText(sharedPreferences.getString("ch7_11at", ""));
        u8_ch.setText(sharedPreferences.getString("ch8_11at", ""));

        u1_pt.setText(sharedPreferences.getString("pt1_11at", ""));
        u2_pt.setText(sharedPreferences.getString("pt2_11at", ""));
        u3_pt.setText(sharedPreferences.getString("pt3_11at", ""));
        u4_pt.setText(sharedPreferences.getString("pt4_11at", ""));
        u5_pt.setText(sharedPreferences.getString("pt5_11at", ""));
        u6_pt.setText(sharedPreferences.getString("pt6_11at", ""));
        u7_pt.setText(sharedPreferences.getString("pt7_11at", ""));
        u8_pt.setText(sharedPreferences.getString("pt8_11at", ""));

        u1_sb.setText(sharedPreferences.getString("sb1_11at", ""));
        u2_sb.setText(sharedPreferences.getString("sb2_11at", ""));
        u3_sb.setText(sharedPreferences.getString("sb3_11at", ""));
        u4_sb.setText(sharedPreferences.getString("sb4_11at", ""));
        u5_sb.setText(sharedPreferences.getString("sb5_11at", ""));
        u6_sb.setText(sharedPreferences.getString("sb6_11at", ""));
        u7_sb.setText(sharedPreferences.getString("sb7_11at", ""));
        u8_sb.setText(sharedPreferences.getString("sb8_11at", ""));
    }

    void rasp11as() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RaspActivity.this);
        u1_pn.setText(sharedPreferences.getString("pn1_11as", ""));
        u2_pn.setText(sharedPreferences.getString("pn2_11as", ""));
        u3_pn.setText(sharedPreferences.getString("pn3_11as", ""));
        u4_pn.setText(sharedPreferences.getString("pn4_11as", ""));
        u5_pn.setText(sharedPreferences.getString("pn5_11as", ""));
        u6_pn.setText(sharedPreferences.getString("pn6_11as", ""));
        u7_pn.setText(sharedPreferences.getString("pn7_11as", ""));
        u8_pn.setText(sharedPreferences.getString("pn8_11as", ""));

        u1_vt.setText(sharedPreferences.getString("vt1_11as", ""));
        u2_vt.setText(sharedPreferences.getString("vt2_11as", ""));
        u3_vt.setText(sharedPreferences.getString("vt3_11as", ""));
        u4_vt.setText(sharedPreferences.getString("vt4_11as", ""));
        u5_vt.setText(sharedPreferences.getString("vt5_11as", ""));
        u6_vt.setText(sharedPreferences.getString("vt6_11as", ""));
        u7_vt.setText(sharedPreferences.getString("vt7_11as", ""));
        u8_vt.setText(sharedPreferences.getString("vt8_11as", ""));

        u1_sr.setText(sharedPreferences.getString("sr1_11as", ""));
        u2_sr.setText(sharedPreferences.getString("sr2_11as", ""));
        u3_sr.setText(sharedPreferences.getString("sr3_11as", ""));
        u4_sr.setText(sharedPreferences.getString("sr4_11as", ""));
        u5_sr.setText(sharedPreferences.getString("sr5_11as", ""));
        u6_sr.setText(sharedPreferences.getString("sr6_11as", ""));
        u7_sr.setText(sharedPreferences.getString("sr7_11as", ""));
        u8_sr.setText(sharedPreferences.getString("sr8_11as", ""));

        u1_ch.setText(sharedPreferences.getString("ch1_11as", ""));
        u2_ch.setText(sharedPreferences.getString("ch2_11as", ""));
        u3_ch.setText(sharedPreferences.getString("ch3_11as", ""));
        u4_ch.setText(sharedPreferences.getString("ch4_11as", ""));
        u5_ch.setText(sharedPreferences.getString("ch5_11as", ""));
        u6_ch.setText(sharedPreferences.getString("ch6_11as", ""));
        u7_ch.setText(sharedPreferences.getString("ch7_11as", ""));
        u8_ch.setText(sharedPreferences.getString("ch8_11as", ""));

        u1_pt.setText(sharedPreferences.getString("pt1_11as", ""));
        u2_pt.setText(sharedPreferences.getString("pt2_11as", ""));
        u3_pt.setText(sharedPreferences.getString("pt3_11as", ""));
        u4_pt.setText(sharedPreferences.getString("pt4_11as", ""));
        u5_pt.setText(sharedPreferences.getString("pt5_11as", ""));
        u6_pt.setText(sharedPreferences.getString("pt6_11as", ""));
        u7_pt.setText(sharedPreferences.getString("pt7_11as", ""));
        u8_pt.setText(sharedPreferences.getString("pt8_11as", ""));

        u1_sb.setText(sharedPreferences.getString("sb1_11as", ""));
        u2_sb.setText(sharedPreferences.getString("sb2_11as", ""));
        u3_sb.setText(sharedPreferences.getString("sb3_11as", ""));
        u4_sb.setText(sharedPreferences.getString("sb4_11as", ""));
        u5_sb.setText(sharedPreferences.getString("sb5_11as", ""));
        u6_sb.setText(sharedPreferences.getString("sb6_11as", ""));
        u7_sb.setText(sharedPreferences.getString("sb7_11as", ""));
        u8_sb.setText(sharedPreferences.getString("sb8_11as", ""));
    }

    void rasp10t() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RaspActivity.this);
        u1_pn.setText(sharedPreferences.getString("pn1_10t", ""));
        u2_pn.setText(sharedPreferences.getString("pn2_10t", ""));
        u3_pn.setText(sharedPreferences.getString("pn3_10t", ""));
        u4_pn.setText(sharedPreferences.getString("pn4_10t", ""));
        u5_pn.setText(sharedPreferences.getString("pn5_10t", ""));
        u6_pn.setText(sharedPreferences.getString("pn6_10t", ""));
        u7_pn.setText(sharedPreferences.getString("pn7_10t", ""));
        u8_pn.setText(sharedPreferences.getString("pn8_10t", ""));

        u1_vt.setText(sharedPreferences.getString("vt1_10t", ""));
        u2_vt.setText(sharedPreferences.getString("vt2_10t", ""));
        u3_vt.setText(sharedPreferences.getString("vt3_10t", ""));
        u4_vt.setText(sharedPreferences.getString("vt4_10t", ""));
        u5_vt.setText(sharedPreferences.getString("vt5_10t", ""));
        u6_vt.setText(sharedPreferences.getString("vt6_10t", ""));
        u7_vt.setText(sharedPreferences.getString("vt7_10t", ""));
        u8_vt.setText(sharedPreferences.getString("vt8_10t", ""));

        u1_sr.setText(sharedPreferences.getString("sr1_10t", ""));
        u2_sr.setText(sharedPreferences.getString("sr2_10t", ""));
        u3_sr.setText(sharedPreferences.getString("sr3_10t", ""));
        u4_sr.setText(sharedPreferences.getString("sr4_10t", ""));
        u5_sr.setText(sharedPreferences.getString("sr5_10t", ""));
        u6_sr.setText(sharedPreferences.getString("sr6_10t", ""));
        u7_sr.setText(sharedPreferences.getString("sr7_10t", ""));
        u8_sr.setText(sharedPreferences.getString("sr8_10t", ""));

        u1_ch.setText(sharedPreferences.getString("ch1_10t", ""));
        u2_ch.setText(sharedPreferences.getString("ch2_10t", ""));
        u3_ch.setText(sharedPreferences.getString("ch3_10t", ""));
        u4_ch.setText(sharedPreferences.getString("ch4_10t", ""));
        u5_ch.setText(sharedPreferences.getString("ch5_10t", ""));
        u6_ch.setText(sharedPreferences.getString("ch6_10t", ""));
        u7_ch.setText(sharedPreferences.getString("ch7_10t", ""));
        u8_ch.setText(sharedPreferences.getString("ch8_10t", ""));

        u1_pt.setText(sharedPreferences.getString("pt1_10t", ""));
        u2_pt.setText(sharedPreferences.getString("pt2_10t", ""));
        u3_pt.setText(sharedPreferences.getString("pt3_10t", ""));
        u4_pt.setText(sharedPreferences.getString("pt4_10t", ""));
        u5_pt.setText(sharedPreferences.getString("pt5_10t", ""));
        u6_pt.setText(sharedPreferences.getString("pt6_10t", ""));
        u7_pt.setText(sharedPreferences.getString("pt7_10t", ""));
        u8_pt.setText(sharedPreferences.getString("pt8_10t", ""));

        u1_sb.setText(sharedPreferences.getString("sb1_10t", ""));
        u2_sb.setText(sharedPreferences.getString("sb2_10t", ""));
        u3_sb.setText(sharedPreferences.getString("sb3_10t", ""));
        u4_sb.setText(sharedPreferences.getString("sb4_10t", ""));
        u5_sb.setText(sharedPreferences.getString("sb5_10t", ""));
        u6_sb.setText(sharedPreferences.getString("sb6_10t", ""));
        u7_sb.setText(sharedPreferences.getString("sb7_10t", ""));
        u8_sb.setText(sharedPreferences.getString("sb8_10t", ""));
    }

    void rasp10s() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RaspActivity.this);
        u1_pn.setText(sharedPreferences.getString("pn1_10s", ""));
        u2_pn.setText(sharedPreferences.getString("pn2_10s", ""));
        u3_pn.setText(sharedPreferences.getString("pn3_10s", ""));
        u4_pn.setText(sharedPreferences.getString("pn4_10s", ""));
        u5_pn.setText(sharedPreferences.getString("pn5_10s", ""));
        u6_pn.setText(sharedPreferences.getString("pn6_10s", ""));
        u7_pn.setText(sharedPreferences.getString("pn7_10s", ""));
        u8_pn.setText(sharedPreferences.getString("pn8_10s", ""));

        u1_vt.setText(sharedPreferences.getString("vt1_10s", ""));
        u2_vt.setText(sharedPreferences.getString("vt2_10s", ""));
        u3_vt.setText(sharedPreferences.getString("vt3_10s", ""));
        u4_vt.setText(sharedPreferences.getString("vt4_10s", ""));
        u5_vt.setText(sharedPreferences.getString("vt5_10s", ""));
        u6_vt.setText(sharedPreferences.getString("vt6_10s", ""));
        u7_vt.setText(sharedPreferences.getString("vt7_10s", ""));
        u8_vt.setText(sharedPreferences.getString("vt8_10s", ""));

        u1_sr.setText(sharedPreferences.getString("sr1_10s", ""));
        u2_sr.setText(sharedPreferences.getString("sr2_10s", ""));
        u3_sr.setText(sharedPreferences.getString("sr3_10s", ""));
        u4_sr.setText(sharedPreferences.getString("sr4_10s", ""));
        u5_sr.setText(sharedPreferences.getString("sr5_10s", ""));
        u6_sr.setText(sharedPreferences.getString("sr6_10s", ""));
        u7_sr.setText(sharedPreferences.getString("sr7_10s", ""));
        u8_sr.setText(sharedPreferences.getString("sr8_10s", ""));

        u1_ch.setText(sharedPreferences.getString("ch1_10s", ""));
        u2_ch.setText(sharedPreferences.getString("ch2_10s", ""));
        u3_ch.setText(sharedPreferences.getString("ch3_10s", ""));
        u4_ch.setText(sharedPreferences.getString("ch4_10s", ""));
        u5_ch.setText(sharedPreferences.getString("ch5_10s", ""));
        u6_ch.setText(sharedPreferences.getString("ch6_10s", ""));
        u7_ch.setText(sharedPreferences.getString("ch7_10s", ""));
        u8_ch.setText(sharedPreferences.getString("ch8_10s", ""));

        u1_pt.setText(sharedPreferences.getString("pt1_10s", ""));
        u2_pt.setText(sharedPreferences.getString("pt2_10s", ""));
        u3_pt.setText(sharedPreferences.getString("pt3_10s", ""));
        u4_pt.setText(sharedPreferences.getString("pt4_10s", ""));
        u5_pt.setText(sharedPreferences.getString("pt5_10s", ""));
        u6_pt.setText(sharedPreferences.getString("pt6_10s", ""));
        u7_pt.setText(sharedPreferences.getString("pt7_10s", ""));
        u8_pt.setText(sharedPreferences.getString("pt8_10s", ""));

        u1_sb.setText(sharedPreferences.getString("sb1_10s", ""));
        u2_sb.setText(sharedPreferences.getString("sb2_10s", ""));
        u3_sb.setText(sharedPreferences.getString("sb3_10s", ""));
        u4_sb.setText(sharedPreferences.getString("sb4_10s", ""));
        u5_sb.setText(sharedPreferences.getString("sb5_10s", ""));
        u6_sb.setText(sharedPreferences.getString("sb6_10s", ""));
        u7_sb.setText(sharedPreferences.getString("sb7_10s", ""));
        u8_sb.setText(sharedPreferences.getString("sb8_10s", ""));
    }
}