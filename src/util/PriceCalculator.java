package util;

import entity.Equipment;
import entity.Membership;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class PriceCalculator {

    // Base rental calculation
    public static double calculateBasePrice(
            Equipment equipment,
            LocalDate startDate,
            LocalDate endDate
    ) {
        long days = java.time.temporal.ChronoUnit.DAYS
                .between(startDate, endDate) + 1;

        double total = days * equipment.getBaseDailyPrice();

        // Weekend surcharge (10%)
        if (includesWeekend(startDate, endDate)) {
            total *= 1.10;
        }

        return total;
    }

    // Membership discount
    public static double applyMembershipDiscount(
            double amount,
            Membership membership
    ) {
        if (membership == null) return amount;

        double discount = membership.getDiscountPercentage();
        return amount - (amount * discount / 100);
    }


    private static boolean includesWeekend(
            LocalDate start,
            LocalDate end
    ) {
        LocalDate date = start;
        while (!date.isAfter(end)) {
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return true;
            }
            date = date.plusDays(1);
        }
        return false;
    }
}
