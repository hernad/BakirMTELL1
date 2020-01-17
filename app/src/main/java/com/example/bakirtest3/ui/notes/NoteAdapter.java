package com.example.bakirtest3.ui.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakirtest3.R;
import com.example.bakirtest3.ui.NotesUpload;
import com.example.bakirtest3.ui.gallery.ImageAdapter;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private Context mCon;
    private List<NotesUpload> notesUploadList;

    public NoteAdapter(Context mCon, List<NotesUpload> notesUploadList) {
        this.mCon = mCon;
        this.notesUploadList = notesUploadList;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mCon).inflate(R.layout.notes_fragment, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NotesUpload uploadCurrent = notesUploadList.get(position);
    }


    @Override
    public int getItemCount() {
        return notesUploadList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView txtView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.note_text);
        }
    }


}
