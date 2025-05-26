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

public class LanguageWebTest extends TestBase{

    @EnumSource(Language.class)
    @ParameterizedTest
    void fonbetSiteShouldDisplayCorrectTest(Language language) {
        Allure.step("Открываем главную страницу Fonbet", () -> {
            open("https://fon.bet/");
        });

        Allure.step("Открываем меню выбора языка", () -> {
            $("[data-testid='btn.languages']").click();
        });

        Allure.step("Выбираем язык: " + language.name(), () -> {
            $$("[data-testid='dd.language']")
                    .find(text(language.name()))
                    .click();
        });

        Allure.step("Проверяем, что текст кнопки 'Log In' изменился на '" + language.description + "'", () -> {
            $("[data-testid='btn.logIn']").shouldHave(text(language.description));
        });
    }

    public enum Language {
        RU("Войти"),
        EN("Log in");

        public final String description;

        Language(String description) {
            this.description = description;
        }
    }
}