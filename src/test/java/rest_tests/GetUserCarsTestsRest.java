package rest_tests;

import api_rest.CarController;
import dto.CarDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetUserCarsTestsRest extends CarController {
    SoftAssert softAssert= new SoftAssert();
    CarDto cars;

    @Test
    public void getUserCarsPositiveTest(){
        Response response= getUserCars();
        cars= response.getBody().as(CarDto.class);
        System.out.println(cars);
        softAssert.assertEquals(response.getStatusCode(),200,"validate status code");
        softAssert.assertAll();

    }

    @Test
    public void getUserCarsNegativeTest_WrongUrl(){
        Response response= getUserCarsNegative_WrongUrl(REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 403);

    }
}
