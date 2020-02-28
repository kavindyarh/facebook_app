import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper{



    //DB NAME N VERSION
    public static final String DB_NAME = "fb_database";
    public static final int DB_VERSION = 1;

    //STUDENT TABLE N COLUMNS
    private static final String STUDENT_TABLE = "student";

    private static final String STUDENT_NAME = "name";
    private static final String STUDENT_AGE = "age";
    private static final String STUDENT_MARKS = "marks";

    private static

    public MyDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    public boolean insertData(String Name,String Age,String Marks){
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues contentValues=new ContentValues();
            contentValues.put(u_name,Name);
            contentValues.put(u_age,Age);
            contentValues.put(u_marks,Marks);
            long result=db.insert(TABLE_NAME,null,contentValues);
            if (result== -1)
                return true;
        }
        return false;
    }

    public Cursor getAllRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resultSet = db.rawQuery("select * from student",null);
        return  resultSet;
    }

    }
}

