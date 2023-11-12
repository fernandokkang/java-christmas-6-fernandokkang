package christmas.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum MenuGroup {

    APPETIZER("애피타이저", Arrays.asList(Menu.MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN_MENU("메인", Arrays.asList(Menu.T_BONE_STAKE, Menu.BBQ_RIBS, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(Menu.CHOCOLATE_CAKE, Menu.ICE_CREAM)),
    DRINK("음료", Arrays.asList(Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAGNE)),
    EMPTY("없음", Collections.EMPTY_LIST);

    private String menuType;
    private List<Menu> menuList;

    MenuGroup(String menuType, List<Menu> menuList) {

        this.menuType = menuType;
        this.menuList = menuList;
    }

    public static MenuGroup findByMenu(String menuName) {

        return Arrays.stream(MenuGroup.values())
                .filter(MenuGroup->MenuGroup.hasMenuName(menuName))
                .findAny()
                .orElse(EMPTY);

    }

    public boolean hasMenuName(String menuName) {

        for (MenuGroup menuGroup : MenuGroup.values()) {
            for(Menu menu : menuGroup.menuList){
                if(menu.getMenuName().equals(menuName)){
                    return true;
                }
            }
        }
        return false;
    }

    public String getMenuType() {
        return menuType;
    }
}
