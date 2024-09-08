package pojo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SerialiseTest {
	
	public static void main(String[] args) {
		serialise();
	}
	
	public static void serialise() {
		
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		
		Locations loc = new Locations();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setLocation(loc);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setTypes(types);
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		
		ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		
		RequestSpecification res = given().spec(req)
				.body(p);
		
		Response response = res.when().post("maps/api/place/add/json")
		.then().spec(responseSpec).extract().response();

	}
	
}