package com.example.bakirtest3.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakirtest3.R;
import com.example.bakirtest3.ui.Subject;
import com.example.bakirtest3.ui.SubjectAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView;
    private SubjectAdapter adapter;
    public List<Subject> subjectList;
    private DatabaseReference dataRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //recyclerviewStuff
        subjectList = new ArrayList<Subject>();
        recyclerView = view.findViewById(R.id.recyclerView);
        dataRef = FirebaseDatabase.getInstance().getReference("/Subjects");
            dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.getKey();
                subjectList.add(new Subject(name));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        subjectList.add(new Subject("Hemija"));
        subjectList.add(new Subject("Matematika"));
        subjectList.add(new Subject("Maternji"));
        subjectList.add(new Subject("Engleski"));
        subjectList.add(new Subject("Biologija"));
        subjectList.add(new Subject("Fizika"));
        subjectList.add(new Subject("Psihologija"));

        adapter = new SubjectAdapter(getContext(), subjectList);
        recyclerView.setAdapter(adapter);
    }
}