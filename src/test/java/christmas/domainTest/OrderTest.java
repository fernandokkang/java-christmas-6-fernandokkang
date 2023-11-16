package christmas.domainTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import christmas.constant.ErrorMessage;
import christmas.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

public class OrderTest extends NsTest{

    private static final String LINE_SEPARATOR = System.lineSeparator();
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


/*
    메뉴 입력 형식 처리를 하면서 메뉴-숫자가 아닌 형태는 걸러지도록 했기 때문에
    해당 코드가 필요 없어짐
    이 코드 실행하면 MENU_FORMAT_ERROR_MESSAGE 가 출력 될 것

    @Test
    @DisplayName("메뉴에 숫자 아닌 입력")
    void 메뉴에_숫자_아닌_입력() {

        String orderMenu = "제로콜라-1a";

        assertThatThrownBy(() -> new Order(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_COUNT_NOT_NUMBER_ERROR_MESSAGE.name());
    }*/

    @Test
    @DisplayName("주문 메뉴 개수 20개 이상일시 메시지 출력")
    void 총_주문_메뉴_개수_20개_이상() {

        String orderMenu = "타파스-1,제로콜라-21";

        assertThatThrownBy(() -> new Order(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_COUNT_RANGE_ERROR_MESSAGE.name());
    }

    @Test
    @DisplayName("주문 메뉴 개수 위반")
    void 총_주문_메뉴_개수_위반_0개() {

        String orderMenu = "타파스-0";

        assertThatThrownBy(() -> new Order(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_COUNT_RANGE_ERROR_MESSAGE.name());
    }

    @Test
    @DisplayName("음료만 있는 경우 주문 불가")
    void hasOnlyDrinkTest() {

        String orderMenu = "제로콜라-1,레드와인-2,샴페인-3";

        assertThatThrownBy(() -> new Order(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ONLY_DRINK_CAN_NOT_ORDER.name());
    }

    @Test
    @DisplayName("메뉴판에 없는 메뉴 주문한 경우 주문 불가")
    void isExistMenu() {

        String orderMenu = "제로콜라-1,타파스-3,후라이드치킨-2";

        assertThatThrownBy(() -> new Order(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_EXIST_MENU_ERROR_MESSAGE.name());
    }

    @Test
    @DisplayName("주문 메뉴 출력")
    void printOrderMenu() {

        String orderMenu = "해산물파스타-2,레드와인-1,초코케이크-1";
        Order order = new Order(orderMenu);

        assertThat(order.createOrderMenuInfo()).contains("<주문 메뉴>")
                .contains("해산물파스타 2개").contains("레드와인 1개").contains("초코케이크 1개");
    }

    @Test
    @DisplayName("주문 금액 출력")
    void printOrderPrice() {

        String orderMenu = "해산물파스타-2,레드와인-1,초코케이크-1";
        Order order = new Order(orderMenu);

        assertThat(order.calculateOrderPrice()).isEqualTo(145000);
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
    @DisplayName("할인 내역 출력 테스트")
    void calculateDiscountPriceTest() {

        String date = "10";
        //12월 10일 일요일, 평일할인, 특별할인 적용일
        String orderMenu = "해산물파스타-2,레드와인-1,초코케이크-1";

        Order order = new Order(orderMenu);
        assertThat(order.discountInfo(date)).contains("크리스마스 디데이 할인: -1,900원")
                .contains("평일 할인: -2,023원").contains("특별 할인: -1,000원");

    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
