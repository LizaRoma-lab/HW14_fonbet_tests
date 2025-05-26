package bet.fon;

import io.qameta.allure.Allure;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchWebTest extends TestBase {

    @ValueSource(strings = {
            "Циципас",
            "Филс",
            "Алькарас"
    })
    @ParameterizedTest
    void successfulSearchTest(String testData) {
        Allure.step("Открываем главную страницу Fonbet", () -> {
            open("https://fon.bet/");
            $("body").shouldBe(visible);
        });

        Allure.step("Открываем поисковую строку", () -> {
            $("[data-testid='btn.search']")
                    .shouldBe(visible, enabled)
                    .click();
            $("[data-testid=stringEdit]").shouldBe(visible);
        });

        Allure.step("Ищем: " + testData, () -> {
            $("[data-testid=stringEdit]")
                    .setValue(testData)
                    .pressEnter();
        });

        Allure.step("Проверяем результаты поиска", () -> {
            $("[data-testid=teams]")
                    .shouldBe(visible)
                    .shouldHave(text(testData));
        });
        
    }
}
