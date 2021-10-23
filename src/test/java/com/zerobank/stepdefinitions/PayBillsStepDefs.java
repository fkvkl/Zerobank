package com.zerobank.stepdefinitions;

import com.zerobank.pages.HomePage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;


public class PayBillsStepDefs {
    HomePage homePage = new HomePage();

    @When(": the user uses selected option as payee, selected option as Account, {string} as Amount, {string} as date and {string} as Description")
    public void the_user_uses_selected_option_as_payee_selected_option_as_Account_as_Amount_as_date_and_as_Description(String amount, String date, String description) {
        Select selectPayee = new Select(homePage.payee);
        selectPayee.getFirstSelectedOption();
        Select selectAccount = new Select(homePage.payAccount);
        selectAccount.getFirstSelectedOption();
        homePage.amount.sendKeys(amount);
        homePage.date.sendKeys(date + Keys.ENTER);
        homePage.description.sendKeys(description);
    }

    @When(": click the Pay button")
    public void click_the_Pay_button() {
        BrowserUtils.waitForClickablility(homePage.pay, 3);
        homePage.pay.click();
    }

    @When(": {string} message should be displayed")
    public void message_should_be_displayed(String expected) {
        String actual = homePage.successMessage.getText();
        Assert.assertEquals(expected, actual);

    }

    @When(": {string} alert should be displayed")
    public void alert_should_be_displayed(String expected) {
        //String errorMessage = homePage.getErrorMessage();
        BrowserUtils.waitFor(3);
        String errorMessage=homePage.amount.getAttribute("validationMessage");
        System.out.println("errorMessage = " + errorMessage);
        Assert.assertEquals(expected,errorMessage);
    }

    @Given("Add New Payee tab")
    public void add_New_Payee_tab() {
        homePage.navigateToTab("Pay Bills");
        homePage.addNewPayee.click();
    }

    @Given("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String, String> list) {
        homePage.payeeName.sendKeys(list.get("Payee Name"));
        homePage.payeeAddress.sendKeys(list.get("Payee Address"));
        homePage.payeeAccount.sendKeys(list.get("Account"));
        homePage.payeeDetails.sendKeys(list.get("Payee details") + Keys.ENTER);
    }

    @Then("message The new payee {string} was successfully created. should be displayed")
    public void message_The_new_payee_was_successfully_created_should_be_displayed(String string) {
        String actual = homePage.payeeSuccessMessage.getText();
        String expected = "The new payee " + string + " was successfully created.";
        Assert.assertEquals(expected, actual);
    }

    @Given("the user accesses the Purchase foreign currency cash tab")
    public void the_user_accesses_the_Purchase_foreign_currency_cash_tab() {
        homePage.navigateToTab("Pay Bills");
        homePage.purchaseForeignCurrency.click();
    }

    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> info) {
        List<String> currencyList = BrowserUtils.getElementsText(homePage.currency);
        Assert.assertEquals(info, currencyList);

    }

    @When("user tries to calculate cost without selecting a currency")
    public void user_tries_to_calculate_cost_without_selecting_a_currency() {
        homePage.calculate.click();
    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        BrowserUtils.waitFor(2);
        Alert alert = Driver.get().switchTo().alert();
        String text = alert.getText();
        alert.accept();
        Assert.assertTrue(text.length() > 0);

    }

    @When("user tries to calculate cost without entering a value")
    public void user_tries_to_calculate_cost_without_entering_a_value() {
        homePage.calculate.click();
    }

}
