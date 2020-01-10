package com.example.bakirtest3.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.bakirtest3.R;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>{

    private Context mCon;
    private List<Subject> subjectList;

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

    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public class SubjectViewHolder extends ViewHolder{

        TextView nameOfSubjectText;

        public SubjectViewHolder(View itemView) {
            super(itemView);

            nameOfSubjectText = itemView.findViewById(R.id.textView2);
        }
    }

}
