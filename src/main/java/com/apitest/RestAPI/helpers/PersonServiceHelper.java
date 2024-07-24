package com.apitest.RestAPI.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.apitest.RestAPI.constants.EndPoints;
import com.apitest.RestAPI.model.Address;
import com.apitest.RestAPI.model.Person;
import com.apitest.RestAPI.model.PhoneNumber;
import com.apitest.RestAPI.utils.ConfigManager;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PersonServiceHelper {

	
	//We need to read the config varaiables
	//Tell REST Assured about the url and port
	
	//Making a GET Request on the url and sent the data to GETPersonTes
	
	private static final String BASE_URL =ConfigManager.getInstance().getString("baseUrl");
	private static final String PORT =ConfigManager.getInstance().getString("port");
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public PersonServiceHelper() {
		System.out.println("Base url:"+BASE_URL);
		System.out.println("Port No:"+PORT);
		RestAssured.baseURI = BASE_URL;
		RestAssured.port = Integer.parseInt(PORT);

//		**
//		 * Use relaxed HTTP validation with protocol {@value #SSL}. This means that you'll trust all hosts regardless if the SSL certificate is invalid. By using this
//		 * method you don't need to specify a keystore (see {@link #keyStore(String, String)} or trust store (see {@link #trustStore(java.security.KeyStore)}.
//		 * <p>
//		 * This is just a shortcut for:
//		 * </p>
//		 * <pre>
//		 * RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation());
//		 * </pre>
//		 */
		RestAssured.useRelaxedHTTPSValidation();
	}
	
	public List<Person> getAllPerson(){
		Response res=RestAssured
				.given().log().all()
				.contentType(ContentType.JSON)
				.when()
				.get(EndPoints.GET_ALL_PERSON_DETAILS)
				.andReturn();
		
		Type type=new TypeReference<List<Person>>(){}.getType();
		assertEquals(res.getStatusCode(), HttpStatus.SC_OK,"OK");
		List<Person> personList=res.as(type);
		return personList;
	}

	public Response createPerson(){
		//Need to make a post call
		Person person=new Person();
		List<PhoneNumber> phoneNumbers = new ArrayList<>();
		PhoneNumber ph1=new PhoneNumber();
		PhoneNumber ph2 =new PhoneNumber();
		List<Address> address = new ArrayList<>();
		Address ad1=new Address();
		Address ad2=new Address();

		person.setId(4);
		person.setFirstName("Sara");
		person.setLastName("Lahore");
		person.setAge(27);

		ph1.setType("Home");
		ph1.setNumber("1234-678-980");
		ph2.setType("Mobile");
		ph2.setNumber("1234-345-980");
		phoneNumbers.add(ph1);
		phoneNumbers.add(ph2);
		person.setPhoneNumbers(phoneNumbers);

		ad1.setAddressType("Home");
		ad1.setCity("Preston");
		ad1.setCountry("Lancashire");
		ad1.setZipcode("PET456");
		ad2.setAddressType("Office");
		ad2.setCity("Preston");
		ad2.setCountry("Lancashire");
		ad2.setZipcode("PET456");
		address.add(ad1);
		address.add(ad2);
		person.setAddress(address);

		Response res=RestAssured.given().contentType(ContentType.JSON)
				.when().body(person).post(EndPoints.CREATE_PERSON)
				.andReturn();

		assertEquals(res.getStatusCode(),HttpStatus.SC_CREATED,"CREATED");
		return res;
	}

	public Response updatePerson(Integer id) throws IOException {


		// Make the GET request
		Response res=RestAssured
				.given().log().all().pathParam("id",id)
				.contentType(ContentType.JSON)
				.when()
				.get(EndPoints.GET_SINGLE_PERSON)
				.andReturn();

		// Extract the response body as a string
		String jsonString = res.getBody().asString();

		/*Deserialize the JSON string to a List of Person objects because the error message you're encountering suggests that Jackson,
		the JSON library used for serialization and deserialization in your Java application, is expecting a JSON object ({})
		but is receiving a JSON array ([]) instead. This mismatch occurs during the deserialization process when Jackson
		attempts to convert JSON data into a Java object (Person in this case).*/
		List<Person> personList = objectMapper.readValue(jsonString, new TypeReference<List<Person>>() {});
		Person person=personList.get(0);

		person.setFirstName("Halad");
		person.getPhoneNumbers().get(0).setType("Home");
		person.getAddress().get(1).setCountry("Sheffield");

		//The modified Person object is converted to a Map to be used in the PATCH request body.
		Map<String, Object> updateFieldsMap=objectMapper.convertValue(person,Map.class);

		Response response=RestAssured.given().contentType(ContentType.JSON)
				.pathParam("id",id)
				.when().body(updateFieldsMap)
				.patch(EndPoints.UPDATE_PERSON)
				.andReturn();
		System.out.println(response.getStatusCode());
		assertTrue((response.getStatusCode()==HttpStatus.SC_OK));
		return  response;

	}

	public Response deletePerson(Integer id){
		Response res=RestAssured.given().contentType(ContentType.JSON)
				.pathParam("id",id)
				.log()
				.all().when()
				.delete(EndPoints.DELETE_SINGLE_PERSON).andReturn();
		assertTrue((res.getStatusCode()==HttpStatus.SC_OK));
		return res;
	}
	

}