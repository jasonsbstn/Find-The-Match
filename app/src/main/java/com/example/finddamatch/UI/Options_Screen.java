package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import static com.example.finddamatch.MainActivity.option;
import static com.example.finddamatch.MainActivity.order;
import static com.example.finddamatch.MainActivity.order;
import static com.example.finddamatch.MainActivity.length;

/*

Description : user chooses the theme

 */
import com.example.finddamatch.R;

public class Options_Screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options__screen);
        createThemeSelection();
        createGameOrders();
        createGameLength();
    }

    private void createGameOrders() {
        RadioGroup group = (RadioGroup) findViewById(R.id.orders);
        final int[] gameOrder = getResources().getIntArray(R.array.gameOrders);

        RadioButton btn1 = new RadioButton(this);
        RadioButton btn2 = new RadioButton(this);
        RadioButton btn3 = new RadioButton(this);

        btn1.setText("Order "+gameOrder[0]);
        btn2.setText("Order "+gameOrder[1]);
        btn3.setText("Order "+gameOrder[2]);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order=gameOrder[0];
                saveOrders(order);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order=gameOrder[1];
                saveOrders(order);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order=gameOrder[2];
                saveOrders(order);
            }
        });

        group.addView(btn1);
        group.addView(btn2);
        group.addView(btn3);
    }

    private void createGameLength() {
        RadioGroup group = (RadioGroup) findViewById(R.id.gameLength);
        final String[] gameLength = getResources().getStringArray(R.array.gameLength);

        RadioButton btn1 = new RadioButton(this);
        RadioButton btn2 = new RadioButton(this);
        RadioButton btn3 = new RadioButton(this);
        RadioButton btn4 = new RadioButton(this);
        RadioButton btn5 = new RadioButton(this);

        btn1.setText("Draw-pile size "+gameLength[0]);
        btn2.setText("Draw-pile size "+gameLength[1]);
        btn3.setText("Draw-pile size "+gameLength[2]);
        btn4.setText("Draw-pile size "+gameLength[3]);
        btn5.setText("Draw-pile size "+gameLength[4]);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                length=5;
                saveLength(length);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option==2) {
                    length=7;
                    //toast to signal player
                }
                else {
                    length = 10;
                }
                saveLength(length);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option==2) {
                    length=7;
                    //toast to signal player
                }
                else if (option==3) {
                    length=13;
                }
                else {
                    length = 15;
                }
                saveLength(length);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option==2) {
                    length=7;
                    //toast to signal player
                }
                else if (option==3) {
                    length=13;
                }
                else {
                    length = 20;
                }
                saveLength(length);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option==2) {
                    length=7;
                }
                if(option==3) {
                    length=13;
                }
                if(option==5) {
                    length=31;
                }
                saveLength(length);
            }
        });

        group.addView(btn1);
        group.addView(btn2);
        group.addView(btn3);
        group.addView(btn4);
        group.addView(btn5);
    }

    public static Intent makeLaunchIntent(Context c){
        Intent intent = new Intent(c, Options_Screen.class);
        return intent;
    }
    private void createThemeSelection() {
        RadioGroup group = (RadioGroup) findViewById(R.id.collumnrowRadio);
        String[] themeOptions = getResources().getStringArray(R.array.themeselection);
        RadioButton button1 = new RadioButton(this);
        RadioButton button2 = new RadioButton(this);
        button1.setText(themeOptions[0]);
        button2.setText(themeOptions[1]);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option= 1;
                saveTheme(option);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option= 2;
                saveTheme(option);
            }
        });
        group.addView(button1);
        group.addView(button2);
        if(option == 1)
        {
            button1.setChecked(true);
        }
        else if(option ==2)
        {
            button2.setChecked(true);
        }



    }
    private void saveTheme(int optionNum) {
        SharedPreferences prefs = this.getSharedPreferences("ThemePrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("ThemeNum",optionNum);
        editor.apply();
    }

    private void saveOrders(int optionNum) {
        SharedPreferences orders = this.getSharedPreferences("Orders",MODE_PRIVATE);
        SharedPreferences.Editor editor = orders.edit();
        editor.putInt("Orders",optionNum);
        editor.apply();
    }

    private void saveLength(int optionNum) {
        SharedPreferences lengths = this.getSharedPreferences("gameLength",MODE_PRIVATE);
        SharedPreferences.Editor editor = lengths.edit();
        editor.putInt("gameLength",optionNum);
        editor.apply();
    }

}
