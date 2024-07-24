package com.apitest.RestAPI.tests;

import com.apitest.RestAPI.helpers.PersonServiceHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DELETEPersonTest {
    private PersonServiceHelper personServiceHelper;

    @BeforeClass
    public void initialise(){
        personServiceHelper=new PersonServiceHelper();

    }

    @Test
    public  void deletePersonTest(){
        personServiceHelper.deletePerson(4);
    }

}
