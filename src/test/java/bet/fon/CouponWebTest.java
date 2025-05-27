package bet.fon;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class CouponWebTest extends TestBase {
    @Test
    void addSingleBetToCouponTest() {
        step("Открываем страницу футбольных событий", () -> {
            open("/sports/football");
        });

        step("Добавляем первый доступный коэффициент в купон", () -> {
            $$("[data-testid='factorValue.921']").first().click();
        });

        step("Проверяем, что купон отобразился", () -> {
            $(".coupon-cart-bets--pLb2C").shouldBe(visible);
        });
    }
}
