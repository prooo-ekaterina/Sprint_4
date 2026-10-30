package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private WebDriver driver;

    private WebDriverWait wait;


    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openWebsite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }


    private static final By cookieButton = By.id("rcc-confirm-button");

    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    private final By orderButtonTop = By.xpath(".//div[@class='Header_Header__214zg']//button[text()='Заказать']");
    private final By orderButtonMiddle = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");

    public void clickOnOrderButtonTop() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
    }
    public void clickOnOrderButtonMiddle() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonMiddle)).click();
    }

    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By familyNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//div[@class='Order_NextButton__1_rCA']//button[text()='Далее']");


    public void fillInName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void fillInFamilyName(String familyName) {
        driver.findElement(familyNameField).sendKeys(familyName);
    }

    public void fillInAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }


    public void chooseMetroStation(String metro) {
        driver.findElement(metroStationField).click();
        By stationLocator = By.cssSelector("[data-value='" + metro + "']");
        WebElement stationElement = wait.until(ExpectedConditions.elementToBeClickable(stationLocator));
        stationElement.click();

    }


    public void fillInPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }


    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }


    public void fillInPersonalDetailsPage(String name, String familyName, String address, String metro, String phoneNumber) {
        fillInName(name);
        fillInFamilyName(familyName);
        fillInAddress(address);
        chooseMetroStation(metro);
        fillInPhoneNumber(phoneNumber);
        clickNextButton();
}



    private final By whenField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By durationFied = By.className("Dropdown-placeholder");
    private final By colourField = By.className("Checkbox_Label__3wxSf");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[contains(@class, 'Button_Middle') and text()='Заказать']");




    public void chooseWhen(String date) {
        driver.findElement(whenField).click();
        WebElement chooseDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='" + date + "']")
        ));
        chooseDay.click();

    }

    public void chooseDuration(String duration) {
        driver.findElement(durationFied).click();
        WebElement chooseDuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'Dropdown-option') and text()='" + duration + "']")));
        chooseDuration.click();
    }

    public void chooseColour() {
        driver.findElement(colourField).click();
    }

    public void fillInCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void fillInAboutRentPage(String date, String duration, boolean colour, String comment) {
        chooseWhen(date);
        chooseDuration(duration);
        chooseColour();
        fillInCommentField(comment);
        clickOrderButton();
    }


    private final By yesButton = By.xpath("//button[text()='Да']");


    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }


    private final By orderCreatedWindow = By.xpath("//div[contains(text(), 'Заказ оформлен')]");

    public boolean isOrderSuccessful() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderCreatedWindow));
        return driver.findElement(orderCreatedWindow).isDisplayed();
    }




}
