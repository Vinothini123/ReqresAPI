package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class StepDefReqres {
	
	public Response response;
	
	// Set the Login URL	
	@Given("Set the Login URL")
	public void loginURL() {
		RestAssured.baseURI = "https://reqres.in/api/login";
	}
	
	// Post the valid credentials as a JSON content type
	@When("Enter the valid credentials")
	public void enterCredentials() {
		response = SerenityRest
				.given()
				.contentType(ContentType.JSON).body("{\r\n"
						+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
						+ "    \"password\": \"cityslicka\"\r\n"
						+ "}")
				.post();
		
	}
	
	// Verify the returned status code
	@Then("Verify the status code {int}")
	public void verifyStatusCode(int code) {
		SerenityRest.restAssuredThat(response -> response.statusCode(code));
	}

	// Post the invalid credentials as JSON content type
	@When("Enter the invalid Credentials")
	public void enterInvalidCredentials() {
		SerenityRest
				.given()
				.contentType(ContentType.JSON).body("{\r\n"
						+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
						+ "}")
				.post();
	}
	
	// Verify the status code for Failure
	@Then("Verify the status code {int} for failure")
	public void verifyFailureStatusCode(int code) {
		SerenityRest.restAssuredThat(response -> response.statusCode(code));
	}
	
	// Set the URL for retrieving user list
	@Given("Send the endpoint URL for List users")
	public void loadUserListUrl () {
		RestAssured.baseURI = "https://reqres.in/api/users?page=2";
	}
	
	// Verify the status code for success scenario
	@Then("Verify the user list status code {int}")
	public void verifyUserListStatusCode(int code) {		
		response = RestAssured.get();
		response.then().statusCode(code).extract().response();
		response.prettyPrint();
	}
	
	// Set the URL for retrieving user list
	@Given("Send the endpoint URL for usersList")
	public void loadUsersListUrl () {
		RestAssured.baseURI = "https://reqres.in/api/unknown/23";
	}

	// Set the valid url for update user
	@Given("Set the URL for Update user")
	public void updateURL() {
		RestAssured.baseURI = "https://reqres.in/api/users/2";
	}
	
	// Send the valid user details to be update
	@When("Send the valid details to be updated")
	public void updateUserDetail() {
		SerenityRest.given().contentType(ContentType.JSON).body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").put();
	}
	
	// Set the invalid URL for update user
	@Given("Set the invalid URL for Update user")
	public void invalidUpdateURL() {
		RestAssured.baseURI = "https://reqres.in/api/";
	}
	
	// Send the invalid details to be updated	
	@When("Send the invalid details to be updated")
	public void updateUserDetailInvalid() {
		SerenityRest.given().contentType(ContentType.XML).body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").put();
	}
	
	// Set the valid url for delete user
	@When("Set the URL for Delete User")
	public void deleteUserURL() {
		RestAssured.baseURI = "https://reqres.in/api/users/2";
	}
	
	// Verify the status code for delete
	@Then("Verify the status code {int} for delete")
	public void deleteUser(int code) {
		SerenityRest.delete().then().statusCode(code);
	}
	
	// Set the invalid url for delete user
	@When("Set the invalid URL for Delete User")
	public void deleteInvalidURL() {
		RestAssured.baseURI = "https://reqres.in/api/";
	}
}
