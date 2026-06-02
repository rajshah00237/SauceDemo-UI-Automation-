package com.sauceDemo.Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.sauceDemo.driver.DriverFactory;

public class BaseTest {

    public WebDriver driver;


    @BeforeMethod
    public void setup() {

        String browser = System.getProperty("browser", "chrome");
        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
        System.out.println("get Url : " + DriverFactory.getDriver().getCurrentUrl());
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}
