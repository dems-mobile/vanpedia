package com.demsmobile.vanpedia.database;

import android.provider.BaseColumns;

/**
 * Created by Diego on 2/3/2016.
 */
public interface DataAccessObject extends BaseColumns {

//    public static final String TABLE_PK = "id";
//    public static final String COLLUMN_UUID = "uuid"; // not sure if needed
    public static final String COLLUMN_CREATED_AT = "created_at";
    public static final String COLLUMN_UPDATED_AT = "updated_at";

}
