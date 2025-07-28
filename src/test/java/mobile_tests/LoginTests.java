package mobile_tests;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {


    @BeforeMethod
    public void openScreenLogin(){
        new SplashScreen(driver).goToSearchScreen(5);
        SearchScreen searchScreen= new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnLogin();
    }
    @Test
    public void loginPositiveTest(){
        RegistrationBodyDto user= RegistrationBodyDto.builder()
                .username("rimma003@gmail.com")
                .password("Rr12345!")
                .build();
        LoginScreen loginScreen= new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
        Assert.assertTrue(new SearchScreen(driver).textInElementPresent_popUpMessage("Login success!"));
    }

    @Test
    public void loginNegativeTest_WrongPasswordWithoutSymbol(){
        RegistrationBodyDto user= RegistrationBodyDto.builder()
                .username("Rimma12345@gmail.com")
                .password("Rrrr@12345")
                .build();
        LoginScreen loginScreen= new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_EmptyEmail(){
        RegistrationBodyDto user= RegistrationBodyDto.builder()
                .username("")
                .password("Rrrr@12345")
                .build();
        LoginScreen loginScreen= new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("All fields must be filled and agree terms"));
    }


}
