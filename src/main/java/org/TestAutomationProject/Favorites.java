package org.TestAutomationProject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Favorites {
    private WebDriver driver;
    private By SearchBar = By.xpath("//*[@id='app']//input[@type='text']");



    public Favorites(WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        if (!driver.getCurrentUrl().contains("favorites")) {
            throw new IllegalStateException("This is not the Login Page. Current page: " + driver.getCurrentUrl());
        }
    }


    public Favorites SearchByTitle(String Title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(SearchBar));
        WebElement searchField = driver.findElement(SearchBar);
        searchField.sendKeys(Title);
        searchField.sendKeys(Keys.RETURN);
        return this;
    }

    public String getFirstImageTitle() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By firstImageTitleLocator = By.cssSelector("button.action-title-edit");
        WebElement firstImageTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstImageTitleLocator));

        return firstImageTitleElement.getText();
    }


    public boolean isTitleInFavorites(String title) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            By favoriteItemsLocator = By.xpath("//div[@class='favorite-item']"); // Adjust XPath if needed
            List<WebElement> favoriteItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(favoriteItemsLocator));
            if (favoriteItems.isEmpty()) {
                return false;
            }
            By favoriteTitleLocator = By.xpath("//div[@class='favorite-item']//h3[text()='" + title + "']");
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(favoriteTitleLocator));

            return titleElement != null;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

}
