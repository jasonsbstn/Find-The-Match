package com.example.finddamatch.UI;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finddamatch.Flickr_and_Import.PhotoGalleryActivity;
import com.example.finddamatch.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.finddamatch.MainActivity.bitmaps;
import static com.example.finddamatch.MainActivity.length;
import static com.example.finddamatch.MainActivity.mode;
import static com.example.finddamatch.MainActivity.option;
import static com.example.finddamatch.MainActivity.order;
import static com.example.finddamatch.MainActivity.length;
import static com.example.finddamatch.MainActivity.difficultmode;
import static com.example.finddamatch.Flickr_and_Import.Photo_Gallery_Fragment.imageSelected;

/*

Description : user chooses the theme

 */
import com.example.finddamatch.R;
import com.example.finddamatch.Flickr_and_Import.PhotoGalleryActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class Options_Screen extends AppCompatActivity {
    Button searchBtn;
    Button editFlickrPhoto;
    Button clearFlickrPhoto;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options__screen);
        FloatingActionButton backButton = (FloatingActionButton) findViewById(R.id.backButtonOptionsScreen);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        createThemeSelection();
        createGameOrders();
        createGameLength();
        createGameMode();
        createDifficultyModes();
        createImportButton();
        searchBtn = findViewById(R.id.searchBtn);
        editFlickrPhoto = findViewById(R.id.editBtn);
        clearFlickrPhoto = findViewById(R.id.clearBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_Screen.this, PhotoGalleryActivity.class);
                startActivity(intent);
            }
        });
        editFlickrPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_Screen.this, Flickr_Img_Class.class);
                startActivity(intent);
            }
        });
        clearFlickrPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmaps.clear();
                option = 1;
            }
        });

    }

    private void createImportButton() {
        Button btn = findViewById(R.id.chooseImage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
    }

    private void OpenGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            //save many pictures at once
            //REFERENCE: https://stackoverflow.com/questions/23426113/how-to-select-multiple-images-from-gallery-in-android
            ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                for (int i = 0; i < mClipData.getItemCount(); i++) {
                    ClipData.Item item = mClipData.getItemAt(i);
                    Uri uri = item.getUri();
                    mArrayUri.add(uri);
                }
            }

            //scale and save those pictures
            for (int i = 0; i < mArrayUri.size(); i++) {
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mArrayUri.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap scaledBitmap;
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                bitmaps.add(scaledBitmap);
            }
        }
        imageSelected++;
        option = 3;

        saveBitmap(bitmaps);
    }

    private void saveBitmap(List<Bitmap> bitmaps) {
        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit=shre.edit();

        //go through the list of pictures
        for(int i=0;i<bitmaps.size();i++) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmaps.get(i).compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();

            //encode bitmap to string using base64 and saved it to shared preferences
            //REFERENCE: https://stackoverflow.com/questions/17268519/how-to-store-bitmap-object-in-sharedpreferences-in-android
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

            edit.putString("image_data"+i,encodedImage);
            edit.commit();
        }


        //save number of bitmaps in the bitmap's list
        SharedPreferences prefs = this.getSharedPreferences("numberPics", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("numberPics", bitmaps.size());
        editor.apply();
    }

    private void createGameMode() {
        RadioGroup group = (RadioGroup) findViewById(R.id.gameModes);
        String[] gameMode = getResources().getStringArray(R.array.game_mode);
        final RadioButton button1 = new RadioButton(this);
        final RadioButton button2 = new RadioButton(this);
        button1.setText(gameMode[0]);
        button2.setText(gameMode[1]);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 1;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 2;
            }
        });
        group.addView(button1);
        group.addView(button2);
        if (mode == 1) {
            button1.setChecked(true);
        } else if (mode == 2) {
            button2.setChecked(true);
        }

        saveGameMode(mode);
    }

    private void saveGameMode(int optionNum) {
        SharedPreferences prefs = this.getSharedPreferences("modes", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("modes", optionNum);
        editor.apply();
    }

    private void createDifficultyModes(){
        RadioGroup group = (RadioGroup) findViewById(R.id.DifficultyModes);
        final String[] difficulty = getResources().getStringArray(R.array.diffmodes);
        final RadioButton button1 = new RadioButton(this);
        final RadioButton button2 = new RadioButton(this);
        final RadioButton button3 = new RadioButton(this);
        button1.setText(difficulty[0]);
        button2.setText(difficulty[1]);
        button3.setText(difficulty[2]);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficultmode = 1;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficultmode = 2;
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficultmode = 3;
            }
        });
        group.addView(button1);
        group.addView(button2);
        group.addView(button3);
        if (difficultmode == 1) {
            button1.setChecked(true);
        }else if(difficultmode == 2){
            button2.setChecked(true);
        }else if(difficultmode == 3){
            button3.setChecked(true);
        }
        saveDifficultyMode(difficultmode);

    }
    private void saveDifficultyMode(int optionNum){
        SharedPreferences prefs = this.getSharedPreferences("DiffModes", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("DiffModes", optionNum);
        editor.apply();
    }


    private void createGameOrders() {
        RadioGroup group = (RadioGroup) findViewById(R.id.Orders);
        final int[] gameOrder = getResources().getIntArray(R.array.gameOrders);

        final RadioButton btn1 = new RadioButton(this);
        final RadioButton btn2 = new RadioButton(this);
        final RadioButton btn3 = new RadioButton(this);

        btn1.setText("Order " + gameOrder[0]);
        btn2.setText("Order " + gameOrder[1]);
        btn3.setText("Order " + gameOrder[2]);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order = gameOrder[0];
                if (length > 7) {
                    length = 7;
                    Toast.makeText(getApplicationContext(), "Unavailable option, automatically changed to nearest pile length", Toast.LENGTH_SHORT).show();
                }
                btn1.setChecked(true);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order = gameOrder[1];
                if (length > 13 || length == 7) {
                    if (length == 7)
                        length = 10;
                    else
                        length = 13;
                    Toast.makeText(getApplicationContext(), "Unavailable option, automatically changed to nearest pile length", Toast.LENGTH_SHORT).show();
                }
                btn2.setChecked(true);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order = gameOrder[2];
                if (length == 7 || length == 13) {
                    if (length == 7)
                        length = 10;
                    else if (length == 13)
                        length = 15;
                    Toast.makeText(getApplicationContext(), "Unavailable option, automatically changed to nearest pile length", Toast.LENGTH_SHORT).show();
                }
                btn3.setChecked(true);
            }
        });

        group.addView(btn1);
        group.addView(btn2);
        group.addView(btn3);

        saveOrders(order);

        if (order == 2) {
            btn1.setChecked(true);
        } else if (order == 3) {
            btn2.setChecked(true);
        } else if (order == 5) {
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

        btn1.setText("Draw-pile size " + gameLength[0]);
        btn2.setText("Draw-pile size " + gameLength[1]);
        btn3.setText("Draw-pile size " + gameLength[2]);
        btn4.setText("Draw-pile size " + gameLength[3]);
        btn5.setText("Draw-pile size " + gameLength[4]);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                length = 5;
                btn1.setChecked(true);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order == 2) {
                    Toast.makeText(getApplicationContext(), "Unavailable option, automatically changed to nearest pile length", Toast.LENGTH_SHORT).show();
                    length = 7;
                    btn5.setChecked(true);
                } else {
                    length = 10;
                    btn2.setChecked(true);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order == 2) {
                    Toast.makeText(getApplicationContext(), "Unavailable option, automatically changed to nearest pile length", Toast.LENGTH_SHORT).show();
                    length = 7;
                    btn5.setChecked(true);
                } else if (order == 3) {
                    Toast.makeText(getApplicationContext(), "Unavailable option, automatically changed to nearest pile length", Toast.LENGTH_SHORT).show();
                    length = 13;
                    btn5.setChecked(true);
                } else {
                    length = 15;
                    btn3.setChecked(true);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order == 2) {
                    Toast.makeText(getApplicationContext(), "Unavailable option, automatically changed to nearest pile length", Toast.LENGTH_SHORT).show();
                    length = 7;
                    btn5.setChecked(true);
                } else if (order == 3) {
                    Toast.makeText(getApplicationContext(), "Unavailable option, automatically changed to nearest pile length", Toast.LENGTH_SHORT).show();
                    length = 13;
                    btn5.setChecked(true);
                } else {
                    length = 20;
                    btn4.setChecked(true);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order == 2) {
                    length = 7;
                } else if (order == 3) {
                    length = 13;
                } else {
                    length = 31;
                }
                btn5.setChecked(true);
            }
        });

        group.addView(btn1);
        group.addView(btn2);
        group.addView(btn3);
        group.addView(btn4);
        group.addView(btn5);

        if (length == 5)
            btn1.setChecked(true);
        else if (length == 10)
            btn2.setChecked(true);
        else if (length == 15)
            btn3.setChecked(true);
        else if (length == 20)
            btn4.setChecked(true);
        else
            btn5.setChecked(true);

        saveLength(length);
    }

    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, Options_Screen.class);
        return intent;
    }

    private void createThemeSelection() {
        RadioGroup group = (RadioGroup) findViewById(R.id.collumnrowRadio);
        String[] themeOptions = getResources().getStringArray(R.array.themeselection);
        RadioButton button1 = new RadioButton(this);
        RadioButton button2 = new RadioButton(this);
        RadioButton button3 = new RadioButton(this);
        button1.setText(themeOptions[0]);
        button2.setText(themeOptions[1]);
        button3.setText(themeOptions[2]);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option = 1;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option = 2;

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option = 3;
            }
        });
        group.addView(button1);
        group.addView(button2);
        group.addView(button3);
        if (option == 1) {
            button1.setChecked(true);
        } else if (option == 2) {
            button2.setChecked(true);
        } else if (option == 3) {
            button3.setChecked(true);
        }
        saveTheme(option);

    }

    private void saveTheme(int optionNum) {
        SharedPreferences prefs = this.getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("ThemeNum", optionNum);
        editor.apply();
    }

    private void saveOrders(int optionNum) {
        SharedPreferences orders = this.getSharedPreferences("Orders", MODE_PRIVATE);
        SharedPreferences.Editor editor = orders.edit();
        editor.putInt("Orders", optionNum);
        editor.apply();
    }

    private void saveLength(int optionNum) {
        SharedPreferences lengths = this.getSharedPreferences("gameLength", MODE_PRIVATE);
        SharedPreferences.Editor editor = lengths.edit();
        editor.putInt("gameLength", optionNum);
        editor.apply();
    }

}
