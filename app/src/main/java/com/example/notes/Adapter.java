package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<NotesModel> notesModels;

    Adapter(Context context, List<NotesModel> notesModels) {
        this.inflater = LayoutInflater.from(context);
        this.notesModels = notesModels;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note_view, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        String title = notesModels.get(position).getNotetitle();
        Log.d("tit", "onBindViewHolder: title"+title);
        String Date = notesModels.get(position).getNoteDate();
        Log.d("date", "onBindViewHolder: Date"+Date);
        String time = notesModels.get(position).getNoteTime();
        Log.d("time", "onBindViewHolder: time"+time);

        holder.nTitle.setText(title);
        holder.nDate.setText(Date);
        holder.nTime.setText(time);

    }

    @Override
    public int getItemCount() {
        return notesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nTitle, nDate, nTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.ntitle);
            nTime = itemView.findViewById(R.id.nTime);
            nDate = itemView.findViewById(R.id.nDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                    intent.putExtra("ID", notesModels.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
