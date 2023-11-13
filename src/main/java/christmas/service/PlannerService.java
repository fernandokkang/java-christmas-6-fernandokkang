package christmas.service;

public interface PlannerService {

    public void makeReservationDate(String date);
    public void reservationMenu(String orderMenu);
    public String printOrderMenu();
    public String printOrderPrice();
    public String printGift();
}
