package com.example.bakirtest3.ui.notetake;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bakirtest3.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class NoteTakerActivity extends AppCompatActivity {

    private Button btn;
    private EditText note;
    private StorageReference mStorage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_taker_fragment);

        mStorage = FirebaseStorage.getInstance().getReference("Hemija/Hemija-Notes");

        btn = findViewById(R.id.button);
        note = findViewById(R.id.note_text);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
