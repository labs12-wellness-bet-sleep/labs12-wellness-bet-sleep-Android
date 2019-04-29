package com.example.labs12_wellness_bet_sleep_android.SignUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.labs12_wellness_bet_sleep_android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class AddPhotoActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imageToUpload;
    CardView cardViewUploadPhoto;
    ImageView ProfileImage;
    final static int Gallery_Pick = 1;
    private StorageReference UserProfileImageRef;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private DatabaseReference UsersRef;
    String currentUserID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        imageToUpload = (ImageView) findViewById(R.id.upload_photo);
        cardViewUploadPhoto = (CardView) findViewById(R.id.cardView_add_photo);
        ProfileImage = (ImageView) findViewById(R.id.upload_photo);

        //ref to firebase storage
        UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);

        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, Gallery_Pick);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Pick && resultCode == RESULT_OK && data != null) {
            Uri ImageUri = data.getData();

            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
           CropImage.ActivityResult result = CropImage.getActivityResult(data);

           if(resultCode== RESULT_OK)
           {

               loadingBar.setTitle("Profile Image");
               loadingBar.setMessage("Please wait, while we updating your profile image...");
               loadingBar.show();
               loadingBar.setCanceledOnTouchOutside(true);

               Uri resultUri = result.getUri();

               StorageReference filePath = UserProfileImageRef.child(currentUserID+".jpg");

               //saving the image inside of the firebase
               filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task)
                   {
                       if(task.isSuccessful())
                       {
                           Toast.makeText(AddPhotoActivity.this, "Profile Image Stored Successfully to Storage...", Toast.LENGTH_SHORT).show();

                           final String downloadUrl = task.getResult().getDownloadUrl().toString();

                           UsersRef.child("profileImage").setValue(downloadUrl)
                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task)
                                       {
                                           if(task.isSuccessful())
                                           {
                                               Intent selfIntent = new Intent(AddPhotoActivity.this, AddPhotoActivity.class);
                                               startActivity(selfIntent);

                                               Toast.makeText(AddPhotoActivity.this, "Profile Image Stored Successfully to Storage...", Toast.LENGTH_SHORT).show();
                                               loadingBar.dismiss();
                                           }
                                           else
                                           {
                                               String messege = task.getException().getMessage();
                                               Toast.makeText(AddPhotoActivity.this, "Error:" +messege, Toast.LENGTH_SHORT).show();
                                               loadingBar.dismiss();
                                           }

                                       }
                                   });
                       }

                   }
               });
           }
           else{
               Toast.makeText(this, "Error: Image can't be cropped. Try again.", Toast.LENGTH_SHORT).show();
               loadingBar.dismiss();
           }
        }

    }
}






