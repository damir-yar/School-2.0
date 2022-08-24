package com.example.school20;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        date.setText(day_week);

        rasp();

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
            }
        });

        raspZ();

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
                } else {
                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_WEEK);

                    switch (day) {
                        case Calendar.SATURDAY:
                            z1.setText(R.string.rasp_z1_sb);
                            z2.setText(R.string.rasp_z2_sb);
                            z3.setText(R.string.rasp_z3_sb);
                            z4.setText(R.string.rasp_z4_sb);
                            z5.setText(R.string.rasp_z5_sb);
                            z6.setText(R.string.rasp_z6_sb);
                            z7.setText(R.string.rasp_z7_sb);
                            z8.setText(R.string.rasp_z8_sb);
                            break;
                        default:
                            z1.setText(R.string.rasp_z1_default);
                            z2.setText(R.string.rasp_z2_default);
                            z3.setText(R.string.rasp_z3_default);
                            z4.setText(R.string.rasp_z4_default);
                            z5.setText(R.string.rasp_z5_default);
                            z6.setText(R.string.rasp_z6_default);
                            z7.setText(R.string.rasp_z7_default);
                            z8.setText(R.string.rasp_z8_default);
                            break;
                    }
                }
            }});
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
            default:
                return super.onOptionsItemSelected(item);
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
    }

    void rasp() {
        if (class_pol.equals("11") && (symbol_pol.equals("а") || symbol_pol.equals("А")) && prof_pol.equals("tech"))
            rasp11at();
        else if (class_pol.equals("11") && (symbol_pol.equals("а") || symbol_pol.equals("А")) && prof_pol.equals("social"))
            rasp11as();
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
        switch (day_week) {
            case "Воскресенье":
                u1.setText("Выходной");
                u2.setText("");
                u3.setText("");
                u4.setText("");
                u5.setText("");
                u6.setText("");
                u7.setText("");
                u8.setText("");
                break;
            case "Понедельник":
                u1.setText(R.string.pn_one_11at);
                u2.setText(R.string.pn_two_11at);
                u3.setText(R.string.pn_three_11at);
                u4.setText(R.string.pn_four_11at);
                u5.setText(R.string.pn_five_11at);
                u6.setText(R.string.pn_six_11at);
                u7.setText(R.string.pn_seven_11at);
                u8.setText(R.string.pn_eight_11at);
                break;
            case "Вторник":
                u1.setText(R.string.vt_one_11at);
                u2.setText(R.string.vt_two_11at);
                u3.setText(R.string.vt_three_11at);
                u4.setText(R.string.vt_four_11at);
                u5.setText(R.string.vt_five_11at);
                u6.setText(R.string.vt_six_11at);
                u7.setText(R.string.vt_seven_11at);
                u8.setText(R.string.vt_eight_11at);
                break;
            case "Среда":
                u1.setText(R.string.sr_one_11at);
                u2.setText(R.string.sr_two_11at);
                u3.setText(R.string.sr_three_11at);
                u4.setText(R.string.sr_four_11at);
                u5.setText(R.string.sr_five_11at);
                u6.setText(R.string.sr_six_11at);
                u7.setText(R.string.sr_seven_11at);
                u8.setText(R.string.sr_eight_11at);
                break;
            case "Четверг":
                u1.setText(R.string.ch_one_11at);
                u2.setText(R.string.ch_two_11at);
                u3.setText(R.string.ch_three_11at);
                u4.setText(R.string.ch_four_11at);
                u5.setText(R.string.ch_five_11at);
                u6.setText(R.string.ch_six_11at);
                u7.setText(R.string.ch_seven_11at);
                u8.setText(R.string.ch_eight_11at);
                break;
            case "Пятница":
                u1.setText(R.string.pt_one_11at);
                u2.setText(R.string.pt_two_11at);
                u3.setText(R.string.pn_three_11at);
                u4.setText(R.string.pt_four_11at);
                u5.setText(R.string.pt_five_11at);
                u6.setText(R.string.pt_six_11at);
                u7.setText(R.string.pt_seven_11at);
                u8.setText(R.string.pt_eight_11at);
                break;
            case "Суббота":
                u1.setText(R.string.sb_one_11at);
                u2.setText(R.string.sb_two_11at);
                u3.setText(R.string.sb_three_11at);
                u4.setText(R.string.sb_four_11at);
                u5.setText(R.string.sb_five_11at);
                u6.setText(R.string.sb_six_11at);
                u7.setText(R.string.sb_seven_11at);
                u8.setText(R.string.sb_eight_11at);
                break;
        }
    }

    void rasp11as(){
        switch (day_week) {
            case "Воскресенье":
                u1.setText("Выходной");
                u2.setText("");
                u3.setText("");
                u4.setText("");
                u5.setText("");
                u6.setText("");
                u7.setText("");
                u8.setText("");
                break;
            case "Понедельник":
                u1.setText(R.string.pn_one_11as);
                u2.setText(R.string.pn_two_11as);
                u3.setText(R.string.pn_three_11as);
                u4.setText(R.string.pn_four_11as);
                u5.setText(R.string.pn_five_11as);
                u6.setText(R.string.pn_six_11as);
                u7.setText(R.string.pn_seven_11as);
                u8.setText(R.string.pn_eight_11as);
                break;
            case "Вторник":
                u1.setText(R.string.vt_one_11as);
                u2.setText(R.string.vt_two_11as);
                u3.setText(R.string.vt_three_11as);
                u4.setText(R.string.vt_four_11as);
                u5.setText(R.string.vt_five_11as);
                u6.setText(R.string.vt_six_11as);
                u7.setText(R.string.vt_seven_11as);
                u8.setText(R.string.vt_eight_11as);
                break;
            case "Среда":
                u1.setText(R.string.sr_one_11as);
                u2.setText(R.string.sr_two_11as);
                u3.setText(R.string.sr_three_11as);
                u4.setText(R.string.sr_four_11as);
                u5.setText(R.string.sr_five_11as);
                u6.setText(R.string.sr_six_11as);
                u7.setText(R.string.sr_seven_11as);
                u8.setText(R.string.sr_eight_11as);
                break;
            case "Четверг":
                u1.setText(R.string.ch_one_11as);
                u2.setText(R.string.ch_two_11as);
                u3.setText(R.string.ch_three_11as);
                u4.setText(R.string.ch_four_11as);
                u5.setText(R.string.ch_five_11as);
                u6.setText(R.string.ch_six_11as);
                u7.setText(R.string.ch_seven_11as);
                u8.setText(R.string.ch_eight_11as);
                break;
            case "Пятница":
                u1.setText(R.string.pt_one_11as);
                u2.setText(R.string.pt_two_11as);
                u3.setText(R.string.pn_three_11as);
                u4.setText(R.string.pt_four_11as);
                u5.setText(R.string.pt_five_11as);
                u6.setText(R.string.pt_six_11as);
                u7.setText(R.string.pt_seven_11as);
                u8.setText(R.string.pt_eight_11as);
                break;
            case "Суббота":
                u1.setText(R.string.sb_one_11as);
                u2.setText(R.string.sb_two_11as);
                u3.setText(R.string.sb_three_11as);
                u4.setText(R.string.sb_four_11as);
                u5.setText(R.string.sb_five_11as);
                u6.setText(R.string.sb_six_11as);
                u7.setText(R.string.sb_seven_11as);
                u8.setText(R.string.sb_eight_11as);
                break;
        }
    }
}