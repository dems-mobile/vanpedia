package com.demsmobile.vanpedia.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diego on 2/3/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = MySQLiteHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "vanpedia.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES = new StringBuilder()
            .append("CREATE TABLE ").append(UserContract.TABLE_NAME).append("(")
            .append(UserContract._ID).append(" INTEGER PRIMARY KEY,")
            .append(UserContract.COLLUMN_USERNAME).append(" TEXT,")
            .append(UserContract.COLLUMN_EMAIL).append(" TEXT UNIQUE,")
//            .append(UserContract.COLLUMN_UUID).append(" TEXT,")
            .append(UserContract.COLLUMN_CREATED_AT).append(" TEXT, ")
            .append(UserContract.COLLUMN_UPDATED_AT).append(" TEXT")
            .append(");")
            .toString();

    private static final String SQL_DELETE_ENTRIES = new StringBuilder()
            .append("DROP TABLE IF EXISTS ").append(UserContract.TABLE_NAME).append(";")
            .toString();

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
