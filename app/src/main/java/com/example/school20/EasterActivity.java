package com.example.school20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EasterActivity extends AppCompatActivity {

    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;

    private Button reset;

    private int kr = 1;

    private Boolean oneB = false;
    private Boolean twoB = false;
    private Boolean threeB = false;
    private Boolean fourB = false;
    private Boolean fiveB = false;
    private Boolean sixB = false;
    private Boolean sevenB = false;
    private Boolean eightB = false;
    private Boolean nineB = false;

    private LinearLayout lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easter);

        one = findViewById(R.id.but_one);
        two = findViewById(R.id.but_two);
        three = findViewById(R.id.but_three);
        four = findViewById(R.id.but_four);
        five = findViewById(R.id.but_five);
        six = findViewById(R.id.but_six);
        seven = findViewById(R.id.but_seven);
        eight = findViewById(R.id.but_eight);
        nine = findViewById(R.id.but_nine);

        reset = findViewById(R.id.reset);

        lay = findViewById(R.id.lay);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(EasterActivity.this);
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

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (oneB == false) {
                        if (kr == 1) {
                            one.setText("X");
                            kr = 0;
                            oneB = true;
                        } else {
                            one.setText("O");
                            kr = 1;
                            oneB = true;
                        }
                    }
                    chek();
                }
            });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (twoB == false) {
                    if (kr == 1) {
                        two.setText("X");
                        kr = 0;
                        twoB = true;
                    } else {
                        two.setText("O");
                        kr = 1;
                        twoB = true;
                    }
                }
                chek();
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (threeB == false) {
                    if (kr == 1) {
                        three.setText("X");
                        kr = 0;
                        threeB = true;
                    } else {
                        three.setText("O");
                        kr = 1;
                        threeB = true;
                    }
                }
                chek();
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fourB == false) {
                    if (kr == 1) {
                        four.setText("X");
                        kr = 0;
                        fourB = true;
                    } else {
                        four.setText("O");
                        kr = 1;
                        fourB = true;
                    }
                }
                chek();
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fiveB == false) {
                    if (kr == 1) {
                        five.setText("X");
                        kr = 0;
                        fiveB = true;
                    } else {
                        five.setText("O");
                        kr = 1;
                        fiveB = true;
                    }
                }
                chek();
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sixB == false) {
                    if (kr == 1) {
                        six.setText("X");
                        kr = 0;
                        sixB = true;
                    } else {
                        six.setText("O");
                        kr = 1;
                        sixB = true;
                    }
                }
                chek();
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sevenB == false) {
                    if (kr == 1) {
                        seven.setText("X");
                        kr = 0;
                        sevenB = true;
                    } else {
                        seven.setText("O");
                        kr = 1;
                        sevenB = true;
                    }
                }
                chek();
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eightB == false) {
                    if (kr == 1) {
                        eight.setText("X");
                        kr = 0;
                        eightB = true;
                    } else {
                        eight.setText("O");
                        kr = 1;
                        eightB = true;
                    }
                }
                chek();
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nineB == false) {
                    if (kr == 1) {
                        nine.setText("X");
                        kr = 0;
                        nineB = true;
                    } else {
                        nine.setText("O");
                        kr = 1;
                        nineB = true;
                    }
                }
                chek();
            }
        });
    }

    void reset() {
        one.setText("");
        two.setText("");
        three.setText("");
        four.setText("");
        five.setText("");
        six.setText("");
        seven.setText("");
        eight.setText("");
        nine.setText("");
        kr = 1;
        oneB = false;
        twoB = false;
        threeB = false;
        fourB = false;
        fiveB = false;
        sixB = false;
        sevenB = false;
        eightB = false;
        nineB = false;
    }

    void chek() {
        if (one.getText() == "X" && two.getText() == "X" && three.getText() == "X" ||
        four.getText() == "X" && five.getText() == "X" && six.getText() == "X" ||
        seven.getText() == "X" && eight.getText() == "X" && nine.getText() == "X" ||
        one.getText() == "X" && four.getText() == "X" && seven.getText() == "X" ||
        two.getText() == "X" && five.getText() == "X" && eight.getText() == "X" ||
        three.getText() == "X" && six.getText() == "X" && nine.getText() == "X" ||
        one.getText() == "X" && five.getText() == "X" && nine.getText() == "X" ||
        three.getText() == "X" && five.getText() == "X" && seven.getText() == "X") {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Крестики победили!", Toast.LENGTH_SHORT);
            toast.show();
            reset();
        }
        else if (one.getText() == "O" && two.getText() == "O" && three.getText() == "O" ||
                four.getText() == "O" && five.getText() == "O" && six.getText() == "O" ||
                seven.getText() == "O" && eight.getText() == "O" && nine.getText() == "O" ||
                one.getText() == "O" && four.getText() == "O" && seven.getText() == "O" ||
                two.getText() == "O" && five.getText() == "O" && eight.getText() == "O" ||
                three.getText() == "O" && six.getText() == "O" && nine.getText() == "O" ||
                one.getText() == "O" && five.getText() == "O" && nine.getText() == "O" ||
                three.getText() == "O" && five.getText() == "O" && seven.getText() == "O") {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Нолики победили!", Toast.LENGTH_SHORT);
            toast.show();
            reset();
        }
    }

    private void nigth_mode() {
        one.setTextColor(getResources().getColor(R.color.black));
        one.setBackgroundColor(getResources().getColor(R.color.white));
        two.setTextColor(getResources().getColor(R.color.black));
        two.setBackgroundColor(getResources().getColor(R.color.white));
        three.setTextColor(getResources().getColor(R.color.black));
        three.setBackgroundColor(getResources().getColor(R.color.white));
        four.setTextColor(getResources().getColor(R.color.black));
        four.setBackgroundColor(getResources().getColor(R.color.white));
        five.setTextColor(getResources().getColor(R.color.black));
        five.setBackgroundColor(getResources().getColor(R.color.white));
        six.setTextColor(getResources().getColor(R.color.black));
        six.setBackgroundColor(getResources().getColor(R.color.white));
        seven.setTextColor(getResources().getColor(R.color.black));
        seven.setBackgroundColor(getResources().getColor(R.color.white));
        eight.setTextColor(getResources().getColor(R.color.black));
        eight.setBackgroundColor(getResources().getColor(R.color.white));
        nine.setTextColor(getResources().getColor(R.color.black));
        nine.setBackgroundColor(getResources().getColor(R.color.white));
        reset.setTextColor(getResources().getColor(R.color.black));
        reset.setBackgroundColor(getResources().getColor(R.color.white));
        lay.setBackgroundColor(getResources().getColor(R.color.nigth_mode));
    }

    private void day_mode() {
        one.setTextColor(getResources().getColor(R.color.white));
        one.setBackgroundColor(getResources().getColor(R.color.black));
        two.setTextColor(getResources().getColor(R.color.white));
        two.setBackgroundColor(getResources().getColor(R.color.black));
        three.setTextColor(getResources().getColor(R.color.white));
        three.setBackgroundColor(getResources().getColor(R.color.black));
        four.setTextColor(getResources().getColor(R.color.white));
        four.setBackgroundColor(getResources().getColor(R.color.black));
        five.setTextColor(getResources().getColor(R.color.white));
        five.setBackgroundColor(getResources().getColor(R.color.black));
        six.setTextColor(getResources().getColor(R.color.white));
        six.setBackgroundColor(getResources().getColor(R.color.black));
        seven.setTextColor(getResources().getColor(R.color.white));
        seven.setBackgroundColor(getResources().getColor(R.color.black));
        eight.setTextColor(getResources().getColor(R.color.white));
        eight.setBackgroundColor(getResources().getColor(R.color.black));
        nine.setTextColor(getResources().getColor(R.color.white));
        nine.setBackgroundColor(getResources().getColor(R.color.black));
        reset.setTextColor(getResources().getColor(R.color.white));
        reset.setBackgroundColor(getResources().getColor(R.color.black));
        lay.setBackgroundColor(getResources().getColor(R.color.white));
    }
}