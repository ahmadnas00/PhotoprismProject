package org.TestAutomationProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class myLoginpage {
    private WebDriver driver;

    public static final String baseURL = "https://1761-83-229-24-163.ngrok-free.app/library/browse";
    //public static final String baseURL = "http://localhost:2342/library/browse";

    private By emailFieldBy = By.id("auth-username");
    private By passwordFieldBy = By.id("auth-password");
    private By loginButtonBy = By.cssSelector("button.action-confirm.ra-6");

    public myLoginpage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        if (!driver.getCurrentUrl().contains("login")) {
            throw new IllegalStateException("This is not the Login Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public Landingpage loginAsValidUser(String userName, String password) {
        driver.findElement(emailFieldBy).sendKeys(userName);
        driver.findElement(passwordFieldBy).sendKeys(password);
        driver.findElement(loginButtonBy).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(baseURL));
        return new Landingpage(driver);
    }


    public myLoginpage loginInValidUser(String username, String password){
        driver.findElement(emailFieldBy).sendKeys(username);
        driver.findElement(passwordFieldBy).sendKeys(password);
        driver.findElement(loginButtonBy).click();
        return new myLoginpage(driver);
    }

    public boolean isNotLoggedIn(){
        return driver.getCurrentUrl().contains("login");
    }












}
