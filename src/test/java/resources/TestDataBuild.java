package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Locations;

public class TestDataBuild {
	
	public static AddPlace addPlacePayload(String name, String language, String address) {
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		
		Locations loc = new Locations();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setLocation(loc);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setTypes(types);
		return p;
	}
	
	public String deletePlacePayload(String placeId) {
		
		
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
