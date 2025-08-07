package mobile_tests;

import config.AppiumConfig;
import dto.CarDto;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import java.util.Random;

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
        int i= new Random().nextInt(1000)+1000;
        CarDto car = CarDto.builder()
                .serialNumber("003-"+i)
                .manufacture("Opel")
                .model("Astra")
                .city("Haifa")
                .pricePerDay(76.50)
                .carClass("A")
                .fuel("Diesel")
                .year("2020")
                .seats(4)
                .build();
        SearchScreen searchScreen= new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen=new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
        AddNewCarScreen addNewCarScreen= new AddNewCarScreen(driver);
        addNewCarScreen.typeAddNewCarForm(car);
        addNewCarScreen.clickBtnAddCar();
        Assert.assertTrue(new MyCarsScreen(driver).textInElementPresent_popUpMessage("Car was added!"));

    }

    @Test
    public void addNewCarNegativeTest_WrongSerialNumber(){
        CarDto car = CarDto.builder()
                .serialNumber("003-1991")
                .manufacture("Opel")
                .model("Astra")
                .city("Haifa")
                .pricePerDay(76.50)
                .carClass("A")
                .fuel("Diesel")
                .year("2020")
                .seats(4)
                .build();
        SearchScreen searchScreen= new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen=new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
        AddNewCarScreen addNewCarScreen= new AddNewCarScreen(driver);
        addNewCarScreen.typeAddNewCarForm(car);
        addNewCarScreen.clickBtnAddCar();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Car with serial number 003-1991 already exists"));

    }

    @Test
    public void addNewCarNegativeTest_AllFieldEmpty(){
        CarDto car = CarDto.builder()
                .serialNumber("")
                .manufacture("")
                .model("")
                .city("")
                .pricePerDay(0.)
                .carClass("")
                .fuel("")
                .year("")
                .seats(0)
                .build();
        SearchScreen searchScreen= new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen=new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
        AddNewCarScreen addNewCarScreen= new AddNewCarScreen(driver);
        addNewCarScreen.typeAddNewCarForm(car);
        addNewCarScreen.clickBtnAddCar();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));

    }

    @Test
    public void addNewCarNegativeTest_EmptyFieldManufacture(){
        int i= new Random().nextInt(1000)+1000;
        CarDto car = CarDto.builder()
                .serialNumber("003-"+i)
                .manufacture("")
                .model("Astra")
                .city("Haifa")
                .pricePerDay(76.50)
                .carClass("A")
                .fuel("Diesel")
                .year("2020")
                .seats(4)
                .build();
        SearchScreen searchScreen= new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen=new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
        AddNewCarScreen addNewCarScreen= new AddNewCarScreen(driver);
        addNewCarScreen.typeAddNewCarForm(car);
        addNewCarScreen.clickBtnAddCar();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));

    }

    @Test
    public void addNewCarNegativeTest_WrongFieldCity(){
        int i= new Random().nextInt(1000)+1000;
        CarDto car = CarDto.builder()
                .serialNumber("003-"+i)
                .manufacture("Mazda")
                .model("3")
                .city("Uyy")
                .pricePerDay(76.50)
                .carClass("A")
                .fuel("Diesel")
                .year("2020")
                .seats(4)
                .build();
        SearchScreen searchScreen= new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        MyCarsScreen myCarsScreen=new MyCarsScreen(driver);
        myCarsScreen.clickBtnAddNewCar();
        AddNewCarScreen addNewCarScreen= new AddNewCarScreen(driver);
        addNewCarScreen.typeAddNewCarForm(car);
        addNewCarScreen.clickBtnAddCar();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("City Uyy not available"));

    }




}
