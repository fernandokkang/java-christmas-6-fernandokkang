package christmas.service;

import christmas.domain.Order;
import christmas.domain.ReservationInfo;

public class PlannerServiceImpl implements PlannerService {

    private ReservationInfo reservationInfo;
    private Order order;
    @Override
    public void makeReservationDate(String date) {

        reservationInfo = new ReservationInfo(date);
    }

    @Override
    public void reservationMenu(String orderMenu) {

        reservationInfo.reserveToOrder(orderMenu);
    }
}
