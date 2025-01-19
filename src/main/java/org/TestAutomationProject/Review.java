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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement ApproveButton = wait.until(ExpectedConditions.elementToBeClickable(approveButton));
        ApproveButton.click();
        return this;
    }

    public void GoToLandingPage() {
        driver.get("http://localhost:2342/library/browse");
        new Landingpage(driver);
    }


    public static class DriverFactory {

        private static final String grid_url = System.getenv("GRID_URL");

        private static final String browser = Optional
                .ofNullable(System.getenv("BROWSER"))
                .orElse("chrome");

        public static WebDriver getDriver() {
            if (grid_url != null) {
                return getRemoteDriver(browser);
            } else {
                return getLocalDriver(browser);
            }
        }

        private static WebDriver getRemoteDriver(String browser) {
            URL hubUrl;
            try {
                hubUrl = new URI(grid_url).toURL();
            } catch (URISyntaxException | MalformedURLException err) {
                throw new IllegalArgumentException("Invalid grid URL");
            }

            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                return new RemoteWebDriver(hubUrl, options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("-headless");
                return new RemoteWebDriver(hubUrl, options);
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        private static WebDriver getLocalDriver(String browser) {

            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();

                // Add the argument for incognito mode
                options.addArguments("--incognito");

                // Initialize ChromeDriver instance with options
                return new ChromeDriver(options);


            } else if (browser.equalsIgnoreCase("firefox")) {
                return new FirefoxDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
    }
}
