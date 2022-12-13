package com.herokuapp.testsuite;

import com.herokuapp.customlisteners.CustomListeners;
import com.herokuapp.pages.LoginPage;
import com.herokuapp.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(CustomListeners.class)
public class LoginPageTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        loginPage = new LoginPage();
    }



    @Test(groups = {"sanity","regression"})
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //Login with valid username
        loginPage.usernameField("tomsmith");

        // Login with valid password
        loginPage.passwordField("SuperSecretPassword!");

        // Click on login button
        loginPage.clickLoginButton();

        //verify Secure Area text
        String expectedText = "Secure Area";
        Assert.assertEquals(loginPage.getSecureAreaMessage(), expectedText, "message not displayed");


    }

    @Test(groups = {"smoke","regression"})
    public void verifyTheUsernameErrorMessage() {
        //Login with valid username
        loginPage.usernameField("tomsmith1");

        // Login with valid password
        loginPage.passwordField("SuperSecretPassword!");

        // Click on login button
        loginPage.clickLoginButton();

        String expectedMessage = "Your username is invalid!\n" +
                "×";
        Assert.assertEquals(loginPage.usernameError(), expectedMessage, "Message Not Displayed");
    }

    @Test(groups = {"regression"})
    public void verifyThePasswordErrorMessage() {
        // enter username
        loginPage.usernameField("tomsmith");

        // Login with valid password
        loginPage.passwordField("SuperSecretPassword");

        // Click on login button
        loginPage.clickLoginButton();

        // Check valid message occur or not
        String expectedMessage = "Your password is invalid!\n" +
                "×";
        Assert.assertEquals(loginPage.passwordError(), expectedMessage, "Message not displayed");


    }
}
