package bet.fon;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class SwitchSectionWebTest extends TestBase {
    @ParameterizedTest
    @ValueSource(strings = {"Sport", "Лотереи", "Игры 24/7"})

    void shouldSwitchMainSections(String section) {
        open("https://fon.bet/");
        $("[data-testid='btn." + section + "']").click();
        switch (section) {
            case "Sport":
                webdriver().shouldHave(urlContaining("/sports")); //
                break;
            case "Лотереи":
                webdriver().shouldHave(urlContaining("/lottery"));
                break;
            case "Игры 24/7":
                webdriver().shouldHave(urlContaining("/quick-games"));
                break;
        }
    }
}

