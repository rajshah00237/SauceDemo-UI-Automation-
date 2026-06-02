package  com.sauceDemo.pages;

import com.sauceDemo.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {

    private WebDriver driver;

    private By userName = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HomePage performLogin(String username, String pwd) {
        type(userName,username);
        type(password,pwd);
        click(loginButton);
        return new  HomePage(driver);
    }
}