package christmas.domain;

import christmas.constant.Benefit;
import christmas.constant.Price;

public class Event {
    private Gift gift;
    private Badge badge;

    public Event(int orderPrice) {

        checkReceiveGift(orderPrice);
    }

    private void checkReceiveGift(int orderPrice) {

        gift = Gift.giveGift(orderPrice);
    }

    public void applyEvent(int benefitPrice) {

        badge = Badge.giveBadge(benefitPrice);
    }

    public String findGiftName() {

        return gift.getGiftName();
    }

    public String getGiftPriceInfo() {

        Menu menu = Menu.findMenu(gift.getGiftName());

        StringBuilder builder = new StringBuilder();

        if (!menu.name().equals("EMPTY")) {
            builder.append(Benefit.GIFT_EVENT).append(": ")
                    .append(Price.df.format(-1 * menu.getPrice()))
                    .append("원\n");
        }
        return builder.toString();
    }

    public int getGiftPrice() {

        Menu menu = Menu.findMenu(gift.getGiftName());

        return menu.getPrice();
    }

    public String getBadgeType() {

        return badge.getType();
    }
}
