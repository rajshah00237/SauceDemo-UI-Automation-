package com.sauceDemo.pages;

import com.sauceDemo.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {
    private final WebDriver driver;
    private final By appLogo = By.xpath("//div[@class='app_logo']");
    private final By haburgerMenu = By.xpath("//button[text()='Open Menu']");
    private final By products = By.className("inventory_item");
    private final By productNames = By.className("inventory_item_name");
    private final By addToCartBtn = By.xpath("//button[text()='Add to cart']");
    private final By removeFromCartBtn = By.xpath("//button[text()='Remove']");
    private final By cartLink = By.xpath("//a[@class='shopping_cart_link']");
    private final By cartBadge = By.xpath("//span[@class='shopping_cart_badge']");

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isCorrectAppLogoText(String expected) {
        return getText(appLogo).equalsIgnoreCase(expected);
    }

    public boolean isAppLogoDisplayed() {
        return isDisplayed(appLogo);
    }

    public List<WebElement> getProductNames() {
        return finds(productNames);
    }

    public int getProductCount() {
        return finds(productNames).size();
    }

    public void addToCartProduct(String productName) {
        List<WebElement> allProducts = finds(products);
        boolean found = false;
        for (WebElement product : allProducts) {
            String currentProductName = product.findElement(productNames).getText();
            System.out.println("Current Product = " + currentProductName);
            if (currentProductName.equalsIgnoreCase(productName)) {
                System.out.println("MATCH FOUND");
                product.findElement(addToCartBtn).click();
                break;
            }
        }
        if (!found) {
            System.out.println("Product " + productName + " not found");
        }
    }

    public void removeFromCartProduct(String productName) {
        List<WebElement> allProducts = finds(products);
        for (WebElement product : allProducts) {
            String currentProductName = product.findElement(productNames).getText();
            if (currentProductName.equalsIgnoreCase(productName)) {
                product.findElement(removeFromCartBtn).click();
                break;
            }
        }
    }

    public boolean verifyRemoveBtnDisplayedForProductName(String productName) {
        List<WebElement> allProducts = finds(products);
        for (WebElement product : allProducts) {
            String currentProductName = product.findElement(productNames).getText();
            if (currentProductName.equalsIgnoreCase(productName)) {
                return product.findElement(removeFromCartBtn).isDisplayed();
            }
        }
        return false;
    }

    public boolean verifyCartIconDisplayed() {
        return isDisplayed(cartLink);
    }

    public boolean verifyCartBadgeDisplayed() {
        return isDisplayed(cartBadge);
    }

    public String getCartBadgeCount() {
        return getText(cartBadge);
    }

    public CartPage clickCart() {
        click(cartLink);
        return new CartPage(driver);
    }
}
