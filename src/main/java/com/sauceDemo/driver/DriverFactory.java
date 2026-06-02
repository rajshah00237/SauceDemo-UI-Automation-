package com.sauceDemo.driver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
        // Prevent object creation
    }

    public static void initDriver(String browserName) {

        if (browserName == null) {
            browserName = "chrome";
        }

        switch (browserName.toLowerCase()) {

            case "chrome":
                ChromeOptions options = new ChromeOptions();


                options.addArguments("--guest");
                options.addArguments("--start-maximized");

                driver.set(new ChromeDriver(options));
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                driver.set(new EdgeDriver(edgeOptions));
                getDriver().manage().window().maximize();
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported Browser : " + browserName);
        }

        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
