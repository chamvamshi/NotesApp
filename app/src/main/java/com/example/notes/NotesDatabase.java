package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NotesDatabase extends SQLiteOpenHelper {

    public static final int DB_VERSION = 2;
    public static String DB_NAME = "NotesDB.db";
    public static String DB_TABLE = "NotesTAble";

    public static String COLUMN_ID = "NotesId";
    public static String COLUMN_TITLE = "NotesTitle";
    public static String COLUMN_DETAILS = "NotesDetails";
    public static String COLUMN_DATE = "NotesDate";
    public static String COLUMN_TIME = "NotesTime";

    public NotesDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query = "CREATE TABLE " + DB_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DETAILS + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT " + ")";

        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i > i1)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);

    }

    public long AddNote(NotesModel notesModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, notesModel.getNotetitle());
        contentValues.put(COLUMN_DETAILS, notesModel.getNoteDetails());
        contentValues.put(COLUMN_DATE, notesModel.getNoteDate());
        contentValues.put(COLUMN_TIME, notesModel.getNoteTime());

        long ID = db.insert(DB_TABLE, null, contentValues);
        Log.d("Inserted", "AddNote: " + ID);
        return ID;
    }

    public List<NotesModel> getNote() {
        String TAG = "abc";
        SQLiteDatabase db = this.getReadableDatabase();
        List<NotesModel> allnote = new ArrayList<NotesModel>();

        String queryStatement = " SELECT  * FROM " + DB_TABLE;
        Cursor cursor = db.rawQuery(queryStatement, null);

        if (cursor.moveToFirst()) {
            do {

                NotesModel notesModel = new NotesModel();
                notesModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                notesModel.setNotetitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                notesModel.setNoteDetails(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS)));
                notesModel.setNoteDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                notesModel.setNoteTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
                allnote.add(notesModel);
            } while (cursor.moveToNext());
        }
        return allnote;
    }

    public NotesModel getNotes(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] query = new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_DETAILS, COLUMN_DATE, COLUMN_TIME};
        Cursor cursor = db.query(DB_TABLE, query, COLUMN_ID + " =? ", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return new NotesModel(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
    }
    void deleteNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(DB_TABLE, COLUMN_ID + " =? ", new String[]{String.valueOf(id)});
        db.close();
    }
}

