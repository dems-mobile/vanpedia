package com.demsmobile.vanpedia.service;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Michelle on 2016-03-21.
 */
public class Destination {

    String dest_name;
    String dest_location;
    String phone;
    String email;
    String description;
    String [] images;

    public Destination(String n, String l, String p, String e, String d, String [] img ){

        dest_name = n;
        dest_location = l;
        phone = p;
        email = e;
        description = d;
        images = img;
    }

    //get
    public String dest_name(){
        return dest_name;
    }
    public String dest_location(){
        return dest_location;
    }
    public String phone(){
        return phone;
    }
    public String email(){
        return email;
    }
    public String description(){
        return description;
    }
    public String[] images(String[] array) {
        return array;
    }

    //set
    public void setDest_name(String n){
        dest_name = n;
    }
    public void setDest_location(String l){
        dest_location = l;
    }
    public void setPhone(String p){
        phone = p;
    }
    public void setEmail(String e){
        email = e;
    }
    public void setDescription(String d){
        description = d;
    }
    public void setImages(String [] img){images = img;
    }


}
