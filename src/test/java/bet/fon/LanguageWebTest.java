package bet.fon;

import bet.fon.data.Language;
import com.codeborne.selenide.CollectionCondition;
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
        open("https://fon.bet/");
        $("[data-testid='btn.languages']").click();
        $$("[data-testid='dd.language']").find(text(language.name())).click();
        $("[data-testid='btn.logIn']").shouldHave(text(language.description));
        closeWebDriver();
    }
}
