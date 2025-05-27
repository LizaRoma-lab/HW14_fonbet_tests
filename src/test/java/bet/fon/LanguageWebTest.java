package bet.fon;

import bet.fon.data.Language;
import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static io.qameta.allure.Allure.step;

public class LanguageWebTest extends TestBase{

    @EnumSource(Language.class)
    @ParameterizedTest
    void fonbetSiteShouldDisplayCorrectTest(Language language) {
        step("Открываем главную страницу Fonbet", () -> {
            open("https://fon.bet/");
        });

        step("Открываем меню выбора языка", () -> {
            $("[data-testid='btn.languages']").click();
        });

        step("Выбираем язык: " + language.name(), () -> {
            $$("[data-testid='dd.language']")
                    .find(text(language.name()))
                    .click();
        });

        step("Проверяем, что текст кнопки 'Log In' изменился на '" + language.description + "'", () -> {
            $("[data-testid='btn.logIn']").shouldHave(text(language.description));
        });
    }

}