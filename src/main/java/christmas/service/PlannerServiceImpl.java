package christmas.service;

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

        String LINE_SEPARATOR = System.lineSeparator();

        StringBuilder paper = new StringBuilder();
        paper.append(printMessage())
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
    public String printMessage() {

        return reservationInfo.printMessage();
    }

    @Override
    public String printOrderMenu() {

        return reservationInfo.printOrderMenu();
    }

    @Override
    public String printOrderPrice() {

        return reservationInfo.printOrderPrice();
    }

    @Override
    public String printGift() {

        return reservationInfo.printGiftInfo();
    }

    @Override
    public String printBenefit() {

        return reservationInfo.printBenefitInfo();
    }

    @Override
    public String printBenefitPrice() {

        return reservationInfo.printBenefitPrice();
    }

    @Override
    public String printExpectedPayment() {

        return reservationInfo.printExpectedPayment();
    }

    @Override
    public String printEventBadge() {

        return reservationInfo.printEventBadge();
    }
}
