package org.TestAutomationProject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class Landingpage {

    //public static final String URL = "http://localhost:2342/";
   public static final String URL = "https://60ab-83-229-24-161.ngrok-free.app/";


    public static final String baseURL = URL + "library/browse";
    public static final String LoginURL =  URL +  "library/login";
    public static final String ReviewURL = URL +  "library/review";
    public static final String ArchiveUrl = URL +  "library/archive";
    public static final String LibraryURL = URL + "library/index";

    private WebDriver driver;
    private By FavoriteSection = By.cssSelector("a[href='/library/favorites']");
    private By SearchBar = By.xpath("//*[@id='app']//input[@type='text']");
    private By ReloadIcon = By.className("action-reload");
    private By UpdateToast = By.id("p-notify");
    private By HeartIcon = By.xpath("//div[@data-index='0']//button[@class='input-favorite']");
    private By FullHeartIcon = By.xpath("//div[@data-index='0']//button[@class='input-favorite']");
    private By UploadIcon = By.cssSelector(".v-btn__content .v-icon.material-icons");
    private By UploadIcon2 = By.xpath("//button[contains(@class, 'v-btn') and .//i[contains(@class, 'material-icons') and text()='cloud_upload']]");
    private By dropdownArrow = By.xpath("//i[@class='v-icon material-icons theme--light' and text()='keyboard_arrow_down']");
    private By CountryDropDown = By.className("p-countries-select");
    private By CameraDropDown = By.className("p-camera-select");
    private By TimeDropDown = By.className("p-time-select");
    private By YearDropDown = By.className("p-year-select");
    private By MonthDropDown = By.className("p-month-select");
    private By ColorDropDown = By.className("p-color-select");
    private By CategoryDropDown = By.className("p-category-select");
    private By ToggleViewlist = By.xpath("//i[@class='v-icon material-icons theme--light' and text()='view_list']");
    private By ToggleViewcomfy = By.xpath("//i[@class='v-icon material-icons theme--light' and text()='view_comfy']");
    private By ToggleViewcolumn = By.xpath("//i[@class='v-icon material-icons theme--light' and text()='view_column']");
    private By SelectImage = By.cssSelector("div[data-index='0'] button.input-select i.select-off");
    private By Optionsdrop = By.className("action-menu");
    private By actionshare = By.className("action-share");
    private By cancelshare = By.className("action-cancel");
    private By firstImageHover = By.cssSelector("div[data-index='0']");
    private By QRButton = By.className("action-qr");
    private By QRImage = By.cssSelector(".qr-container img");
    private By UID = By.cssSelector("div.result.card.is-photo");


    public Landingpage(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("AI-Powered Photos App"));
    }

    public Favorites GoToFavorites() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement favoriteLink = wait.until(ExpectedConditions.elementToBeClickable(FavoriteSection));
        favoriteLink.click();
        return new Favorites(driver);
    }

    public Review GoToreview() {
        driver.get(ReviewURL);
        return new Review(driver);
    }

    public Archive GoToArchive(){
        driver.get(ArchiveUrl);
        try {
            Thread.sleep(2000); // Pause for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the InterruptedException
        }
        return new Archive(driver);
    }

    public Library GoToLibrary(){
        driver.get(LibraryURL);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Library(driver);
    }




    public WebElement GenerateQR(String Title){
        SearchByTitle(Title).HoverFirstImage().ClickFirstImage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(QRButton));
        WebElement MyQRbutton = driver.findElement(QRButton);
        MyQRbutton.click();
        WebElement qrImage = wait.until(ExpectedConditions.visibilityOfElementLocated(QRImage));

        return qrImage;
    }


    public Landingpage SearchByTitle(String Title) {

        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the exception
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(SearchBar));
        WebElement searchField = driver.findElement(SearchBar);
        searchField.sendKeys(Title);
        searchField.sendKeys(Keys.RETURN);
        return this;
    }

    public Landingpage Favoritefirstimage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(HeartIcon));
        WebElement heartIcon = driver.findElement(HeartIcon);
        heartIcon.click();
        return this;
    }

    public Landingpage UnFavoritefirstimage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FullHeartIcon));
        WebElement fullheartIcon = driver.findElement(FullHeartIcon);
        fullheartIcon.click();
        return this;
    }

    public String GetinitialIcon(){

        try {
            Thread.sleep(1000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the exception
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".input-favorite i")));
        WebElement initialIcon = driver.findElement(By.cssSelector(".input-favorite i"));
        return initialIcon.getText();
    }

    public String GetfinalIcon(){

        try {
            Thread.sleep(1000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the exception
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector(".input-favorite i")),"favorite"));
        WebElement finalIcon = driver.findElement(By.cssSelector(".input-favorite i"));
        return finalIcon.getText();
    }

    public String getFirstImageTitle() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By firstImageTitleLocator = By.cssSelector("button.action-title-edit");
        WebElement firstImageTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstImageTitleLocator));

        return firstImageTitleElement.getText();
    }

    public Landingpage ClickReloadButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement ReloadButton = wait.until(ExpectedConditions.elementToBeClickable(ReloadIcon));
        ReloadButton.click();
        return this;
    }

    public boolean IsUpdateToastVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(UpdateToast));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public Landingpage Addimage(String filePath) {
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the exception
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement FirstUploadButton = wait.until(ExpectedConditions.elementToBeClickable(UploadIcon));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", FirstUploadButton);
        FirstUploadButton.click();

        WebElement SecondUploadButton = wait.until(ExpectedConditions.elementToBeClickable(UploadIcon2));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SecondUploadButton);
        SecondUploadButton.click();

        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
        fileInput.sendKeys(filePath);

//        WebElement toastNotification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#p-notify > div > div > span")));
//        wait.until(ExpectedConditions.textToBePresentInElement(toastNotification, "Upload complete"));

        try {
            Thread.sleep(10000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the exception
        }

        return this;
    }

    public boolean isGalleryEmpty() {
        List<WebElement> galleryItems = driver.findElements(By.cssSelector("[data-index]"));
        return galleryItems.isEmpty();
    }

    public boolean isLoggedInSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("library"));
        return driver.getCurrentUrl().contains("library");
    }

    public Landingpage OpenCountryDropDown(String Country) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownArrow));
        WebElement DropdownArrow = driver.findElement(dropdownArrow);
        DropdownArrow.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(CountryDropDown));
        WebElement countryDropDown = driver.findElement(CountryDropDown);
        countryDropDown.click();
        WebElement countryOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='v-list__tile__title' and contains(text(), '" + Country + "')]")
        ));

        countryOption.click();
        return this;
    }

    public Landingpage OpenYearDropDown(String Year){
        WebElement DropdownArrow = driver.findElement(dropdownArrow);
        DropdownArrow.click();

        WebElement YearDropdownArrow = driver.findElement(YearDropDown);
        YearDropdownArrow.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement yearOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='v-list__tile__title' and contains(text(), '" + Year + "')]")
        ));

        yearOption.click();
        return this;
    }

    public Landingpage OpenCameraDropDown(String CameraType){
        WebElement DropdownArrow = driver.findElement(dropdownArrow);
        DropdownArrow.click();
        WebElement cameraDropdownArrow = driver.findElement(CameraDropDown);
        cameraDropdownArrow.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cameraOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='v-list__tile__title' and contains(text(), '" + CameraType + "')]")
        ));
        cameraOption.click();
        return this;
    }

    public Landingpage OpenMonthDropDown(String Month){
        WebElement DropdownArrow = driver.findElement(dropdownArrow);
        DropdownArrow.click();
        WebElement MonthDropdownArrow = driver.findElement(MonthDropDown);
        MonthDropdownArrow.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement MonthOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='v-list__tile__title' and contains(text(), '" + Month + "')]")
        ));
        MonthOption.click();
        return this;
    }

    public Landingpage OpenColorDropDown(String Color){
        WebElement DropdownArrow = driver.findElement(dropdownArrow);
        DropdownArrow.click();
        WebElement colorDropdownArrow = driver.findElement(ColorDropDown);
        colorDropdownArrow.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement colorOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='v-list__tile__title' and contains(text(), '" + Color + "')]")
        ));
        colorOption.click();
        return this;
    }

    public Landingpage OpenCategoryDropDown(String Category){
        WebElement DropdownArrow = driver.findElement(dropdownArrow);
        DropdownArrow.click();
        WebElement categoryDropdownArrow = driver.findElement(CategoryDropDown);
        categoryDropdownArrow.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement categoryOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='v-list__tile__title' and contains(text(), '" + Category + "')]")
        ));
        categoryOption.click();
        return this;
    }

    public Landingpage OpenTimeDropDown(String Time){
        WebElement DropdownArrow = driver.findElement(dropdownArrow);
        DropdownArrow.click();
        WebElement timeDropdownArrow = driver.findElement(TimeDropDown);
        timeDropdownArrow.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement timeOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='v-list__tile__title' and contains(text(), '" + Time + "')]")
        ));
        timeOption.click();
        return this;
    }

    public Landingpage HoverFirstImage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement firstImage = wait.until(ExpectedConditions.elementToBeClickable(firstImageHover));
        Actions actions = new Actions(driver);
        actions.moveToElement(firstImage).perform();
        return this;
    }

    public Landingpage ClickFirstImage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement firstImage = wait.until(ExpectedConditions.elementToBeClickable(firstImageHover));
        Actions actions = new Actions(driver);
        actions.moveToElement(firstImage).click().perform(); // Ensure the action is executed
        return this;
    }


    public Landingpage select(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement selectButton = wait.until(ExpectedConditions.elementToBeClickable(SelectImage));
        selectButton.click();
        return this;
    }

    public Landingpage OpenOptions() {
        try {
            Thread.sleep(1000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the exception
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement imageoption1 = wait.until(ExpectedConditions.elementToBeClickable(Optionsdrop));
        imageoption1.click();
        return this;
    }

    public Landingpage ClearPick(){
        WebElement pressclose = driver.findElement(By.className("action-clear"));
        pressclose.click();
        return this;
    }

    public Landingpage share(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement sharebutton = wait.until(ExpectedConditions.elementToBeClickable(actionshare));
        sharebutton.click();
        return this;
    }

    public Landingpage sharecancel(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cancelbutton = wait.until(ExpectedConditions.elementToBeClickable(cancelshare));
        cancelbutton.click();
        return this;
    }

    public Landingpage SendToArchive(){
        WebElement pressclose = driver.findElement(By.className("action-archive"));
        pressclose.click();
        return this;
    }

    public boolean ClickToggleView1() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ToggleViewlist));
        WebElement toggleButton = driver.findElement(ToggleViewlist);
        toggleButton.click();
        return true;
    }

    public boolean ClickToggleView2() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ToggleViewcomfy));
        WebElement toggleButton = driver.findElement(ToggleViewcomfy);
        toggleButton.click();
        return true;
    }

    public boolean ClickToggleView3() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ToggleViewcolumn));
        WebElement toggleButton = driver.findElement(ToggleViewcolumn);
        toggleButton.click();
        return true;
    }

    public String getUID() {
        WebElement element = driver.findElement(UID);
        return element.getAttribute("data-uid");
    }
}
