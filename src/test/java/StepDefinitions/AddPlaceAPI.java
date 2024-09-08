package StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Locations;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

public class AddPlaceAPI extends Utils {
	ResponseSpecification responseSpec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id ;

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

	}

	@When("User calls the {string} with {string} request")
	public void user_calls_the_with_post_request(String resource, String method) {
		
		// constructor will be called when we use value of method
		APIResources getResource = APIResources.valueOf(resource);

		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("post")) {
		response = res.when().post(getResource.getResource()).then().spec(responseSpec).extract().response();
		}
		else if(method.equalsIgnoreCase("get")) {
			response = res.when().get(getResource.getResource()).then().spec(responseSpec).extract().response();
		}
		else if(method.equalsIgnoreCase("delete")) {
			response = res.when().delete(getResource.getResource()).then().spec(responseSpec).extract().response();
		}
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String key, String value) {
		
		assertEquals(getJsonPath(response, key), value);
	}

	@Then("I verify the place id created to {string} using {string}")
	public void i_Verify_the_PlaceId_Created(String expectedName, String resouce) throws IOException {
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_the_with_post_request(resouce, "get");
		String name = getJsonPath(response, "name");
		assertEquals(expectedName, name);
	}
	
	@Given("Delete place payload")
	public void i_Delete_Place() throws IOException {
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}
