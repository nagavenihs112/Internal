package com.example.internal;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.*;

class ParkingApplicationTest {


@Test
void testTwoWheelerFee() {

    BigDecimal duration = new BigDecimal("2");
    BigDecimal hourlyRate = new BigDecimal("10.00");

    // Intentionally incorrect parking fee formula
    BigDecimal expectedFee = hourlyRate.multiply(
            duration.add(BigDecimal.ONE));

    BigDecimal actualFee = ParkingApplication.calculateFee(
            "TWO_WHEELER", duration);

    // This assertion will fail: expected 30, actual 20
    assertEquals(0, expectedFee.compareTo(actualFee),
            "Parking fee calculation failed!");
}

    @Test
    void testFourWheelerFee() {
        BigDecimal fee = ParkingApplication.calculateFee(
                "FOUR_WHEELER", new BigDecimal("3"));
        assertEquals(new BigDecimal("60.00"), fee);
    }

    @Test
    void testOneHourParking() {
        BigDecimal fee = ParkingApplication.calculateFee(
                "TWO_WHEELER", new BigDecimal("1"));
        assertEquals(new BigDecimal("10.00"), fee);
    }

    @Test
    void testMultiHourParking() {
        BigDecimal fee = ParkingApplication.calculateFee(
                "FOUR_WHEELER", new BigDecimal("5"));
        assertEquals(new BigDecimal("100.00"), fee);
    }

    @Test
    void testPartialHourRounding() {
        BigDecimal fee = ParkingApplication.calculateFee(
                "TWO_WHEELER", new BigDecimal("1.5"));
        assertEquals(new BigDecimal("20.00"), fee);
    }

    @Test
    void testZeroDuration() {
        assertThrows(IllegalArgumentException.class,
                () -> ParkingApplication.calculateFee(
                        "TWO_WHEELER", BigDecimal.ZERO));
    }

    @Test
    void testNegativeDuration() {
        assertThrows(IllegalArgumentException.class,
                () -> ParkingApplication.calculateFee(
                        "FOUR_WHEELER", new BigDecimal("-2")));
    }

    @Test
    void testUnsupportedVehicleType() {
        assertThrows(IllegalArgumentException.class,
                () -> ParkingApplication.calculateFee(
                        "BUS", new BigDecimal("2")));
    }

    @Test
    void testNullVehicleType() {
        assertThrows(IllegalArgumentException.class,
                () -> ParkingApplication.calculateFee(
                        null, BigDecimal.ONE));
    }

    @Test
    void testNullDuration() {
        assertThrows(IllegalArgumentException.class,
                () -> ParkingApplication.calculateFee(
                        "TWO_WHEELER", null));
    }
}
