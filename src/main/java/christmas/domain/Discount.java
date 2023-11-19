package christmas.domain;

import christmas.constant.BenefitMessage;
import christmas.constant.Price;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public enum Discount {

    WEEKDAY(Arrays.asList("SUN", "MON", "TUE", "WED", "THU"), -Price.WEEKDAY_DESSERT_DISCOUNT_PRICE),
    WEEKEND(Arrays.asList("FRI", "SAT"), -Price.WEEKEND_MAIN_MENU_DISCOUNT_PRICE),
    SPECIAL(Arrays.asList("3", "10", "17", "24", "25", "31"), -Price.SPECIAL_DISCOUNT_PRICE),
    EMPTY(Collections.EMPTY_LIST, 0);

    private final List<String> dateList;
    private final int price;
    private static int sumDiscountPrice;
    private Discount(List<String> dateList, int price) {

        this.dateList = dateList;
        this.price = price;
    }

    public static Map<String, Integer> makeDiscountEventOptions(String date) {

        String[] dayOfWeekEng = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

        LocalDate localDateDate = LocalDate.of(2023, 12, Integer.parseInt(date));

        DayOfWeek dayOfWeek = localDateDate.getDayOfWeek();

        String day = dayOfWeekEng[dayOfWeek.getValue() - 1];

        return makeEventOptions(date, day);
    }

    private static Map<String, Integer> makeEventOptions(String date, String day) {

        Map<String, Integer> options = new HashMap<>();
        if (DDayDiscountPrice(date) != 0) {
            options.put(BenefitMessage.CHRISTMAS_D_DAY_DISCOUNT, DDayDiscountPrice(date));
        }
        if (findWeekdayDiscountPrice(day) != 0) {
            options.put(BenefitMessage.WEEKDAY_DISCOUNT, findWeekdayDiscountPrice(day));
        }
        if (findWeekendDiscountPrice(day) != 0) {
            options.put(BenefitMessage.WEEKEND_DISCOUNT, findWeekendDiscountPrice(day));
        }
        if (findSpecialDiscountPrice(date) != 0) {
            options.put(BenefitMessage.SPECIAL_DISCOUNT, findSpecialDiscountPrice(date));
        }
        return options;
    }

    private static int DDayDiscountPrice(String date) {

        int parseDate = Integer.parseInt(date);

        if (parseDate > 25) {
            return 0;
        }

        return (1 - parseDate) * Price.UNIT_PRICE_FOR_D_DAY_DISCOUNT
                - Price.DEFAULT_D_DAY_DISCOUNT_PRICE;
    }

    private static int findWeekendDiscountPrice(String day) {

        for (String s : WEEKEND.dateList) {
            if (s.equals(day)) {
                return WEEKEND.price;
            }
        }
        return EMPTY.price;
    }

    private static int findWeekdayDiscountPrice(String day) {

        for (String s : WEEKDAY.dateList) {
            if (s.equals(day)) {
                return WEEKDAY.price;
            }
        }
        return EMPTY.price;
    }

    private static int findSpecialDiscountPrice(String date) {

        for (String s : SPECIAL.dateList) {
            if (s.equals(date)) {
                return SPECIAL.price;
            }
        }
        return EMPTY.price;
    }

    public static String discountInfo(Map<String, String> orders, String date) {

        Map<String, Integer> menuCounts = calculateDiscountMenuCount(orders);
        Map<String, Integer> options = makeDiscountEventOptions(date);

        StringBuilder builder = new StringBuilder();
        String LINE_SEPARATOR = System.lineSeparator();

        for(String key : options.keySet()) {
            builder.append(key).append(": ");
            if(key.equals(BenefitMessage.WEEKDAY_DISCOUNT)) {
                builder.append(Price.df.format(options.get(key) *
                                menuCounts.get(MenuGroup.DESSERT.getMenuType())))
                        .append(Price.WON).append(LINE_SEPARATOR);
                sumDiscountPrice += options.get(key) * menuCounts.get(MenuGroup.DESSERT.getMenuType());
                continue;
            }
            if(key.equals(BenefitMessage.WEEKEND_DISCOUNT)) {
                builder.append(Price.df.format(options.get(key) *
                                menuCounts.get(MenuGroup.MAIN_MENU.getMenuType())))
                        .append(Price.WON).append(LINE_SEPARATOR);
                sumDiscountPrice += options.get(key) * menuCounts.get(MenuGroup.MAIN_MENU.getMenuType());
                continue;
            }
            builder.append(Price.df.format(options.get(key)))
                    .append(Price.WON).append(LINE_SEPARATOR);
            sumDiscountPrice += options.get(key);
        }
        return builder.toString();
    }

    private static void function() {


    }

    public static Map<String, Integer> calculateDiscountMenuCount(Map<String, String> orders) {

        Map<String, Integer> discountMenus = initializeMap();
        for(String key : orders.keySet()) {
            Menu menu = Menu.findMenu(key);
            MenuGroup menuGroup = MenuGroup.findByMenu(menu.getMenuName());

            if(menuGroup.getMenuType().equals(MenuGroup.DESSERT.getMenuType()) ||
                menuGroup.getMenuType().equals(MenuGroup.MAIN_MENU.getMenuType())) {

                int menuCount = Integer.parseInt(orders.get(key));
                discountMenus.merge(menuGroup.getMenuType(), menuCount, Integer::sum);
            }
        }
        return discountMenus;
    }

    private static Map<String, Integer> initializeMap() {

        Map<String, Integer> discountMenus = new HashMap<>();
        discountMenus.put(MenuGroup.DESSERT.getMenuType(), 0);
        discountMenus.put(MenuGroup.MAIN_MENU.getMenuType(), 0);

        return discountMenus;
    }

    public static int getSumDiscountPrice() {
        return sumDiscountPrice;
    }
}
