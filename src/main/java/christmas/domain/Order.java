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
        calculateMenuCount(orders);
        hasOnlyDrink(orders);
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

    private void hasOnlyDrink(Map<String, String> orders) {
        String message = InfoMessage.ONLY_DRINK_CAN_NOT_ORDER.getInfoMessage();
        for (String key : orders.keySet()) {

            Menu menu = Menu.findMenu(key);
            MenuGroup menuGroup = MenuGroup.findByMenu(menu.getMenuName());

            if(!menuGroup.name().equals("DRINK")) {
                message = "";
                break;
            }
        }
        if(message.equals(InfoMessage.ONLY_DRINK_CAN_NOT_ORDER.getInfoMessage())){
            throw new IllegalArgumentException(ErrorMessage.ONLY_DRINK_CAN_NOT_ORDER.name());
        }
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
}
