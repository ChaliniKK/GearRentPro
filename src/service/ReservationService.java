package service;

import dao.ReservationDAO;
import entity.Reservation;

import java.time.LocalDate;

public class ReservationService {

    private final ReservationDAO reservationDAO = new ReservationDAO();

    public boolean createReservation(
            int equipmentId,
            int customerId,
            LocalDate start,
            LocalDate end
    ) {

        if (end.isBefore(start)) {
            System.out.println("Invalid date range");
            return false;
        }

        if (start.plusDays(30).isBefore(end)) {
            System.out.println("Reservation exceeds 30 days");
            return false;
        }

        if (reservationDAO.hasOverlappingReservation(equipmentId, start, end)) {
            System.out.println("Equipment already reserved for these dates");
            return false;
        }

        Reservation r = new Reservation();
        r.setEquipmentId(equipmentId);
        r.setCustomerId(customerId);
        r.setStartDate(start);
        r.setEndDate(end);
        r.setStatus("ACTIVE");

        reservationDAO.createReservation(r);
        System.out.println("Reservation created successfully");
        return true;
    }
}
