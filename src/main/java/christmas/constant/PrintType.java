package christmas.constant;

public enum PrintType {

    EVENT_INFO_MESSAGE("%s %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n"),
    ORDER_MENU("<주문 메뉴>"),
    ORDER_PRICE("<할인 전 총주문 금액>"),
    GIFT("<증정 메뉴>"),
    BENEFIT("<혜택 내역>"),
    BENEFIT_PRICE("<총혜택 금액>"),
    EXPECTED_PRICE("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<%s 이벤트 배지>%n");

    private String label;
    PrintType(String label) {
        
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
