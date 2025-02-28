package org.TestAutomationProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Library {
    private WebDriver driver;
    private By StartButton = By.cssSelector(".action-index.primary-button");



    public Library(WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        if (!driver.getCurrentUrl().contains("library")) {
            throw new IllegalStateException("This is not the Library Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public Library StartIndexing() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(StartButton));
        WebElement myStartButton = driver.findElement(StartButton);
        myStartButton.click();
        try {
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            WebElement indexingMessage = longWait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Indexing completed in')]"))
            );
        } catch (Exception e) {
            System.err.println("Failed: Indexing message did not appear within 60 seconds.");
            throw new RuntimeException("Indexing did not complete in time.");
        }
        return this;
    }



}
