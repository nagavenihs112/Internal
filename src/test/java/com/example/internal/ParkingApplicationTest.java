package com.example.internal;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ParkingApplicationTest {

    // Test 1: Two wheeler fee
    @Test
    void testTwoWheelerFee() {

        BigDecimal fee = ParkingApplication.calculateFee(
                "TWO_WHEELER",
                new BigDecimal("2"));

        assertEquals(new BigDecimal("20.00"), fee);
    }

    // Test 2: Four wheeler fee
    @Test
    void testFourWheelerFee() {

        BigDecimal fee = ParkingApplication.calculateFee(
                "FOUR_WHEELER",
                new BigDecimal("3"));

        assertEquals(new BigDecimal("60.00"), fee);
    }

    // Test 3: One hour parking
    @Test
    void testOneHourParking() {

        BigDecimal fee = ParkingApplication.calculateFee(
                "TWO_WHEELER",
                new BigDecimal("1"));

        assertEquals(new BigDecimal("10.00"), fee);
    }

    // Test 4: Multi-hour parking
    @Test
    void testMultiHourParking() {

        BigDecimal fee = ParkingApplication.calculateFee(
                "FOUR_WHEELER",
                new BigDecimal("5"));

        assertEquals(new BigDecimal("100.00"), fee);
    }

    // Test 5: Partial hour rounding
    @Test
    void testPartialHourRounding() {

        BigDecimal fee = ParkingApplication.calculateFee(
                "TWO_WHEELER",
                new BigDecimal("1.5"));

        assertEquals(new BigDecimal("20.00"), fee);
    }

    // Test 6: Zero duration
    @Test
    void testZeroDuration() {

        assertThrows(
                IllegalArgumentException.class,
                () -> ParkingApplication.calculateFee(
                        "TWO_WHEELER",
                        BigDecimal.ZERO)
        );
    }

    // Test 7: Negative duration
    @Test
    void testNegativeDuration() {

        assertThrows(
                IllegalArgumentException.class,
                () -> ParkingApplication.calculateFee(
                        "FOUR_WHEELER",
                        new BigDecimal("-2"))
        );
    }

    // Test 8: Unsupported vehicle type
    @Test
    void testUnsupportedVehicleType() {

        assertThrows(
                IllegalArgumentException.class,
                () -> ParkingApplication.calculateFee(
                        "BUS",
                        new BigDecimal("2"))
        );
    }

    // Test 9: Null vehicle type
    @Test
    void testNullVehicleType() {

        assertThrows(
                IllegalArgumentException.class,
                () -> ParkingApplication.calculateFee(
                        null,
                        BigDecimal.ONE)
        );
    }

    // Test 10: Null duration
    @Test
    void testNullDuration() {

        assertThrows(
                IllegalArgumentException.class,
                () -> ParkingApplication.calculateFee(
                        "TWO_WHEELER",
                        null)
        );
    }
}
