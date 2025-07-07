package rest_tests;

import api_rest.AutheniticationController;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginTestsRest extends AutheniticationController {
    RegistrationBodyDto user;

    @BeforeMethod
    public void registrationUser(){
        int i= new Random().nextInt(1000);
         user = RegistrationBodyDto.builder()
                .username("malinki"+i+"@mail.com")
                .password("Malina12345!") // c 8 до 30 (7,8,30,31); aaaaa1234! без большой буквы; AAaaaaa!#; AAAA12345!!; 1234567!#; Капкапкап123!
                .firstName("Modern")
                .lastName("Klon")
                .build();
        System.out.println("Registration result ---> "+registrationLogin(user, REGISTRATION_URL)
                .getStatusCode());
        System.out.println(user);

    }
    @Test
    public void loginPositiveTest(){
        Assert.assertEquals(registrationLogin(user, LOGIN_URL)
                .getStatusCode(), 200);
    }

    @Test
    public void loginNegativeTest_WrongEmail_UnregisteredUser(){
        user.setUsername("mango1plumo1@gmail.com");
        Response response = registrationLogin(user, LOGIN_URL);
        System.out.println(response.getBody().print());
        Assert.assertEquals(response.getStatusCode(), 401);

    }
}