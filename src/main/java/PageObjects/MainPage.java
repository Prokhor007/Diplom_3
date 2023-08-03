package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class MainPage {
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private WebDriver driver;

    /** Кнопка "Войти в аккаунт" **/
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    /** Ссылка "Личный кабинет"  **/
    private final By profileLink = By.xpath(".//p[text()='Личный Кабинет']");

    /** Кнопка "Конструктор" **/
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");

    /** Логотип "Stellar Burgers" **/
    private final By logo = By.className("AppHeader_header__logo__2D0X2");

    /** Заголовок "Соберите бургер" **/
    private final By сreateBurgerText = By.xpath(".//h1[text()='Соберите бургер']");

    /** Раздел "Булки" **/
    private final By bunsTab = By.xpath(".//span[text()='Булки']");
    private final By bunsTabSelected = By.xpath(".//div[(contains(span/text(),'Булки')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");

    /** Раздел "Соусы" **/
    private final By saucesTab = By.xpath(".//span[text()='Соусы']");
    private final By saucesTabSelected = By.xpath(".//div[(contains(span/text(),'Соусы')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");

    /** Раздел "Начинки" **/
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']");
    private final By fillingsTabSelected = By.xpath(".//div[(contains(span/text(),'Начинки')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");

    @Step("Открытие сайта")
    public MainPage openSite() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        return this;
    }

    @Step("Проверка наличия заголовка 'Соберите бургер'")
    public String getCreateBurgerText() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(сreateBurgerText));
        return driver.findElement(сreateBurgerText).getText();
    }

    @Step("Нажать на лого")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Нажать на кнопку 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажать на кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажать на ссылку 'Личный кабинет'")
    public void clickProfileLink() {
        driver.findElement(profileLink).click();
    }

    @Step("Нажать на вкладку 'Булки'")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Нажать на вкладку 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Нажать на вкладку 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Выбрана вкладка 'Булки'")
    public boolean bunsButtonIsEnabled() {
        return driver.findElement(bunsTabSelected).isEnabled();
    }

    @Step("Выбрана вкладка 'Соусы'")
    public boolean saucesButtonIsEnabled() {
        return driver.findElement(saucesTabSelected).isEnabled();
    }

    @Step("Выбрана вкладка 'Начинки'")
    public boolean fillingsButtonIsEnabled() {
        return driver.findElement(fillingsTabSelected).isEnabled();
    }
}
