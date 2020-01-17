package com.example.bakirtest3.ui.notetake;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bakirtest3.R;
import com.example.bakirtest3.ui.home.HomeFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class NoteTakerActivity extends AppCompatActivity {

    private Button btn;
    private EditText note;
    private DatabaseReference mDataRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_taker_fragment);
        String pathName = "Hemija/Notes";
        mDataRef = FirebaseDatabase.getInstance().getReference(pathName);

        btn = findViewById(R.id.button);
        note = findViewById(R.id.note_text);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNote(String.valueOf(note.getText()));

            }
        });
    }
    private void createNote(String content) {
                mDataRef.child("note" + System.currentTimeMillis()).setValue(content);
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
    }
}
