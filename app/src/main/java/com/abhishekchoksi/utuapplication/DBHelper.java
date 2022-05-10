package com.abhishekchoksi.utuapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE 'tblstudent' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'name' varchar(20) NOT NULL, 'email'	varchar(30) NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS 'tblstudent'");
    }

    public void addStudent(String name, String email){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);

        this.getWritableDatabase().insertOrThrow("tblstudent","",values);
    }

    // displayAllStudents In ListView
    public ArrayList displayAllStudent()
    {
        ArrayList list = new ArrayList();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM tblStudent",null);
        while(cursor.moveToNext())
        {
            list.add(cursor.getInt(0) + "," + cursor.getString(1) + "," + cursor.getString(2));
        }
        return list;
    }

    //displayAllStudents in Text View
    public void displayAllStudents(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM tblStudent",null);
        while(cursor.moveToNext()){
            textView.append("\nID:\t" + cursor.getInt(0) + "\t\tName:\t" + cursor.getString(1) + "\t\tEmail:\t" + cursor.getString(2));
        }
    }

   // displayStudent In Spinner
    public ArrayList displayStudentInSpinner()
    {
        ArrayList list = new ArrayList();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT email FROM tblStudent",null);
        while(cursor.moveToNext())
        {
            list.add(cursor.getString(0));
        }
        return list;
    }

    //displayStudent By Email ID
    public ArrayList displayStudentsByEmail(String email)
    {
        ArrayList list = new ArrayList();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM tblStudent WHERE email='"+email+"'",null);
        while(cursor.moveToNext())
        {
            list.add("\nID:\t" + cursor.getInt(0) + "\t\tName:\t" + cursor.getString(1) + "\t\tEmail:\t" + cursor.getString(2));
        }
        return list;
    }

    //displayStudent By Email ID For Edit
    public Cursor displayStudentsByEmailEdit(String email)
    {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM tblStudent WHERE email='"+email+"'",null);
        return cursor;
    }

    //Update Student Details
    public void updateStudentByEmail(String name,String newEmail,String oldEmail){
        this.getWritableDatabase().execSQL("UPDATE tblStudent SET name='"+ name +"',email='" + newEmail + "' WHERE email='"+ oldEmail +"'");
    }

    //Delete Student
    public void deletStudent(String email){
        //this.getWritableDatabase().delete("tblStudent","email='"+email+"'",null);
        this.getWritableDatabase().execSQL("DELETE FROM tblStudent WHERE email='"+ email +"'");
    }
}
