package christmas.domainTest;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.constant.ErrorMessage;
import christmas.constant.InfoMessage;
import christmas.domain.Menu;
import christmas.domain.MenuGroup;
import christmas.domain.Order;
import christmas.domain.ReservationInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderTest {

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

    @Test
    @DisplayName("메뉴에 숫자 아닌 입력")
    void 메뉴에_숫자_아닌_입력() {

        String orderMenu = "타파스-1,제로콜라-1a";

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

        Map<String, String> orderMenus = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        assertThatThrownBy(() -> new Order(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InfoMessage.MORE_THAN_TWENTY_MENUS_CAN_NOT_ORDER.getInfoMessage());
    }

    @Test
    @DisplayName("주문 메뉴 개수 위반")
    void 총_주문_메뉴_개수_위반_0개() {

        String orderMenu = "타파스-0";

        Map<String, String> orderMenus = Arrays.stream(orderMenu.split(","))
                .map(a -> a.split("-", 2))
                .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        assertThatThrownBy(() -> new Order(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_COUNT_RANGE_ERROR_MESSAGE.name());
    }

    @Test
    @DisplayName("음료만 있는 경우 주문 불가")
    void checkMenuTest() {

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
            }
        }

        assertThat(message).isEqualTo(InfoMessage.ONLY_DRINK_CAN_NOT_ORDER.getInfoMessage());
    }
}
