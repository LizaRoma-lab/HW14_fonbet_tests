package bet.fon;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;


import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void setup() {
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments(
                        "--remote-allow-origins=*",
                        "--disable-dev-shm-usage",
                        "--no-sandbox",
                        "--incognito",  // вместо user-data-dir
                        "--window-size=1920,1080"
                );

        Configuration.timeout = 10000;
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
