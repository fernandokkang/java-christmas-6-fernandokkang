package christmas.domainTest;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.constant.Price;
import christmas.domain.Discount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class DiscountTest {

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 평일할인")
    public void findDiscountToApplyTest_Weekdays() {

        String date = "4";

        Map<String, Integer> options =
                Discount.getDiscountEventOptions(date);

        assertThat(options).containsEntry("크리스마스 디데이 할인",1300)
                .containsEntry("평일 할인",Price.WEEKDAY_DESSERT_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 평일, 특별할인")
    public void findDiscountToApplyTest_Weekdays_Special() {

        String date = "10";

        Map<String, Integer> options =
        Discount.getDiscountEventOptions(date);

        assertThat(options).containsEntry("크리스마스 디데이 할인",1900)
                .containsEntry("평일 할인",Price.WEEKDAY_DESSERT_DISCOUNT_PRICE)
                .containsEntry("특별 할인",Price.SPECIAL_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 주말할인")
    public void findDiscountToApplyTest_Weekend() {

        String date = "9";

        Map<String, Integer> options =
                Discount.getDiscountEventOptions(date);

        assertThat(options).containsEntry("크리스마스 디데이 할인",1800)
                .containsEntry("주말 할인",Price.WEEKEND_MAIN_MENU_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 크리스마스 당일")
    public void findDiscountToApplyTest_Christmas() {

        String date = "25";

        Map<String, Integer> options =
                Discount.getDiscountEventOptions(date);

        assertThat(options).containsEntry("크리스마스 디데이 할인",3400)
                .containsEntry("평일 할인",Price.WEEKDAY_DESSERT_DISCOUNT_PRICE)
                .containsEntry("특별 할인",Price.SPECIAL_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 크리스마스 이후 평일")
    public void findDiscountToApplyTest_AfterChristmas_Weekday() {

        String date = "26";

        Map<String, Integer> options =
                Discount.getDiscountEventOptions(date);

        assertThat(options)
                .containsEntry("평일 할인",Price.WEEKDAY_DESSERT_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 크리스마스 이후 평일 특별 할인")
    public void findDiscountToApplyTest_AfterChristmas_Weekday_Special() {

        String date = "27";

        Map<String, Integer> options =
                Discount.getDiscountEventOptions(date);

        assertThat(options)
                .containsEntry("평일 할인",Price.WEEKDAY_DESSERT_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("날짜 입력하면 해당하는 할인 출력 크리스마스 이후 주말")
    public void findDiscountToApplyTest_AfterChristmas_Weekend() {

        String date = "31";

        Map<String, Integer> options =
                Discount.getDiscountEventOptions(date);

        assertThat(options)
                .containsEntry("평일 할인",Price.WEEKDAY_DESSERT_DISCOUNT_PRICE)
                .containsEntry("특별 할인",Price.SPECIAL_DISCOUNT_PRICE);
    }
}
