package christmas.view;

import christmas.constant.ErrorMessage;
import christmas.controller.PlannerController;

public class ChristmasPlanner {

    PlannerController plannerController = new PlannerController();
    private final InputView inputView = new InputView(plannerController);
    private final OutputView outputView = new OutputView(plannerController);

    public void run() {

        try {
            inputView.enterVisitDate();
            inputView.enterOrderMenu();

            outputView.printOrderMenu();
            outputView.printOrderPrice();

        } catch (Exception e) {

            String errorType = e.getMessage();

            System.out.println(ErrorMessage.valueOf(errorType).getErrorMessage());
        }
    }
}
