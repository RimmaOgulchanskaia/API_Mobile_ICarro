package mobile_tests;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MyCarsScreen;
import screens.SearchScreen;
import screens.SplashScreen;

public class AddNewCarTests extends AppiumConfig {

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


}
    @Test
    public void addNewCarPositiveTest(){
        SearchScreen searchScreen= new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen=new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
    }
}
