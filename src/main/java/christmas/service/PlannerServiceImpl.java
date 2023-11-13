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

        reservationInfo.submitOrderWithEvent(orderMenu);
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
}
