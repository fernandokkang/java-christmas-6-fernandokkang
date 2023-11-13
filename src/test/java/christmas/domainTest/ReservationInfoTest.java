package christmas.domainTest;

import christmas.constant.ErrorMessage;
import christmas.domain.ReservationInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReservationInfoTest {

    ReservationInfo reservationInfo = new ReservationInfo();

    @Test
    void 날짜_숫자_아닌_경우_예외_처리() {

        String date = "11a";

        assertThatThrownBy(() -> reservationInfo.makeReservation(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DATE_FORMAT_ERROR_MESSAGE.name());
    }

    @Test
    void 날짜_범위_벗어난_경우_예외_처리_0일() {

        String date = "0";

        assertThatThrownBy(() -> reservationInfo.makeReservation(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DATE_RANGE_ERROR_MESSAGE.name());
    }

    @Test
    void 날짜_범위_벗어난_경우_예외_처리_32일() {

        String date = "32";

        assertThatThrownBy(() -> reservationInfo.makeReservation(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DATE_RANGE_ERROR_MESSAGE.name());
    }
}
