import PageObjects.MainPage;
import Utilities.WebDriverSettings;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class ConstructorTest extends WebDriverSettings {
    @Test
    @DisplayName("Переход на вкладку 'Булки'")
    @Description("Проверяем возможность перехода на вкладку 'Булки'")
    public void chooseBunsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();
        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        Assert.assertTrue(mainPage.bunsButtonIsEnabled());
    }

    @Test
    @DisplayName("Переход на вкладку 'Соусы'")
    @Description("Проверяем возможность перехода на вкладку 'Соусы'")
    public void chooseSaucesTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();
        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
        mainPage.clickSaucesTab();
        Assert.assertTrue(mainPage.saucesButtonIsEnabled());
    }

    @Test
    @DisplayName("Переход на вкладку 'Ингредиенты'")
    @Description("Проверяем возможность перехода на вкладку 'Ингредиенты'")
    public void chooseFillingsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();
        MatcherAssert.assertThat(mainPage.getCreateBurgerText(), equalTo("Соберите бургер"));
        mainPage.clickFillingsTab();
        Assert.assertTrue(mainPage.fillingsButtonIsEnabled());
    }
}
