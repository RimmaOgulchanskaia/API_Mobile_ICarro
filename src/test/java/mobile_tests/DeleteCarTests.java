package mobile_tests;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MyCarsScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import java.util.List;

public class DeleteCarTests extends AppiumConfig {
    @BeforeMethod
    public void login(){
        RegistrationBodyDto user= RegistrationBodyDto.builder()
                .username("rimma003@gmail.com")
                .password("Rr12345!")
                .build();
        new SplashScreen(driver).goToSearchScreen(5);
        SearchScreen searchScreen= new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnLogin();
        LoginScreen loginScreen= new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();

        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();


    }
    @Test
    public void deieteCarPositiveTest(){
        MyCarsScreen myCarsScreen= new MyCarsScreen(driver);
        List<String> listBeforeDelete = myCarsScreen.readCarsListScreen();
        myCarsScreen.deleteCar();
        myCarsScreen.clickBtnYes();
        List<String> listAfterDelete = myCarsScreen.readCarsListScreen();
        Assert.assertNotEquals(listAfterDelete, listBeforeDelete);
    }
}
