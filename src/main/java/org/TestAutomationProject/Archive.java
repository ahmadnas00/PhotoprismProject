package org.TestAutomationProject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Archive {
    private WebDriver driver;
    private By SearchBar = By.xpath("//*[@id='app']//input[@type='text']");


    private By SelectImage = By.cssSelector("div[data-index='0'] button.input-select i.select-off");
   // private By SelectImage = By.className("select-off");


    private By RestoreButton = By.className("action-restore");
    private By Optionsdrop = By.className("action-menu");

    private By DeleteOption = By.className("action-delete");

    public Archive(WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        if (!driver.getCurrentUrl().contains("archive")) {
            throw new IllegalStateException("This is not the Login Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public Archive SearchByTitle(String Title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(SearchBar));
        WebElement searchField = driver.findElement(SearchBar);
        searchField.sendKeys(Title);
        searchField.sendKeys(Keys.RETURN);
        return this;
    }

    public String getFirstImageTitle() {
        WebElement firstImageTitleElement = driver.findElement(By.xpath("//div[@data-id='0' or @data-index='0']//h3"));
        String imageTitleText = firstImageTitleElement.getText();
        return imageTitleText;
    }

    public Archive HoverFirstImage(){
        By firstImageLocator = By.cssSelector("div[data-index='0']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement firstImage = wait.until(ExpectedConditions.visibilityOfElementLocated(firstImageLocator));

        Actions actions = new Actions(driver);
        actions.moveToElement(firstImage).perform();
        return this;
    }

    public Archive select(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement selectButton = wait.until(ExpectedConditions.elementToBeClickable(SelectImage));
        selectButton.click();
        return this;
    }

    public Archive OpenOptions() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement imageoption1 = wait.until(elementToBeClickable(Optionsdrop));
        imageoption1.click();
        return this;
    }


    public Archive RestoreImage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement imageoption1 = wait.until(elementToBeClickable(RestoreButton));
        imageoption1.click();
        return this;
    }

    public boolean delete(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement imageoption1 = wait.until(elementToBeClickable(DeleteOption));
        imageoption1.click();
        return true;


    }

    public boolean ArchiveIsEmpty(){
        WebElement textElement = driver.findElement(By.cssSelector("h3.body-2.ma-0.pa-0"));
        return textElement.getText().equals("No pictures found");
    }




}
