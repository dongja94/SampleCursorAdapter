package com.begentgroup.samplecursoradapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dongja94 on 2015-09-16.
 */
public class DataManager extends SQLiteOpenHelper {

    private static DataManager instance;
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;

    public DataManager() {
        super(MyApplication.getContext(), DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + Constants.MessageTable.TABLE_NAME + "(" +
                Constants.MessageTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.MessageTable.FIELD_MATE + " TEXT," +
                Constants.MessageTable.FIELD_MESSAGE + " TEXT," +
                Constants.MessageTable.FIELD_TYPE + " INTEGER);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertMessage(String mate, String message, int type) {
        ContentValues values = new ContentValues();
        values.put(Constants.MessageTable.FIELD_MATE, mate);
        values.put(Constants.MessageTable.FIELD_MESSAGE, message);
        values.put(Constants.MessageTable.FIELD_TYPE,type);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Constants.MessageTable.TABLE_NAME, null, values);
    }

    public Cursor getMessageCursor() {
        String[] columns = {Constants.MessageTable._ID, Constants.MessageTable.FIELD_MATE, Constants.MessageTable.FIELD_MESSAGE, Constants.MessageTable.FIELD_TYPE};
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Constants.MessageTable.TABLE_NAME,columns, null, null, null, null, null);
    }
}
