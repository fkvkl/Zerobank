package com.zerobank.stepdefinitions;

import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefinitions {
    @Given("the user navigates to login page")
    public void the_user_navigates_to_login_page() {
        Driver.get().get(ConfigurationReader.get("url"));

    }

    @When("the user enters the credentials: as username:{string} and password: {string}")
    public void the_user_enters_the_credentials_as_username_and_password(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.userInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.signIn.click();


    }

    @Then("the user should be able to login and the Account summary page should be displayed")
    public void the_user_should_be_able_to_login_and_the_Account_summary_page_should_be_displayed() {

        BrowserUtils.waitFor(2);
        String actual = Driver.get().getTitle();
        String expected = "Zero - Account Summary";
        Assert.assertEquals(expected, actual);
    }


    @Then("the user should unable to login and the error	message	{string} should be displayed")
    public void the_user_should_unable_to_login_and_the_error_message_should_be_displayed(String expected) {
        BrowserUtils.waitFor(2);
        String actual = new LoginPage().error.getText();
        Assert.assertEquals(expected, actual);
    }


    @And("the user approves security message")
    public void theUserApprovesSecurityMessage() {

        LoginPage loginPage = new LoginPage();
        loginPage.advanced.click();
        loginPage.proceed.click();
    }
}
