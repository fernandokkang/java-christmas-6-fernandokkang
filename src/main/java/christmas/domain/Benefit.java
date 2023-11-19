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
        builder.append(gift.getGiftInfo()).append(LINE_SEPARATOR);

        return builder.toString();
    }

    public String getBenefitInfo(String date, Map<String, String> orders) {

        StringBuilder builder = new StringBuilder();
        builder.append(Discount.discountInfo(orders, date));
        builder.append(gift.getGiftPriceInfo());

        return builder.toString();
    }

    public String getBenefitPrice(int orderPrice) {

        if(orderPrice >= Price.MINIMUM_PRICE_APPLY_EVENT) {
            discountPrice = Discount.getSumDiscountPrice();
        }
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
        builder.append(badge.getType())
                .append(LINE_SEPARATOR);

        return builder.toString();
    }
}
