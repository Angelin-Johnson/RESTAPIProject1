package com.apitest.RestAPI.tests;

import com.apitest.RestAPI.helpers.PersonServiceHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class POSTPersonTest {


   private PersonServiceHelper personServiceHelper;

    //Initilaise the peron service helper

    @BeforeClass
    public void initilaise(){
        personServiceHelper=new PersonServiceHelper();

    }

    @Test
    public void createPersonTest(){
        String id=personServiceHelper.createPerson().jsonPath().getString("id");
        System.out.println(id);
        assertNotNull("id","person id is not null");


    }

}
