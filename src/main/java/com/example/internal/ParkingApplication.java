package com.example.internal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class ParkingApplication {

    // Calculate parking fee
    public static BigDecimal calculateFee(
            String vehicleType, BigDecimal duration) {

        if (vehicleType == null) {
            throw new IllegalArgumentException(
                    "Vehicle type is required");
        }

        if (duration == null ||
                duration.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Duration must be greater than zero");
        }

        BigDecimal hourlyRate;

        switch (vehicleType.trim().toUpperCase()) {

            case "TWO_WHEELER":
                hourlyRate = new BigDecimal("10.00");
                break;

            case "FOUR_WHEELER":
                hourlyRate = new BigDecimal("20.00");
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported vehicle type");
        }

        // Round partial hours up
        BigDecimal billableHours =
                duration.setScale(0, RoundingMode.CEILING);

        return hourlyRate.multiply(billableHours)
                .setScale(2, RoundingMode.HALF_UP);
    }

    // Main method
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println(
                    "===== VEHICLE PARKING FEE CALCULATOR =====");

            System.out.println("1. Two Wheeler");
            System.out.println("2. Four Wheeler");

            System.out.print("Enter vehicle type: ");
            String choice = scanner.nextLine();

            String vehicleType;

            if (choice.equals("1")) {
                vehicleType = "TWO_WHEELER";
            } else if (choice.equals("2")) {
                vehicleType = "FOUR_WHEELER";
            } else {
                vehicleType = choice;
            }

            System.out.print(
                    "Enter parking duration in hours: ");

            BigDecimal duration =
                    new BigDecimal(scanner.nextLine());

            BigDecimal fee =
                    calculateFee(vehicleType, duration);

            BigDecimal hourlyRate =
                    vehicleType.equalsIgnoreCase("TWO_WHEELER")
                            ? new BigDecimal("10.00")
                            : new BigDecimal("20.00");

            System.out.println("\n===== PARKING DETAILS =====");

            System.out.println(
                    "Vehicle Type: " + vehicleType);

            System.out.println(
                    "Parking Duration: " + duration + " hours");

            System.out.println(
                    "Hourly Rate: Rs. " + hourlyRate);

            System.out.println(
                    "Total Parking Fee: Rs. " + fee);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: Please enter a valid duration.");

        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}
