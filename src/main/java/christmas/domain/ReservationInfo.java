package christmas.domain;

import christmas.constant.DateRange;
import christmas.constant.ErrorMessage;

public class ReservationInfo {

    private final String date;

    private Order order;

    private Event event;

    public ReservationInfo(String date) {

        this.date = date;
        int parseDate = isInteger(date);
        isCorrectDateRange(parseDate);
    }


    private int isInteger(String date) {

        try {

            return Integer.parseInt(date);

        }catch (Exception e){

            throw new IllegalArgumentException(ErrorMessage.DATE_FORMAT_ERROR_MESSAGE.name());
        }
    }

    public void reserveToOrder(String orderMenu) {

        order = new Order(orderMenu);
    }

    private void isCorrectDateRange(int date) {

        if(DateRange.FIRST_DAY_IN_DECEMBER > date ||
                DateRange.LAST_DAY_IN_DECEMBER < date) {

            throw new IllegalArgumentException(ErrorMessage.DATE_RANGE_ERROR_MESSAGE.name());
        }
    }

    public String getDate() {
        return date;
    }
}
