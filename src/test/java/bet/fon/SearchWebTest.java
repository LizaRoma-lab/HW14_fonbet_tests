package bet.fon;

import io.qameta.allure.Allure;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SearchWebTest extends TestBase {

    @ValueSource(strings = {
            "Циципас",
            "Филс",
            "Алькарас"
    })
    @ParameterizedTest
    void successfulSearchTest(String testData) {
        step("Открываем главную страницу Fonbet", () -> {
            open("https://fon.bet/");
            $("body").shouldBe(visible);
        });

        step("Открываем поисковую строку", () -> {
            $("[data-testid='btn.search']")
                    .shouldBe(visible, enabled)
                    .click();
            $("[data-testid=stringEdit]").shouldBe(visible);
        });

        step("Ищем: " + testData, () -> {
            $("[data-testid=stringEdit]")
                    .setValue(testData)
                    .pressEnter();
        });

        step("Проверяем результаты поиска", () -> {
            $("[data-testid=teams]")
                    .shouldBe(visible)
                    .shouldHave(text(testData));
        });

    }
}
