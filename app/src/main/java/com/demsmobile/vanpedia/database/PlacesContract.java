package com.demsmobile.vanpedia.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.demsmobile.vanpedia.places.Place;

import java.util.Calendar;

/**
 * Created by Diego on 3/21/2016.
 */
public class PlacesContract  implements DataAccessObject {

    private static final String TAG = UserContract.class.getSimpleName();

    public static final String TABLE_NAME = "places";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_REFERENCE = "reference";
    public static final String COLUMN_ICON = "icon";
    public static final String COLUMN_VICINITY = "vicinity";
    public static final String COLUMN_WEBSITE = "website";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LON = "log";
    public static final String COLUMN_ADDRESS = "formatted_address";
    public static final String COLUMN_PHONE = "formatted_phone_number";

    public MySQLiteHelper dbHelper;

    public PlacesContract(MySQLiteHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public long createPlace(Place place) {
        ContentValues values = new ContentValues();
        values.put(_ID, place.id);
        values.put(COLUMN_NAME, place.name);
        values.put(COLUMN_REFERENCE, place.reference);
        values.put(COLUMN_ICON, place.icon);
        values.put(COLUMN_VICINITY, place.vicinity);
        values.put(COLUMN_WEBSITE, place.website);
        values.put(COLUMN_LAT, place.geometry.location.lat);
        values.put(COLUMN_LON, place.geometry.location.lng);
        values.put(COLUMN_ADDRESS, place.formatted_address);
        values.put(COLUMN_PHONE, place.formatted_phone_number);

        values.put(COLUMN_CREATED_AT, Calendar.getInstance().getTimeInMillis());

        long result = dbHelper.getWritableDatabase().insert(TABLE_NAME, null, values);
        dbHelper.close();

        return result;
    }

    public Place findPlace(String id) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + _ID + " =  \"" + id + "\"";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, null);
        Place place = new Place();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            place.id = cursor.getString(0);
            place.name = cursor.getString(1);
            place.reference = cursor.getString(2);
            place.icon = cursor.getString(3);
            place.vicinity = cursor.getString(4);
            place.website = cursor.getString(5);
            Place.Geometry geometry = new Place.Geometry();
            geometry.location = new Place.Location();
            geometry.location.lat = Double.parseDouble(cursor.getString(6));
            geometry.location.lng = Double.parseDouble(cursor.getString(7));
            place.geometry = geometry;
            place.formatted_address = cursor.getString(8);
            place.formatted_phone_number = cursor.getString(9);

            cursor.close();
        } else {
            place = null;
        }
        dbHelper.close();

        return place;
    }

    public int deletePlace(Place place) {
        return dbHelper.getWritableDatabase().delete(TABLE_NAME,
                _ID + "= ? ",
                new String[]{place.id});

    }
}
