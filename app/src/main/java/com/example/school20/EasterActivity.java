package com.example.school20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
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
}