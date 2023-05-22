package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class DetailsActivity extends AppCompatActivity {

    TextView showtitle,showDetails;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        showtitle = findViewById(R.id.showTitle);
        showDetails = findViewById(R.id.showDetails);

        NotesDatabase db = new NotesDatabase(this);
        Intent intent = getIntent();

        id= intent.getIntExtra("10",0);

        NotesModel notesModel = db.getNotes(id);

        showtitle.setText(notesModel.getNotetitle());
        showDetails.setText(notesModel.getNoteDetails());
        Toast.makeText(getApplicationContext(), "id"+notesModel.getId(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        if (item.getItemId() == R.id.delete){
            NotesDatabase db = new NotesDatabase(this);
            Intent intent = getIntent();
            id= intent.getIntExtra("ID",0);
            db.deleteNote(id);
            Toast.makeText(getApplicationContext(), "note deleted", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(DetailsActivity.this,MainActivity.class);
            startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }
}