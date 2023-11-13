package christmas.domain;

import christmas.constant.DateRange;
import christmas.constant.ErrorMessage;

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
        if(orderPrice >= 10000) {
            event = new Event(date, orderPrice);
        }
    }

    public String printOrderMenu() {

        return order.createOrderMenu();
    }

    public String printOrderPrice() {

        StringBuilder builder = new StringBuilder();
        builder.append("<할인 전 총주문 금액>\n")
                .append(order.calculateOrderPrice()).append("원\n");

        return builder.toString();
    }

    public String printGiftInfo() {

        return event.makeGiftInfo();
    }

    public String getDate() {
        return date;
    }
}
