package com.demsmobile.vanpedia.service;

import java.util.HashMap;
import java.util.Map;

public class Globals{
    private static Globals instance;

    // Global variable
    private String categoryName;
    private String subCategoryName;

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
        keywords.put("Rent", "rent condo|rent apartment|rent bedroom|rent room");
        keywords.put("Fine", "restaurant fine");
        keywords.put("Casual", "restaurant casual");
        keywords.put("Pub", "restaurant pub");
        keywords.put("Breakfast", "restaurant breakfast");
        keywords.put("Bistro", "restaurant bistro");
        keywords.put("Coffee", "restaurant coffee");
        keywords.put("Concert", "show concert");
        keywords.put("Night Life", "show night");
        keywords.put("Beach", "beach lake");
        keywords.put("Sport", "sport");
        keywords.put("Bike", "sport bike|rental bike");
        keywords.put("Hike", "sport hike");
        keywords.put("Mountain", "sky resort");
    }
}
