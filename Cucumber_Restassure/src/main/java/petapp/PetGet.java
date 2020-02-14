package petapp;

import static io.restassured.RestAssured.get;

import io.restassured.response.Response;

public class PetGet {

	public Response getAllPets(String status) {
		return get("https://petstore.swagger.io/v2/pet/findByStatus?status=available&status=pending&status=sold");
	}
	public Response getPetByStatus(String status) {
		return get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
	}

}
