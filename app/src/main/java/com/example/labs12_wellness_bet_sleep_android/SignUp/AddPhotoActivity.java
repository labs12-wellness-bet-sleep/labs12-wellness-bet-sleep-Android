package com.example.labs12_wellness_bet_sleep_android.SignUp;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.example.labs12_wellness_bet_sleep_android.R;

public class AddPhotoActivity extends AppCompatActivity {
    private static  final int RESULT_LOAD_IMAGE =1;
    ImageView imageToUpload;
    CardView cardViewUploadPhoto;
    ImageView ProfileImage;
    final static int Gallery_Pick = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        imageToUpload = (ImageView) findViewById(R.id.upload_photo);
        cardViewUploadPhoto =(CardView) findViewById(R.id.cardView_add_photo);
        ProfileImage = (ImageView) findViewById(R.id.upload_photo);

        ProfileImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, Gallery_Pick);
        }
        });
    }
}
