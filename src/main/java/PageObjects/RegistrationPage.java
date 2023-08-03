package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    private WebDriver driver;

    /** Заголовок "Регистрация" **/
    private final By registrationText = By.xpath(".//h2[text()='Регистрация']");

    /** Поле ввода "Имя" **/
    private final By inputName = By.xpath(".//label[text()='Имя']/../input");

    /** Поле ввода "Email" **/
    private final By inputEmail = By.xpath(".//label[text()='Email']/../input");

    /** Поле ввода "Пароль" **/
    private final By inputPassword = By.xpath(".//label[text()='Пароль']/../input");

    /** Кнопка "Зарегистрироваться" **/
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");

    /** Текст об ошибке "Некорректный пароль" **/
    private final By textInvalidPassword = By.xpath(".//p[text()='Некорректный пароль']");

    /** Ссылка "Войти" **/
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    @Step("Проверка наличия заголовка 'Регистрация'")
    public String getRegistrationTextFromHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationText));
        return driver.findElement(registrationText).getText();
    }

    @Step("Заполняем поле 'Имя'")
    public void setName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    @Step("Заполняем поле 'Email'")
    public void setEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    @Step("Заполняем поле 'Пароль'")
    public void setPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    @Step("Нажимаем кнопку 'Зарегистрироваться'")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Получаем текст об ошибке")
    public String getInvalidPasswordText() {
        return driver.findElement(textInvalidPassword).getText();
    }

    @Step("Нажимаем ссылку 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
