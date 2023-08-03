package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    public  ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    private WebDriver driver;

    /** Вкладка "Профиль" **/
    private final By profileTab = By.xpath(".//a[text()='Профиль']");

    /** Вкладка "Выход" **/
    private final By exitTab = By.xpath(".//button[text()='Выход']");

    @Step("Проверка что выбрана вкладка 'Профиль'")
    public boolean profileTabIsEnabled() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileTab));
        return driver.findElement(profileTab).isEnabled();
    }

    @Step("Проверка нажатия на вкладку 'Выход'")
    public void clickExitTab() {
        driver.findElement(exitTab).click();
    }
}
