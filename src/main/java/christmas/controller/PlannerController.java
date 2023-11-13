package christmas.controller;

import christmas.service.PlannerService;
import christmas.service.PlannerServiceImpl;

public class PlannerController {

    private final PlannerService plannerService = new PlannerServiceImpl();

    public void setReservationDate(String date) {

        plannerService.makeReservationDate(date);
    }

    public void setOrderMenu(String orderMenu) {

        plannerService.reservationMenu(orderMenu);
    }

    public void getOrderPrice() {

        //할인 전 총 주문 금액
    }

    public void getGift() {

        //증정 메뉴
    }
}
