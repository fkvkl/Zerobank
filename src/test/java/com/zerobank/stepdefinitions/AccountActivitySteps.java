package com.zerobank.stepdefinitions;

import com.zerobank.pages.HomePage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class AccountActivitySteps {
    @Given(": the user should be already logged in")
    public void the_user_should_be_already_logged_in() {
        new LoginPage().login();
    }

    @Given(": the user navigates to {string} tab")
    public void the_user_navigates_to_tab(String tab) {
        new HomePage().navigateToTab(tab);
    }

    @Then(": page should have the title {string}")
    public void page_should_have_the_title(String expected) {
        BrowserUtils.waitFor(2);
        String actual = Driver.get().getTitle();
        Assert.assertEquals(expected, actual);
    }

    @Then(": In the Account drop down default option should be {string}")
    public void in_the_Account_drop_down_default_option_should_be(String expected) {
        Select select = new Select(new HomePage().accountDropdown);
        String actual = select.getFirstSelectedOption().getText();
        Assert.assertEquals(expected, actual);

    }

    @Then(": Account drop down should have the following options:")
    public void account_drop_down_should_have_the_following_options(List<String> expected) {
        Select select = new Select(new HomePage().accountDropdown);
        List<WebElement> dropElements = select.getOptions();
        List<String> actual = new ArrayList<>();
        for (WebElement dropElement : dropElements) {
            actual.add(dropElement.getText());
        }
        Assert.assertEquals(expected, actual);

    }

    @Then(": Transactions table should have following column names:")
    public void transactions_table_should_have_following_column_names(List<String> expected) {
        List<String> actual = BrowserUtils.getElementsText(new HomePage().transactionTable);
        Assert.assertEquals(expected, actual);

    }
}
