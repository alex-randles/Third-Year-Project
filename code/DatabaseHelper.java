package coogans2.dcu.ie.wordnu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wonde on 28/02/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database name
    public static final String DATABASE_NAME = "WORDUP.DB";
    // Table 1 variables
    public static final String TABLE_1 = "carer_table";
    public static final String CARER_1 = "USERNAME";
    public static final String CARER_2 = "PASSWORD";
    // Table 2 variables
    public static final String TABLE_2 = "patient_table";
    public static final String PATIENT_1 = "PNAME";
    public static final String PATIENT_2 = "LANGUAGE";
    public static final String PATIENT_3 = "CAPTIONS";
    public static final String PATIENT_4 = "CARER";
    public static final String PATIENT_5 = "AVATAR";
    // Create Database
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
    // Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_1 + " (USERNAME TEXT PRIMARY KEY, PASSWORD TEXT)");
        db.execSQL("create table " + TABLE_2 + " (PNAME TEXT, LANGUAGE TEXT, CAPTIONS TEXT, CARER TEXT, AVATAR TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_2);
        onCreate(db);
    }
    // Insert carer into table 1
    public boolean insertCarer(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARER_1,username);
        contentValues.put(CARER_2,password);
        long result = db.insert(TABLE_1,null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
    // insert patient, check if already exists
    public boolean insertPatient(String pname, String language, String captions, String carer){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase rb = this.getReadableDatabase();
        String query = "Select * FROM patient_table WHERE PNAME = '" +pname+ "' AND CARER = '" +carer+"'";
        Cursor cursor = rb.rawQuery(query,null);
        if(cursor.getCount() > 0){
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_1, pname);
        contentValues.put(PATIENT_2, language);
        contentValues.put(PATIENT_3, captions);
        contentValues.put(PATIENT_4, carer);
        contentValues.put(PATIENT_5, "wordup");
        long result = db.insert(TABLE_2,null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
    // update patient settings
    public boolean updatePatient(String pname, String language, String captions, String carer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_1, pname);
        contentValues.put(PATIENT_2, language);
        contentValues.put(PATIENT_3, captions);
        contentValues.put(PATIENT_4, carer);
        contentValues.put(PATIENT_5, "wordup");
        long result = db.update(TABLE_2,contentValues,"PNAME = ? AND CARER = ?",new String[]{pname,carer});

        if(result == -1){
            return false;
        }else{
            return true;
        }
        //long result = db.update(TABLE_2,contentValues,carer==equals(new String[]{carer}));
    }

    // update patient avatar
    public void updateAvatar(String pname, String carer, String avatar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_5, avatar);
        db.update(TABLE_2,contentValues,"PNAME = ? AND CARER = ?",new String[]{pname,carer});
    }

    // retrieve avatar
    public String getAvatar(String pname, String carer){
        String avatar = "wordup";
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT AVATAR FROM patient_table WHERE PNAME = '" +pname+ "' AND CARER = '" +carer+ "'";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            do {
                avatar = cursor.getString(cursor.getColumnIndex("AVATAR"));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning av
        return avatar;
    }


    // retrive patients
    public List<String> getLabels(String cname){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT PNAME FROM patient_table WHERE CARER = '" + cname + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
    // retrive patient settings
    public List<String> getSettings(String pname){
        List<String> settings = new ArrayList<>();

        String selectQuery = "SELECT * FROM patient_table WHERE PNAME = '" + pname + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                settings.add(cursor.getString(1));
                settings.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // returning lables
        return settings;
    }

    // CHECK if username and pw match
    public boolean login(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT PASSWORD FROM carer_table WHERE USERNAME = '" + username + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }

        if (password.equals(cursor.getString(0))){
            cursor.close();
            db.close();
            return true;
        }else {
            cursor.close();
            db.close();
            return false;
        }
    }
    // delete patient
    public Integer deletePat(String pname){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("patient_table", "PNAME = ?", new String[] {pname});
    }

} // end o class
