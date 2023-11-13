package christmas.domain;

public enum Gift {

    EMPTY("없음"),
    GIFT(Menu.CHAMPAGNE.getMenuName());

    private String giftName;

    private Gift(String giftName) {

        this.giftName = giftName;
    }

    public static Gift giveGift(int orderPrice) {

        if(orderPrice >= 120000) {
            return GIFT;
        }

        return EMPTY;
    }

    public String getGiftName() {
        return giftName;
    }
}
