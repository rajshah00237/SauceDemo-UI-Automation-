package com.sauceDemo.pages;

import com.sauceDemo.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartItems = By.className("cart_item");
    private final By productName = By.className("inventory_item_name");
    private final By removeBtn = By.xpath("//button[text()='Remove']");
    private final WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public List<WebElement> cartItems() {
        return finds(cartItems);
    }


    public void removeCartItems(List<String> names) {
        for (String productName : names) {
            System.out.println("Removing " + productName);
            List<WebElement> allCartItems = finds(cartItems);
            for (WebElement item : allCartItems) {
                String currentName = item.findElement(this.productName).getText();
                System.out.println(item.getText() + " " + currentName);
                if (currentName.equalsIgnoreCase(productName)) {
                    item.findElement(removeBtn).click();
                    break;
                }
            }
        }
    }


}
