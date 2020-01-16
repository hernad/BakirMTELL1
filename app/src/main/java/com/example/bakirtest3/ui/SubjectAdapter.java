package com.example.bakirtest3.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.bakirtest3.R;
import com.example.bakirtest3.ui.notetake.NoteTakerActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;


public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private Context mCon;
    private List<Subject> subjectList;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    MainActivity mainActivity;


    public SubjectAdapter(Context mCon, List<Subject> productList) {
        this.mCon = mCon;
        this.subjectList = productList;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCon);
        View view = inflater.inflate(R.layout.list_item_subject, null);
        SubjectViewHolder holder = new SubjectViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        holder.nameOfSubjectText.setText(subject.getSubjectName());
        mStorageRef = FirebaseStorage.getInstance().getReference("Hemija/Hemija-Photos");
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public class SubjectViewHolder extends ViewHolder {


        TextView nameOfSubjectText;
        ImageView cameraButton;
        ImageView noteTakeButton;
        private final static int CAMERA_REQUEST_CODE = 1;

/*
        public class UplaodActivity extends AppCompatActivity {
           public static final String EXTRA_MESSAGE = "com.example.bakirtest3.ui.UPLOAD";



            @Override
            protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
            @Override
            protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                mImageUri = data.getData();
                //uploadFile();
            }


        }
*/

        public SubjectViewHolder(View itemView) {
            super(itemView);
            nameOfSubjectText = itemView.findViewById(R.id.textView2);
            cameraButton = itemView.findViewById(R.id.imageView4);
            cameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mCon, CameraMiddleManActivity.class);
                    startActivity(mCon, intent, null);
                     //mImageUri = MainActivity.uploadCamera();

                    //Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //if (takePictureIntent.resolveActivity(getPackageManager())
                    //startActivity(mCon, intent, null);
                    /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivity(mCon, intent, null);
                    //intent.putExtra(MediaStore.ACTION_IMAGE_CAPTURE, mImageUri);
                    mImageUri = intent.getData();
                    uploadFile()*/;

                }
            });
            noteTakeButton = itemView.findViewById(R.id.imageView5);
            noteTakeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCon, NoteTakerActivity.class);
                    startActivity(mCon, intent, null);
                }
            });
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = mCon.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            Toast.makeText(mCon, "Primljen URI", Toast.LENGTH_LONG).show();
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));
            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(mCon, "Snimanje uspjelo", Toast.LENGTH_LONG);
                    PhotoUpload upload = new PhotoUpload(System.currentTimeMillis() + "mill", taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                }
            });

        } else {
            Toast.makeText(mCon, "No file Selected", Toast.LENGTH_LONG).show();
        }


    }
}