package com.example.facebook.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.facebook.entities.StudentList;

import java.util.ArrayList;
import java.util.List;

public class myDataBase extends SQLiteOpenHelper {

    public  static final String DB_NAME = "fb_database";
    public static final int DB_VERSION = 1;

    private static final String STUDENT_TABLE = "student";

    //private static final String STUDENT_ID = "id";
    private static final String STUDENT_NAME = "name";
    private static final String STUDENT_AGE = "age";
    private static final String STUDENT_MARKS = "marks";

    private static final String CREATE_STUDENT_TABLE = "CREATE TABLE "+STUDENT_TABLE+
            "("+STUDENT_NAME+" VARCHAR(20),"+
            STUDENT_AGE+" INT,"+
            STUDENT_MARKS+" INT);";

    private static final String SELECT_STUDENT = "SELECT * FROM  "+STUDENT_TABLE+";";

    public myDataBase(@Nullable Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_TABLE);
        Log.d("cham","onCreat: "+"Database created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_STUDENT_TABLE);
        onCreate(db);
    }

    public long insertStudent(String name,int age,int marks){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO "+STUDENT_TABLE+" VALUES ("+
                name+","+age+","+marks+");");
        getAllStudent();
        db.close();
        return 0;
    }

    public void getAllStudent(){
        SQLiteDatabase db = getWritableDatabase();
        //java vala result(rs) eka android wala cursor
        Cursor cursor = db.rawQuery(SELECT_STUDENT,null);

        if (cursor.moveToFirst()){
            do{
               Log.d("cham", "getAllStudent: "+
                       "Name:"+cursor.getString(cursor.getColumnIndex(STUDENT_NAME))+
                       "Age:"+cursor.getInt(cursor.getColumnIndex(STUDENT_AGE))+
                       "Marks:"+cursor.getInt(cursor.getColumnIndex(STUDENT_NAME)));
            }while (cursor.moveToNext());
        }
    }

//    public Cursor getWordMatches(String query, String[] columns){
//            String search = STUDENT_NAME + " MATCH ?";
//            String[] searchArgs = new String[] {query+"*"};
//
//            return query(search, searchArgs, columns);
//    }

//    public Cursor query(String search, String[] searchArg, String[] columns){
//        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//        queryBuilder.setTables(STUDENT_TABLE);
//
//        Cursor read = queryBuilder.query(databaseOpenHelper.get)
//    }

    public List<StudentList> search(String keyword){

        List<StudentList> studentList = null;
        try{
            SQLiteDatabase database = getReadableDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM "+STUDENT_TABLE+" WHERE "+STUDENT_NAME+" LIKE ?",new String[] {"%"+keyword+"%"});
            if (cursor.moveToFirst()) {
                studentList = new ArrayList<StudentList>();
                do{
                    StudentList studentList1 = new StudentList();
                    studentList1.setName(cursor.getString(1));
                    studentList1.setAge(cursor.getString(2));
                    studentList1.setMarks(cursor.getString(3));
                    studentList.add(studentList1);
                } while(cursor.moveToNext());
                }
            }
        catch (Exception e){
            studentList = null;
        }
            return studentList;
        }
    }

