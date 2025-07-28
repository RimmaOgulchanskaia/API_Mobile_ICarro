package mobile_tests;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {
    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user= RegistrationBodyDto.builder()
                .firstName("Rim")
                .lastName("Og")
                .username("Rimma003@gmail.com")
                .password("Rr12345!")
                .build();
        System.out.println(user.toString());
        new SplashScreen(driver).goToSearchScreen(5);
        SearchScreen searchScreen= new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnRegistration();
        RegistrationScreen registrationScreen= new RegistrationScreen(driver);
        registrationScreen.typeRegistraionForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(searchScreen
                .textInElementPresent_popUpMessage("Registration success!"));
    }

    @Test
    public void registrationNegativeTest_WrongEmail(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user= RegistrationBodyDto.builder()
                .firstName("Rim")
                .lastName("Og")
                .username("Rimmagmail.com")
                .password("Rrrr@12345!")
                .build();
        System.out.println(user.toString());
        new SplashScreen(driver).goToSearchScreen(5);
        SearchScreen searchScreen= new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnRegistration();
        RegistrationScreen registrationScreen= new RegistrationScreen(driver);
        registrationScreen.typeRegistraionForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("username=must be a well-formed email address"));
    }

    @Test
    public void registrationNegativeTest_WithoutCheckBox(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user= RegistrationBodyDto.builder()
                .firstName(i+"Rim")
                .lastName("Og")
                .username("Rimma"+i+"@gmail.com")
                .password("Rrrr@12345!")
                .build();
        System.out.println(user.toString());
        new SplashScreen(driver).goToSearchScreen(5);
        SearchScreen searchScreen= new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnRegistration();
        RegistrationScreen registrationScreen= new RegistrationScreen(driver);
        registrationScreen.typeRegistraionForm(user);
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("All fields must be filled and agree terms"));
    }



}
