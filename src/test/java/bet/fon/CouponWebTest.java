package bet.fon;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CouponWebTest extends TestBase {
    @Test
    void addSingleBetToCouponTest() {
        open("/sports/football");
        $$("[data-testid='factorValue.921']").first().click();
        $(".coupon-cart-bets--pLb2C").shouldBe(visible);
    }
}
