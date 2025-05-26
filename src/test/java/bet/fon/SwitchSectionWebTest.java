package bet.fon;

import io.qameta.allure.Allure;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class SwitchSectionWebTest extends TestBase {
    @ParameterizedTest
    @ValueSource(strings = {"Sport", "Лотереи", "Игры 24/7"})
    void shouldSwitchMainSections(String section) {
        Allure.step("Открываем главную страницу", () -> open("https://fon.bet/"));

        Allure.step("Кликаем на раздел " + section, () ->
                $("[data-testid='btn." + section + "']").click()
        );

        Allure.step("Проверяем URL", () -> {
            String expectedPath = switch (section) {
                case "Sport" -> "/sports";
                case "Лотереи" -> "/lottery";
                case "Игры 24/7" -> "/quick-games";
                default -> throw new IllegalStateException("Неизвестный раздел: " + section);
            };
            webdriver().shouldHave(urlContaining(expectedPath));
        });
    }
}

