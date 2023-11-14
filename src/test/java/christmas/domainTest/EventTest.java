package christmas.domainTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Benefit;
import christmas.constant.Price;
import christmas.domain.Gift;
import christmas.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventTest {

    @DisplayName("증정품 정보 출력")
    @Test
    void getGiftPriceInfo() {

        int orderPrice = 150000;

        Gift gift = Gift.giveGift(150000);

        Menu menu = Menu.findMenu(gift.getGiftName());

        StringBuilder builder = new StringBuilder();
        builder.append(Benefit.GIFT_EVENT).append(": ")
                .append(Price.df.format(-1*menu.getPrice()))
                .append("원\n");

        assertThat(builder.toString())
                .contains("-25,000원");
    }
}
