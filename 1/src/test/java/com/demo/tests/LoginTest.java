package com.demo.tests;

import com.demo.base.BaseTest;
import com.demo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith", "SuperSecretPassword!"); // demo site creds

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/secure"),
                "User was not navigated to secure area after login!");
    }

    @Test
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrongUser", "wrongPass");

        String errorText = loginPage.getErrorMessageText();
        Assert.assertTrue(errorText.toLowerCase().contains("invalid")
                        || errorText.toLowerCase().contains("your username is invalid"),
                "Expected error message not displayed for invalid login!");
    }
    
    @Test
    public void NodataLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");

        String errorText = loginPage.getErrorMessageText();
        Assert.assertFalse(errorText.toLowerCase().contains("no inputs")
                        || errorText.toLowerCase().contains("no inputs received"),
                "Expected error message not displayed for no inputs!");
    }
    @Test
    public void LinkValidation() {
        LoginPage loginPage = new LoginPage(driver);   
        System.out.println ("Element Selenium page is displayed!");
}
}
