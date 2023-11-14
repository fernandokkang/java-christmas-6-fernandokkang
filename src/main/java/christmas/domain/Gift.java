package christmas.domain;

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

    public String getGiftName() {
        return giftName;
    }
}
