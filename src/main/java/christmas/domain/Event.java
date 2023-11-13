package christmas.domain;

public class Event {

    private final int MINIMUM_EVENT_PARTICIPATION_PRICE = 120000;

    private Discount discount;
    private Gift gift;
    private Badge badge;

    public Event(String date, int orderPrice) {

       checkReceiveGift(orderPrice);
    }

    private void checkReceiveGift(int orderPrice) {

        gift = Gift.giveGift(orderPrice);
    }

    public String makeGiftInfo() {

        StringBuilder builder = new StringBuilder();
        builder.append("<증정 메뉴>\n");
        if(gift.getGiftName().equals("없음")){
            builder.append(gift.getGiftName()).append("\n");
            return builder.toString();
        }
        builder.append(gift.getGiftName()).append(" 1개\n");

        return builder.toString();
    }

    public String createAppliedEventHistory() {

        return null;
    }
}
