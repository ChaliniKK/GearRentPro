package service;

import dao.EquipmentDAO;
import entity.Equipment;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class RentalService {

    // Calculate number of rental days
    public long calculateDays(LocalDate start, LocalDate end) {
        return end.toEpochDay() - start.toEpochDay() + 1;
    }

    // Check if a date is weekend
    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    // Calculate final rental price
    public double calculateRentalAmount(
            Equipment equipment,
            double categoryFactor,
            double weekendMultiplier,
            LocalDate startDate,
            LocalDate endDate
    ) {
        double total = 0;
        LocalDate date = startDate;

        while (!date.isAfter(endDate)) {
            double dailyPrice = equipment.getBaseDailyPrice() * categoryFactor;
            if (isWeekend(date)) {
                dailyPrice *= weekendMultiplier;
            }
            total += dailyPrice;
            date = date.plusDays(1);
        }

        return total;
    }

    // Long rental discount
    public double applyLongRentalDiscount(double amount, long days) {
        if (days >= 7) {
            return amount * 0.10; // 10% discount
        }
        return 0;
    }

    // Membership discount
    public double applyMembershipDiscount(double amount, double discountPercentage) {
        return amount * (discountPercentage / 100);
    }
}
