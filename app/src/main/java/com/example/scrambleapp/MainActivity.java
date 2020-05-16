package com.example.scrambleapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int CAMERA_REQUEST = 1888;
    private static final String TAG ="ScrambleApp";
    ArrayList <ImageView> imageViewArrayList = new ArrayList<ImageView>();
    ArrayList<Bitmap> splitMapArrayList = new ArrayList<Bitmap>();
    public Bitmap[] splitMapArray;
    public Bitmap[] splitMapArrayWin;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    boolean hasClicked = false;
    int index1;
    int index2;



    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = this.findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        imageView2 = this.findViewById(R.id.imageView2);
        imageView2.setOnClickListener(this);
        imageView3 = this.findViewById(R.id.imageView3);
        imageView3.setOnClickListener(this);
        imageView4 = this.findViewById(R.id.imageView4);
        imageView4.setOnClickListener(this);
        imageView5 = this.findViewById(R.id.imageView5);
        imageView5.setOnClickListener(this);
        imageView6 = this.findViewById(R.id.imageView6);
        imageView6.setOnClickListener(this);
        imageView7 = this.findViewById(R.id.imageView7);
        imageView7.setOnClickListener(this);
        imageView8 = this.findViewById(R.id.imageView8);
        imageView8.setOnClickListener(this);
        imageView9 = this.findViewById(R.id.imageView9);
        imageView9.setOnClickListener(this);

        Button photoButton = (Button) this.findViewById(R.id.button);

        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            photo = getResizedBitmap(photo, 700, 700);

            Bitmap[][] splitMap = splitBitmap(photo, 3, 3);
            ImageView[] imageViewArray = {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
            imageViewArrayList.addAll(Arrays.asList(imageViewArray));
            
            splitMapArray = new Bitmap[]{splitMap[0][0],splitMap[1][0],splitMap[2][0],splitMap[0][1],splitMap[1][1],splitMap[2][1],splitMap[0][2],splitMap[1][2],splitMap[2][2]};
            splitMapArrayWin = splitMapArray;
            shuffle(splitMapArray);
            splitMapArrayList.addAll(Arrays.asList(splitMapArray));
            for (int i=0; i < imageViewArrayList.size(); i++) {
                imageViewArrayList.get(i).setImageBitmap(splitMapArrayList.get(i));
            }

        }
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

    public Bitmap[][] splitBitmap(Bitmap bitmap, int xCount, int yCount) {
        // Allocate a two dimensional array to hold the individual images.
        Bitmap[][] bitmaps = new Bitmap[xCount][yCount];
        int width, height;
        // Divide the original bitmap width by the desired vertical column count
        width = bitmap.getWidth() / xCount;
        // Divide the original bitmap height by the desired horizontal row count
        height = bitmap.getHeight() / yCount;
        // Loop the array and create bitmaps for each coordinate
        for (int x = 0; x < xCount; ++x) {
            for (int y = 0; y < yCount; ++y) {
                // Create the sliced bitmap
                bitmaps[x][y] = Bitmap.createBitmap(bitmap, x * width, y * height, width, height);
            }
        }
        // Return the array
        return bitmaps;

    }

    void shuffle(Bitmap[] a) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = a.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Bitmap temp = a[index];
            a[index] = a[i];
            a[i] = temp;
        }
        }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (hasClicked){
            switch (v.getId()) {

                case R.id.imageView:
                    index2=0;
                    // do your code
                    break;

                case R.id.imageView2:
                    index2=1;
                    // do your code
                    break;

                case R.id.imageView3:
                    index2=2;
                    // do your code
                    break;

                case R.id.imageView4:
                    index2=3;
                    // do your code
                    break;

                case R.id.imageView5:
                    index2=4;
                    // do your code
                    break;

                case R.id.imageView6:
                    index2=5;
                    // do your code
                    break;

                case R.id.imageView7:
                    index2=6;
                    // do your code
                    break;

                case R.id.imageView8:
                    index2=7;
                    // do your code
                    break;

                case R.id.imageView9:
                    index2=8;
                    // do your code
                    break;

                default:
                    break;
            }
            Bitmap temp1 = splitMapArrayList.get(index1);
            Bitmap temp2 = splitMapArrayList.get(index2);
            splitMapArrayList.set(index1,temp2);
            splitMapArrayList.set(index2,temp1);
            for (int i=0; i < imageViewArrayList.size(); i++) {
                imageViewArrayList.get(i).setImageBitmap(splitMapArrayList.get(i));
            }
            checkWin();

        }
        switch (v.getId()) {

            case R.id.imageView:
                index1=0;
                // do your code
                break;

            case R.id.imageView2:
                index1=1;
                // do your code
                break;

            case R.id.imageView3:
                index1=2;
                // do your code
                break;

            case R.id.imageView4:
                index1=3;
                // do your code
                break;

            case R.id.imageView5:
                index1=4;
                // do your code
                break;

            case R.id.imageView6:
                index1=5;
                // do your code
                break;

            case R.id.imageView7:
                index1=6;
                // do your code
                break;

            case R.id.imageView8:
                index1=7;
                // do your code
                break;

            case R.id.imageView9:
                index1=8;
                // do your code
                break;

            default:
                break;
        }
        Log.d(TAG, "onClick: "+index1 +" "+index2);
        hasClicked = !hasClicked;
    }
    public void checkWin() {
        if (splitMapArrayWin == splitMapArray){
            Log.d(TAG, "checkWin: YOU WIN");
        }
    }
    
}