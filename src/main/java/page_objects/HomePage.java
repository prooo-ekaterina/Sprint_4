package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }


    private final By questionsBlock = By.className("accordion");


    public void openWebsite() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }


    public void scrollToQuestionsBlock() {
        WebElement questionsBlockElement = wait.until(ExpectedConditions.presenceOfElementLocated(questionsBlock));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionsBlockElement);
    }


    public void clickOnQuestion(String questionID) {
        String xpath = "//div[contains(@class, 'accordion')]//*[@id='" + questionID + "']";
        By locator = By.xpath(xpath);
        WebElement questionElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        questionElement.click();
    }


    public String getAnswerText(String panelId) {
        By locator = By.id(panelId);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();

    }
}
