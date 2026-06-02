package tests;

import com.sauceDemo.Base.BaseTest;
import com.sauceDemo.pages.CartPage;
import com.sauceDemo.pages.HomePage;
import com.sauceDemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class OrdersTest extends BaseTest {

    @Test(description = "Verify user can login with valid credentials")
    public void VerifyEndToEndOrderFlow() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        HomePage homePage = login.performLogin("standard_user","secret_sauce");

        // Validate product count is 6
        int count = homePage.getProductCount();
        Assert.assertEquals(count, 6); // expected can be fetched from OrdersData.Json

        //Add product to cart
        homePage.addToCartProduct("Sauce Labs Backpack"); // productName to be fetched from OrdersData.json
        homePage.addToCartProduct("Sauce Labs Bolt T-Shirt"); // productName to be fetched from OrdersData.json


        // Once added verify Remove Btn is Displayed
        Assert.assertTrue(homePage.verifyRemoveBtnDisplayedForProductName("Sauce Labs Backpack"));
        Assert.assertTrue(homePage.verifyRemoveBtnDisplayedForProductName("Sauce Labs Bolt T-Shirt"));

        // Verify cart badge displays 1
        Assert.assertEquals(homePage.getCartBadgeCount(),"2");

        //Click cart
        CartPage cart = homePage.clickCart();
        System.out.println("Clicked cart icon");

        List<String> items = new ArrayList<String>();
        items.add("Sauce Labs Backpack");
        items.add("Sauce Labs Bolt T-Shirt");
        System.out.println("Items are "+items);
        Thread.sleep(2000);

        cart.removeCartItems(items);
        Thread.sleep(5000);







    }

}
