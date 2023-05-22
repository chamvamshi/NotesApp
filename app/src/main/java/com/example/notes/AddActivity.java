package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    EditText title, details;
    Button add;
    String todaydate, currentTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("Add new Note ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.notetitle);
        details = findViewById(R.id.notedetails);
        add = findViewById(R.id.noteadd);

        calendar = Calendar.getInstance();
        todaydate = calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
        currentTime =pad(calendar.get(Calendar.HOUR) )+ ":" +pad(calendar.get(Calendar.MINUTE));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesModel notesModel = new NotesModel();
                NotesDatabase dbase = new NotesDatabase(AddActivity.this);
                dbase.AddNote(notesModel);

                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);

                Toast.makeText(AddActivity.this, "note saved ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public String pad ( int i){
        if (i < 0)
            return "0" + i;
        return String.valueOf(i);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home);{
            finish();
            return true;
        }
      // return super.onOptionsItemSelected(item);
    }
}
