package com.example.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.finddamatch.MainActivity.bitmaps;
import static com.example.finddamatch.MainActivity.option;
import static com.example.finddamatch.MainActivity.order;
import static com.example.finddamatch.MainActivity.length;

/*

Description : user chooses the theme

 */
import com.example.finddamatch.R;
import com.example.finddamatch.flickr.PhotoGalleryActivity;

public class Options_Screen extends AppCompatActivity {
    Button searchBtn;
    Button editFlickrPhoto;
    Button clearFlickrPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options__screen);
        createThemeSelection();
        createGameOrders();
        createGameLength();
        createGameMode();
        searchBtn= findViewById(R.id.searchBtn);
        editFlickrPhoto = findViewById(R.id.editBtn);
        clearFlickrPhoto=findViewById(R.id.clearBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Options_Screen.this,PhotoGalleryActivity.class);
                startActivity(intent);
            }
        });
        editFlickrPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_Screen.this,flickrImgClass.class);
                startActivity(intent);
            }
        });
        clearFlickrPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmaps.clear();
                option =1;
            }
        });
    }

    private void createGameMode() {
        RadioGroup group = (RadioGroup) findViewById(R.id.gameModes);
        String[] gameMode = getResources().getStringArray(R.array.game_mode);
        RadioButton button1 = new RadioButton(this);
        RadioButton button2 = new RadioButton(this);
        button1.setText(gameMode[0]);
        button2.setText(gameMode[1]);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fill in your activity when click
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fill in your activity when click
            }
        });
        group.addView(button1);
        group.addView(button2);
        if(option ==1)//compare if the mode is set correctly then mark the radio button as presses (a full dot, not a blank one) REMOVE THE OPTION == 1
        {
            button1.setChecked(true);
        }
        else if(option ==2)//compare if the mode is set correctly then mark the radio button as presses (a full dot, not a blank one) REMOVE THE OPTION == 2
        {
            button2.setChecked(true);
        }

        saveTheme(option);
    }

    private void createGameOrders() {
        RadioGroup group = (RadioGroup) findViewById(R.id.Orders);
        final int[] gameOrder = getResources().getIntArray(R.array.gameOrders);

        final RadioButton btn1 = new RadioButton(this);
        final RadioButton btn2 = new RadioButton(this);
        final RadioButton btn3 = new RadioButton(this);

        btn1.setText("Order "+gameOrder[0]);
        btn2.setText("Order "+gameOrder[1]);
        btn3.setText("Order "+gameOrder[2]);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order=gameOrder[0];
                if(length>7) {
                    length=7;
                    Toast.makeText(getApplicationContext(), "Unavailable option, automatically changed to nearest pile length", Toast.LENGTH_SHORT).show();
                }
                btn1.setChecked(true);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order=gameOrder[1];
                if(length>13 || length==7) {
                    if(length==7)
                        length=10;
                    else
                        length=13;
                    Toast.makeText(getApplicationContext(), "Unavailable option, automatically changed to nearest pile length", Toast.LENGTH_SHORT).show();
                }
                btn2.setChecked(true);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order=gameOrder[2];
                if(length==7 || length==13) {
                    if(length==7)
                        length=10;
                    else if(length==13)
                        length=15;
                    Toast.makeText(getApplicationContext(), "Unavailable option, automatically changed to nearest pile length", Toast.LENGTH_SHORT).show();
                }
                btn3.setChecked(true);
            }
        });

        group.addView(btn1);
        group.addView(btn2);
        group.addView(btn3);

        saveOrders(order);

        if(order == 2)
        {
            btn1.setChecked(true);
        }
        else if(order ==3)
        {
            btn2.setChecked(true);
        }
        else if(order ==5)
        {
            btn3.setChecked(true);
        }
    }

    private void createGameLength() {
        RadioGroup group = (RadioGroup) findViewById(R.id.gameLength);
        final String[] gameLength = getResources().getStringArray(R.array.gameLength);

        final RadioButton btn1 = new RadioButton(this);
        final RadioButton btn2 = new RadioButton(this);
        final RadioButton btn3 = new RadioButton(this);
        final RadioButton btn4 = new RadioButton(this);
        final RadioButton btn5 = new RadioButton(this);

        btn1.setText("Draw-pile size "+gameLength[0]);
        btn2.setText("Draw-pile size "+gameLength[1]);
        btn3.setText("Draw-pile size "+gameLength[2]);
        btn4.setText("Draw-pile size "+gameLength[3]);
        btn5.setText("Draw-pile size "+gameLength[4]);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                length=5;
                btn1.setChecked(true);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(order == 2) {
                    Toast.makeText(getApplicationContext(),"Unavailable option, automatically changed to nearest pile length",Toast.LENGTH_SHORT).show();
                    length=7;
                    btn5.setChecked(true);
                }
                else {
                    length=10;
                    btn2.setChecked(true);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(order == 2) {
                    Toast.makeText(getApplicationContext(),"Unavailable option, automatically changed to nearest pile length",Toast.LENGTH_SHORT).show();
                    length=7;
                    btn5.setChecked(true);
                }
                else if(order ==3) {
                    Toast.makeText(getApplicationContext(),"Unavailable option, automatically changed to nearest pile length",Toast.LENGTH_SHORT).show();
                    length=13;
                    btn5.setChecked(true);
                }
                else {
                    length=15;
                    btn3.setChecked(true);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(order == 2) {
                    Toast.makeText(getApplicationContext(),"Unavailable option, automatically changed to nearest pile length",Toast.LENGTH_SHORT).show();
                    length=7;
                    btn5.setChecked(true);
                }
                else if(order ==3) {
                    Toast.makeText(getApplicationContext(),"Unavailable option, automatically changed to nearest pile length",Toast.LENGTH_SHORT).show();
                    length=13;
                    btn5.setChecked(true);
                }
                else {
                    length=20;
                    btn4.setChecked(true);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(order == 2) {
                    length=7;
                }
                else if(order ==3) {
                    length=13;
                }
                else {
                    length=31;
                }
                btn5.setChecked(true);
            }
        });

        group.addView(btn1);
        group.addView(btn2);
        group.addView(btn3);
        group.addView(btn4);
        group.addView(btn5);

        if(length==5)
            btn1.setChecked(true);
        else if(length==10)
            btn2.setChecked(true);
        else if(length==15)
            btn3.setChecked(true);
        else if(length==20)
            btn4.setChecked(true);
        else
            btn5.setChecked(true);

        saveLength(length);
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
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option= 2;

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

        saveTheme(option);

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
