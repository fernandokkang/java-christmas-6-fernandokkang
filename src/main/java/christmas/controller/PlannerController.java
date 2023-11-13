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

    public String printOrderMenu() {

        return plannerService.printOrderMenu();
    }

    public String printOrderPrice() {

        return plannerService.printOrderPrice();
    }

    public void getGift() {

        //증정 메뉴
    }
}
