package page.objects;

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

    private By questionID(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By panelID(int index) {
        return By.id("accordion__panel-" + index);
    }

    public void clickOnQuestion(int index) {
        By locator = questionID(index);
        WebElement questionElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        questionElement.click();
    }


    public String getAnswerText(int index) {
        By locator = panelID(index);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();

    }
}
