package christmas.domainTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.BenefitMessage;
import christmas.constant.Price;
import christmas.domain.Discount;
import christmas.domain.Gift;
import christmas.domain.MenuGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class BenefitTest {

    @Test
    @DisplayName("혜택 정보 출력")
    public void getBenefitInfoTest() {

        String date = "3";
        String orderMenu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        int price = 150000;
        Gift gift = Gift.giveGift(price);

        Map<String, String> orders = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        StringBuilder builder = new StringBuilder();

        Map<String, Integer> options = Discount.makeDiscountEventOptions(date);
        Map<String, Integer> discountMenu = Discount.calculateDiscountMenuCount(orders);

        for(String key : options.keySet()) {

            builder.append(key).append(": ");
            if(key.equals(BenefitMessage.WEEKDAY_DISCOUNT)) {
                builder.append(Price.df.format(-1 * options.get(key) *
                        discountMenu.get(MenuGroup.DESSERT.getMenuType()))).append("원\n");
                continue;
            }
            if(key.equals(BenefitMessage.WEEKEND_DISCOUNT)) {
                builder.append(Price.df.format(-1 * options.get(key) *
                        discountMenu.get(MenuGroup.MAIN_MENU.getMenuType()))).append("원\n");
                continue;
            }
            builder.append(Price.df.format(-1 * options.get(key))).append("원\n");
        }

        builder.append(gift.getGiftPriceInfo());

        System.out.println(builder.toString());
    }
}
