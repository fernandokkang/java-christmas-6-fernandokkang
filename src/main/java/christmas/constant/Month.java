package christmas.constant;

public enum Month {

    JANUARY("1월",1,31),
    FEBRUARY("2월",1,28),
    LEAP_YEAR_FEBRUARY("2월",1,29),
    MARCH("3월",1,31),
    APRIL("4월",1,30),
    MAY("5월",1,31),
    JUNE("6월",1,30),
    JULY("7월",1,31),
    AUGUST("8월",1,31),
    SEPTEMBER("9월",1,30),
    OCTOBER("10월",1,31),
    NOVEMBER("11월",1,30),
    DECEMBER("12월",1,31);

    private String month;
    private int firstDay;
    private int lastDay;

    Month(String month, int firstDay, int lastDay) {

        this.month = month;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
    }

    public String getMonth() {
        return month;
    }

    public int getFirstDay() {
        return firstDay;
    }

    public int getLastDay() {
        return lastDay;
    }
}
