package com.sparta.jn;

import com.sparta.jn.pojo.AddPlace;
import com.sparta.jn.pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayLoad() {
        AddPlace p =new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline house");
        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l =new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        return p;
    }
}
