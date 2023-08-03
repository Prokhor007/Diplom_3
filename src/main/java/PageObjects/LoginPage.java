package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import User.User;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public  LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    /** Заголовок "Вход" **/
    private final By loginText = By.xpath(".//h2[text()='Вход']");

    /** Поле "Email" **/
    private final By inputEmail = By.xpath(".//label[text()='Email']/../input");

    /** Поле "Пароль" **/
    private final By inputPassword = By.xpath(".//label[text()='Пароль']/../input");

    /** Кнопка "Войти" **/
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    /** Ссылка "Зарегистрироваться" **/
    private final By registrationLink = By.xpath(".//a[text()='Зарегистрироваться']");

    /** Ссылка "Восстановить пароль" **/
    private final By resetPasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    /** Ссылка "Личный кабинет" **/
    private final By profileLink = By.xpath(".//p[text()='Личный Кабинет']");

    @Step("Проверка наличия заголовка 'Вход'")
    public String getLoginTextFromHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginText));
        return driver.findElement(loginText).getText();
    }

    @Step("Заполнить поле 'Email'")
    public void setEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    @Step("Заполнить поле 'Пароль'")
    public void setPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    @Step("Нажать на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажать на ссылку 'Зарегистрироваться'")
    public void clickRegistrationLink() {
        driver.findElement(registrationLink).click();
    }

    @Step("Нажать на ссылку 'Восстановить пароль'")
    public void clickResetPasswordLink() {
        driver.findElement(resetPasswordLink).click();
    }

    @Step("Нажать на ссылку 'Личный кабинет'")
    public void clickProfileLink() {
        driver.findElement(profileLink).click();
    }

    @Step("Авторизация пользователя")
    public void loginUser(User user){
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickLoginButton();
        clickProfileLink();
    }
}
