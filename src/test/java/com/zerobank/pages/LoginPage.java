package com.zerobank.pages;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "user_login")
    public WebElement userInput;

    @FindBy(id = "user_password")
    public WebElement passwordInput;

    @FindBy(name = "submit")
    public WebElement signIn;

    @FindBy(id = "details-button")
    public WebElement advanced;

    @FindBy(id = "proceed-link")
    public WebElement proceed;

    @FindBy(css = ".alert-error")
    public WebElement error;

    public void login(){
        Driver.get().get(ConfigurationReader.get("url"));
        Driver.get().findElement(By.id("user_login")).sendKeys("username");
        Driver.get().findElement(By.id("user_password")).sendKeys("password"+ Keys.ENTER);
        Driver.get().findElement(By.id("details-button")).click();
        Driver.get().findElement(By.id("proceed-link")).click();
    }


}
