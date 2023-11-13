package christmas.domainTest;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.domain.Menu;
import christmas.domain.MenuGroup;
import org.junit.jupiter.api.Test;

public class MenuTest {

    @Test
    public void Menu_에게_메뉴_이름_물어보기 () {

        String menuName = "제로콜라";
        Menu menu = Menu.findMenu(menuName);

        assertThat(menu).isEqualTo(Menu.ZERO_COKE);

    }
}
