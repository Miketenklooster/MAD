package com.example.noteblock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TableControllerNote extends DatabaseHandler {

    public TableControllerNote(Context context){
        super(context);
    }
    public boolean create(ObjectNote objectNote) {
        ContentValues values = new ContentValues();
        values.put("note", objectNote.note);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessful = db.insert("notes", null, values) > 0;
        db.close();
        return createSuccessful;
    }
    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM notes";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }


}
