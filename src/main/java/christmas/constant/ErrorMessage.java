package christmas.constant;

public enum ErrorMessage {

    DATE_FORMAT_ERROR_MESSAGE ("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요. 날짜에 숫자 아닌 입력"),
    DATE_RANGE_ERROR_MESSAGE ("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요. 날짜 범위에 해당하지 않는 입력"),
    NOT_EXIST_MENU_ERROR_MESSAGE ("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요. 없는 메뉴"),
    MENU_COUNT_NOT_NUMBER_ERROR_MESSAGE ("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요. 메뉴에 숫자 아닌 입력"),
    DUPLICATE_MENU_ERROR_MESSAGE ("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요. 중복 메뉴 입력"),
    MENU_FORMAT_ERROR_MESSAGE ("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요. 메뉴 입력 형식 위반"),
    MENU_COUNT_RANGE_ERROR_MESSAGE ("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요. 주문 가능 수량 위반");

    private String errorMessage;

    ErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
