package bet.fon;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.UUID;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void setup() {

        Configuration.baseUrl = "https://fon.bet";
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments(
                        "--remote-allow-origins=*",
                        "--disable-dev-shm-usage",
                        "--no-sandbox",
                        "--user-data-dir=/tmp/chrome-profile-" + UUID.randomUUID()
                );
    }

    @BeforeEach
    void setUp() {
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
