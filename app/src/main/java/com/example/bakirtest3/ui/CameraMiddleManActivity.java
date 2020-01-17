package com.example.bakirtest3.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import static com.example.bakirtest3.ui.MainActivity.REQUEST_IMAGE_CAPTURE;


public class CameraMiddleManActivity extends AppCompatActivity {
    private android.net.Uri mImageUri;
    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;
    private DatabaseReference mDataRef;
    private StorageReference mStorageRef;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //nema ovdje layout

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE, null);
        mDataRef = FirebaseDatabase.getInstance().getReference("/Hemija/Photos/");
        mStorageRef = FirebaseStorage.getInstance().getReference("/Hemija/Photos/");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                File image = createImageFile();
                mImageUri = Uri.fromFile(image);
                uploadFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = null;
        String imageFileName = "bakir";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        Long length = image.length();
        Toast.makeText(CameraMiddleManActivity.this, length.toString(), Toast.LENGTH_LONG).show();
        return image;
    }
    private void uploadFile() {
        if (mImageUri != null) {
            Toast.makeText(this, "Primljen URI", Toast.LENGTH_LONG).show();
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getMimeType(this, mImageUri));
            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(CameraMiddleManActivity.this, "Snimanje uspjelo", Toast.LENGTH_LONG);
                    PhotoUpload upload = new PhotoUpload(System.currentTimeMillis() + "mill",
                            taskSnapshot
                            .getMetadata()
                            .getReference()
                            .getDownloadUrl()
                            .toString());
                    String uploadId = mDataRef.push().getKey();
                    mDataRef.child(uploadId).setValue(upload);
                    startActivity(new Intent(CameraMiddleManActivity.this, MainActivity.class));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CameraMiddleManActivity.this, "Nije uspjelo spremanje", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CameraMiddleManActivity.this, MainActivity.class));
                }
            });

        } else {
            Toast.makeText(this, "No file Selected", Toast.LENGTH_LONG).show();
        }


    }
    /*private String getFileExtension() {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(mImageUri));
    }*/
    public static String getMimeType(Context context, Uri uri) {
        String extension;

        //Check uri format to avoid null
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //If scheme is a content
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else {
            //If scheme is a File
            //This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());

        }

        return extension;
    }
/*    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }*/
}
