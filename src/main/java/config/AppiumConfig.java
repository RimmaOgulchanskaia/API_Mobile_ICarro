package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumConfig {
    public static AppiumDriver<AndroidElement> driver;
    public static int height=0;
    public static int wedth=0;


    @BeforeMethod
    public void setup(){
        DesiredCapabilities desiredCapabilities= new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Small Phone");
        desiredCapabilities.setCapability("platformVersion", "15");
        desiredCapabilities.setCapability("appPackage", "com.telran.ilcarro");
        desiredCapabilities.setCapability("appActivity", ".SplashActivity");

        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");

        String url= "http://localhost:4723/wd/hub";
        try {
            driver= new AppiumDriver<>(new URL(url), desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        height = driver.manage().window().getSize().getHeight();
        wedth = driver.manage().window().getSize().getWidth();

    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
