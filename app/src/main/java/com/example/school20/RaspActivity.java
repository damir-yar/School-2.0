package com.example.school20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RaspActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        name_pol = sharedPreferences.getString("name", "unknown");
        class_pol = sharedPreferences.getString("class", "unknown");
        symbol_pol = sharedPreferences.getString("symbol", "unknown");
        prof_pol = sharedPreferences.getString("prof", "unknown");

        if (class_pol.equals("11") && (symbol_pol.equals("а") || symbol_pol.equals("А")) && prof_pol.equals("tech"))
            rasp11at();
        else if (class_pol.equals("11") && (symbol_pol.equals("а") || symbol_pol.equals("А")) && prof_pol.equals("social"))
            rasp11as();

    }

    void rasp11at() {
        u1_pn.setText(R.string.pn_one_11at);
        u2_pn.setText(R.string.pn_two_11at);
        u3_pn.setText(R.string.pn_three_11at);
        u4_pn.setText(R.string.pn_four_11at);
        u5_pn.setText(R.string.pn_five_11at);
        u6_pn.setText(R.string.pn_six_11at);
        u7_pn.setText(R.string.pn_seven_11at);
        u8_pn.setText(R.string.pn_eight_11at);

        u1_vt.setText(R.string.vt_one_11at);
        u2_vt.setText(R.string.vt_two_11at);
        u3_vt.setText(R.string.vt_three_11at);
        u4_vt.setText(R.string.vt_four_11at);
        u5_vt.setText(R.string.vt_five_11at);
        u6_vt.setText(R.string.vt_six_11at);
        u7_vt.setText(R.string.vt_seven_11at);
        u8_vt.setText(R.string.vt_eight_11at);

        u1_sr.setText(R.string.sr_one_11at);
        u2_sr.setText(R.string.sr_two_11at);
        u3_sr.setText(R.string.sr_three_11at);
        u4_sr.setText(R.string.sr_four_11at);
        u5_sr.setText(R.string.sr_five_11at);
        u6_sr.setText(R.string.sr_six_11at);
        u7_sr.setText(R.string.sr_seven_11at);
        u8_sr.setText(R.string.sr_eight_11at);

        u1_ch.setText(R.string.ch_one_11at);
        u2_ch.setText(R.string.ch_two_11at);
        u3_ch.setText(R.string.ch_three_11at);
        u4_ch.setText(R.string.ch_four_11at);
        u5_ch.setText(R.string.ch_five_11at);
        u6_ch.setText(R.string.ch_six_11at);
        u7_ch.setText(R.string.ch_seven_11at);
        u8_ch.setText(R.string.ch_eight_11at);

        u1_pt.setText(R.string.pt_one_11at);
        u2_pt.setText(R.string.pt_two_11at);
        u3_pt.setText(R.string.pt_three_11at);
        u4_pt.setText(R.string.pt_four_11at);
        u5_pt.setText(R.string.pt_five_11at);
        u6_pt.setText(R.string.pt_six_11at);
        u7_pt.setText(R.string.pt_seven_11at);
        u8_pt.setText(R.string.pt_eight_11at);

        u1_sb.setText(R.string.sb_one_11at);
        u2_sb.setText(R.string.sb_two_11at);
        u3_sb.setText(R.string.sb_three_11at);
        u4_sb.setText(R.string.sb_four_11at);
        u5_sb.setText(R.string.sb_five_11at);
        u6_sb.setText(R.string.sb_six_11at);
        u7_sb.setText(R.string.sb_seven_11at);
        u8_sb.setText(R.string.sb_eight_11at);
    }

    void rasp11as() {
        u1_pn.setText(R.string.pn_one_11as);
        u2_pn.setText(R.string.pn_two_11as);
        u3_pn.setText(R.string.pn_three_11as);
        u4_pn.setText(R.string.pn_four_11as);
        u5_pn.setText(R.string.pn_five_11as);
        u6_pn.setText(R.string.pn_six_11as);
        u7_pn.setText(R.string.pn_seven_11as);
        u8_pn.setText(R.string.pn_eight_11as);

        u1_vt.setText(R.string.vt_one_11as);
        u2_vt.setText(R.string.vt_two_11as);
        u3_vt.setText(R.string.vt_three_11as);
        u4_vt.setText(R.string.vt_four_11as);
        u5_vt.setText(R.string.vt_five_11as);
        u6_vt.setText(R.string.vt_six_11as);
        u7_vt.setText(R.string.vt_seven_11as);
        u8_vt.setText(R.string.vt_eight_11as);

        u1_sr.setText(R.string.sr_one_11as);
        u2_sr.setText(R.string.sr_two_11as);
        u3_sr.setText(R.string.sr_three_11as);
        u4_sr.setText(R.string.sr_four_11as);
        u5_sr.setText(R.string.sr_five_11as);
        u6_sr.setText(R.string.sr_six_11as);
        u7_sr.setText(R.string.sr_seven_11as);
        u8_sr.setText(R.string.sr_eight_11as);

        u1_ch.setText(R.string.ch_one_11as);
        u2_ch.setText(R.string.ch_two_11as);
        u3_ch.setText(R.string.ch_three_11as);
        u4_ch.setText(R.string.ch_four_11as);
        u5_ch.setText(R.string.ch_five_11as);
        u6_ch.setText(R.string.ch_six_11as);
        u7_ch.setText(R.string.ch_seven_11as);
        u8_ch.setText(R.string.ch_eight_11as);

        u1_pt.setText(R.string.pt_one_11as);
        u2_pt.setText(R.string.pt_two_11as);
        u3_pt.setText(R.string.pt_three_11as);
        u4_pt.setText(R.string.pt_four_11as);
        u5_pt.setText(R.string.pt_five_11as);
        u6_pt.setText(R.string.pt_six_11as);
        u7_pt.setText(R.string.pt_seven_11as);
        u8_pt.setText(R.string.pt_eight_11as);

        u1_sb.setText(R.string.sb_one_11as);
        u2_sb.setText(R.string.sb_two_11as);
        u3_sb.setText(R.string.sb_three_11as);
        u4_sb.setText(R.string.sb_four_11as);
        u5_sb.setText(R.string.sb_five_11as);
        u6_sb.setText(R.string.sb_six_11as);
        u7_sb.setText(R.string.sb_seven_11as);
        u8_sb.setText(R.string.sb_eight_11as);
    }
}