package entity;

import java.time.LocalDate;

public class ReturnRecord {

    private int returnId;
    private int rentalId;
    private LocalDate actualReturnDate;
    private String damageDescription;
    private double damageCharge;
    private double lateFee;

    public ReturnRecord() {
    }

    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public double getDamageCharge() {
        return damageCharge;
    }

    public void setDamageCharge(double damageCharge) {
        this.damageCharge = damageCharge;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }
}
