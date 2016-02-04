package com.demsmobile.vanpedia.database;

/**
 * Created by Diego on 2/3/2016.
 */
public interface DAO {

    public static final String TABLE_PK = "id";
    public static final String TABLE_UUID = "uuid"; // not sure if needed
    public static final String TABLE_CREATED_AT = "created_at";
    public static final String TABLE_UPDATED_AT = "updated_at";

    public void register();
    public String getCreationStatement();
    public String getTableName();
}
