package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResetPasswordPage {
    public  ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    private WebDriver driver;

    /** Заголовок "Восстановление пароля" **/
    private final By resetPasswordText = By.xpath(".//h2[text()='Восстановление пароля']");

    /** Ссылка "Войти" **/
    private final By loginLink = By.className("Auth_link__1fOlj");

    @Step("Проверка наличия заголовка 'Восстановление пароля'")
    public String getResetPasswordTextFromHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(resetPasswordText));
        return driver.findElement(resetPasswordText).getText();
    }

    @Step("Нажать на ссылку 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
