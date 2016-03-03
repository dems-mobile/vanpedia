package com.demsmobile.vanpedia.service;

public class Globals{
    private static Globals instance;

    // Global variable
    private String categoryName;
    private String subCategoryName;
    private String searchKeys;

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

    public void setSearchKeys(String keys){
        this.searchKeys=keys;
    }
    public String getSearchKeys(){
        return this.searchKeys;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
