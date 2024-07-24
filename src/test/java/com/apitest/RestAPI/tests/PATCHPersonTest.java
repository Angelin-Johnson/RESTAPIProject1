package com.apitest.RestAPI.tests;

import com.apitest.RestAPI.helpers.PersonServiceHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertNotNull;

public class PATCHPersonTest {

     private PersonServiceHelper personServiceHelper;

     @BeforeClass
    public void initialise(){
         personServiceHelper=new PersonServiceHelper();
     }

     @Test
    public void updatePersonTest() throws IOException {
         String id=personServiceHelper.updatePerson(4).jsonPath().getString("id");
         System.out.println(id);
         assertNotNull(id,"updated");

     }
}
