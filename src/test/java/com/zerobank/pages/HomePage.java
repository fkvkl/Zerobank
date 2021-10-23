package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage {
    public HomePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "aa_accountId")
    public WebElement accountDropdown;

    @FindBy(css = "#all_transactions_for_account>table>thead>tr>th")
    public List<WebElement> transactionTable;

    @FindBy(css="h2")
    public List<WebElement> accountTypes;

    @FindBy(xpath="(//table)[3]//th")
    public List<WebElement> creditAccountTabs;

    @FindBy(id="sp_payee")
    public WebElement payee;

    @FindBy(id="sp_account")
    public WebElement payAccount;

    @FindBy(id="sp_amount")
    public WebElement amount;

    @FindBy(id="sp_date")
    public WebElement date;

    @FindBy(id="sp_date")
    public WebElement description;

    @FindBy(id="pay_saved_payees")
    public WebElement pay;

    @FindBy(css="#alert_content>span")
    public WebElement successMessage;

    @FindBy(xpath="//a[.='Find Transactions']")
    public WebElement findTransactions;

    @FindBy(id = "aa_fromDate")
    public WebElement fromDate;

    @FindBy(id = "aa_toDate")
    public WebElement toDate;

    @FindBy(css=".pull-right>button")
    public WebElement find;

    @FindBy(id = "aa_description")
    public WebElement transDescription;

    @FindBy(xpath = "//a[.='Add New Payee']")
    public WebElement addNewPayee;

    @FindBy(id = "np_new_payee_name")
    public WebElement payeeName;

    @FindBy(id = "np_new_payee_address")
    public WebElement payeeAddress;

    @FindBy(id = "np_new_payee_account")
    public WebElement payeeAccount;

    @FindBy(id = "np_new_payee_details")
    public WebElement payeeDetails;

    @FindBy(id = "pc_currency")
    public List<WebElement> currency;

    @FindBy(id = "purchase_cash")
    public WebElement purchase;

    @FindBy(id = "pc_calculate_costs")
    public WebElement calculate;

    @FindBy(css="div#alert_content")
    public WebElement payeeSuccessMessage;

    @FindBy(xpath = "//a[.='Purchase Foreign Currency']")
    public WebElement purchaseForeignCurrency;


    @FindBy(xpath="//div[@id=\'filtered_transactions_for_account\']//td[1]")
    public List<WebElement> transactionDates;

    @FindBy(xpath="//div[@id=\'filtered_transactions_for_account\']//td[2]")
    public List<WebElement> transactionDescription;

    @FindBy(xpath="//div[@id=\'filtered_transactions_for_account\']//td[3]")
    public List<WebElement> transactionDeposit;

    @FindBy(xpath="//div[@id=\'filtered_transactions_for_account\']//td[4]")
    public List<WebElement> transactionWithdrawal;

    public void navigateToTab(String tab) {
        String tabLocator = "//a[contains(text(),'" + tab + "')]";

        try {
            BrowserUtils.waitForClickablility(By.xpath(tabLocator), 5);
            WebElement tabElement = Driver.get().findElement(By.xpath(tabLocator));
            new Actions(Driver.get()).moveToElement(tabElement).pause(200).doubleClick(tabElement).build().perform();
        } catch (Exception e) {
            BrowserUtils.clickWithWait(By.xpath(tabLocator), 5);
        }
    }

    public void navigateSubMenu(String menu) {
        String menuLocator = "//a[.='"+menu+"']";

        try {
            BrowserUtils.waitForClickablility(By.xpath(menuLocator), 5);
            WebElement tabElement = Driver.get().findElement(By.xpath(menuLocator));
            new Actions(Driver.get()).moveToElement(tabElement).pause(200).doubleClick(tabElement).build().perform();
        } catch (Exception e) {
            BrowserUtils.clickWithWait(By.xpath(menuLocator), 5);
        }
    }

    public String getErrorMessage(){

        JavascriptExecutor js = (JavascriptExecutor)Driver.get();
        Boolean is_valid = (Boolean)js.executeScript("return arguments[0].checkValidity();", amount);
        String message = (String)js.executeScript("return arguments[0].validationMessage;", amount);

        return message;


    }

}
