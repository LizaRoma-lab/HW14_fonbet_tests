package bet.fon;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
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
        ChromeOptions options = new ChromeOptions();
        // Минимальные необходимые параметры
        options.addArguments(
                "--window-size=1920,1080",
                "--disable-dev-shm-usage"
        );

        // Явно отключаем все автоматические настройки
        Configuration.browserCapabilities = new ChromeOptions();
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.remote = ""; // Важно: явно отключаем удаленный драйвер
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
        Selenide.closeWebDriver(); // Полное закрытие браузера после каждого теста
        try {
            Thread.sleep(500); // Даем время для завершения процессов
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
