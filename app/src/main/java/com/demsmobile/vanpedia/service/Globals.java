package com.demsmobile.vanpedia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Globals{
    private static Globals instance;

    // Global variable
    private Destination topPlaceToShow;
    private String categoryName;
    private String subCategoryName;
    private ArrayList<Destination> destList;

    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setCategoryName(String name){
        this.categoryName=name;
    }
    public String getCategoryName(){
        return this.categoryName;
    }

    public void setSubCategoryName(String subName){
        this.subCategoryName=subName;
    }
    public String getSubCategoryName(){
        return this.subCategoryName;
    }

    public String getSearchKeys(){
        return keywords.get(subCategoryName);
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }

    static Map<String, String> keywords = new HashMap<String, String>();

    static{
        keywords.put("Hotel", "hotel");
        keywords.put("B&B", "bed and breakfast|B&B");
        keywords.put("Hostel", "hostel");
        keywords.put("Rent", "condo for rent|apartment for rent|bedroom|room");
        keywords.put("Fine", "restaurant fine dinning");
        keywords.put("Casual", "restaurant casual dinning");
        keywords.put("Pub", "restaurant pub");
        keywords.put("Breakfast", "restaurant breakfast food");
        keywords.put("Bistro", "restaurant bistro");
        keywords.put("Coffee", "restaurant coffee shop");
        keywords.put("Concert", "entertainment concert");
        keywords.put("Night Life", "night club");
        keywords.put("Beach", "park|beach");
        keywords.put("Sport", "sporting events|stadium");
        keywords.put("Bike", "sport bike|rental bike");
        keywords.put("Hike", "hike|trail");
        keywords.put("Mountain", "ski|resort");
    }

    public void setDestList(ArrayList<Destination> destinationArr){
        this.destList=destinationArr;
    }
    public ArrayList<Destination> getDestList(){
        return destList;
    }

    public void setTopPlaceToShow(Destination d){
        topPlaceToShow = d;
    }
    public Destination getTopPlaceToShow(){
        return topPlaceToShow;
    }
}
