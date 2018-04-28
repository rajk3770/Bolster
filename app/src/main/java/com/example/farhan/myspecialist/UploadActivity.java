package com.example.farhan.myspecialist;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

/**
 * Created by raj on 13/3/18.
 */

public class UploadActivity extends AppCompatActivity {

    private Button mSelectImage;
    private StorageReference mStorage;

    private Button mSend;

    private DatabaseReference mDatabase;

    private Button mCapture;
    private ImageView mImageView;

    private final int CAMERA_REQUEST=1;

    private final int PICK_IMAGE_REQUEST = 71;

    public static final int GALLERY_INTENT=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_upload);


        mImageView=(ImageView)findViewById(R.id.imageView);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        mSend=(Button)findViewById(R.id.sendbtn);

        mStorage = FirebaseStorage.getInstance().getReference();

        mSelectImage = (Button) findViewById(R.id.uploadbtn);

//        mCapture=(Button)findViewById(R.id.capturebtn);

  /*      mCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(UploadActivity.this.getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CAMERA_REQUEST);
                }

            }
        });
*/

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);




            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                mDatabase= FirebaseDatabase.getInstance().getReference().child("users");
                Log.e("Upload Activity",user.getUid());
                mDatabase.child(user.getUid()).child("Active").setValue("1");
                Toast.makeText(UploadActivity.this,"Data Sent",Toast.LENGTH_LONG).show();
                startActivity(new Intent(UploadActivity.this,SendActivity.class));
            }
        });

    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){

            Log.e("Output","Inside Function");

            Uri uri=data.getData();
            Log.e("URI",uri.toString());
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            StorageReference filepath=mStorage.child(user.getUid()).child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(UploadActivity.this,"Upload Successful",Toast.LENGTH_LONG).show();

                    Uri downloaduri=taskSnapshot.getDownloadUrl();

                    Picasso.get().load(downloaduri).fit().centerCrop().into(mImageView);




                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(UploadActivity.this,"Upload Failed",Toast.LENGTH_LONG).show();

                }
            });
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(UploadActivity.this,MainActivity.class);
        startActivity(i);

    }
}

