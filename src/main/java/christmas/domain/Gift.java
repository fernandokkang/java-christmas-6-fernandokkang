package christmas.domain;

import christmas.constant.Benefit;
import christmas.constant.Price;

public enum Gift {

    EMPTY("없음"),
    GIFT(Menu.CHAMPAGNE.getMenuName());

    private String giftName;
    private static final String LINE_SEPARATOR = System.lineSeparator();

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

        builder.append("<증정 메뉴>").append(LINE_SEPARATOR);

        if(menu.name().equals("EMPTY")){
            builder.append("없음")
                    .append(LINE_SEPARATOR);
            return builder.toString();
        }

        builder.append(menu.getMenuName())
                .append(" 1개")
                .append(LINE_SEPARATOR);

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
