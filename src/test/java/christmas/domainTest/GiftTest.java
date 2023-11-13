package christmas.domainTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Badge;
import christmas.domain.Gift;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GiftTest {

    @DisplayName("증정품 출력 테스트 120000원 이상")
    @Test
    void printGiftTest() {

        int orderPrice = 120000;

        Gift gift = Gift.giveGift(orderPrice);

        assertThat(gift.getGiftName()).isEqualTo("샴페인");
    }

    @DisplayName("증정품 출력 테스트 120000원 미만")
    @Test
    void printGiftTest2() {

        int orderPrice = 100000;

        Gift gift = Gift.giveGift(orderPrice);

        assertThat(gift.getGiftName()).isEqualTo("없음");
    }
}
