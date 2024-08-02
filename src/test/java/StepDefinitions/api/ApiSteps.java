package StepDefinitions.api;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
public class ApiSteps {
    private Response response;

    @Given("client send a GET request to the user endpoint for user {int}")
    public void iSendAGETRequestToTheUserEndpointForUser(int userId) {
        RestAssured.baseURI = "https://reqres.in/api";
        response = RestAssured.given()
                .log().all()  // Log the request details
                .when()
                .get("/users/" + userId)
                .then()
                .log().all()  // Log the response details
                .extract().response();
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode, "Correct status code returned");
    }

    @Then("the first name should be {string}")
    public void theFirstNameShouldBe(String firstName) {
        String actualFirstName = response.jsonPath().get("data.first_name");
        Assert.assertEquals(actualFirstName, firstName, "Correct first name returned");
    }

    @Then("the last name should be {string}")
    public void theLastNameShouldBe(String lastName) {
        String actualLastName = response.jsonPath().get("data.last_name");
        Assert.assertEquals(actualLastName, lastName, "Correct last name returned");
    }

    @Then("the email should be {string}")
    public void theEmailShouldBe(String email) {
        String actualEmail = response.jsonPath().get("data.email");
        Assert.assertEquals(actualEmail, email, "Correct email returned");
    }
}
