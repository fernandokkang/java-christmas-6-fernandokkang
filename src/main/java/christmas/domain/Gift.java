package christmas.domain;

import christmas.constant.Benefit;
import christmas.constant.Price;

public enum Gift {

    EMPTY("없음"),
    GIFT(Menu.CHAMPAGNE.getMenuName());

    private String giftName;

    private Gift(String giftName) {

        this.giftName = giftName;
    }

    public static Gift giveGift(int orderPrice) {

        if(orderPrice >= Price.MINIMUM_PRICE_RECEIVE_GIFT) {
            return GIFT;
        }

        return EMPTY;
    }

    private String getGiftName() {
        return giftName;
    }

    public int getGiftPrice() {

        Menu menu = Menu.findMenu(getGiftName());

        return menu.getPrice();
    }

    public String getGiftInfo() {

        StringBuilder builder = new StringBuilder();
        Menu menu = Menu.findMenu(getGiftName());

        if(menu.name().equals("EMPTY")){
            builder.append("없음");
            return builder.toString();
        }

        builder.append(menu.getMenuName())
                .append(" 1개");

        return builder.toString();
    }

    public String getGiftPriceInfo() {

        StringBuilder builder = new StringBuilder();

        if(getGiftPrice() != 0) {
            builder.append(Benefit.GIFT_EVENT).append(": ")
                    .append(Price.df.format(-1 * getGiftPrice()))
                    .append("원\n");
        }

        return builder.toString();
    }
}
