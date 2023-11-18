package christmas.view;

import christmas.constant.ErrorMessage;
import christmas.controller.PlannerController;

public class ChristmasPlanner {

    private final PlannerController plannerController = new PlannerController();
    private final InputView inputView = new InputView(plannerController);
    private final OutputView outputView = new OutputView(plannerController);

    public void run() {

        try {
            inputView.enterVisitDate();
            inputView.enterOrderMenu();

            outputView.printReservationPaper();

        } catch (Exception e) {

            String errorType = e.getMessage();
            System.out.println(ErrorMessage.valueOf(errorType).getErrorMessage());
        }
    }
}
