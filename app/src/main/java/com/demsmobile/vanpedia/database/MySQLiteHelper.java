package com.demsmobile.vanpedia.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Diego on 2/3/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = MySQLiteHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "vanpedia.db";
    private static final int DATABASE_VERSION = 1;

    private List<DAO> tables = new ArrayList<DAO>();

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder createTableSqlStatements = new StringBuilder();

        Iterator<DAO> ite = tables.iterator();
        DAO table;
        while (ite.hasNext()) {
            table = ite.next();
            createTableSqlStatements.append(table.getCreationStatement());
        }

        db.execSQL(createTableSqlStatements.toString());

        Log.d(TAG, "Database tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder dropStatement = new StringBuilder();
        Iterator<DAO> ite = tables.iterator();
        DAO table;
        while (ite.hasNext()) {
            table = ite.next();
            dropStatement.append("DROP TABLE IF EXISTS ")
                    .append(table.getTableName())
                    .append(";");
        }
        db.execSQL(dropStatement.toString());

        onCreate(db);
    }

    public void addTable(DAO table){
        tables.add(table);
    }

    public long insert(String tableName, ContentValues values) {
        SQLiteDatabase db = open();

        long id = db.insert(tableName, null, values);
        db.close();

        Log.d(TAG, "New user inserted into sqlite: " + id);

        return id;
    }

    public void clearTable(String tableName) {
        SQLiteDatabase db = open();
        db.delete(tableName, null, null);
        db.close();

        Log.d(TAG, "Deleted all register from table " + tableName);
    }

    public SQLiteDatabase open() throws SQLException {
        return getWritableDatabase();
    }
}
