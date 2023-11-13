package christmas.view;

import christmas.constant.ErrorMessage;

public class ChristmasPlanner {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {

        try {
            inputView.enterVisitDate();
            inputView.enterOrderMenu();
        } catch (Exception e) {

            String errorType = e.getMessage();

            System.out.println(ErrorMessage.valueOf(errorType).getErrorMessage());
        }
    }
}
