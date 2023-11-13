package christmas.view;

import christmas.controller.PlannerController;

public class OutputView {

    private final PlannerController plannerController;

    OutputView(PlannerController plannerController){

        this.plannerController = plannerController;
    }

    public void printOrderMenu() {

        System.out.println(plannerController.printOrderMenu());
    }

    public void printOrderPrice() {

        System.out.println(plannerController.printOrderPrice());
    }

    public void printGift(){

        System.out.println(plannerController.printGift());
    }
}
