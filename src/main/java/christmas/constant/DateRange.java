package christmas.constant;

public enum DateRange {

    JANUARY(1,31),
    FEBRUARY(1,28),
    LEAP_YEAR_FEBRUARY(1,29),
    MARCH(1,31),
    APRIL(1,30),
    MAY(1,31),
    JUNE(1,30),
    JULY(1,31),
    AUGUST(1,31),
    SEPTEMBER(1,30),
    OCTOBER(1,31),
    NOVEMBER(1,30),
    DECEMBER(1,31);

    private int firstDay;
    private int lastDay;

    DateRange(int firstDay, int lastDay) {

        this.firstDay = firstDay;
        this.lastDay = lastDay;
    }

    public int getFirstDay() {
        return firstDay;
    }

    public int getLastDay() {
        return lastDay;
    }
}
