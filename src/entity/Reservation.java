package entity;

import java.time.LocalDate;

public class Reservation {

    private int reservationId;
    private int equipmentId;
    private int customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public Reservation() {
    }

    public Reservation(int reservationId, int equipmentId, int customerId,
                       LocalDate startDate, LocalDate endDate, String status) {
        this.reservationId = reservationId;
        this.equipmentId = equipmentId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
