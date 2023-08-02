package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSettings {

    public WebDriver driver;

    @Before
    public void setUp() {
        /** Подключение Хром браузера **/
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*, --no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        /** Подключение Яндекс браузера **/
        /*
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*, --no-sandbox", "--headless", "--disable-dev-shm-usage");
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        driver = new ChromeDriver(options);
        */
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}