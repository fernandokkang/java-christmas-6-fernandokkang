package christmas.domain;

import christmas.constant.BenefitMessage;
import christmas.constant.Price;

public enum Gift {

    EMPTY(BenefitMessage.NO_GIFT, 0),
    GIFT(Menu.CHAMPAGNE.getMenuName(), Menu.CHAMPAGNE.getPrice());

    private String giftName;
    private int giftPrice;

    private Gift(String giftName, int giftPrice) {

        this.giftName = giftName;
        this.giftPrice = giftPrice;
    }

    public static Gift giveGift(int orderPrice) {

        if(orderPrice >= Price.MINIMUM_PRICE_RECEIVE_GIFT) {
            return GIFT;
        }
        return EMPTY;
    }

    public int getGiftPrice() {

        return giftPrice;
    }

    public String getGiftInfo() {

        if(this == EMPTY) {
            return giftName;
        }

        return giftName+" "+BenefitMessage.NUMBER_OF_GIFT;
    }

    public String getGiftPriceInfo() {

        StringBuilder builder = new StringBuilder();

        if(getGiftPrice() != 0) {
            builder.append(BenefitMessage.GIFT_EVENT).append(": ")
                    .append(Price.df.format(-1 * getGiftPrice()))
                    .append("원\n");
        }

        return builder.toString();
    }
}
