package com.demsmobile.vanpedia.service;

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

    public void setSubCategoryName(String id){
        this.subCategoryName=id;
    }

    public String getSubCategoryName(){
        return this.subCategoryName;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
