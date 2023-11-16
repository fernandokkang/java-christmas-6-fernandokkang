package christmas.domain;

import christmas.constant.DateRange;
import christmas.constant.ErrorMessage;
import christmas.constant.Price;

public class ReservationInfo {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private String date;
    private Order order;
    private Gift gift;
    private Badge badge;
    private Benefit benefit;

    public void makeReservation(String date) {

        this.date = date;
        int parseDate = isInteger(date);
        isCorrectDateRange(parseDate);
    }

    public void submitOrder(String orderMenu) {

        order = new Order(orderMenu);
        benefit = new Benefit();
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

    public String printMessage() {

        StringBuilder builder = new StringBuilder();
        builder.append("12월 ").append(date)
                .append("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
                .append(LINE_SEPARATOR);

        return builder.toString();
    }

    public String printOrderMenu() {

        StringBuilder builder = new StringBuilder();
        builder.append("<주문 메뉴>").append(LINE_SEPARATOR)
                .append(order.createOrderMenuInfo());

        return builder.toString();
    }

    public String printOrderPrice() {

        StringBuilder builder = new StringBuilder();
        builder.append("<할인 전 총주문 금액>")
                .append(LINE_SEPARATOR)
                .append(Price.df.format(order.calculateOrderPrice())).append(Price.WON)
                .append(LINE_SEPARATOR);

        return builder.toString();
    }

    public String printGiftInfo() {

        StringBuilder builder = new StringBuilder();
        builder.append("<증정 메뉴>").append(LINE_SEPARATOR);
        builder.append(benefit.getGiftInfo(order.calculateOrderPrice())).append(LINE_SEPARATOR);

        return builder.toString();
    }

    public String printBenefitInfo() {

        StringBuilder builder = new StringBuilder();
        builder.append("<혜택 내역>").append(LINE_SEPARATOR);

        String benefitInfo = benefit.getBenefitInfo(date, order.getOrders());

        if(order.calculateOrderPrice() < Price.MINIMUM_PRICE_APPLY_EVENT
                || benefitInfo.equals("")) {

            builder.append("없음").append(LINE_SEPARATOR);
            return builder.toString();
        }
        builder.append(benefitInfo);

        return builder.toString();
    }

    public String printBenefitPrice() {

        StringBuilder builder = new StringBuilder();

        builder.append("<총혜택 금액>").append(LINE_SEPARATOR)
                .append(benefit.getBenefitPrice(order.calculateOrderPrice()));

        return builder.toString();
    }

    public String printExpectedPayment() {

        StringBuilder builder = new StringBuilder();

        builder.append("<할인 후 예상 결제 금액>").append(LINE_SEPARATOR)
                .append(benefit.getExpectedPayment(order.calculateOrderPrice()));

        return builder.toString();
    }

    public String printEventBadge() {

        StringBuilder builder = new StringBuilder();
        builder.append(benefit.getBadgeInfo());

        return builder.toString();
    }
}
