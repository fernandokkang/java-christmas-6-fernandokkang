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
}
