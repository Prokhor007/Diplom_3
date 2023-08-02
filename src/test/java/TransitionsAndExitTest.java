import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProfilePage;
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

public class TransitionsAndExitTest extends WebDriverSettings {
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
    @DisplayName("Переход в личный кабинет")
    @Description("Проверяем возможность перехода по клику на «Личный кабинет»")
    public void goToProfilePage() {
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
    @DisplayName("Выход из личного кабинета")
    @Description("Проверяем возможность выхода из личного кабинета")
    public void exitFromProfilePage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.openSite();
        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
        mainPage.clickProfileLink();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.loginUser(user);

        Assert.assertTrue(profilePage.profileTabIsEnabled());
        profilePage.clickExitTab();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по кнопке «Конструктор»")
    @Description("Проверяем возможность перехода по клику на «Конструктор»")
    public void goToConstructorPageWithConstructorButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.openSite();
        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
        mainPage.clickProfileLink();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.loginUser(user);

        Assert.assertTrue(profilePage.profileTabIsEnabled());
        mainPage.clickConstructorButton();

        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор через логотип Stellar Burgers")
    @Description("Проверяем возможность перехода через логотип Stellar Burgers")
    public void goToConstructorPageWithLogo() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.openSite();
        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
        mainPage.clickProfileLink();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.loginUser(user);

        Assert.assertTrue(profilePage.profileTabIsEnabled());
        mainPage.clickLogo();

        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
    }
}
