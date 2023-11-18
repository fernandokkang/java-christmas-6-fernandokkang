package christmas.service;

public interface PlannerService {
    public void makeReservationDate(String date);
    public void reservationMenu(String orderMenu);
    public String printReservationPaper();
    public String printEventMessage();
    public String printOrderMenu();
    public String printOrderPrice();
    public String printGift();
    public String printBenefit();
    public String printBenefitPrice();
    public String printExpectedPayment();
    public String printEventBadge();
}
