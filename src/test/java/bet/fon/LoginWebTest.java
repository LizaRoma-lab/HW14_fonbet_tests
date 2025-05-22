package bet.fon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginWebTest extends TestBase {
    @DisplayName("Негативный тест на вход в систему")
    @Tag("WEB")
    @ParameterizedTest
    @CsvSource({
            "79123456789, invalidPassword, Логин или пароль указан неверно",
            "invalidEmail, randomPassword, Логин или пароль указан неверно"
    })
    void unsuccessfulLogInWithPhoneNumber(String userPhoneNumber, String password, String result) {
        open("https://fon.bet/");
        $("[data-testid='btn.logIn']").click();
        $("[name=login]").setValue(userPhoneNumber);
        $("[type=password]").setValue(password).pressEnter();
        $(".text--kseTA").shouldHave(text(result));
    }
}
