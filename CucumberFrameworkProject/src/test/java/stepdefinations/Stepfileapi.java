package stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import Utilites.AddPlace;
import Utilites.ApiResources;
import Utilites.TestDataBuild;
 import Utilites.Utils;

public class Stepfileapi  extends Utils{
	
	 AddPlace p =new AddPlace();
	 RequestSpecification res;
	 ResponseSpecification resspec;
	 Response response;
	 String resource;
	 TestDataBuild testdata = new TestDataBuild();
	 static String place_id;
	 JsonPath js;
	
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		res=given().spec(RequestSpec())
				 .body(testdata.addplace(name,language,address));
		
	    
	}
	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String method) {
		ApiResources resourceapi = ApiResources.valueOf(resource);
		System.out.println(resourceapi.getResource());
		
		 
		 resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		
		  if (method.equalsIgnoreCase("POST"))		  
			  response = res.when().post(resourceapi.getResource());
			  else if(method.equalsIgnoreCase("GET"))
		    response = res.when().get(resourceapi.getResource());
			  
		  	 
		 response = res.when().post(resourceapi.getResource()).
				 then().spec(resspec).extract().response();
	    
	}
	
	@Then("the api call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) {
		 assertEquals(getjsonpath(response,keyvalue).toString(),expectedvalue);
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
		 place_id = getjsonpath(response, "place_id");
		  System.out.println(place_id);
		 res = given().spec(RequestSpec()).queryParam("place_id", place_id);
		 user_call_with_http_request(resource,"GET");
		 String actualname = getjsonpath(response, "name");
		 System.out.println(actualname);
		 assertEquals(actualname, expectedname);
	     
	}


	@Given("Deleteplace payload")
	public void deleteplace_payload() throws IOException {
		 System.out.println(place_id);
	     res= given().spec(RequestSpec()).body(testdata.deletepayload(place_id));
	}



}
