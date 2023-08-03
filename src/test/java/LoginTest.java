import PageObjects.*;
import User.User;
import User.UserClient;
import User.UserGenerator;
import Utilities.WebDriverSettings;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static User.UserClient.loginUser;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginTest extends WebDriverSettings {
    private static User user;
    private static UserClient userClient;
    private static String accessToken;

    @BeforeClass
    public static void beforeSetUp() {
        user = UserGenerator.generateUser();
        userClient = new UserClient();
        userClient.createUser(user);
    }

    @AfterClass
    public static void cleanUp() {
        accessToken = loginUser(user).extract().path("accessToken").toString();
        if (accessToken != null)
            userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Авторизация по кнопке «Войти в аккаунт» на главной")
    @Description("Проверяем возможность авторизации с главной страницы")
    public void loginOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.openSite();
        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
        mainPage.clickLoginButton();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.loginUser(user);

        Assert.assertTrue(profilePage.profileTabIsEnabled());
    }

    @Test
    @DisplayName("Авторизация через «Личный кабинет»")
    @Description("Проверяем возможность авторизации через Личный кабинет")
    public void loginOnProfilePage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.openSite();
        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
        mainPage.clickProfileLink();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.loginUser(user);

        Assert.assertTrue(profilePage.profileTabIsEnabled());
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме регистрации")
    @Description("Проверяем возможность авторизации через кнопку в форме регистрации")
    public void loginOnRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.openSite();
        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
        mainPage.clickLoginButton();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.clickRegistrationLink();

        MatcherAssert.assertThat(registrationPage.getRegistrationTextFromHeader(), equalTo("Регистрация"));
        registrationPage.clickLoginLink();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.loginUser(user);

        Assert.assertTrue(profilePage.profileTabIsEnabled());
    }

    @Test
    @DisplayName("Авторизация через через кнопку в форме восстановления пароля")
    @Description("Проверяем возможность авторизации через кнопку в форме восстановления пароля")
    public void loginOnResetPasswordPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.openSite();
        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
        mainPage.clickLoginButton();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.clickResetPasswordLink();

        MatcherAssert.assertThat(resetPasswordPage.getResetPasswordTextFromHeader(), equalTo("Восстановление пароля"));
        resetPasswordPage.clickLoginLink();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.loginUser(user);

        Assert.assertTrue(profilePage.profileTabIsEnabled());
    }
}