package christmas.view;

import christmas.controller.PlannerController;

public class OutputView {

    private final PlannerController plannerController;

    OutputView(PlannerController plannerController) {

        this.plannerController = plannerController;
    }

    public void printMessage() {

        System.out.println(plannerController.printMessage());
    }

    public void printOrderMenu() {

        System.out.println(plannerController.printOrderMenu());
    }

    public void printOrderPrice() {

        System.out.println(plannerController.printOrderPrice());
    }

    public void printGift() {

        System.out.println(plannerController.printGift());
    }

    public void printBenefit() {

        System.out.println(plannerController.printBenefit());
    }

    public void printBenefitPrice() {

        System.out.println(plannerController.printBenefitPrice());
    }

    public void printExpectedPayment() {

        System.out.println(plannerController.printExpectedPayment());
    }

    public void printEventBadge() {

        System.out.println(plannerController.printEventBadge());
    }
}
