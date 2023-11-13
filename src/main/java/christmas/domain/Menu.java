package christmas.domain;

import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),
    T_BONE_STAKE("티본스테이크", 55000),
    BBQ_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000),
    EMPTY("없음", 0);

    private String menuName;
    private int price;

    private Menu(String menuName, int price) {

        this.menuName = menuName;
        this.price = price;
    }

    public static Menu findMenu(String menuName) {

        for(Menu menu : Menu.values()) {
            if(menu.getMenuName().equals(menuName)) {
                return menu;
            }
        }
        return Menu.EMPTY;
    }


    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }
}
