package com.example.christoffer.fisherfriend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by christoffer on 2016-03-16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 20;
    private static final String DATABASE_NAME = "fiskstore";
    private static final String TABLE_FISKAR = "fiskar";
    private static final String KEY_ID = "id";
    private static final String KEY_ART = "art";
    private static final String KEY_LANGD = "langd";
    private static final String KEY_VIKT = "vikt";
    private static final String KEY_PLATS = "plats";
    private static final String KEY_DATUM = "datum";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FISKAR_TABLE = "CREATE TABLE " + TABLE_FISKAR + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ART + " VARCHAR(225),"
                + KEY_LANGD + " INTEGER," + KEY_VIKT + " INTEGER," + KEY_PLATS + " VARCHAR(225)," + KEY_DATUM + " VARCHAR(225)" + ")";
        db.execSQL(CREATE_FISKAR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FISKAR);
        onCreate(db);
    }

    void addFisk(String a, String l, String v, String p, String d) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ART, a);
        values.put(KEY_LANGD, l);
        values.put(KEY_VIKT, v);
        values.put(KEY_PLATS, p);
        values.put(KEY_DATUM, d);

        db.insert(TABLE_FISKAR, null, values);
        db.close();
    }

    public String getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {KEY_ID, KEY_ART, KEY_LANGD, KEY_VIKT, KEY_PLATS, KEY_DATUM};
        Cursor cursor = db.query(TABLE_FISKAR, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String fiskart = cursor.getString(1);
            int langd = cursor.getInt(2);
            int vikt = cursor.getInt(3);
            String plats = cursor.getString(4);
            String datum = cursor.getString(5);
            buffer.append(id + ".  " + fiskart + " " + langd + "cm,  " + vikt + "g,  " + plats + ", " + datum + "\n");
        }
        return buffer.toString();
    }

    public String getSort() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_FISKAR + " ORDER BY " + KEY_LANGD + " DESC LIMIT 5";
        Cursor cursor = db.rawQuery(selectQuery, null);
        StringBuffer buffer = new StringBuffer();
        int i = 0;

        while (cursor.moveToNext()) {
            int langd = cursor.getInt(2);
            i = i + 1;
            buffer.append(i + ". " + langd + "cm " + "\n");
        }
        return buffer.toString();

    }

    public String getSum() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_FISKAR + " ORDER BY " + KEY_LANGD + " DESC LIMIT 5";
        Cursor cursor = db.rawQuery(selectQuery, null);
        StringBuffer buffer = new StringBuffer();
        int sum = 0;

        while (cursor.moveToNext()) {
            int langd = cursor.getInt(2);
            sum = langd + sum;
        }
        buffer.append(sum + "cm");
        return buffer.toString();
    }

    public ArrayList<String> getid() {
        ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from fiskar  ", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            array_list.add(res.getString(res.getColumnIndex(KEY_ID)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getArt() {
        ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from fiskar  ", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            array_list.add(res.getString(res.getColumnIndex(KEY_ART)));
            res.moveToNext();
        }
        return array_list;
    }


    public ArrayList<String> getLangd() {
        ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from fiskar  ", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            array_list.add(res.getString(res.getColumnIndex(KEY_LANGD)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getVikt() {
        ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from fiskar  ", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            array_list.add(res.getString(res.getColumnIndex(KEY_VIKT)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getPlats() {
        ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from fiskar  ", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            array_list.add(res.getString(res.getColumnIndex(KEY_PLATS)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getDatum() {
        ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from fiskar  ", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            array_list.add(res.getString(res.getColumnIndex(KEY_DATUM)));
            res.moveToNext();
        }
        return array_list;
    }


    public boolean deleteRow(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_FISKAR, KEY_ID + "=" + id, null) != 0;
    }

    public void deletall() {
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ID);
        if (c.moveToFirst()) {
            do {
                deleteRow(c.getInt((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }

    public Cursor getAllRows() {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = null;
        String[] columns = {KEY_ID, KEY_ART, KEY_LANGD, KEY_VIKT, KEY_PLATS};
        Cursor c = db.query(TABLE_FISKAR, columns, where, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }


}
