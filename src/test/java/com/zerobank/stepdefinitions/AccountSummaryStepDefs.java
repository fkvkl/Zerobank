package com.zerobank.stepdefinitions;

import com.zerobank.pages.HomePage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class AccountSummaryStepDefs {
    @Then(":Account summary page should have to following	account	types:")
    public void account_summary_page_should_have_to_following_account_types(List<String> expected) {
        List<String> actual = BrowserUtils.getElementsText(new HomePage().accountTypes);
        Assert.assertEquals(expected,actual);

    }

    @Then(": Credit Accounts table must have following column names:")
    public void credit_Accounts_table_must_have_following_column_names(List<String> expected) {
        List<String> actual = BrowserUtils.getElementsText(new HomePage().creditAccountTabs);
        Assert.assertEquals(expected,actual);

    }

}
