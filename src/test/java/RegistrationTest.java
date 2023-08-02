import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProfilePage;
import PageObjects.RegistrationPage;
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

public class RegistrationTest extends WebDriverSettings {
    private static User user;
    private static UserClient userClient;
    private static String accessToken;

    @BeforeClass
    public static void beforeSetUp() {
        user = UserGenerator.generateUser();
        userClient = new UserClient();
    }

    @AfterClass
    public static void cleanUp() {
        accessToken = loginUser(user).extract().path("accessToken").toString();
        if (accessToken !=null)
            userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Регистрация с корректными данными")
    @Description("Проверяем возможность регистрации указав корректные данные")
    public void registrationWithCorrectData() {
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
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
        registrationPage.setPassword(user.getPassword());
        registrationPage.clickRegistrationButton();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.loginUser(user);

        Assert.assertTrue(profilePage.profileTabIsEnabled());
    }

    @Test
    @DisplayName("Регистрация с некорректными данными")
    @Description("Проверяем невозможность регистрации указав пароль менее 6 символов")
    public void registrationWithIncorrectData() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        mainPage.openSite();
        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
        mainPage.clickLoginButton();

        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
        loginPage.clickRegistrationLink();

        MatcherAssert.assertThat(registrationPage.getRegistrationTextFromHeader(), equalTo("Регистрация"));
        registrationPage.setName(user.getName());
        registrationPage.setEmail(user.getEmail());
        registrationPage.setPassword(UserGenerator.generateWrongPassword());
        registrationPage.clickRegistrationButton();

        MatcherAssert.assertThat(registrationPage.getInvalidPasswordText(), equalTo("Некорректный пароль"));
    }
}

