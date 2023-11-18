package christmas.domain;

import christmas.constant.*;

public class ReservationInfo {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private String date;
    private Order order;
    private Benefit benefit;

    public void makeReservation(String date) {

        this.date = date;
        int parseDate = parseDate(date);
        validateDateRange(parseDate);
    }

    public void submitOrder(String orderMenu) {

        order = new Order(orderMenu);
        benefit = new Benefit();
    }

    private int parseDate(String date) {

        try {

            return Integer.parseInt(date);

        }catch (Exception e){

            throw new IllegalArgumentException(ErrorMessage.DATE_FORMAT_ERROR_MESSAGE.name());
        }
    }
    private void validateDateRange(int date) {

        if(Month.DECEMBER.getFirstDay() > date ||
                Month.DECEMBER.getLastDay() < date) {

            throw new IllegalArgumentException(ErrorMessage.DATE_RANGE_ERROR_MESSAGE.name());
        }
    }

    public String getReservationDate() {

        return date;
    }

    public String getOrderMenu() {

        return order.createOrderMenuInfo();
    }

    public String getOrderPrice() {

        return Price.df.format(order.calculateOrderPrice())+Price.WON+LINE_SEPARATOR;
    }

    public String getGiftInfo() {

        return benefit.getGiftInfo(order.calculateOrderPrice());
    }

    public String getBenefitInfo() {

        int orderPrice = order.calculateOrderPrice();
        boolean isLessThanMinimumPrice = orderPrice < Price.MINIMUM_PRICE_APPLY_EVENT;
        String benefitInfo = benefit.getBenefitInfo(date, order.getOrders());

        if(isLessThanMinimumPrice||benefitInfo.equals("")) {

            return BenefitMessage.NO_BENEFIT;
        }
        return benefitInfo;
    }

    public String getBenefitPrice() {

        return benefit.getBenefitPrice(order.calculateOrderPrice());
    }

    public String getExpectedPayment() {

        return benefit.getExpectedPayment(order.calculateOrderPrice());
    }

    public String getEventBadgeInfo() {

        return benefit.getBadgeInfo();
    }
}
