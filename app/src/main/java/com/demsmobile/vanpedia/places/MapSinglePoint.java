package com.demsmobile.vanpedia.places;

/**
 * Created by Michelle on 2016-02-23.
 */
public class MapSinglePoint {

    String lat;
    String log;
    private static MapSinglePoint uniqueInstance;

    public static MapSinglePoint getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new MapSinglePoint();
        }
        return uniqueInstance;
    }
    public String getlat()
    {
        return lat;
    }
    public void setlat(String s)
    {
        lat = s;

    }
    public String getlog()
    {
        return log;
    }

    public void setlog(String s)
    {
        log = s;

    }

}
