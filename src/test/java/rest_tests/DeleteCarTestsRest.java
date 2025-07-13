package rest_tests;

import api_rest.CarController;
import dto.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeleteCarTestsRest extends CarController {
    SoftAssert softAssert= new SoftAssert();
    CarsDto cars;

    @BeforeClass
    public void getUserCarsBeforeDelete(){
        Response response= getUserCars();
        if (response.getStatusCode()==200){
            cars= response.getBody().as(CarsDto.class);
        } else
            System.out.println("Status code not 200" + response.getStatusCode());
    }

    @Test
    public void deleteCarPositiveTest(){
        if(cars.getCars().length!=0){
            String serialNumber = cars.getCars()[0].getSerialNumber();
            Response response = deleteCarBySerialNumber(serialNumber);
            System.out.println(response.getBody().print());
            ResponseMessageDto responseMessageDto= response.getBody().as(ResponseMessageDto.class);
            softAssert.assertEquals(response.getStatusCode(), 200);
            softAssert.assertTrue(responseMessageDto.getMessage().contains("deleted"));
            softAssert.assertAll();
        } else
            Assert.fail("User don't have a cars");
    }

    @Test
    public void deleteCarNegativeTest_wrongSerialNumber(){
        Response response = deleteCarBySerialNumber("serialNumber");
        softAssert.assertEquals(response.getStatusCode(),400, "validate status code");
        ErrorMessageDtoString errorMessageDtoString= response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getMessage(), "Car with serial number serialNumber not found");
        softAssert.assertAll();

    }
}
