package christmas.domain;

import christmas.constant.Benefit;
import christmas.constant.DateRange;
import christmas.constant.ErrorMessage;
import christmas.constant.Price;

import java.util.Map;

public class ReservationInfo {
    private String date;
    private Order order;
    private Event event;

    public void makeReservation(String date) {

        this.date = date;
        int parseDate = isInteger(date);
        isCorrectDateRange(parseDate);
    }

    public void submitOrderWithEvent(String orderMenu) {

        order = new Order(orderMenu);
        applyEvent();
    }

    private int isInteger(String date) {

        try {

            return Integer.parseInt(date);

        }catch (Exception e){

            throw new IllegalArgumentException(ErrorMessage.DATE_FORMAT_ERROR_MESSAGE.name());
        }
    }
    private void isCorrectDateRange(int date) {

        if(DateRange.DECEMBER.getFirstDay() > date ||
                DateRange.DECEMBER.getLastDay() < date) {

            throw new IllegalArgumentException(ErrorMessage.DATE_RANGE_ERROR_MESSAGE.name());
        }
    }

    private void applyEvent () {

        int orderPrice = order.calculateOrderPrice();
        if(orderPrice >= Price.MINIMUM_PRICE_APPLY_EVENT) {
            event = new Event(orderPrice);
        }
    }

    public String printMessage() {

        StringBuilder builder = new StringBuilder();
        builder.append("12월 ").append(date)
                .append("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");

        return builder.toString();
    }

    public String printOrderMenu() {

        return order.createOrderMenu();
    }

    public String printOrderPrice() {

        StringBuilder builder = new StringBuilder();
        builder.append("<할인 전 총주문 금액>\n")
                .append(Price.df.format(order.calculateOrderPrice())).append("원\n");

        return builder.toString();
    }

    public String printGiftInfo() {

        String giftName = event.findGiftName();

        StringBuilder builder = new StringBuilder();
        builder.append("<증정 메뉴>\n");
        if(giftName.equals("없음")){
            builder.append(giftName).append("\n");
            return builder.toString();
        }
        builder.append(giftName).append(Benefit.NUMBER_OF_GIFT).append(" \n");

        return builder.toString();
    }

    public String printBenefit() {

        StringBuilder builder = new StringBuilder();
        builder.append("<혜택 내역>\n");
        builder.append(order.discountInfo(date));
        builder.append(event.getGiftPriceInfo());

        return builder.toString();
    }

    public String printBenefitPrice() {

        StringBuilder builder = new StringBuilder();

        int benefitPrice = order.getDiscountPrice() +
                event.getGiftPrice();

        builder.append("<총혜택 금액>\n")
                .append(Price.df.format(-1*benefitPrice))
                .append("원\n");

        return builder.toString();
    }

    public String printExpectedPayment() {

        StringBuilder builder = new StringBuilder();

        int expectedPayment = order.calculateOrderPrice() -
                order.getDiscountPrice();

        builder.append("<할인 후 예상 결제 금액>\n")
                .append(Price.df.format(expectedPayment))
                .append("원\n");

        return builder.toString();
    }

    public String printEventBadge() {

        StringBuilder builder = new StringBuilder();

        int benefitPrice = order.getDiscountPrice() +
                event.getGiftPrice();

        event.applyEvent(benefitPrice);

        builder.append("<12월 이벤트 배지>\n")
                .append(event.getBadgeType())
                .append("\n");

        return builder.toString();
    }
}
