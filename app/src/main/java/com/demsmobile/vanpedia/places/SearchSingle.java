package com.demsmobile.vanpedia.places;

/**
 * Created by Michelle on 2016-02-23.
 */
public class SearchSingle {

        String ch;
        private static SearchSingle uniqueInstance;

        public static SearchSingle getInstance() {
            if(uniqueInstance == null) {
                uniqueInstance = new SearchSingle();
            }
            return uniqueInstance;
        }
        public String getter()
        {
            return ch;
        }
        public void setter(String s)
        {
            ch=s;

        }

}
