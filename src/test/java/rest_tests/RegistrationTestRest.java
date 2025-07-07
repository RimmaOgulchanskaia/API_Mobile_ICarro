package rest_tests;

import api_rest.AutheniticationController;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class RegistrationTestRest
        extends AutheniticationController {

SoftAssert softAssert= new SoftAssert();

    @Test
    public void registrationPositiveTest(){
        int i= new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("Modern"+i+"@mail.com")
                .password("Asdfg1234!")
                .firstName("Modern")
                .lastName("Klon")
                .build();
        Assert.assertEquals
                (registrationLogin(user, REGISTRATION_URL).getStatusCode(), 200);
        System.out.println(user);

    }

    @Test
    public void registrationNegativeTest_WrongEmail(){
        int i= new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("Modern"+i+"mail.com")
                .password("Asdfg1234!")
                .firstName("Modern")
                .lastName("Klon")
                .build();
//        Assert.assertEquals
//                (registrationLogin(user, REGISTRATION_URL).getStatusCode(), 400);
        Response response = registrationLogin(user, REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode(), 400, "validate status code");
        ErrorMessageDtoString errorMessageDtoString= response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getError(), "Bad Request", "validate error");
        System.out.println(errorMessageDtoString);
        softAssert.assertTrue(errorMessageDtoString.getMessage().toString().contains("must be a well-formed"), "validate message");
        softAssert.assertAll();


    }

    @Test
    public void registrationNegativeTest_WrongPassword(){
        int i= new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("Modern"+i+"@mail.com")
                .password("Asdfg1234")
                .firstName("Modern")
                .lastName("Klon")
                .build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode(), 400, "validate status code");
        ErrorMessageDtoString errorMessageDtoString= response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getError(), "Bad Request", "validate error");
        System.out.println(errorMessageDtoString);
        softAssert.assertTrue(errorMessageDtoString.getMessage().toString().contains("password= At least 8 characters; Must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number"), "validate message");
        softAssert.assertAll();

    }

    @Test
    public void registrationNegativeTest_WrongPassword2(){
        int i= new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("Modern"+i+"@mail.com")
                .password("Asdfg1234") // c 8 до 30 (7,8,30,31); aaaaa1234! без большой буквы; AAaaaaa!#; AAAA12345!!; 1234567!#; Капкапкап123!
                .firstName("Modern")
                .lastName("Klon")
                .build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode(), 400, "validate status code");
        ErrorMessageDtoString errorMessageDtoString= response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getError(), "Bad Request", "validate error");
        System.out.println(errorMessageDtoString);
        softAssert.assertTrue(errorMessageDtoString.getMessage().toString().contains("password= At least 8 characters; Must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number"), "validate message");
        softAssert.assertAll();

    }

    @Test
    public void registrationNegativeTest_EmptyFirstName(){
        int i= new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("Modern"+i+"@mail.com")
                .password("Asdfg1234!") // c 8 до 30 (7,8,30,31); aaaaa1234! без большой буквы; AAaaaaa!#; AAAA12345!!; 1234567!#; Капкапкап123!
                .firstName("")
                .lastName("Klon")
                .build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode(), 400, "validate status code");
        ErrorMessageDtoString errorMessageDtoString= response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getError(), "Bad Request", "validate error");
        System.out.println(errorMessageDtoString);
        softAssert.assertTrue(errorMessageDtoString.getMessage().toString().contains("not be blank"), "validate message");
        softAssert.assertAll();

    }





}
