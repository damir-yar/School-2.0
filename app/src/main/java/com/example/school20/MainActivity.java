package com.example.school20;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
            case R.id.action_rasp:
                Intent intent2 = new Intent(MainActivity.this, RaspActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                u3.setText(R.string.pt_three_11at);
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
                u3.setText(R.string.pt_three_11as);
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