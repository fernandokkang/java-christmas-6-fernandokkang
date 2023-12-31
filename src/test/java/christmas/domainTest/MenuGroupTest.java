package christmas.domainTest;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.domain.Menu;
import christmas.domain.MenuGroup;
import org.junit.jupiter.api.Test;
public class MenuGroupTest {

    @Test
    public void MenuGroup_에게_메뉴_타입_물어보기 () {

        String menuName = Menu.ZERO_COKE.getMenuName();
        MenuGroup menuGroup = MenuGroup.findByMenu(menuName);

        assertThat(menuGroup.name()).isEqualTo("DRINK");
        assertThat(menuGroup.getMenuType()).isEqualTo("음료");
    }
}
