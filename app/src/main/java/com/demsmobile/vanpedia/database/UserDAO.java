package com.demsmobile.vanpedia.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Diego on 2/3/2016.
 */
public class UserDAO implements DAO {

    private static final String TAG = UserDAO.class.getSimpleName();
    public static final String TABLE_NAME = "user";
    private static final String TABLE_USERNAME = "username";
    private static final String TABLE_EMAIL = "email";
    private MySQLiteHelper dbHelper;

    public String getCreationStatement(){
        StringBuilder sqlTableCreation = new StringBuilder();
        sqlTableCreation.append("CREATE TABLE ").append(TABLE_NAME).append("(")
                .append(TABLE_PK).append(" INTEGER PRIMARY KEY,")
                .append(TABLE_USERNAME).append(" TEXT,")
                .append(TABLE_EMAIL).append(" TEXT UNIQUE,")
                .append(TABLE_UUID).append(" TEXT,")
                .append(TABLE_CREATED_AT).append(" TEXT, ")
                .append(TABLE_UPDATED_AT).append(" TEXT")
                .append(");");
        return sqlTableCreation.toString();
    }

    public String getTableName(){
        return  TABLE_NAME;
    }

    public UserDAO(MySQLiteHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void register(){
        dbHelper.addTable(this);
    }

    public long createUser(String name, String email, String uid, String created_at) {
        ContentValues values = new ContentValues();
        values.put(TABLE_USERNAME, name);
        values.put(TABLE_EMAIL, email);
        values.put(TABLE_UUID, uid);
        values.put(TABLE_CREATED_AT, created_at);

        return dbHelper.insert(TABLE_NAME, values);
    }

    public void deleteUsers() {
        dbHelper.clearTable(TABLE_NAME);
    }
}
