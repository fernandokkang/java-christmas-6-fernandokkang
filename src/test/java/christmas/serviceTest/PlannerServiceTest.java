package christmas.serviceTest;
import christmas.domain.ReservationInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlannerServiceTest {

    private ReservationInfo reservationInfo;

    @Test
    public void setReservationDateTest() {

        String date = "15";

        reservationInfo = new ReservationInfo(date);

        assertThat(reservationInfo.getDate()).isEqualTo("15");
    }
}
