package com.zerobank.stepdefinitions;

import com.zerobank.pages.HomePage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FindTransactionsStepDefs {

    HomePage homePage = new HomePage();

    @Given("the user accesses the Find Transactions tab")
    public void the_user_accesses_the_Find_Transactions_tab() {

        homePage.navigateToTab("Account Activity");
        homePage.findTransactions.click();
    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String fromDate, String toDate) {
        homePage.fromDate.clear();
        homePage.toDate.clear();
        homePage.fromDate.sendKeys(fromDate);
        homePage.toDate.sendKeys(toDate);
    }

    @When("clicks find")
    public void clicks_find() {
        homePage.find.click();
    }

    @Then("results table should only show transactions dates between {string} to {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String fromDate, String toDate) throws ParseException {
        BrowserUtils.waitFor(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date from = sdf.parse(fromDate);
        Date to = sdf.parse(toDate);

        boolean result = true;

        for (String date : BrowserUtils.getElementsText(homePage.transactionDates)) {
            Date d = sdf.parse(date);
            if (d.compareTo(from) < 0 || d.compareTo(to) > 0) {
                result = false;
                break;
            }
        }
        System.out.println("result = " + result);
        List<String> elementsText = BrowserUtils.getElementsText(homePage.transactionDates);
        System.out.println("elementsText = " + elementsText);
        Assert.assertTrue(result);


    }

    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() throws ParseException {
        List<Date> dates = new ArrayList<>();
        boolean result = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (String str : BrowserUtils.getElementsText(homePage.transactionDates)) {
            Date dt = sdf.parse(str);
            dates.add(dt);
        }
        for (int i = 0; i < dates.size() - 1; i++) {
            if (dates.get(i).compareTo(dates.get(i + 1)) < 0) {
                result = false;
                break;
            }
        }
        Assert.assertTrue(result);

    }

    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated(String date) throws ParseException {
        List<Date> dates = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (String str : BrowserUtils.getElementsText(homePage.transactionDates)) {
            Date dt = sdf.parse(str);
            dates.add(dt);
        }
        Date dateCheck = sdf.parse(date);
        System.out.println("dates = " + dates);
        Assert.assertFalse(dates.contains(dateCheck));
    }

    @When("the user enters description {string}")
    public void the_user_enters_description(String string) {
        homePage.transDescription.clear();
        homePage.transDescription.sendKeys(string);
    }

    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String string) {
        BrowserUtils.waitFor(2);
        boolean result = true;
        for (String s : BrowserUtils.getElementsText(homePage.transactionDescription)) {
            if (!s.contains(string)) {
                result = false;
                break;
            }
        }
        Assert.assertTrue(result);
    }

    @Then("results table should show at least one result under {string}")
    public void results_table_should_show_at_least_one_result_under(String string) {
        List<WebElement> list=null;
        if(string.equals("Deposit")){
            list= homePage.transactionDeposit;
        }
        else if(string.equals("Withdrawal")){
            list=homePage.transactionWithdrawal;
        }
        Assert.assertTrue(list.size()>0);
    }



}
