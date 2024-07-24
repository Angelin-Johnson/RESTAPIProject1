package step.definitions;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostsStepAPI {
    private final String BASE_URL="http://localhost:3000";
    private Response getResponse;
    private Response postResponse;
    private Response deleteResponse;
    private Scenario scenario;
   private ObjectMapper objectMapper;

    @Before
    public void before(Scenario scen){
        this.scenario=scen;
   }

    @Given("GET call {string} as the request")
    public void getCallUrl(String url) throws URISyntaxException {
        RestAssured.baseURI=BASE_URL;
        RequestSpecification requestSpecification=RestAssured.given();
        getResponse=requestSpecification.when().get(new URI(url));

    }

    @Then("all posts will be displayed with a response status code as {string}")
    public void ResponseStatusCodeAsStatusCode(String code) {
        int actualResponse=getResponse.then().extract().statusCode();
        Assert.assertEquals(code,actualResponse+"");
    }


    @Then("posts for the respective ID will be displayed with a title name as {string}")
    public void postsForTheRespectiveIDWillBeDisplayedWithATitleNameAs(String arg0) {
        String title=getResponse.then().extract().path("title");
        Assert.assertEquals(arg0,title);
    }


    @When("POST call {string} as the request to create a new post")
    public void postCallAsTheRequestToCreateANewPost(String arg0) throws IOException, URISyntaxException {
        RestAssured.baseURI=BASE_URL;
        RequestSpecification requestSpecification=RestAssured.given();
        objectMapper =new ObjectMapper();

        //get the list of ids:

        // Parse the response as a JsonNode
        JsonNode jsonResponse = objectMapper.readTree(getResponse.getBody().asString());
        List<Integer> ids= new ArrayList<>();
        for (JsonNode node : jsonResponse) {
            ids.add(node.get("id").asInt());
        }

        //Generate a new id
        Optional<Integer> highest_id=ids.stream().max(Integer::compareTo);
        Integer new_ID= highest_id.map(id -> id + 1).orElse(1);
        System.out.println(new_ID);

        // Read the JSON file into a JsonNode and convert it to string
        JsonNode jsonNode = objectMapper.readTree(new File("src/test/resources/TestData/newpost.json"));
        ((ObjectNode) jsonNode).put("id",new_ID+"" );
        String body=jsonNode.toString();

        postResponse=requestSpecification.given().contentType(ContentType.JSON)
                .when().body(body).post(new URI(arg0));
    }

    @Then("the new post will be displayed with a response status code as {string}")
    public void theNewPostWillBeDisplayedWithAResponseStatusCodeAs(String arg0) {
        int actualResponse=postResponse.then().extract().statusCode();
        Assert.assertEquals(arg0,actualResponse+"");
    }

    @Given("DELETE call {string} as the request")
    public void deleteCallAsTheRequest(String url) throws URISyntaxException {
        RestAssured.baseURI=BASE_URL;
        RequestSpecification requestSpecification=RestAssured.given();
        deleteResponse=requestSpecification.when().delete(new URI(url));


    }

    @Then("the post will be deleted with a response status code as {string} and {string}")
    public void thePostWillBeDeletedWithAResponseStatusCodeAsAnd(String arg0, String arg1) {
        int status_code=deleteResponse.then().extract().statusCode();
        Assert.assertEquals(arg0,status_code+"");
        String id=deleteResponse.then().extract().path("id");
        Assert.assertEquals(arg1,id);

    }
}
