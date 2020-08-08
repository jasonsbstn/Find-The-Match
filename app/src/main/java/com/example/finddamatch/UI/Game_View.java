package com.example.finddamatch.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.example.finddamatch.MainActivity;
import com.example.finddamatch.R;

import java.util.Arrays;
import java.util.Random;

import static android.content.ContentValues.TAG;
import static com.example.finddamatch.MainActivity.bitmaps;
import static com.example.finddamatch.MainActivity.hand;
import static com.example.finddamatch.MainActivity.mode;
import static com.example.finddamatch.MainActivity.option;
import static com.example.finddamatch.MainActivity.top;
import static com.example.finddamatch.UI.Game_Activity.score;
import static com.example.finddamatch.MainActivity.order;
import static com.example.finddamatch.MainActivity.length;
import static com.example.finddamatch.MainActivity.imagesToRotate;
import static com.example.finddamatch.MainActivity.difficultmode;

/*
    Description: using canvas to start the game layout and game logic
*/
public class Game_View extends SurfaceView {
    private Bitmap[] pic = new Bitmap[12];
    private Bitmap[] picScaled = new Bitmap[12];
    private Bitmap cardBackground;
    private Bitmap scaledBackground;
    private SurfaceHolder holder;
    private float canvasXSize;
    private float canvasYSize;
    private int timer;
    String[] card1;
    String[] card2;
    Canvas c;
    private int drawPileSize;
    public float[] angles = new float[100];

    public Game_View(Context context) {//https://www.youtube.com/watch?v=3V5aV-iM8YA&t=7s
        super(context);
        holder = getHolder();

        drawPileSize = 0;
        timer = (int) SystemClock.elapsedRealtime();
        score = 0;
        setImages();
        if (option == 1) {
            cardBackground = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        } else {
            cardBackground = BitmapFactory.decodeResource(getResources(), R.drawable.bg1);
        }
        holder.addCallback(new SurfaceHolder.Callback() {
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                c = holder.lockCanvas();
                onDraw(c);
                holder.unlockCanvasAndPost(c);

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }

    public void correctImgSound(Context context){
        MediaPlayer correctImgSound = MediaPlayer.create(context, R.raw.positive);
        correctImgSound.start();
    }
    public void incorrectImgSound(Context context){
        MediaPlayer incorrectImgSound = MediaPlayer.create(context, R.raw.negative);
        incorrectImgSound.start();
    }

    public void resizeBitmap(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = 75;
        int newHeight = 75;

        // calculate the scale - in this case = 0.4f
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // rotate the Bitmap
        matrix.postRotate(120);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                width, height, matrix, true);


        // make a Drawable from Bitmap to allow to set the BitMap
        // to the ImageView, ImageButton or what ever
        //BitmapDrawable bmd = new BitmapDrawable(resizedBitmap);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    public static Bitmap rotateImage(Bitmap originalBitmap, float angle)
    {
        // create new matrix
        Matrix matrix = new Matrix();
        float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
        // setup rotation degree
        matrix.postRotate(angle);
        Bitmap scaledBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true);
        return scaledBitmap;
    }

    public void randomRotate(){

    }

    public void setImages() {
        if (top.getCards().equals(hand.getCards()) || drawPileSize == length - 1) {
            wonAction();
        }
        card1 = hand.getCards();
        card2 = top.getCards();
        Log.d(TAG, "setImages: " + card1[0] + card2[0]);
        if (option == 1 && mode == 1 && difficultmode == 1) {
            for (int i = 0; i < card1.length; i++) {
                if (card1[i] == "pic1"){
                    //float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    //angles[i] = toDegrees;
                    pic[i] = (BitmapFactory.decodeResource(getResources(),R.drawable.img1_1)); }
                else if (card1[i] == "pic2")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_2);
                else if (card1[i] == "pic3")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_3);
                else if (card1[i] == "pic4")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_4);
                else if (card1[i] == "pic5")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_5);
                else if (card1[i] == "pic6")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_6);
                else if (card1[i] == "pic7")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_7);
                else if (card1[i] == "pic8")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_8);
                else if (card1[i] == "pic9")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_9);
                else if (card1[i] == "pic10")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_10);
                else if (card1[i] == "pic11")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_11);
                else if (card1[i] == "pic12")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_12);
                else if (card1[i] == "pic13")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_13);
                else if (card1[i] == "pic14")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_14);
                else if (card1[i] == "pic15")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_15);
                else if (card1[i] == "pic16")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_16);
                else if (card1[i] == "pic17")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_17);
                else if (card1[i] == "pic18")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_18);
                else if (card1[i] == "pic19")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_19);
                else if (card1[i] == "pic20")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_20);
                else if (card1[i] == "pic21")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_21);
                else if (card1[i] == "pic22")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_22);
                else if (card1[i] == "pic23")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_23);
                else if (card1[i] == "pic24")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_24);
                else if (card1[i] == "pic25")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_25);
                else if (card1[i] == "pic26")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_26);
                else if (card1[i] == "pic27")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_27);
                else if (card1[i] == "pic28")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_28);
                else if (card1[i] == "pic29")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_29);
                else if (card1[i] == "pic30")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_30);
                else if (card1[i] == "pic31")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_31);
            }
            for (int j = 0; j < card2.length; j++) {
                if (card2[j] == "pic1")
                    pic[j + card1.length] = (BitmapFactory.decodeResource(getResources(), R.drawable.img1_1));
                else if (card2[j] == "pic2")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_2);
                else if (card2[j] == "pic3")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_3);
                else if (card2[j] == "pic4")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_4);
                else if (card2[j] == "pic5")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_5);
                else if (card2[j] == "pic6")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_6);
                else if (card2[j] == "pic7")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_7);
                else if (card2[j] == "pic8")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_8);
                else if (card2[j] == "pic9")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_9);
                else if (card2[j] == "pic10")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_10);
                else if (card2[j] == "pic11")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_11);
                else if (card2[j] == "pic12")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_12);
                else if (card2[j] == "pic13")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_13);
                else if (card2[j] == "pic14")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_14);
                else if (card2[j] == "pic15")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_15);
                else if (card2[j] == "pic16")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_16);
                else if (card2[j] == "pic17")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_17);
                else if (card2[j] == "pic18")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_18);
                else if (card2[j] == "pic19")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_19);
                else if (card2[j] == "pic20")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_20);
                else if (card2[j] == "pic21")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_21);
                else if (card2[j] == "pic22")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_22);
                else if (card2[j] == "pic23")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_23);
                else if (card2[j] == "pic24")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_24);
                else if (card2[j] == "pic25")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_25);
                else if (card2[j] == "pic26")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_26);
                else if (card2[j] == "pic27")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_27);
                else if (card2[j] == "pic28")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_28);
                else if (card2[j] == "pic29")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_29);
                else if (card2[j] == "pic30")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_30);
                else if (card2[j] == "pic31")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_31);
            }
        }
        else if(option == 1 && mode == 1 && difficultmode == 2){
            Random rand1 = new Random();
            int order = MainActivity.order;
            int randNo = rand1.nextInt(order) + 1;
            Integer[] randRotateArr = new Integer[length];
            for (int i = 0; i < length; i++) {
                randRotateArr[i] = rand1.nextInt(length)+1 ;
                Log.d(TAG, "setImages: rand"+randRotateArr[i]);
            }
            for (int i = 0; i < card1.length; i++) {
                if (card1[i] == "pic1" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[0] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_1),angles[0]);
                }
                else if(card1[i] == "pic1" && Arrays.asList(randRotateArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_1);
                else if (card1[i] == "pic2" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[1] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_2),angles[1]);
                }
                else if(card1[i] == "pic2" && Arrays.asList(randRotateArr).contains(i) == false ){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_2);
                }
                else if (card1[i] == "pic3" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[2] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_3),angles[2]);
                }
                else if(card1[i] == "pic3" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_3);
                } else if (card1[i] == "pic4" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[3] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_4),angles[3]);
                }
                else if(card1[i] == "pic4" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_4);
                }
                else if (card1[i] == "pic5" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[4] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_5),angles[4]);
                }
                else if(card1[i] == "pic5" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_5);
                }
                else if (card1[i] == "pic6" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[5] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_6),angles[5]);
                }
                else if(card1[i] == "pic6" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_6);
                }
                else if (card1[i] == "pic7" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[6] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_7),angles[6]);
                }
                else if(card1[i] == "pic7" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_7);
                }
                else if (card1[i] == "pic8" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[7] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_8),angles[7]);
                }
                else if(card1[i] == "pic8" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_8);
                }
                else if (card1[i] == "pic9" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[8] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_9),angles[8]);
                }
                else if(card1[i] == "pic9" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_9);
                }
                else if (card1[i] == "pic10" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[9] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_10),angles[9]);
                }
                else if(card1[i] == "pic10" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_10);
                }
                else if (card1[i] == "pic11" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[10] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_11),angles[10]);
                }
                else if(card1[i] == "pic11" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_11);
                }
                else if (card1[i] == "pic12" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[11] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_12),angles[11]);
                }
                else if(card1[i] == "pic12" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_12);
                }
                else if (card1[i] == "pic13" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[12] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_13),angles[12]);
                }
                else if(card1[i] == "pic13" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_13);
                } else if (card1[i] == "pic14" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[13] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_14),angles[13]);
                }
                else if(card1[i] == "pic14" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_14);
                }
                else if (card1[i] == "pic15" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[14] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_15),angles[14]);
                }
                else if(card1[i] == "pic15" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_15);
                }
                else if (card1[i] == "pic16" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[15] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_16),angles[15]);
                }
                else if(card1[i] == "pic16" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_16);
                }
                else if (card1[i] == "pic17" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[16] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_17),angles[16]);
                }
                else if(card1[i] == "pic17" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_17);
                }
                else if (card1[i] == "pic18" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[17] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_18),angles[17]);
                }
                else if(card1[i] == "pic18" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_18);
                }
                else if (card1[i] == "pic19" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[18] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_19),angles[18]);
                }
                else if(card1[i] == "pic19" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_19);
                }
                else if (card1[i] == "pic20" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[19] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_20),angles[19]);
                }
                else if(card1[i] == "pic20" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_20);
                }
                else if (card1[i] == "pic21" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[20] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_21),angles[20]);
                }
                else if(card1[i] == "pic21" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_21);
                }
                else if (card1[i] == "pic22" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[21] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_22),angles[21]);
                }
                else if(card1[i] == "pic22" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_22);
                }
                else if (card1[i] == "pic23" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[22] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_23),angles[22]);
                }
                else if(card1[i] == "pic23" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_23);
                }
                else if (card1[i] == "pic24" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[23] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_24),angles[23]);
                }
                else if(card1[i] == "pic24" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_24);
                }
                else if (card1[i] == "pic25" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[24] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_25),angles[24]);
                }
                else if(card1[i] == "pic25" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_25);
                }
                else if (card1[i] == "pic26" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[25] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_26),angles[25]);
                }
                else if(card1[i] == "pic26" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_26);
                }
                else if (card1[i] == "pic27" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[26] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_27),angles[26]);
                }
                else if(card1[i] == "pic27" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_27);
                }
                else if (card1[i] == "pic28" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[27] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_28),angles[27]);
                }
                else if(card1[i] == "pic28" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_28);
                }
                else if (card1[i] == "pic29" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[28] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_29),angles[28]);
                }
                else if(card1[i] == "pic29" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_29);
                }
                else if (card1[i] == "pic30" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[29] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_30),angles[29]);
                }
                else if(card1[i] == "pic30" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_30);
                }
                else if (card1[i] == "pic31" && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[30] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_31),angles[30]);
                }
                else if(card1[i] == "pic31" && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_31);
                }
            }
            for (int j = 0; j < card2.length; j++) {
                if (card2[j] == "pic1" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_1),angles[0]);
                }
                else if(card2[j] == "pic1" && Arrays.asList(randRotateArr).contains(j) == false)
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_1);
                else if (card2[j] == "pic2" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_2),angles[1]);
                }
                else if(card2[j] == "pic2" && Arrays.asList(randRotateArr).contains(j) == false ){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_2);
                }
                else if (card2[j] == "pic3" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_3),angles[2]);
                }
                else if(card2[j] == "pic3" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_3);
                } else if (card2[j] == "pic4" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_4),angles[3]);
                }
                else if(card2[j] == "pic4" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_4);
                }
                else if (card2[j] == "pic5" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_5),angles[4]);
                }
                else if(card2[j] == "pic5" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_5);
                }
                else if (card2[j] == "pic6" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_6),angles[5]);
                }
                else if(card2[j] == "pic6" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_6);
                }
                else if (card2[j] == "pic7" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_7),angles[6]);
                }
                else if(card2[j] == "pic7" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_7);
                }
                else if (card2[j] == "pic8" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_8),angles[7]);
                }
                else if(card2[j] == "pic8" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_8);
                }
                else if (card2[j] == "pic9" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_9),angles[8]);
                }
                else if(card2[j] == "pic9" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_9);
                }
                else if (card2[j] == "pic10" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_10),angles[9]);
                }
                else if(card2[j] == "pic10" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_10);
                }
                else if (card2[j] == "pic11" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_11),angles[10]);
                }
                else if(card2[j] == "pic11" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_11);
                }
                else if (card2[j] == "pic12" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_12),angles[11]);
                }
                else if(card2[j] == "pic12" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_12);
                }
                else if (card2[j] == "pic13" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_13),angles[12]);
                }
                else if(card2[j] == "pic13" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_13);
                } else if (card2[j] == "pic14" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_14),angles[13]);
                }
                else if(card2[j] == "pic14" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_14);
                }
                else if (card2[j] == "pic15" && Arrays.asList(randRotateArr).contains(j)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[14] = toDegrees;
                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_15),angles[14]);
                }
                else if(card2[j] == "pic15" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_15);
                }
                else if (card2[j] == "pic16" && Arrays.asList(randRotateArr).contains(j)){

                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_16),angles[15]);
                }
                else if(card2[j] == "pic16" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_16);
                }
                else if (card2[j] == "pic17" && Arrays.asList(randRotateArr).contains(j)){
                   pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_17),angles[16]);
                }
                else if(card2[j] == "pic17" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_17);
                }
                else if (card2[j] == "pic18" && Arrays.asList(randRotateArr).contains(j)){
                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_18),angles[17]);
                }
                else if(card2[j] == "pic18" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_18);
                }
                else if (card2[j] == "pic19" && Arrays.asList(randRotateArr).contains(j)){
                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_19),angles[18]);
                }
                else if(card2[j] == "pic19" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_19);
                }
                else if (card2[j] == "pic20" && Arrays.asList(randRotateArr).contains(j)){
                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_20),angles[19]);
                }
                else if(card2[j] == "pic20" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_20);
                }
                else if (card2[j] == "pic21" && Arrays.asList(randRotateArr).contains(j)){
                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_21),angles[20]);
                }
                else if(card2[j] == "pic21" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_21);
                }
                else if (card2[j] == "pic22" && Arrays.asList(randRotateArr).contains(j)){
                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_22),angles[21]);
                }
                else if(card2[j] == "pic22" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_22);
                }
                else if (card2[j] == "pic23" && Arrays.asList(randRotateArr).contains(j)){
                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_23),angles[22]);
                }
                else if(card2[j] == "pic23" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_23);
                }
                else if (card2[j] == "pic24" && Arrays.asList(randRotateArr).contains(j)){
                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_24),angles[23]);
                }
                else if(card2[j] == "pic24" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_24);
                }
                else if (card2[j] == "pic25" && Arrays.asList(randRotateArr).contains(j)){
                      pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_25),angles[24]);
                }
                else if(card2[j] == "pic25" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_25);
                }
                else if (card2[j] == "pic26" && Arrays.asList(randRotateArr).contains(j)){
                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_26),angles[25]);
                }
                else if(card2[j] == "pic26" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_26);
                }
                else if (card2[j] == "pic27" && Arrays.asList(randRotateArr).contains(j)){
                     pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_27),angles[26]);
                }
                else if(card2[j] == "pic27" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_27);
                }
                else if (card2[j] == "pic28" && Arrays.asList(randRotateArr).contains(j)){
                    pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_28),angles[27]);
                }
                else if(card2[j] == "pic28" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_28);
                }
                else if (card2[j] == "pic29" && Arrays.asList(randRotateArr).contains(j)){
                     pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_29),angles[28]);
                }
                else if(card2[j] == "pic29" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_29);
                }
                else if (card2[j] == "pic30" && Arrays.asList(randRotateArr).contains(j)){
                     pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_30),angles[29]);
                }
                else if(card2[j] == "pic30" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_30);
                }
                else if (card2[j] == "pic31" && Arrays.asList(randRotateArr).contains(j)){
                     pic[j+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_31),angles[30]);
                }
                else if(card2[j] == "pic31" && Arrays.asList(randRotateArr).contains(j) == false){
                    pic[j+card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_31);
                }
            }

        }
        else if(option == 1 && mode == 1 && difficultmode == 3){

        }
        else if(option == 1 && mode == 2 && difficultmode == 1){
            for (int i = 0; i < card1.length; i++) {
                if (card1[i] == "pic1")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_1);
                else if (card1[i] == "pic2")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_2);
                else if (card1[i] == "pic3")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_3);
                else if (card1[i] == "pic4")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_4);
                else if (card1[i] == "pic5")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_5);
                else if (card1[i] == "pic6")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_6);
                else if (card1[i] == "pic7")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_7);
                else if (card1[i] == "pic8")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_8);
                else if (card1[i] == "pic9")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_9);
                else if (card1[i] == "pic10")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_10);
                else if (card1[i] == "pic11")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_11);
                else if (card1[i] == "pic12")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_12);
                else if (card1[i] == "pic13")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_13);
                else if (card1[i] == "pic14")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_14);
                else if (card1[i] == "pic15")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_15);
                else if (card1[i] == "pic16")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_16);
                else if (card1[i] == "pic17")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_17);
                else if (card1[i] == "pic18")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_18);
                else if (card1[i] == "pic19")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_19);
                else if (card1[i] == "pic20")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_20);
                else if (card1[i] == "pic21")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_21);
                else if (card1[i] == "pic22")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_22);
                else if (card1[i] == "pic23")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_23);
                else if (card1[i] == "pic24")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_24);
                else if (card1[i] == "pic25")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_25);
                else if (card1[i] == "pic26")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_26);
                else if (card1[i] == "pic27")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_27);
                else if (card1[i] == "pic28")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_28);
                else if (card1[i] == "pic29")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_29);
                else if (card1[i] == "pic30")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_30);
                else if (card1[i] == "pic31")
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_31);
            }
            for (int j = 0; j < card2.length; j++) {
                if (card2[j] == "pic1")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_1);
                else if (card2[j] == "pic2")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_2);
                else if (card2[j] == "pic3")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_3);
                else if (card2[j] == "pic4")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_4);
                else if (card2[j] == "pic5")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_5);
                else if (card2[j] == "pic6")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_6);
                else if (card2[j] == "pic7")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_7);
                else if (card2[j] == "pic8")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_8);
                else if (card2[j] == "pic9")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_9);
                else if (card2[j] == "pic10")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_10);
                else if (card2[j] == "pic11")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_11);
                else if (card2[j] == "pic12")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_12);
                else if (card2[j] == "pic13")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_13);
                else if (card2[j] == "pic14")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_14);
                else if (card2[j] == "pic15")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_15);
                else if (card2[j] == "pic16")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_16);
                else if (card2[j] == "pic17")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_17);
                else if (card2[j] == "pic18")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_18);
                else if (card2[j] == "pic19")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_19);
                else if (card2[j] == "pic20")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_20);
                else if (card2[j] == "pic21")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_21);
                else if (card2[j] == "pic22")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_22);
                else if (card2[j] == "pic23")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_23);
                else if (card2[j] == "pic24")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_24);
                else if (card2[j] == "pic25")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_25);
                else if (card2[j] == "pic26")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_26);
                else if (card2[j] == "pic27")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_27);
                else if (card2[j] == "pic28")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_28);
                else if (card2[j] == "pic29")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_29);
                else if (card2[j] == "pic30")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_30);
                else if (card2[j] == "pic31")
                    pic[j + card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_31);
            }
        }
        else if (option == 1 && mode == 2 && difficultmode == 2) {
            Random rand = new Random();
            Integer[] randArr = new Integer[length];
            for (int i = 0; i < length; i++) {
                randArr[i] = rand.nextInt(length)+1 ;
                Log.d(TAG, "setImages: rand"+randArr[i]);
            }
            Random rand1 = new Random();
            Integer[] randRotateArr = new Integer[length];
            for (int i = 0; i < length; i++) {
                randRotateArr[i] = rand1.nextInt(length)+1 ;
                Log.d(TAG, "setImages: rand"+randRotateArr[i]);
            }
            for (int i = 0; i < card1.length; i++) {
                if (card1[i] == "pic1" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[0] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_1),angles[0]);
                }
                else if(card1[i] == "pic1" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_1);
                }
                else if(card1[i] == "pic1" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[0] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_1), angles[0]);
                }
                else if(card1[i] == "pic1" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_1);
                }
                else if (card1[i] == "pic2" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[1] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_2),angles[1]);
                }
                else if(card1[i] == "pic2" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_2);
                }
                else if(card1[i] == "pic2" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[1] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_2), angles[1]);
                }
                else if(card1[i] == "pic2" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_2);
                }
                else if (card1[i] == "pic3" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[2] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_3),angles[2]);
                }
                else if(card1[i] == "pic3" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_3);
                }
                else if(card1[i] == "pic3" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[2] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_3), angles[2]);
                }
                else if(card1[i] == "pic3" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_3);
                }
                else if (card1[i] == "pic4" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[3] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_4),angles[3]);
                }
                else if(card1[i] == "pic4" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_4);
                }
                else if(card1[i] == "pic4" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[3] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_4), angles[3]);
                }
                else if(card1[i] == "pic4" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_4);
                }
                else if (card1[i] == "pic5" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[4] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_5),angles[4]);
                }
                else if(card1[i] == "pic5" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_5);
                }
                else if(card1[i] == "pic5" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[4] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_5), angles[4]);
                }
                else if(card1[i] == "pic5" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_5);
                }
                else if (card1[i] == "pic6" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[5] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_6),angles[5]);
                }
                else if(card1[i] == "pic6" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_6);
                }
                else if(card1[i] == "pic6" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[5] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_6), angles[5]);
                }
                else if(card1[i] == "pic6" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_6);
                }
                else if (card1[i] == "pic7" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[6] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_7),angles[6]);
                }
                else if(card1[i] == "pic7" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_7);
                }
                else if(card1[i] == "pic7" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[6] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_7), angles[6]);
                }
                else if(card1[i] == "pic7" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_7);
                }
                else if (card1[i] == "pic8" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[7] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_8),angles[7]);
                }
                else if(card1[i] == "pic8" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_8);
                }
                else if(card1[i] == "pic8" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[7] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_8), angles[7]);
                }
                else if(card1[i] == "pic8" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_8);
                }
                else if (card1[i] == "pic9" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[8] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_9),angles[8]);
                }
                else if(card1[i] == "pic9" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_9);
                }
                else if(card1[i] == "pic9" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[8] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_9), angles[8]);
                }
                else if(card1[i] == "pic9" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_9);
                }
                else if (card1[i] == "pic10" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[9] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_10),angles[9]);
                }
                else if(card1[i] == "pic10" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_10);
                }
                else if(card1[i] == "pic10" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[9] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_10), angles[9]);
                }
                else if(card1[i] == "pic10" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_10);
                }
                else if (card1[i] == "pic11" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[10] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_11),angles[10]);
                }
                else if(card1[i] == "pic11" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_11);
                }
                else if(card1[i] == "pic11" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[10] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_11), angles[10]);
                }
                else if(card1[i] == "pic11" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_11);
                }
                else if (card1[i] == "pic12" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[11] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_12),angles[11]);
                }
                else if(card1[i] == "pic12" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_12);
                }
                else if(card1[i] == "pic12" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[11] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_12), angles[11]);
                }
                else if(card1[i] == "pic12" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_12);
                }
                else if (card1[i] == "pic13" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[12] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_13),angles[12]);
                }
                else if(card1[i] == "pic13" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_13);
                }
                else if(card1[i] == "pic13" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[12] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_13), angles[12]);
                }
                else if(card1[i] == "pic13" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_13);
                }
                else if (card1[i] == "pic14" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[13] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_14),angles[13]);
                }
                else if(card1[i] == "pic14" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_14);
                }
                else if(card1[i] == "pic14" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[13] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_14), angles[13]);
                }
                else if(card1[i] == "pic14" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_14);
                }
                else if (card1[i] == "pic15" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[14] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_15),angles[14]);
                }
                else if(card1[i] == "pic15" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_15);
                }
                else if(card1[i] == "pic15" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[14] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_15), angles[14]);
                }
                else if(card1[i] == "pic15" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_15);
                }
                else if (card1[i] == "pic16" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[15] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_16),angles[15]);
                }
                else if(card1[i] == "pic16" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_16);
                }
                else if(card1[i] == "pic16" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[15] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_16), angles[15]);
                }
                else if(card1[i] == "pic16" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_16);
                }
                else if (card1[i] == "pic17" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[16] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_17),angles[16]);
                }
                else if(card1[i] == "pic17" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_17);
                }
                else if(card1[i] == "pic17" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[16] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_17), angles[16]);
                }
                else if(card1[i] == "pic17" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_17);
                }
                else if (card1[i] == "pic18" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[17] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_18),angles[17]);
                }
                else if(card1[i] == "pic18" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_18);
                }
                else if(card1[i] == "pic18" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[17] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_18), angles[17]);
                }
                else if(card1[i] == "pic18" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_18);
                }
                else if (card1[i] == "pic19" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[18] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_19),angles[18]);
                }
                else if(card1[i] == "pic19" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_19);
                }
                else if(card1[i] == "pic19" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[18] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_19), angles[18]);
                }
                else if(card1[i] == "pic19" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_19);
                }
                else if (card1[i] == "pic20" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[19] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_20),angles[19]);
                }
                else if(card1[i] == "pic20" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_20);
                }
                else if(card1[i] == "pic20" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[19] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_20), angles[19]);
                }
                else if(card1[i] == "pic20" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_20);
                }
                else if (card1[i] == "pic21" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[20] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_21),angles[20]);
                }
                else if(card1[i] == "pic21" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_21);
                }
                else if(card1[i] == "pic21" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[20] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_21), angles[20]);
                }
                else if(card1[i] == "pic21" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_21);
                }
                else if (card1[i] == "pic22" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[21] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_22),angles[21]);
                }
                else if(card1[i] == "pic22" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_22);
                }
                else if(card1[i] == "pic22" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[21] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_22), angles[21]);
                }
                else if(card1[i] == "pic22" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_22);
                }
                else if (card1[i] == "pic23" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[22] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_23),angles[22]);
                }
                else if(card1[i] == "pic23" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_23);
                }
                else if(card1[i] == "pic23" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[22] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_23), angles[22]);
                }
                else if(card1[i] == "pic23" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_23);
                }
                else if (card1[i] == "pic24" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[23] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_24),angles[23]);
                }
                else if(card1[i] == "pic24" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_24);
                }
                else if(card1[i] == "pic24" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[23] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_24), angles[23]);
                }
                else if(card1[i] == "pic24" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_24);
                }
                else if (card1[i] == "pic25" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[24] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_25),angles[24]);
                }
                else if(card1[i] == "pic25" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_25);
                }
                else if(card1[i] == "pic25" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[24] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_25), angles[24]);
                }
                else if(card1[i] == "pic25" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_25);
                }
                else if (card1[i] == "pic26" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[25] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_26),angles[25]);
                }
                else if(card1[i] == "pic26" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_26);
                }
                else if(card1[i] == "pic26" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[25] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_26), angles[25]);
                }
                else if(card1[i] == "pic26" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_26);
                }
                else if (card1[i] == "pic27" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[26] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_27),angles[26]);
                }
                else if(card1[i] == "pic27" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_27);
                }
                else if(card1[i] == "pic27" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[26] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_27), angles[26]);
                }
                else if(card1[i] == "pic27" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_27);
                }
                else if (card1[i] == "pic28" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[27] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_28),angles[27]);
                }
                else if(card1[i] == "pic28" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_28);
                }
                else if(card1[i] == "pic28" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[27] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_28), angles[27]);
                }
                else if(card1[i] == "pic28" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_28);
                }
                else if (card1[i] == "pic29" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[28] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_29),angles[28]);
                }
                else if(card1[i] == "pic29" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_29);
                }
                else if(card1[i] == "pic29" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[28] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_29), angles[28]);
                }
                else if(card1[i] == "pic29" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_29);
                }
                else if (card1[i] == "pic30" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[29] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_30),angles[29]);
                }
                else if(card1[i] == "pic30" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_30);
                }
                else if(card1[i] == "pic30" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[29] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_30), angles[29]);
                }
                else if(card1[i] == "pic30" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_30);
                }
                else if (card1[i] == "pic31" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[30] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_31),angles[30]);
                }
                else if(card1[i] == "pic31" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1text_31);
                }
                else if(card1[i] == "pic31" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    float toDegrees = new Random().nextFloat() * Integer.MAX_VALUE % 360;
                    angles[30] = toDegrees;
                    pic[i] = rotateImage(BitmapFactory.decodeResource(getResources(),R.drawable.img1_31), angles[30]);
                }
                else if(card1[i] == "pic31" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_31);
                }
            }
            for (int i = 0; i < card2.length; i++) {
                if (card2[i] == "pic1" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    pic[i+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_1),angles[0]);
                }
                else if (card2[i] == "pic1" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_1);
                }
                else if(card2[i] == "pic1" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    pic[i + card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_1),angles[0]);
                }
                else if(card2[i] == "pic1" && Arrays.asList(randArr).contains(i)== false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i + card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_1);
                }
                else if (card2[i] == "pic2" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    pic[i+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_2),angles[1]);
                }
                else if (card2[i] == "pic2" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_2);
                }
                else if(card2[i] == "pic2" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    pic[i + card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_2),angles[1]);
                }
                else if(card2[i] == "pic2" && Arrays.asList(randArr).contains(i)== false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i + card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_2);
                }
                else if (card2[i] == "pic3" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    pic[i+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_3),angles[2]);
                }
                else if (card2[i] == "pic3" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_3);
                }
                else if(card2[i] == "pic3" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    pic[i + card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_3),angles[2]);
                }
                else if(card2[i] == "pic3" && Arrays.asList(randArr).contains(i)== false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i + card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_3);
                }
                else if (card2[i] == "pic4" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    pic[i+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_4),angles[3]);
                }
                else if (card2[i] == "pic4" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_4);
                }
                else if(card2[i] == "pic4" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    pic[i + card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_4),angles[3]);
                }
                else if(card2[i] == "pic4" && Arrays.asList(randArr).contains(i)== false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i + card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_4);
                }
                else if (card2[i] == "pic5" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    pic[i+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_5),angles[4]);
                }
                else if (card2[i] == "pic5" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_5);
                }
                else if (card2[i] == "pic5" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    pic[i + card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_5),angles[4]);
                }
                else if (card2[i] == "pic5" && Arrays.asList(randArr).contains(i)== false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i + card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_5);
                }
                else if (card2[i] == "pic6" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    pic[i+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_6),angles[5]);
                }
                else if (card2[i] == "pic6" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_6);
                }
                else if(card2[i] == "pic6" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    pic[i + card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_6),angles[5]);
                }
                else if(card2[i] == "pic6" && Arrays.asList(randArr).contains(i)== false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i + card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_6);
                }
                else if (card2[i] == "pic7" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    pic[i+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_7),angles[6]);
                }
                else if (card2[i] == "pic7" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_7);
                }
                else if(card2[i] == "pic7" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    pic[i + card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_7),angles[6]);
                }
                else if(card2[i] == "pic7" && Arrays.asList(randArr).contains(i)== false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i + card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_7);
                }
                else if (card2[i] == "pic8" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    pic[i+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_8),angles[7]);
                }
                else if (card2[i] == "pic8" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_8);
                }
                else if(card2[i] == "pic8" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    pic[i + card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_8),angles[7]);
                }
                else if(card2[i] == "pic8" && Arrays.asList(randArr).contains(i)== false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i + card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_8);
                }
                else if (card2[i] == "pic9" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i)){
                    pic[i+card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1text_9),angles[8]);
                }
                else if (card2[i] == "pic9" && Arrays.asList(randArr).contains(i) && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_9);
                }
                else if(card2[i] == "pic9" && Arrays.asList(randArr).contains(i) == false && Arrays.asList(randRotateArr).contains(i)){
                    pic[i + card1.length] = rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.img1_9),angles[8]);
                }
                else if(card2[i] == "pic9" && Arrays.asList(randArr).contains(i)== false && Arrays.asList(randRotateArr).contains(i) == false){
                    pic[i + card1.length] = BitmapFactory.decodeResource(getResources(),R.drawable.img1_9);
                }

            }
        }
        else if (option == 1 && mode == 2) {
            Random rand = new Random();
            int order = MainActivity.order;
            int randNo = rand.nextInt(order) + 1;
            Integer[] randArr = new Integer[length];
            for (int i = 0; i < length; i++) {
                randArr[i] = rand.nextInt(length)+1 ;
                Log.d(TAG, "setImages: rand"+randArr[i]);
            }
            for (int i = 0; i < card1.length; i++) {
                if (card1[i] == "pic1" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_1);
                else if (card1[i] == "pic1" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_1);
                else if (card1[i] == "pic2" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_2);
                else if (card1[i] == "pic2" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_2);
                else if (card1[i] == "pic3" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_3);
                else if (card1[i] == "pic3" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_3);
                else if (card1[i] == "pic4" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_4);
                else if (card1[i] == "pic4" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_4);
                else if (card1[i] == "pic5" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_5);
                else if (card1[i] == "pic5" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_5);
                else if (card1[i] == "pic6" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_6);
                else if (card1[i] == "pic6" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_6);
                else if (card1[i] == "pic7" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_7);
                else if (card1[i] == "pic7" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_7);
                else if (card1[i] == "pic8" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_8);
                else if (card1[i] == "pic8" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_8);
                else if (card1[i] == "pic9" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_9);
                else if (card1[i] == "pic9" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_9);
                else if (card1[i] == "pic10" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_10);
                else if (card1[i] == "pic10" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_10);
                else if (card1[i] == "pic11" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_11);
                else if (card1[i] == "pic11" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_11);
                else if (card1[i] == "pic12" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_12);
                else if (card1[i] == "pic12" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_12);
                else if (card1[i] == "pic13" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_13);
                else if (card1[i] == "pic13" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_13);
                else if (card1[i] == "pic14" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_14);
                else if (card1[i] == "pic14" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_14);
                else if (card1[i] == "pic15" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_15);
                else if (card1[i] == "pic15" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_15);
                else if (card1[i] == "pic16" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_16);
                else if (card1[i] == "pic16" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_16);
                else if (card1[i] == "pic17" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_17);
                else if (card1[i] == "pic17" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_17);
                else if (card1[i] == "pic18" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_18);
                else if (card1[i] == "pic18" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_18);
                else if (card1[i] == "pic19" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_19);
                else if (card1[i] == "pic19" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_19);
                else if (card1[i] == "pic20" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_20);
                else if (card1[i] == "pic20" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_20);
                else if (card1[i] == "pic21" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_21);
                else if (card1[i] == "pic21" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_21);
                else if (card1[i] == "pic22" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_22);
                else if (card1[i] == "pic22" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_22);
                else if (card1[i] == "pic23" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_23);
                else if (card1[i] == "pic23" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_23);
                else if (card1[i] == "pic24" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_24);
                else if (card1[i] == "pic24" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_24);
                else if (card1[i] == "pic25" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_25);
                else if (card1[i] == "pic25" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_25);
                else if (card1[i] == "pic26" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_26);
                else if (card1[i] == "pic26" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_26);
                else if (card1[i] == "pic27" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_27);
                else if (card1[i] == "pic27" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_27);
                else if (card1[i] == "pic28" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_28);
                else if (card1[i] == "pic28" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_28);
                else if (card1[i] == "pic29" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_29);
                else if (card1[i] == "pic29" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_29);
                else if (card1[i] == "pic30" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_30);
                else if (card1[i] == "pic30" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_30);
                else if (card1[i] == "pic31" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_31);
                else if (card1[i] == "pic31" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_31);
            }
            for (int i = 0; i < card2.length; i++) {
                if (card2[i] == "pic1" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_1);
                else if (card2[i] == "pic1" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_1);
                else if (card2[i] == "pic2" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_2);
                else if (card2[i] == "pic2" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_2);
                else if (card2[i] == "pic3" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_3);
                else if (card2[i] == "pic3" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_3);
                else if (card2[i] == "pic4" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_4);
                else if (card2[i] == "pic4" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_4);
                else if (card2[i] == "pic5" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_5);
                else if (card2[i] == "pic5" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_5);
                else if (card2[i] == "pic6" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_6);
                else if (card2[i] == "pic6" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_6);
                else if (card2[i] == "pic7" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_7);
                else if (card2[i] == "pic7" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_7);
                else if (card2[i] == "pic8" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_8);
                else if (card2[i] == "pic8" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_8);
                else if (card2[i] == "pic9" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_9);
                else if (card2[i] == "pic9" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_9);
                else if (card2[i] == "pic10" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_10);
                else if (card2[i] == "pic10" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_10);
                else if (card2[i] == "pic11" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_11);
                else if (card2[i] == "pic11" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_11);
                else if (card2[i] == "pic12" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_12);
                else if (card2[i] == "pic12" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_12);
                else if (card2[i] == "pic13" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_13);
                else if (card2[i] == "pic13" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_13);
                else if (card2[i] == "pic14" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_14);
                else if (card2[i] == "pic14" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_14);
                else if (card2[i] == "pic15" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_15);
                else if (card2[i] == "pic15" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_15);
                else if (card2[i] == "pic16" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_16);
                else if (card2[i] == "pic16" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_16);
                else if (card2[i] == "pic17" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_17);
                else if (card2[i] == "pic17" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_17);
                else if (card2[i] == "pic18" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_18);
                else if (card2[i] == "pic18" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_18);
                else if (card2[i] == "pic19" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_19);
                else if (card2[i] == "pic19" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_19);
                else if (card2[i] == "pic20" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_20);
                else if (card2[i] == "pic20" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_20);
                else if (card2[i] == "pic21" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_21);
                else if (card2[i] == "pic21" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_21);
                else if (card2[i] == "pic22" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_22);
                else if (card2[i] == "pic22" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_22);
                else if (card2[i] == "pic23" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_23);
                else if (card2[i] == "pic23" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_23);
                else if (card2[i] == "pic24" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_24);
                else if (card2[i] == "pic24" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_24);
                else if (card2[i] == "pic25" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_25);
                else if (card2[i] == "pic25" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_25);
                else if (card2[i] == "pic26" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_26);
                else if (card2[i] == "pic26" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_26);
                else if (card2[i] == "pic27" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_27);
                else if (card2[i] == "pic27" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_27);
                else if (card2[i] == "pic28" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_28);
                else if (card2[i] == "pic28" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_28);
                else if (card2[i] == "pic29" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_29);
                else if (card2[i] == "pic29" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_29);
                else if (card2[i] == "pic30" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_30);
                else if (card2[i] == "pic30" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_30);
                else if (card2[i] == "pic31" && Arrays.asList(randArr).contains(i))
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1text_31);
                else if (card2[i] == "pic31" && Arrays.asList(randArr).contains(i) == false)
                    pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img1_31);
            }
        }
        else if(option==2 && mode==2)
        {
            Random rand = new Random();
            int order = MainActivity.order;
            int randNo = rand.nextInt(order) + 1;
            Integer[] randArr = new Integer[length];
            for (int i = 0; i < length; i++) {
                randArr[i] = rand.nextInt(length) + 1;
                Log.d(TAG, "setImages: rand"+randArr[i]);
            }
            for (int i = 0; i < card1.length; i++) {
                if (card1[i] == "pic1" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_1);
                else if (card1[i] == "pic1" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_1);
                else if (card1[i] == "pic2" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_2);
                else if (card1[i] == "pic2" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_2);
                else if (card1[i] == "pic3" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_3);
                else if (card1[i] == "pic3" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_3);
                else if (card1[i] == "pic4" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_4);
                else if (card1[i] == "pic4" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_4);
                else if (card1[i] == "pic5" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_5);
                else if (card1[i] == "pic5" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_5);
                else if (card1[i] == "pic6" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_6);
                else if (card1[i] == "pic6" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_6);
                else if (card1[i] == "pic7" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_7);
                else if (card1[i] == "pic7" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_7);
                else if (card1[i] == "pic8" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_8);
                else if (card1[i] == "pic8" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_8);
                else if (card1[i] == "pic9" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_9);
                else if (card1[i] == "pic9" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_9);
                else if (card1[i] == "pic10" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_10);
                else if (card1[i] == "pic10" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_10);
                else if (card1[i] == "pic11" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_11);
                else if (card1[i] == "pic11" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_11);
                else if (card1[i] == "pic12" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_12);
                else if (card1[i] == "pic12" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_12);
                else if (card1[i] == "pic13" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_13);
                else if (card1[i] == "pic13" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_13);
                else if (card1[i] == "pic14" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_14);
                else if (card1[i] == "pic14" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_14);
                else if (card1[i] == "pic15" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_15);
                else if (card1[i] == "pic15" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_15);
                else if (card1[i] == "pic16" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_16);
                else if (card1[i] == "pic16" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_16);
                else if (card1[i] == "pic17" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_17);
                else if (card1[i] == "pic17" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_17);
                else if (card1[i] == "pic18" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_18);
                else if (card1[i] == "pic18" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_18);
                else if (card1[i] == "pic19" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_19);
                else if (card1[i] == "pic19" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_19);
                else if (card1[i] == "pic20" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_20);
                else if (card1[i] == "pic20" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_20);
                else if (card1[i] == "pic21" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_21);
                else if (card1[i] == "pic21" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_21);
                else if (card1[i] == "pic22" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_22);
                else if (card1[i] == "pic22" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_22);
                else if (card1[i] == "pic23" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_23);
                else if (card1[i] == "pic23" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_23);
                else if (card1[i] == "pic24" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_24);
                else if (card1[i] == "pic24" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_24);
                else if (card1[i] == "pic25" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_25);
                else if (card1[i] == "pic25" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_25);
                else if (card1[i] == "pic26" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_26);
                else if (card1[i] == "pic26" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_26);
                else if (card1[i] == "pic27" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_27);
                else if (card1[i] == "pic27" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_27);
                else if (card1[i] == "pic28" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_28);
                else if (card1[i] == "pic28" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_28);
                else if (card1[i] == "pic29" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_29);
                else if (card1[i] == "pic29" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_29);
                else if (card1[i] == "pic30" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_30);
                else if (card1[i] == "pic30" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_30);
                else if (card1[i] == "pic31" && Arrays.asList(randArr).contains(i))
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_31);
                else if (card1[i] == "pic31" && Arrays.asList(randArr).contains(i) == false)
                    pic[i] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_31);
            }
            for (int i = 0; i < card2.length; i++) {
                    if (card2[i] == "pic1" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_1);
                    else if (card2[i] == "pic1" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_1);
                    else if (card2[i] == "pic2" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_2);
                    else if (card2[i] == "pic2" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_2);
                    else if (card2[i] == "pic3" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_3);
                    else if (card2[i] == "pic3" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_3);
                    else if (card2[i] == "pic4" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_4);
                    else if (card2[i] == "pic4" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_4);
                    else if (card2[i] == "pic5" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_5);
                    else if (card2[i] == "pic5" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_5);
                    else if (card2[i] == "pic6" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_6);
                    else if (card2[i] == "pic6" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_6);
                    else if (card2[i] == "pic7" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_7);
                    else if (card2[i] == "pic7" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_7);
                    else if (card2[i] == "pic8" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_8);
                    else if (card2[i] == "pic8" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_8);
                    else if (card2[i] == "pic9" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_9);
                    else if (card2[i] == "pic9" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_9);
                    else if (card2[i] == "pic10" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_10);
                    else if (card2[i] == "pic10" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_10);
                    else if (card2[i] == "pic11" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_11);
                    else if (card2[i] == "pic11" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_11);
                    else if (card2[i] == "pic12" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_12);
                    else if (card2[i] == "pic12" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_12);
                    else if (card2[i] == "pic13" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_13);
                    else if (card2[i] == "pic13" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_13);
                    else if (card2[i] == "pic14" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_14);
                    else if (card2[i] == "pic14" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_14);
                    else if (card2[i] == "pic15" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_15);
                    else if (card2[i] == "pic15" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_15);
                    else if (card2[i] == "pic16" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_16);
                    else if (card2[i] == "pic16" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_16);
                    else if (card2[i] == "pic17" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_17);
                    else if (card2[i] == "pic17" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_17);
                    else if (card2[i] == "pic18" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_18);
                    else if (card2[i] == "pic18" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_18);
                    else if (card2[i] == "pic19" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_19);
                    else if (card2[i] == "pic19" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_19);
                    else if (card2[i] == "pic20" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_20);
                    else if (card2[i] == "pic20" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_20);
                    else if (card2[i] == "pic21" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_21);
                    else if (card2[i] == "pic21" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_21);
                    else if (card2[i] == "pic22" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_22);
                    else if (card2[i] == "pic22" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_22);
                    else if (card2[i] == "pic23" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_23);
                    else if (card2[i] == "pic23" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_23);
                    else if (card2[i] == "pic24" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_24);
                    else if (card2[i] == "pic24" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_24);
                    else if (card2[i] == "pic25" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_25);
                    else if (card2[i] == "pic25" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_25);
                    else if (card2[i] == "pic26" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_26);
                    else if (card2[i] == "pic26" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_26);
                    else if (card2[i] == "pic27" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_27);
                    else if (card2[i] == "pic27" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_27);
                    else if (card2[i] == "pic28" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_28);
                    else if (card2[i] == "pic28" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_28);
                    else if (card2[i] == "pic29" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_29);
                    else if (card2[i] == "pic29" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_29);
                    else if (card2[i] == "pic30" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_30);
                    else if (card2[i] == "pic30" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_30);
                    else if (card2[i] == "pic31" && Arrays.asList(randArr).contains(i))
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2text_31);
                    else if (card2[i] == "pic31" && Arrays.asList(randArr).contains(i) == false)
                        pic[i+card1.length] = BitmapFactory.decodeResource(getResources(), R.drawable.img2_31);
                }
        }

        else if (option == 3 && mode==1) {
            for (int i = 0; i < card1.length; i++) {
                if (card1[i] == "pic1")
                    pic[i] = bitmaps.get(0);
                else if (card1[i] == "pic2")
                    pic[i] = bitmaps.get(1);
                else if (card1[i] == "pic3")
                    pic[i] = bitmaps.get(2);
                else if (card1[i] == "pic4")
                    pic[i] = bitmaps.get(3);
                else if (card1[i] == "pic5")
                    pic[i] = bitmaps.get(4);
                else if (card1[i] == "pic6")
                    pic[i] = bitmaps.get(5);
                else if (card1[i] == "pic7")
                    pic[i] = bitmaps.get(6);
                else if (card1[i] == "pic8")
                    pic[i] = bitmaps.get(7);
                else if (card1[i] == "pic9")
                    pic[i] = bitmaps.get(8);
                else if (card1[i] == "pic10")
                    pic[i] = bitmaps.get(9);
                else if (card1[i] == "pic11")
                    pic[i] = bitmaps.get(10);
                else if (card1[i] == "pic12")
                    pic[i] = bitmaps.get(11);
                else if (card1[i] == "pic13")
                    pic[i] = bitmaps.get(12);
                else if (card1[i] == "pic14")
                    pic[i] = bitmaps.get(13);
                else if (card1[i] == "pic15")
                    pic[i] = bitmaps.get(14);
                else if (card1[i] == "pic16")
                    pic[i] = bitmaps.get(15);
                else if (card1[i] == "pic17")
                    pic[i] = bitmaps.get(16);
                else if (card1[i] == "pic18")
                    pic[i] = bitmaps.get(17);
                else if (card1[i] == "pic19")
                    pic[i] = bitmaps.get(18);
                else if (card1[i] == "pic20")
                    pic[i] = bitmaps.get(19);
                else if (card1[i] == "pic21")
                    pic[i] = bitmaps.get(20);
                else if (card1[i] == "pic22")
                    pic[i] = bitmaps.get(21);
                else if (card1[i] == "pic23")
                    pic[i] = bitmaps.get(22);
                else if (card1[i] == "pic24")
                    pic[i] = bitmaps.get(23);
                else if (card1[i] == "pic25")
                    pic[i] = bitmaps.get(24);
                else if (card1[i] == "pic26")
                    pic[i] = bitmaps.get(25);
                else if (card1[i] == "pic27")
                    pic[i] = bitmaps.get(26);
                else if (card1[i] == "pic28")
                    pic[i] = bitmaps.get(27);
                else if (card1[i] == "pic29")
                    pic[i] = bitmaps.get(28);
                else if (card1[i] == "pic30")
                    pic[i] = bitmaps.get(29);
                else if (card1[i] == "pic31")
                    pic[i] = bitmaps.get(30);
            }
            for (int j = 0; j < card2.length; j++) {
                if (card2[j] == "pic1")
                    pic[j + card1.length] = bitmaps.get(0);
                else if (card2[j] == "pic2")
                    pic[j + card1.length] = bitmaps.get(1);
                else if (card2[j] == "pic3")
                    pic[j + card1.length] = bitmaps.get(2);
                else if (card2[j] == "pic4")
                    pic[j + card1.length] = bitmaps.get(3);
                else if (card2[j] == "pic5")
                    pic[j + card1.length] = bitmaps.get(4);
                else if (card2[j] == "pic6")
                    pic[j + card1.length] = bitmaps.get(5);
                else if (card2[j] == "pic7")
                    pic[j + card1.length] = bitmaps.get(6);
                else if (card2[j] == "pic8")
                    pic[j + card1.length] = bitmaps.get(7);
                else if (card2[j] == "pic9")
                    pic[j + card1.length] = bitmaps.get(8);
                else if (card2[j] == "pic10")
                    pic[j + card1.length] = bitmaps.get(9);
                else if (card2[j] == "pic11")
                    pic[j + card1.length] = bitmaps.get(10);
                else if (card2[j] == "pic12")
                    pic[j + card1.length] = bitmaps.get(11);
                else if (card2[j] == "pic13")
                    pic[j + card1.length] = bitmaps.get(12);
                else if (card2[j] == "pic14")
                    pic[j + card1.length] = bitmaps.get(13);
                else if (card2[j] == "pic15")
                    pic[j + card1.length] = bitmaps.get(14);
                else if (card2[j] == "pic16")
                    pic[j + card1.length] = bitmaps.get(15);
                else if (card2[j] == "pic17")
                    pic[j + card1.length] = bitmaps.get(16);
                else if (card2[j] == "pic18")
                    pic[j + card1.length] = bitmaps.get(17);
                else if (card2[j] == "pic19")
                    pic[j + card1.length] = bitmaps.get(18);
                else if (card2[j] == "pic20")
                    pic[j + card1.length] = bitmaps.get(19);
                else if (card2[j] == "pic21")
                    pic[j + card1.length] = bitmaps.get(20);
                else if (card2[j] == "pic22")
                    pic[j + card1.length] = bitmaps.get(21);
                else if (card2[j] == "pic23")
                    pic[j + card1.length] = bitmaps.get(22);
                else if (card2[j] == "pic24")
                    pic[j + card1.length] = bitmaps.get(23);
                else if (card2[j] == "pic25")
                    pic[j + card1.length] = bitmaps.get(24);
                else if (card2[j] == "pic26")
                    pic[j + card1.length] = bitmaps.get(25);
                else if (card2[j] == "pic27")
                    pic[j + card1.length] = bitmaps.get(26);
                else if (card2[j] == "pic28")
                    pic[j + card1.length] = bitmaps.get(27);
                else if (card2[j] == "pic29")
                    pic[j + card1.length] = bitmaps.get(28);
                else if (card2[j] == "pic30")
                    pic[j + card1.length] = bitmaps.get(29);
                else if (card2[j] == "pic31")
                    pic[j + card1.length] = bitmaps.get(30);
            }
        }

        for (int i = 0; i < card1.length; i++) {
            picScaled[i] = Bitmap.createScaledBitmap(pic[i], 300, 300, true);
            picScaled[i + card1.length] = Bitmap.createScaledBitmap(pic[i + card1.length], 300, 300, true);//https://stackoverflow.com/questions/27466099/how-to-resize-bitmap-when-drawing-in-canvas
            Log.d(TAG, "setImages: " + i);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        //when touched compare the coordinates to see whether it is within the picture's coordinate and check if the picture is contained in the top and hand
        if (order == 2) {
            if (x > canvasXSize * 1 / 10 && x < canvasXSize * 1 / 10 + 300 && y > canvasYSize * 1 / 10 && y < canvasYSize * 1 / 10 + 300) {
                if (top.contain(card1[0])) {
                    correctImgSound(getContext());
                    drawAction();
                }
                else {
                    incorrectImgSound(getContext());
                }
            } else if (x > canvasXSize * 6.5 / 10 && x < canvasXSize * 6.5 / 10 + 300 && y > canvasYSize * 1 / 10 && y < canvasYSize * 1 / 10 + 300) {
                if (top.contain(card1[1])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            } else if (x > canvasXSize * 3.5 / 10 && x < canvasXSize * 3.5 / 10 + 300 && y > canvasYSize * 3 / 10 && y < canvasYSize * 3 / 10 + 300) {
                if (top.contain(card1[2])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            }
        } else if (order == 3) {
            if (x > canvasXSize * 1 / 10 && x < canvasXSize * 1 / 10 + 300 &&
                    y > canvasYSize * 1 / 10 && y < canvasYSize * 1 / 10 + 300) {
                if (top.contain(card1[0])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            } else if (x > canvasXSize * 6.5 / 10 && x < canvasXSize * 6.5 / 10 + 300 &&
                    y > canvasYSize * 1 / 10 && y < canvasYSize * 1 / 10 + 300) {
                if (top.contain(card1[1])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            } else if (x > canvasXSize * 1 / 10 && x < canvasXSize * 1 / 10 + 300 &&
                    y > canvasYSize * 3 / 10 && y < canvasYSize * 3 / 10 + 300) {
                if (top.contain(card1[2])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            } else if (x > canvasXSize * 6.5 / 10 && x < canvasXSize * 6.5 / 10 + 300 &&
                    y > canvasYSize * 3 / 10 && y < canvasYSize * 3 / 10 + 300) {
                if (top.contain(card1[3])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            }
        } else if (order == 5) {
            if (x > canvasXSize * 1 / 10 && x < canvasXSize * 1 / 10 + 300 &&
                    y > canvasYSize * 1 / 10 && y < canvasYSize * 1 / 10 + 300) {
                if (top.contain(card1[0])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            } else if (x > canvasXSize * 3.85 / 10 && x < canvasXSize * 3.85 / 10 + 300 &&
                    y > canvasYSize * 1 / 10 && y < canvasYSize * 1 / 10 + 300) {
                if (top.contain(card1[1])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            } else if (x > canvasXSize * 6.7 / 10 && x < canvasXSize * 6.7 / 10 + 300 &&
                    y > canvasYSize * 1 / 10 && y < canvasYSize * 1 / 10 + 300) {
                if (top.contain(card1[2])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            } else if (x > canvasXSize * 1 / 10 && x < canvasXSize * 1 / 10 + 300 &&
                    y > canvasYSize * 3 / 10 && y < canvasYSize * 3 / 10 + 300) {
                if (top.contain(card1[3])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            } else if (x > canvasXSize * 3.85 / 10 && x < canvasXSize * 3.85 / 10 + 300 &&
                    y > canvasYSize * 3 / 10 && y < canvasYSize * 3 / 10 + 300) {
                if (top.contain(card1[4])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            } else if (x > canvasXSize * 6.7 / 10 && x < canvasXSize * 6.7 / 10 + 300 &&
                    y > canvasYSize * 3 / 10 && y < canvasYSize * 3 / 10 + 300) {
                if (top.contain(card1[5])) {
                    correctImgSound(getContext());
                    drawAction();
                }else {
                    incorrectImgSound(getContext());
                }
            }
        }

        return super.onTouchEvent(event);
    }

    private void drawPic(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        canvas.drawBitmap(scaledBackground, 0, 0, null);
        canvas.drawBitmap(scaledBackground, 0, canvas.getHeight() / 2, null);
        if (order == 2) {
            canvas.drawBitmap(picScaled[0], canvas.getWidth() * 1 / 10, canvas.getHeight() * 1 / 10, null);
            canvas.drawBitmap(picScaled[1], (float) (canvas.getWidth() * 6.5 / 10), canvas.getHeight() * 1 / 10, null);
            canvas.drawBitmap(picScaled[2], (float) (canvas.getWidth() * 3.5 / 10), canvas.getHeight() * 3 / 10, null);

            canvas.drawBitmap(picScaled[3], canvas.getWidth() * 1 / 10, canvas.getHeight() * 6 / 10, null);
            canvas.drawBitmap(picScaled[4], (float) (canvas.getWidth() * 6.5 / 10), canvas.getHeight() * 6 / 10, null);
            canvas.drawBitmap(picScaled[5], (float) (canvas.getWidth() * 3.5 / 10), canvas.getHeight() * 8 / 10, null);
        } else if (order == 3) {
            canvas.drawBitmap(picScaled[0], canvas.getWidth() * 1 / 10, canvas.getHeight() * 1 / 10, null);
            canvas.drawBitmap(picScaled[1], (float) (canvas.getWidth() * 6.5 / 10), canvas.getHeight() * 1 / 10, null);
            canvas.drawBitmap(picScaled[2], (float) (canvas.getWidth() * 1 / 10), canvas.getHeight() * 3 / 10, null);
            canvas.drawBitmap(picScaled[3], (float) (canvas.getWidth() * 6.5 / 10), canvas.getHeight() * 3 / 10, null);

            canvas.drawBitmap(picScaled[4], canvas.getWidth() * 1 / 10, canvas.getHeight() * 6 / 10, null);
            canvas.drawBitmap(picScaled[5], (float) (canvas.getWidth() * 6.5 / 10), canvas.getHeight() * 6 / 10, null);
            canvas.drawBitmap(picScaled[6], (float) (canvas.getWidth() * 1 / 10), canvas.getHeight() * 8 / 10, null);
            canvas.drawBitmap(picScaled[7], (float) (canvas.getWidth() * 6.5 / 10), canvas.getHeight() * 8 / 10, null);
        } else if (order == 5) {
            canvas.drawBitmap(picScaled[0], canvas.getWidth() * 1 / 10, canvas.getHeight() * 1 / 10, null);
            canvas.drawBitmap(picScaled[1], (float) (canvas.getWidth() * 3.85 / 10), canvas.getHeight() * 1 / 10, null);
            canvas.drawBitmap(picScaled[2], (float) (canvas.getWidth() * 6.7 / 10), canvas.getHeight() * 1 / 10, null);
            canvas.drawBitmap(picScaled[3], (float) (canvas.getWidth() * 1 / 10), canvas.getHeight() * 3 / 10, null);
            canvas.drawBitmap(picScaled[4], (float) (canvas.getWidth() * 3.85 / 10), canvas.getHeight() * 3 / 10, null);
            canvas.drawBitmap(picScaled[5], (float) (canvas.getWidth() * 6.7 / 10), canvas.getHeight() * 3 / 10, null);

            canvas.drawBitmap(picScaled[6], canvas.getWidth() * 1 / 10, canvas.getHeight() * 6 / 10, null);
            canvas.drawBitmap(picScaled[7], (float) (canvas.getWidth() * 3.85 / 10), canvas.getHeight() * 6 / 10, null);
            canvas.drawBitmap(picScaled[8], (float) (canvas.getWidth() * 6.7 / 10), canvas.getHeight() * 6 / 10, null);
            canvas.drawBitmap(picScaled[9], (float) (canvas.getWidth() * 1 / 10), canvas.getHeight() * 8 / 10, null);
            canvas.drawBitmap(picScaled[10], (float) (canvas.getWidth() * 3.85 / 10), canvas.getHeight() * 8 / 10, null);
            canvas.drawBitmap(picScaled[11], (float) (canvas.getWidth() * 6.7 / 10), canvas.getHeight() * 8 / 10, null);
        }

    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {//https://stackoverflow.com/questions/12944275/crop-image-as-circle-in-android
        //crop bitmap into circle
        int widthLight = bitmap.getWidth();
        int heightLight = bitmap.getHeight();

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        Paint paintColor = new Paint();
        paintColor.setFlags(Paint.ANTI_ALIAS_FLAG);

        RectF rectF = new RectF(new Rect(0, 0, widthLight, heightLight));

        canvas.drawRoundRect(rectF, widthLight / 2, heightLight / 2, paintColor);

        Paint paintImage = new Paint();
        paintImage.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmap, 0, 0, paintImage);

        return output;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        scaledBackground = Bitmap.createScaledBitmap(cardBackground, canvas.getWidth(), canvas.getHeight() / 2, true);
        canvasXSize = canvas.getWidth();
        canvasYSize = canvas.getHeight();


        drawPic(canvas);

    }

    private void wonAction() {
        drawPileSize = 0;
        //calculate time
        score = (int) (SystemClock.elapsedRealtime() - timer);
        Intent intent = new Intent().setClass(getContext(), Won.class);

        //pass score value to winning screen activity
        intent.putExtra("score", score);
        ((Activity) getContext()).startActivity(intent);
        ((Activity) getContext()).finish();
    }

    private void drawAction() {
        setImages();
        holder.lockCanvas();
        drawPic(c);
        holder.unlockCanvasAndPost(c);
        drawPileSize++;
    }
}
