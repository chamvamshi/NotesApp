package com.example.notes;

public class NotesModel {
    int id;
    String Notetitle;
    String NoteDetails;
    String NoteDate;
    String NoteTime;

    public NotesModel() {
        this.Notetitle = notetitle;
        this.NoteDetails = noteDetails;
        this.NoteDate = noteDate;
        this.NoteTime = noteTime;
    }

    public NotesModel(int id, String notetitle, String noteDetails, String noteDate, String noteTime) {
        this.id = id;
        this.Notetitle = notetitle;
        this.NoteDetails = noteDetails;
        this.NoteDate = noteDate;
        this.NoteTime = noteTime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotetitle() {
        return Notetitle;
    }

    public void setNotetitle(String notetitle) {
        Notetitle = notetitle;
    }

    public String getNoteDetails() {
        return NoteDetails;
    }

    public void setNoteDetails(String noteDetails) {
        NoteDetails = noteDetails;
    }

    public String getNoteDate() {
        return NoteDate;
    }

    public void setNoteDate(String noteDate) {
        NoteDate = noteDate;
    }

    public String getNoteTime() {
        return NoteTime;
    }

    public void setNoteTime(String noteTime) {
        NoteTime = noteTime;
    }
}
