package christmas.constant;

public enum InfoMessage {

    LESS_THAN_TEN_THOUSAND_WON("총주문 금액 10,000원 이상부터 이벤트가 적용됩니다."),
    ONLY_DRINK_CAN_NOT_ORDER("음료만 주문 시, 주문할 수 없습니다."),
    MORE_THAN_TWENTY_MENUS_CAN_NOT_ORDER("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    private String infoMessage;

    private InfoMessage(String infoMessage) {

        this.infoMessage = infoMessage;
    }

    public String getInfoMessage() {
        return infoMessage;
    }
}
