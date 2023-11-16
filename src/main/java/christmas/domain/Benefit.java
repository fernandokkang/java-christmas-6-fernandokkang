package christmas.domain;

import christmas.constant.BenefitMessage;
import christmas.constant.Price;

import java.util.Map;

public class Benefit {

    private Gift gift;
    private Badge badge;
    private int discountPrice;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    public String getGiftInfo(int orderPrice) {

        gift = Gift.giveGift(orderPrice);

        StringBuilder builder = new StringBuilder();
        builder.append(gift.getGiftInfo());

        return builder.toString();
    }

    public String getBenefitInfo(String date, Map<String, String> orders) {

        StringBuilder builder = new StringBuilder();

        Map<String, Integer> options = Discount.getDiscountEventOptions(date);
        Map<String, Integer> discountMenu = Discount.calculateDiscountMenuCount(orders);

        for(String key : options.keySet()) {

            builder.append(key).append(": ");
            if(key.equals(BenefitMessage.WEEKDAY_DISCOUNT)) {
                builder.append(Price.df.format( options.get(key) *
                        discountMenu.get(MenuGroup.DESSERT.getMenuType())))
                        .append(Price.WON).append(LINE_SEPARATOR);
                discountPrice += options.get(key) *
                        discountMenu.get(MenuGroup.DESSERT.getMenuType());
                continue;
            }
            if(key.equals(BenefitMessage.WEEKEND_DISCOUNT)) {
                builder.append(Price.df.format(options.get(key) *
                        discountMenu.get(MenuGroup.MAIN_MENU.getMenuType())))
                        .append(Price.WON).append(LINE_SEPARATOR);
                discountPrice += options.get(key) *
                        discountMenu.get(MenuGroup.MAIN_MENU.getMenuType());
                continue;
            }
            builder.append(Price.df.format(options.get(key)))
                    .append(Price.WON).append(LINE_SEPARATOR);
            discountPrice += options.get(key);
        }

        builder.append(gift.getGiftPriceInfo());

        return builder.toString();
    }

    public String getBenefitPrice(int orderPrice) {

        int benefitPrice = discountPrice - gift.getGiftPrice();

        StringBuilder builder = new StringBuilder();
        if(orderPrice < Price.MINIMUM_PRICE_APPLY_EVENT){
            builder.append(0).append(Price.WON).append(LINE_SEPARATOR);
            return builder.toString();
        }
        builder.append(Price.df.format(benefitPrice))
                .append(Price.WON).append(LINE_SEPARATOR);

        return builder.toString();
    }

    public String getExpectedPayment(int orderPrice) {

        int expectedPayment = orderPrice + discountPrice;
        StringBuilder builder = new StringBuilder();
        builder.append(Price.df.format(expectedPayment)).append(Price.WON)
                .append(LINE_SEPARATOR);

        return builder.toString();
    }

    public String getBadgeInfo() {

        int benefitPrice = gift.getGiftPrice() - discountPrice;
        badge = Badge.giveBadge(benefitPrice);

        StringBuilder builder = new StringBuilder();
        builder.append("<12월 이벤트 배지>").append(LINE_SEPARATOR)
                .append(badge.getBadgeTypeInfo());

        return builder.toString();
    }
}
