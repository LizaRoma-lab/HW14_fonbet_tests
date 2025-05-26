package bet.fon;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void setup() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*",
                "--disable-browser-side-navigation",
                "--disable-gpu",
                "--incognito",
                "--window-size=1920,1080"
        );

        // Важные настройки Selenide
        Configuration.browserCapabilities = options;
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 15000;
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
    }

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "https://fon.bet";
        pageLoadStrategy = "eager"; // не ждать полной загрузки страницы
        timeout = 10000; // 10 секунд для ожидания элементов
        pageLoadTimeout = 30000; // 30 секунд для загрузки страницы
        browserSize = "1920x1080";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }

    @AfterEach
    void shutDown() {
        closeWebDriver();
    }
}
