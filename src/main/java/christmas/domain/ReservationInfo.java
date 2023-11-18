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

    private String printSection(PrintType printType, String content) {

        return String.format("%s%n%s%n", printType.getLabel(),content);
    }

    public String printMessage() {

        return String.format(PrintType.EVENT_INFO_MESSAGE.getLabel(), Month.DECEMBER.getMonth(), date);
    }

    public String printOrderMenu() {

        return printSection(PrintType.ORDER_MENU, order.createOrderMenuInfo());
    }

    public String printOrderPrice() {

        return printSection(PrintType.ORDER_PRICE,
                Price.df.format(order.calculateOrderPrice())+Price.WON+LINE_SEPARATOR);
    }

    public String printGiftInfo() {

        return printSection(PrintType.GIFT, benefit.getGiftInfo(order.calculateOrderPrice()));
    }

    public String printBenefitInfo() {

        String benefitInfo = benefit.getBenefitInfo(date, order.getOrders());

        if(order.calculateOrderPrice() < Price.MINIMUM_PRICE_APPLY_EVENT
                || benefitInfo.equals("")) {

            return printSection(PrintType.BENEFIT, BenefitMessage.NO_BENEFIT);
        }
        return printSection(PrintType.BENEFIT, benefitInfo);
    }

    public String printBenefitPrice() {

        return printSection(PrintType.BENEFIT_PRICE, benefit.getBenefitPrice(order.calculateOrderPrice()));
    }

    public String printExpectedPayment() {

        return printSection(PrintType.EXPECTED_PRICE, benefit.getExpectedPayment(order.calculateOrderPrice()));
    }

    public String printEventBadge() {

        return String.format(PrintType.EVENT_BADGE.getLabel(), Month.DECEMBER.getMonth()) + benefit.getBadgeInfo();
    }
}
