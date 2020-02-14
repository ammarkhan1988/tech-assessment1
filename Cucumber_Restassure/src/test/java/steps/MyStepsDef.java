package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import petapp.PetGet;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MyStepsDef {
	
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	private ResponseBody responseBodyGET;
	Response apicall = get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");

	@When("^a user executes a search$")
	public void iPerformGETOperationFor() throws Throwable{
	//	Response apicall = get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
		System.out.println("response: " + apicall.prettyPrint());
			}

	
	@Given("^I get a \"([^\"]*)\" response code$")
	public void iGetAResponseCode(String status) throws Throwable{
		
		Response petGetResponse = new PetGet().getPetByStatus(status);

		Assert.assertTrue(petGetResponse.getStatusCode() == 200);
		Assert.assertTrue(apicall.getStatusCode() == 200);

	}


	@And("^the number of pets that have status \"([^\"]*)\" is \"([^\"]*)\"$")
	public void numberofPetsThatHaveStatusAvailable(String status, String numberofPets) throws Throwable{
		apicall.jsonPath();
	
		System.out.print(apicall.jsonPath());

		Pattern pattern = Pattern.compile(status);
		Matcher matcher = pattern.matcher(apicall.asString());
		int count = 0;
		while (matcher.find()) {
		    count++;
		}
		  

		int NoOfPets = Integer.valueOf(numberofPets);
		assertEquals(NoOfPets, count);	
		
	}

	@And("^the number of dogs with the name \"([^\"]*)\" is \"([^\"]*)\"$")
	public void numberofPetsThatHaveNameDoggie(String name, String numberofPets) throws Throwable{
		apicall.jsonPath();
	
		System.out.print(apicall.jsonPath());

		Pattern pattern = Pattern.compile(name);
		Matcher matcher = pattern.matcher(apicall.asString());
		int count = 0;
		while (matcher.find()) {
		    count++;
		}
		
		int NoOfPets = Integer.valueOf(numberofPets);
		assertEquals(NoOfPets, count);	
		
	}

	
	
}
