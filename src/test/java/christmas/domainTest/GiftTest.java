package christmas.domainTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Badge;
import christmas.domain.Gift;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GiftTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @DisplayName("증정품 가격 출력 테스트 120000원 이상")
    @Test
    void printGiftPriceInfoTest() {

        int orderPrice = 120000;

        Gift gift = Gift.giveGift(orderPrice);

        assertThat(gift.getGiftPriceInfo())
                .contains("증정 이벤트")
                .contains("-25,000원");
    }

    @DisplayName("증정품 가격 출력 테스트 120000원 미만")
    @Test
    void printGiftPriceInfoTest_lessThan120000() {

        int orderPrice = 110000;

        Gift gift = Gift.giveGift(orderPrice);

        assertThat(gift.getGiftPriceInfo())
                .contains("");
    }

    @DisplayName("증정품 출력 테스트 120000원 이상")
    @Test
    void printGiftInfoTest() {

        int orderPrice = 120000;

        Gift gift = Gift.giveGift(orderPrice);

        assertThat(gift.getGiftInfo())
                .contains("샴페인 1개");
    }

    @DisplayName("증정품 출력 테스트 120000원 미만")
    @Test
    void printGiftInfoTest_lessThan120000() {

        int orderPrice = 110000;

        Gift gift = Gift.giveGift(orderPrice);

        assertThat(gift.getGiftInfo())
                .contains("없음");
    }
}
