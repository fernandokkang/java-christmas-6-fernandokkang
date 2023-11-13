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

    public void reserveToOrder(String orderMenu) {

        order = new Order(orderMenu);
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

    public String printOrderMenu() {

        return order.printOrderMenu();
    }

    public String getDate() {
        return date;
    }
}
