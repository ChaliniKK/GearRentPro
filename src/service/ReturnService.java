package service;

import dao.ReturnDAO;
import dao.RentalDAO;
import entity.Rental;
import entity.ReturnRecord;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReturnService {

    private final ReturnDAO returnDAO = new ReturnDAO();
    private final RentalDAO rentalDAO = new RentalDAO();

    public void processReturn(
            Rental rental,
            LocalDate actualReturnDate,
            String damageDescription,
            double damageCharge,
            double lateFeePerDay
    ) {

        long lateDays = ChronoUnit.DAYS.between(rental.getEndDate(), actualReturnDate);
        double lateFee = 0;

        if (lateDays > 0) {
            lateFee = lateDays * lateFeePerDay;
        }

        ReturnRecord record = new ReturnRecord();
        record.setRentalId(rental.getRentalId());
        record.setActualReturnDate(actualReturnDate);
        record.setDamageDescription(damageDescription);
        record.setDamageCharge(damageCharge);
        record.setLateFee(lateFee);

        returnDAO.saveReturn(record);

        System.out.println("Return processed");
        System.out.println("Late fee: " + lateFee);
        System.out.println("Damage charge: " + damageCharge);
    }
}
