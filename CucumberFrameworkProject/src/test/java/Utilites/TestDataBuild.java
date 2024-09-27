package Utilites;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import Utilites.AddPlace;
import Utilites.Location;

public class TestDataBuild {

	
	public AddPlace addplace(String name, String lang, String address) {
		 AddPlace p = new AddPlace();
		 p.setAccuracy(70);
		 p.setAddress(address);
		 p.setLanguage(lang);
		 p.setPhone_number("(+91) 383 893 3937");
		 p.setWebsite("https://rahulshettyacademy.com");
		 p.setName(name);
		 List<String> myList =new ArrayList<String>();
		 myList.add("Maple park");
		 myList.add("Malli");

		 p.setTypes(myList);
		 Location l =new Location();
		 l.setLat(-48.383494);
		 l.setLng(43.427362);
		 p.setLocation(l);
		 return p;
		 
		 
				 
		 
	}
	
	public String deletepayload(String placeid) {
		return "{\r\n \"place_id\":\""+placeid+"\"\r\n}";
		
		 
	}
}
