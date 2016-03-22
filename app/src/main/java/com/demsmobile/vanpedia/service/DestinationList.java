package com.demsmobile.vanpedia.service;

import java.util.ArrayList;

/**
 * Created by Michelle on 2016-03-21.
 */
public class DestinationList {

    //private static DestinationList instance;

    //public DestinationList(){}
    
    public ArrayList<Destination> topD;

    //  3 images per destination        0            1                   2                   3                   4
    String[][] destImg= {{"d00","d01","d02"},{"d10","d11","d12"},{"d20","d21","d22"},{"d30","d31","d32"},{"d40","d41","d42"}};

    String d0 = "Stanley Park is a 1,001-acre public park that borders the downtown of Vancouver in British Columbia, " +
            "Canada and is almost entirely surrounded by waters of Vancouver Harbour and English Bay.The park has a long " +
            "history and was one of the first areas to be explored in the city.";
    String d1 = "Vancouver is a science centre run by a not-for-profit organization in Vancouver, British Columbia," +
            " Canada. It is located at the end of False Creek, and features many permanent interactive exhibits and displays, " +
            "as well as areas with varying topics throughout the years";
    String d2 ="Grouse Mountain is one of the North Shore Mountains of the Pacific Ranges in the District Municipality of North Vancouver, " +
            "British Columbia, Canada. Exceeding 1,200 m (4,000 feet) in altitude at its peak, is the site of an alpine ski area, Grouse Mountain" +
            " Resort, which in the winter season overlooks Greater Vancouver with four chairlifts servicing 26 runs. In the summer, Grouse " +
            "Mountain Resort features lumberjack shows, a birds of prey wildlife demonstration, a scenic chairlift ride, and a 2.9 km";
    String d3 = "The Capilano Suspension Bridge is a simple suspension bridge crossing the Capilano River in the District of North Vancouver, " +
            "British Columbia, Canada. The current bridge is 140 metres (460 ft)[1] long and 70 metres (230 ft) above the river. It is part of " +
            "a private facility, with an admission fee, and draws over 800,000 visitors a year.";
    String d4 = "The Vancouver Art Gallery (VAG) is the fifth-largest art gallery in Canada and the largest in Western Canada. " +
            "It is located at 750 Hornby Street in Vancouver, British Columbia. Its permanent collection of about 11,000 artworks includes" +
            " more than 200 major works by Emily Carr, the Group of Seven, Jeff Wall, Harry Callahan and Marc Chagall.";

//    public static synchronized DestinationList getInstance(){
//        if(instance==null){
//            instance=new DestinationList();
//        }
//        return instance;
//    }

    public void createList(){

        topD.add(new Destination("Stanley Park", "735 Stanley Park Drive, Stanley Park, Vancouver, BC V6C 2T1","604-681-5115","www.stanleypark.com",d0, destImg[0]));
        topD.add(new Destination("Science World", "1455 Quebec St, Vancouver, BC V6A 3Z7","604-443-7440","www.scienceworld.ca",d1, destImg[1]));
        topD.add(new Destination("Grouse Mountain","6400 Nancy Greene Way North Vancouver, BC V7R 4K9","604-980-9311","www.grousemountain.com",d2,destImg[2]));
        topD.add(new Destination("Capilano Suspension Bridge","3735 Capilano Road North Vancouver, BC Canada V7R 4J1","604-985-7474","www.capbridge.com",d3,destImg[3]));
        topD.add(new Destination("Vancouver Art Gallery","750 Hornby Street Vancouver, British Columbia Canada V6Z 2H7","604-662-4719","www.vanartgallery.bc.ca",d4,destImg[4]));

        Globals g = Globals.getInstance();
        g.setDestList(topD);
    }

}
