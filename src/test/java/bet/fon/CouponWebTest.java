package bet.fon;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CouponWebTest extends TestBase {
    @Test
    void addSingleBetToCouponTest() {
        Allure.step("Открываем страницу футбольных событий", () -> {
            open("/sports/football");
        });

        Allure.step("Добавляем первый доступный коэффициент в купон", () -> {
            $$("[data-testid='factorValue.921']").first().click();
        });

        Allure.step("Проверяем, что купон отобразился", () -> {
            $(".coupon-cart-bets--pLb2C").shouldBe(visible);
        });
    }
}
