package task8;

//Tasks
//
//1: Create 3 simple unit tests for Task_2 (modify your code to have 3 different methods in Task_2 solving if needed).
//2. Create testng.xml which should execute your test class. Execute this file
//3. Add a Data provider for each test.
//4. Create a test with parameters loaded from testng.xml.

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import at.task6.Address;

import java.util.UUID;

import static at.task6.Task6.*;

public class Task8Test {
    @DataProvider
    public static Object[][] updateDBData() {
        return new Object[][]{
                {"AQA" + UUID.randomUUID().toString().substring(0, 10), 1},
                {"AQA" + UUID.randomUUID().toString().substring(0, 10), 2}
        };
    }


    @Test
    public void createDataBaseTest() {

        String testCity = "TestCity";
        String testState = "TestState";

        //create
        Address newAddress = createAddress(testCity, testState);
        //check objectIsCreated
        Address actualAddress = read(newAddress.getId());

        Assert.assertEquals(actualAddress.getCity(), testCity, "Invalid city!");
        Assert.assertEquals(actualAddress.getState(), testState, "Invalid state!");

    }

    @Test(dataProvider = "updateDBData")
    public void updateDataBaseTest(String updatedCity, Integer idTest) {

        //read
        Address address = read(idTest);
        address.setCity(updatedCity);

        //update
        update(address);

        //check
        Address actualAddress = read(address.getId());
        Assert.assertEquals(actualAddress.getCity(), updatedCity, "Invalid city!");
    }

    @Test
    @Parameters({"CityParameter", "StateParameter"})
    public void parameterTest(String CityParameter, String StateParameter) {

        Address newAddress = createAddress(CityParameter, StateParameter);
        Address actualAddress = read(newAddress.getId());

        //check
        Assert.assertEquals(actualAddress.getCity(), CityParameter, "Invalid xml city!");
        Assert.assertEquals(actualAddress.getState(), StateParameter, "Invalid xml state!");
    }


}
