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

    public String printMessage() {

        return plannerService.printMessage();
    }

    public String printOrderMenu() {

        return plannerService.printOrderMenu();
    }

    public String printOrderPrice() {

        return plannerService.printOrderPrice();
    }

    public String printGift() {

        return plannerService.printGift();
    }

    public String printBenefit() {

        return plannerService.printBenefit();
    }

    public String printBenefitPrice() {

        return plannerService.printBenefitPrice();
    }

    public String printExpectedPayment() {

        return plannerService.printExpectedPayment();
    }

    public String printEventBadge() {

        return plannerService.printEventBadge();
    }
}
