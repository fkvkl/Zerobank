package com.zerobank.stepdefinitions;

import com.zerobank.pages.HomePage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;


public class AccountActivityNavigationSteps {
    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        new LoginPage().login();
    }

    @When("the user clicks on {string} link on the Account Summary page")
    public void the_user_clicks_on_link_on_the_Account_Summary_page(String menu) {
        new HomePage().navigateSubMenu(menu);
    }

    @Then("the {string} page should be displayed")
    public void the_page_should_be_displayed(String expected) {
        BrowserUtils.waitFor(2);
        String actual = Driver.get().getTitle();
        Assert.assertEquals("Zero - "+expected, actual);
    }

    @Then("Account drop down should have {string} selected")
    public void account_drop_down_should_have_selected(String expected) {
        Select select = new Select(new HomePage().accountDropdown);
        String actual = select.getFirstSelectedOption().getText();
        Assert.assertEquals(expected, actual);
    }


}