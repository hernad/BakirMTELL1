package com.example.bakirtest3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakirtest3.R;
import com.example.bakirtest3.ui.Subject;
import com.example.bakirtest3.ui.SubjectAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView;
    private SubjectAdapter adapter;
    private List<Subject> subjectList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //recyclerviewStuff
        subjectList = new ArrayList<Subject>();
        recyclerView = view.findViewById(R.id.recyclerView);

        subjectList.add(new Subject("Hemija"));
        subjectList.add(new Subject("Matematika"));
        subjectList.add(new Subject("Bosanski"));
        subjectList.add(new Subject("Engleski"));
        subjectList.add(new Subject("Biologija"));
        subjectList.add(new Subject("Fizika"));
        subjectList.add(new Subject("Psihologija"));

        adapter = new SubjectAdapter(getContext(), subjectList);
        recyclerView.setAdapter(adapter);
    }
}