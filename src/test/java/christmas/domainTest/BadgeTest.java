package christmas.domainTest;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Badge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BadgeTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @DisplayName("뱃지 출력 확인 산타")
    @Test
    void printBadgeTest_SANTA() {

        int benefitPrice = 30000;

        Badge badge = Badge.giveBadge(benefitPrice);

        assertThat(badge.getType())
                .contains("산타");
    }

    @DisplayName("뱃지 출력 확인 트리")
    @Test
    void printBadgeTest_TREE() {

        int benefitPrice = 15000;

        Badge badge = Badge.giveBadge(benefitPrice);

        assertThat(badge.getType())
                .contains("트리");
    }

    @DisplayName("뱃지 출력 확인 스타")
    @Test
    void printBadgeTest_STAR() {

        int benefitPrice = 5000;

        Badge badge = Badge.giveBadge(benefitPrice);

        assertThat(badge.getType())
                .contains("별");
    }

    @DisplayName("뱃지 출력 확인 없음")
    @Test
    void printBadgeTest_EMPTY() {

        int benefitPrice = 0;

        Badge badge = Badge.giveBadge(benefitPrice);

        assertThat(badge.getType())
                .contains("없음");
    }
}
