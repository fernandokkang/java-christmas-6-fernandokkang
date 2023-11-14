package christmas.domainTest;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.constant.Benefit;
import christmas.constant.ErrorMessage;
import christmas.constant.InfoMessage;
import christmas.constant.Price;
import christmas.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class OrderTest {

    private int orderPrice;

    @Test
    void makeOrderTest() {

        String orderMenu = "타파스-1,제로콜라-1";

        Map<String, String> orderMenus = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        assertThat(orderMenus).hasSize(2)
                .containsEntry("타파스", "1").containsEntry("제로콜라", "1");
    }

    @Test
    @DisplayName("중복 메뉴 체크")
    void duplicateOrderTest() {

        String orderMenu = "타파스-1,제로콜라-1,타파스-2";
        String date = "10";

        assertThatThrownBy(() -> new Order(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_MENU_ERROR_MESSAGE.name());
    }

    @Test
    @DisplayName("주문한 메뉴의 수가 몇개인지")
    void howManyOrderMenusTest() {

        String orderMenu = "타파스-1,제로콜라-1";

        Map<String, String> orderMenus = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        int sum = orderMenus.values().stream()
                .mapToInt(Integer::valueOf).sum();

        assertThat(sum).isEqualTo(2);
    }

    @Test
    @DisplayName("메뉴에 숫자 아닌 입력")
    void 메뉴에_숫자_아닌_입력() {

        String orderMenu = "타파스-1,제로콜라-1a";
        String date = "10";

        Map<String, String> orderMenus = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        assertThatThrownBy(() -> new Order(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_COUNT_NOT_NUMBER_ERROR_MESSAGE.name());
    }

    @Test
    @DisplayName("주문 메뉴 개수 20개 이상일시 메시지 출력")
    void 총_주문_메뉴_개수_20개_이상() {

        String orderMenu = "타파스-1,제로콜라-21";
        String date = "10";

        Map<String, String> orderMenus = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        assertThatThrownBy(() -> new Order(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_COUNT_RANGE_ERROR_MESSAGE.name());
    }

    @Test
    @DisplayName("주문 메뉴 개수 위반")
    void 총_주문_메뉴_개수_위반_0개() {

        String orderMenu = "타파스-0";
        String date = "10";

        Map<String, String> orderMenus = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        assertThatThrownBy(() -> new Order(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_COUNT_RANGE_ERROR_MESSAGE.name());
    }

    @Test
    @DisplayName("음료만 있는 경우 주문 불가")
    void hasOnlyDrinkTest() {

        String orderMenu = "제로콜라-1,레드와인-2,샴페인-3";

        Map<String, String> orderMenus = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        String message = InfoMessage.ONLY_DRINK_CAN_NOT_ORDER.getInfoMessage();

        for (String key : orderMenus.keySet()) {

            Menu menu = Menu.findMenu(key);

            MenuGroup menuGroup = MenuGroup.findByMenu(menu.getMenuName());

            if(!menuGroup.name().equals("DRINK")){
                message = "";
                break;
            }
        }

        assertThat(message).isEqualTo(InfoMessage.ONLY_DRINK_CAN_NOT_ORDER.getInfoMessage());
    }

    @Test
    @DisplayName("메뉴판에 없는 메뉴 주문한 경우 주문 불가")
    void isExistMenu() {

        String orderMenu = "제로콜라-1,타파스-3,후라이드치킨-2";

        Map<String, String> orderMenus = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        String message = "";

        for (String key : orderMenus.keySet()) {

            Menu menu = Menu.findMenu(key);

            if(Menu.EMPTY.equals(menu)){

                message = ErrorMessage.NOT_EXIST_MENU_ERROR_MESSAGE.getErrorMessage();
            }
        }

        assertThat(message).isEqualTo(ErrorMessage.NOT_EXIST_MENU_ERROR_MESSAGE.getErrorMessage());
    }

    @Test
    @DisplayName("주문 메뉴 출력")
    void printOrderMenu() {

        String orderMenu = "해산물파스타-2,레드와인-1,초코케이크-1";

        Map<String, String> orders = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        orders.forEach((key, value) -> {
            Menu menu = Menu.findMenu(key);
            System.out.println(menu.getMenuName()+" "+value+"개");
        });
    }

    @Test
    @DisplayName("주문 금액 출력")
    void printOrderPrice() {

        String orderMenu = "해산물파스타-2,레드와인-1,초코케이크-1";

        Map<String, String> orders = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));



        orders.forEach((key, value) -> {
            Menu menu = Menu.findMenu(key);

            orderPrice += menu.getPrice() * Integer.parseInt(value);
        });

        assertThat(orderPrice).isEqualTo(145000);
    }
    @Test
    @DisplayName("할인 메뉴 갯수 출력")
    void calculateDiscountMenuCountTest() {

        String orderMenu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        Map<String, String> orders = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        int count = 0;

        for (String key : orders.keySet()) {

            Menu menu = Menu.findMenu(key);
            MenuGroup menuGroup = MenuGroup.findByMenu(menu.getMenuName());
            if(menuGroup.equals(MenuGroup.MAIN_MENU)) {

                count += Integer.parseInt(orders.get(key));
            }
        }
       assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("할인 금액 계산 테스트")
    void calculateDiscountPriceTest() {

        String date = "10";

        Map<String, Integer> options =
               Discount.getDiscountEventOptions(date);

        int discountPrice = 0;

        for (String key : options.keySet()) {
            if(key.equals(Benefit.WEEKDAY_DISCOUNT)){
                discountPrice = options.get(key) * 2;//평일 주말인지 확인하기 위한 임의의 값
                options.replace(Benefit.WEEKDAY_DISCOUNT,discountPrice);
            }

            if(key.equals(Benefit.WEEKEND_DISCOUNT)){
                discountPrice = options.get(key) * 3;
                options.replace(Benefit.WEEKEND_DISCOUNT,discountPrice);
            }
        }

        assertThat(options).containsEntry("크리스마스 디데이 할인",1900)
                .containsEntry("평일 할인",Price.WEEKDAY_DESSERT_DISCOUNT_PRICE * 2)
                .containsEntry("특별 할인",Price.SPECIAL_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("할인 내역 출력")
    void discountInfoTest() {

        String date = "3";
        Map<String, Integer> options =
                Discount.getDiscountEventOptions(date);

        int discountPrice = 0;

        for (String key : options.keySet()) {
            if(key.equals(Benefit.WEEKDAY_DISCOUNT)){
                discountPrice = options.get(key) * 2;//평일 주말인지 확인하기 위한 임의의 값
                options.replace(Benefit.WEEKDAY_DISCOUNT,discountPrice);
            }

            if(key.equals(Benefit.WEEKEND_DISCOUNT)){
                discountPrice = options.get(key) * 3;
                options.replace(Benefit.WEEKEND_DISCOUNT,discountPrice);
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("<혜택 내역>\n");
        for(String key : options.keySet()) {
            builder.append(key).append(": ")
                    .append(Price.df.format(-1*options.get(key))).append("원\n");
        }

        System.out.println(builder);
    }

}
