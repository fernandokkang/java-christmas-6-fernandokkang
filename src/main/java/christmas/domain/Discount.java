package christmas.domain;

import christmas.constant.Price;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public enum Discount {

    WEEKDAY(Arrays.asList("SUN","MON","TUE","WED","THU"), Price.WEEKDAY_DESSERT_DISCOUNT_PRICE),
    WEEKEND(Arrays.asList("FRI","SAT"), Price.WEEKEND_MAIN_MENU_DISCOUNT_PRICE),
    SPECIAL(Arrays.asList("3","10","17","24","25","31"), Price.SPECIAL_DISCOUNT_PRICE),
    EMPTY(Collections.EMPTY_LIST, 0);

    private List<String> dateList;
    private int price;

    private Discount(List<String> dateList, int price) {

        this.dateList = dateList;
        this.price = price;
    }
    public static Map<String, Integer> getDiscountEventOptions(String date){

        String [] dayOfWeekEng = {"MON","TUE","WED","THU","FRI","SAT","SUN"};

        LocalDate localDateDate = LocalDate.of(2023, 12, Integer.parseInt(date));;
        DayOfWeek dayOfWeek = localDateDate.getDayOfWeek();

        String day = dayOfWeekEng[dayOfWeek.getValue() - 1];

        return makeEventOptions(date, day);
    }
    private static Map<String, Integer> makeEventOptions(String date, String day) {

        Map<String, Integer> options = new HashMap<>();

        options.put("크리스마스 디데이 할인",DDayDiscountPrice(date));
        options.put("평일 할인",findWeekdayDiscountPrice(day));
        options.put("주말 할인",findWeekendDiscountPrice(day));
        options.put("특별 할인",findSpecialDiscountPrice(date));

        return options;
    }

    private static int DDayDiscountPrice(String date) {

        int parseDate = Integer.parseInt(date);

        if(parseDate > 25) {
            return 0;
        }

        return (parseDate-1)*100 + Price.DEFAULT_D_DAY_DISCOUNT_PRICE;
    }

    private static int findWeekendDiscountPrice(String day) {

        for(String s : WEEKEND.dateList) {
            if(s.equals(day)) {
                return WEEKEND.price;
            }
        }
        return EMPTY.price;
    }

    private static int findWeekdayDiscountPrice(String day) {

        for(String s : WEEKDAY.dateList) {
            if(s.equals(day)) {
                return WEEKDAY.price;
            }
        }
        return EMPTY.price;
    }

    private static int findSpecialDiscountPrice(String date) {

        for (String s : SPECIAL.dateList) {
            if (s.equals(date)){
                return SPECIAL.price;
            }
        }
        return EMPTY.price;
    }
}
