package christmas.util;

public enum Printer {

    EVENT_INFO_MESSAGE("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    ORDER_PRICE("<할인 전 총주문 금액>"),
    GIFT("<증정 메뉴>"),
    BENEFIT("<혜택 내역>"),
    BENEFIT_PRICE("<총혜택 금액>"),
    EXPECTED_PRICE("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<%s 이벤트 배지>");

    private String format;
    Printer(String format) {
        
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public String print(String content) {

        return String.format("%s%n%s%n", getFormat(),content);
    }

    public String printEventMessage(String month, String date) {

        return String.format("%s %s%s%n%n",month,date,getFormat());
    }

    public String printEventBadge(String month, String content) {

        return String.format("%s%n%s%n", String.format(getFormat(), month),content);
    }
}
