package bet.fon;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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
        Allure.step("Открываем главную страницу Fonbet", () -> {
            open("https://fon.bet/");
        });

        Allure.step("Нажимаем кнопку 'Войти'", () -> {
            $("[data-testid='btn.logIn']").click();
            $("[name=login]").shouldBe(visible);
        });

        Allure.step("Вводим некорректные данные: логин '" + userPhoneNumber + "', пароль '" + password + "'", () -> {
            $("[name=login]").setValue(userPhoneNumber);
            $("[type=password]").setValue(password).pressEnter();
        });

        Allure.step("Проверяем сообщение об ошибке: '" + result + "'", () -> {
            $(".text--kseTA")
                    .shouldBe(visible)
                    .shouldHave(text(result));
        });
    }
}