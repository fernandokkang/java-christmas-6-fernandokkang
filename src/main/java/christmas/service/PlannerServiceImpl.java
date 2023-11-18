package christmas.service;

import christmas.constant.Month;
import christmas.util.Printer;
import christmas.domain.ReservationInfo;

public class PlannerServiceImpl implements PlannerService {

    private final ReservationInfo reservationInfo = new ReservationInfo();

    @Override
    public void makeReservationDate(String date) {

        reservationInfo.makeReservation(date);
    }

    @Override
    public void reservationMenu(String orderMenu) {

        reservationInfo.submitOrder(orderMenu);
    }

    @Override
    public String printReservationPaper() {

        StringBuilder paper = new StringBuilder();
        paper.append(printEventMessage())
                .append(printOrderMenu())
                .append(printOrderPrice())
                .append(printGift())
                .append(printBenefit())
                .append(printBenefitPrice())
                .append(printExpectedPayment())
                .append(printEventBadge());

        return paper.toString();
    }

    @Override
    public String printEventMessage() {

        return Printer.EVENT_INFO_MESSAGE.
                printEventMessage(Month.DECEMBER.getMonth(),
                        reservationInfo.getReservationDate());
    }

    @Override
    public String printOrderMenu() {

        return Printer.ORDER_MENU.print(reservationInfo.getOrderMenu());
    }

    @Override
    public String printOrderPrice() {

        return Printer.ORDER_PRICE.print(reservationInfo.getOrderPrice());
    }

    @Override
    public String printGift() {

        return Printer.GIFT.print(reservationInfo.getGiftInfo());
    }

    @Override
    public String printBenefit() {

        return Printer.BENEFIT.print(reservationInfo.getBenefitInfo());
    }

    @Override
    public String printBenefitPrice() {

        return Printer.BENEFIT_PRICE.print(reservationInfo.getBenefitPrice());
    }

    @Override
    public String printExpectedPayment() {

        return Printer.EXPECTED_PRICE.print(reservationInfo.getExpectedPayment());
    }

    @Override
    public String printEventBadge() {

        return Printer.EVENT_BADGE.printEventBadge(Month.DECEMBER.getMonth(),
                reservationInfo.getEventBadgeInfo());
    }
}
