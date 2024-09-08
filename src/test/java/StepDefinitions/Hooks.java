package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deleteplace")
	public void beforeScenario() throws IOException {
		AddPlaceAPI p = new AddPlaceAPI();
		
		if(AddPlaceAPI.place_id == null) {
		p.add_place_payload("Accenture", "French", "Mainroad Dublin Ireland");
		p.user_calls_the_with_post_request("AddPlace", "post");
		p.i_Verify_the_PlaceId_Created("Accenture", "getPlace");
		
		}
		
	}

}
