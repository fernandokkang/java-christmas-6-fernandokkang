package christmas.domain;

import christmas.constant.ErrorMessage;
import christmas.constant.InfoMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private final Map<String, String> orders;

    private int orderPrice;

    public Order(String orderMenu) {

        orders = makeOrders(orderMenu);
        validate(orders);
        calculateMenuCount(orders);
    }

    private Map<String, String> makeOrders(String orderMenu) {

        try {
            return Arrays.stream(orderMenu.split(","))
                    .map(a -> a.split("-", 2))
                    .collect(Collectors.toMap(b -> b[0], b -> b[1]));

        } catch (Exception e) {

            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_MENU_ERROR_MESSAGE.name());
        }
    }

    private void validate(Map<String, String> orders) {

        if(!isExistMenu(orders)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_EXIST_MENU_ERROR_MESSAGE.name());
        }
        if(hasOnlyDrink(orders)) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_DRINK_CAN_NOT_ORDER.name());
        }
    }

    private boolean isExistMenu(Map<String, String> orders) {

        for (String key : orders.keySet()) {
            Menu menu = Menu.findMenu(key);
            if(Menu.EMPTY.equals(menu)){
                return false;
            }
        }
        return true;
    }

    private boolean hasOnlyDrink(Map<String, String> orders) {

        for (String key : orders.keySet()) {

            Menu menu = Menu.findMenu(key);
            MenuGroup menuGroup = MenuGroup.findByMenu(menu.getMenuName());

            if(!menuGroup.name().equals("DRINK")) {

                return false;
            }
        }
        return true;
    }

    private void calculateMenuCount(Map<String, String> orders) {

        int menuCount = orders.values().stream()
                .mapToInt(Integer::valueOf).sum();

        if (menuCount < 1) {

            throw new IllegalArgumentException(ErrorMessage.MENU_COUNT_RANGE_ERROR_MESSAGE.name());
        }

        if(menuCount > 20) {

            throw new IllegalArgumentException(ErrorMessage.MENU_COUNT_RANGE_ERROR_MESSAGE.name());
        }
    }

    public String printOrderMenu() {

        StringBuilder builder = new StringBuilder();
        builder.append("<주문 메뉴>\n");

        if(orders == null) return "주문이 존재하지 않습니다.";

        orders.forEach((key, value) -> {
            Menu menu = Menu.findMenu(key);
            builder.append(menu.getMenuName()+" ").append(value+"개\n");
        });

        return builder.toString();
    }
}
