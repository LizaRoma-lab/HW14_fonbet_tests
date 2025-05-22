package bet.fon;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchWebTest extends TestBase {
    @ValueSource(strings = {
            "Циципас",
            "Филс",
            "Алькарас"
    })
    @ParameterizedTest
    void successfulSearchTest(String testData) {
        open("https://fon.bet/");
        $("[data-testid='btn.search']").click();

        $("[data-testid=stringEdit]").setValue(testData);
        $("[data-testid=teams]").shouldHave(text(testData));
        sleep(5000);
    }
}
