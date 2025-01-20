package org.TestAutomationProject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Optional;

public class Review {


    public static final String baseURL = "https://1761-83-229-24-163.ngrok-free.app/library/browse";
    // LOCAL :  public static final String baseURL = http://localhost:2342/library/browse";
    private WebDriver driver;
    private By approveButton = By.cssSelector(".action-approve");


    public Review(WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        if (!driver.getCurrentUrl().contains("review")) {
            throw new IllegalStateException("This is not the Login Page. Current page: " + driver.getCurrentUrl());
        }
    }


    public Review approveimage() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ApproveButton = wait.until(ExpectedConditions.elementToBeClickable(approveButton));
        ApproveButton.click();
        return this;
    }

    public void GoToLandingPage() {
        driver.get(baseURL);
        new Landingpage(driver);
    }
}
