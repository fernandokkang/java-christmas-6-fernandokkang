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

            outputView.printMessage();
            outputView.printOrderMenu();
            outputView.printOrderPrice();
            outputView.printGift();
            outputView.printBenefit();
            outputView.printBenefitPrice();
            outputView.printExpectedPayment();
            outputView.printEventBadge();

        } catch (Exception e) {

            String errorType = e.getMessage();
            System.out.println(ErrorMessage.valueOf(errorType).getErrorMessage());
        }
    }
}
