package christmas.constant;

public enum ErrorMessage {

    DATE_FORMAT_ERROR_MESSAGE ("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    DATE_RANGE_ERROR_MESSAGE ("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_EXIST_MENU_ERROR_MESSAGE ("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_COUNT_NOT_NUMBER_ERROR_MESSAGE ("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DUPLICATE_MENU_ERROR_MESSAGE ("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_FORMAT_ERROR_MESSAGE ("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_COUNT_RANGE_ERROR_MESSAGE ("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_DRINK_CAN_NOT_ORDER("[ERROR] 음료만 주문 시, 주문할 수 없습니다.");

    private String errorMessage;

    ErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
