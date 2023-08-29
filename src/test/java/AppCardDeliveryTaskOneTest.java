import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;



import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTaskOneTest {



        private String generateData(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
        }



@Test
public void shouldBeSuccessfullyDelivery() {
        Configuration.timeout = 20;
        open( "http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Волгоград");
        String planingData = generateData(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(planingData);
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $("[data-test-id='phone'] input").setValue("+79639856230");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planingData));

        }

}


