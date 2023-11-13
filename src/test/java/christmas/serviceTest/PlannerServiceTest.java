package christmas.serviceTest;
import christmas.domain.ReservationInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlannerServiceTest {

    ReservationInfo reservationInfo = new ReservationInfo();

    @Test
    public void setReservationDateTest() {

        String date = "15";

        reservationInfo.makeReservation(date);

        assertThat(reservationInfo.getDate()).isEqualTo("15");
    }

    @Test
    public void printOrderTest() {

        String orderMenu = "티본스테이크-2,레드와인-2";

        reservationInfo.submitOrderWithEvent(orderMenu);

        System.out.println(reservationInfo.printOrderMenu());
    }
}
