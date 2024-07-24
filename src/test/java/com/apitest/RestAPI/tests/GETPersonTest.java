package com.apitest.RestAPI.tests;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;


import com.apitest.RestAPI.helpers.PersonServiceHelper;
import com.apitest.RestAPI.model.Person;

public class GETPersonTest {

	private PersonServiceHelper personServiceHelper;

	@Test
	public void initialise() {
		personServiceHelper=new PersonServiceHelper();
		System.out.println("instance created executed");
		}
	
	@Test
	public void testGetAllPerson() {
		List<Person> personList=personServiceHelper.getAllPerson();
		assertNotNull(personList,"Person list not empty");
		assertFalse(personList.isEmpty(),"Person list empty is not true");
		
}
}

