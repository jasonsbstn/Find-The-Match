/**
 * Activity for winning screen
 */

package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.finddamatch.MainActivity.length;
import static com.example.finddamatch.MainActivity.order;
import static com.example.finddamatch.UI.Game_Activity.score;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finddamatch.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Won extends AppCompatActivity {
    private EditText userNameEntered;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        MediaPlayer wonSound = MediaPlayer.create(this, R.raw.gamewon);
        wonSound.start();
        FloatingActionButton backButton = (FloatingActionButton) findViewById(R.id.backButtonWonScreen);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (order == 2) {
            if (length == 5) {
                saveInfo("highScore_2_5", "date_2_5");
            } else if (length == 7) {
                saveInfo("highScore_2_7", "date_2_7");
            }
        } else if (order == 3) {
            if (length == 5) {
                saveInfo("highScore_3_5", "date_3_5");
            } else if (length == 10) {
                saveInfo("highScore_3_10", "date_3_10");
            } else if (length == 13) {
                saveInfo("highScore_3_13", "date_3_13");
            }
        } else if (order == 5) {
            if (length == 5) {
                saveInfo("highScore_5_5", "date_5_5");
            } else if (length == 10) {
                saveInfo("highScore_5_10", "date_5_10");
            } else if (length == 15) {
                saveInfo("highScore_5_15", "date_5_15");
            } else if (length == 20) {
                saveInfo("highScore_5_20", "date_5_20");
            } else if (length == 31) {
                saveInfo("highScore_5_31", "date_5_31");
            }
        }
    }

    private void saveButton() {
        Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameEntered = (EditText) findViewById(R.id.userNameEntered);
                if (order == 2) {
                    if (length == 5) {
                        SharedPreferences name = getSharedPreferences("name_2_5", 0);
                        SharedPreferences.Editor nameEdit = name.edit();
                        nameEdit.putString("name6", userNameEntered.getText().toString());
                        nameEdit.apply();
                    } else if (length == 7) {
                        SharedPreferences name = getSharedPreferences("name_2_7", 0);
                        SharedPreferences.Editor nameEdit = name.edit();
                        nameEdit.putString("name6", userNameEntered.getText().toString());
                        nameEdit.apply();
                    }
                } else if (order == 3) {
                    if (length == 5) {
                        SharedPreferences name = getSharedPreferences("name_3_5", 0);
                        SharedPreferences.Editor nameEdit = name.edit();
                        nameEdit.putString("name6", userNameEntered.getText().toString());
                        nameEdit.apply();
                    } else if (length == 10) {
                        SharedPreferences name = getSharedPreferences("name_3_10", 0);
                        SharedPreferences.Editor nameEdit = name.edit();
                        nameEdit.putString("name6", userNameEntered.getText().toString());
                        nameEdit.apply();
                    } else if (length == 13) {
                        SharedPreferences name = getSharedPreferences("name_3_13", 0);
                        SharedPreferences.Editor nameEdit = name.edit();
                        nameEdit.putString("name6", userNameEntered.getText().toString());
                        nameEdit.apply();
                    }
                } else if (order == 5) {
                    if (length == 5) {
                        SharedPreferences name = getSharedPreferences("name_5_5", 0);
                        SharedPreferences.Editor nameEdit = name.edit();
                        nameEdit.putString("name6", userNameEntered.getText().toString());
                        nameEdit.apply();
                    } else if (length == 10) {
                        SharedPreferences name = getSharedPreferences("name_5_10", 0);
                        SharedPreferences.Editor nameEdit = name.edit();
                        nameEdit.putString("name6", userNameEntered.getText().toString());
                        nameEdit.apply();
                    } else if (length == 15) {
                        SharedPreferences name = getSharedPreferences("name_5_15", 0);
                        SharedPreferences.Editor nameEdit = name.edit();
                        nameEdit.putString("name6", userNameEntered.getText().toString());
                        nameEdit.apply();
                    } else if (length == 20) {
                        SharedPreferences name = getSharedPreferences("name_5_20", 0);
                        SharedPreferences.Editor nameEdit = name.edit();
                        nameEdit.putString("name6", userNameEntered.getText().toString());
                        nameEdit.apply();
                    } else if (length == 31) {
                        SharedPreferences name = getSharedPreferences("name_5_31", 0);
                        SharedPreferences.Editor nameEdit = name.edit();
                        nameEdit.putString("name6", userNameEntered.getText().toString());
                        nameEdit.apply();
                    }
                }
                Intent mainMenu = Main_Menu.makeLaunchIntent(Won.this);
                startActivity(mainMenu);
            }
        });
    }

    private void saveInfo(String scores, String dates) {
        //save score
        preferences = getSharedPreferences(scores, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Sixth", score);
        editor.apply();

        //save date
        SharedPreferences date = getSharedPreferences(dates, 0);
        SharedPreferences.Editor dateEdit = date.edit();

        //From:
        //https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android#:~:text=on%20
        //this%20post.-,Date%20c%20%3D%20Calendar.getInstance().getTime()%3B%20System.,%3D%20df.format(c)%3B
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        dateEdit.putString("date6", formattedDate);
        dateEdit.apply();


        //set winning text
        TextView scoreView = findViewById(R.id.winText);
        scoreView.setText("Congratulations, you won! Your time is " + (float) score / 1000 + " s.");
        saveButton();
    }
}