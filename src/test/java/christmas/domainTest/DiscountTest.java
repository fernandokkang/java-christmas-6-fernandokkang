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

public class DiscountTest {

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 평일할인")
    public void findDiscountToApplyTest_Weekdays() {

        String date = "4";

        Map<String, Integer> options =
                Discount.makeDiscountEventOptions(date);

        assertThat(options).containsEntry("크리스마스 디데이 할인",-1300)
                .containsEntry("평일 할인",-Price.WEEKDAY_DESSERT_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 평일, 특별할인")
    public void findDiscountToApplyTest_Weekdays_Special() {

        String date = "10";

        Map<String, Integer> options =
        Discount.makeDiscountEventOptions(date);

        assertThat(options).containsEntry("크리스마스 디데이 할인",-1900)
                .containsEntry("평일 할인",-Price.WEEKDAY_DESSERT_DISCOUNT_PRICE)
                .containsEntry("특별 할인",-Price.SPECIAL_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 주말할인")
    public void findDiscountToApplyTest_Weekend() {

        String date = "9";

        Map<String, Integer> options =
                Discount.makeDiscountEventOptions(date);

        assertThat(options).containsEntry("크리스마스 디데이 할인",-1800)
                .containsEntry("주말 할인",-Price.WEEKEND_MAIN_MENU_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 크리스마스 당일")
    public void findDiscountToApplyTest_Christmas() {

        String date = "25";

        Map<String, Integer> options =
                Discount.makeDiscountEventOptions(date);

        assertThat(options).containsEntry("크리스마스 디데이 할인",-3400)
                .containsEntry("평일 할인",-Price.WEEKDAY_DESSERT_DISCOUNT_PRICE)
                .containsEntry("특별 할인",-Price.SPECIAL_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 크리스마스 이후 평일")
    public void findDiscountToApplyTest_AfterChristmas_Weekday() {

        String date = "26";

        Map<String, Integer> options =
                Discount.makeDiscountEventOptions(date);

        assertThat(options)
                .containsEntry("평일 할인",-Price.WEEKDAY_DESSERT_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 크리스마스 이후 평일 특별 할인")
    public void findDiscountToApplyTest_AfterChristmas_Weekday_Special() {

        String date = "27";

        Map<String, Integer> options =
                Discount.makeDiscountEventOptions(date);

        assertThat(options)
                .containsEntry("평일 할인",-Price.WEEKDAY_DESSERT_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 크리스마스 이후 주말")
    public void findDiscountToApplyTest_AfterChristmas_Weekend() {

        String date = "31";

        Map<String, Integer> options =
                Discount.makeDiscountEventOptions(date);

        assertThat(options)
                .containsEntry("평일 할인",-Price.WEEKDAY_DESSERT_DISCOUNT_PRICE)
                .containsEntry("특별 할인",-Price.SPECIAL_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("할인 금액 총합 구하기")
    public void sumOfDiscountPriceTest() {

        String date = "3";
        String orderMenu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        Map<String, String> orders = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        Map<String, Integer> menuCounts = Discount.calculateDiscountMenuCount(orders);
        Map<String, Integer> options = Discount.makeDiscountEventOptions(date);

        int discountPrice = 0;

        for(String key : options.keySet()) {
            if(key.equals(BenefitMessage.WEEKDAY_DISCOUNT)) {
                discountPrice += options.get(key) * menuCounts.get(MenuGroup.DESSERT.getMenuType());
                continue;
            }
            if(key.equals(BenefitMessage.WEEKEND_DISCOUNT)) {
                discountPrice += options.get(key) * menuCounts.get(MenuGroup.MAIN_MENU.getMenuType());
                continue;
            }
            discountPrice += options.get(key);
        }

        assertThat(discountPrice).isEqualTo(-6246);
    }
}
