package entity;

import java.time.LocalDate;

public class Rental {

    private int rentalId;
    private int equipmentId;
    private int customerId;
    private int branchId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double rentalAmount;
    private double depositAmount;
    private double membershipDiscount;
    private double longRentalDiscount;
    private double finalAmount;
    private String paymentStatus;
    private String rentalStatus;

    public Rental() {
    }

    public Rental(int rentalId, int equipmentId, int customerId, int branchId,
                  LocalDate startDate, LocalDate endDate, double rentalAmount,
                  double depositAmount, double membershipDiscount,
                  double longRentalDiscount, double finalAmount,
                  String paymentStatus, String rentalStatus) {
        this.rentalId = rentalId;
        this.equipmentId = equipmentId;
        this.customerId = customerId;
        this.branchId = branchId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalAmount = rentalAmount;
        this.depositAmount = depositAmount;
        this.membershipDiscount = membershipDiscount;
        this.longRentalDiscount = longRentalDiscount;
        this.finalAmount = finalAmount;
        this.paymentStatus = paymentStatus;
        this.rentalStatus = rentalStatus;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
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

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
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

    public double getRentalAmount() {
        return rentalAmount;
    }

    public void setRentalAmount(double rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getMembershipDiscount() {
        return membershipDiscount;
    }

    public void setMembershipDiscount(double membershipDiscount) {
        this.membershipDiscount = membershipDiscount;
    }

    public double getLongRentalDiscount() {
        return longRentalDiscount;
    }

    public void setLongRentalDiscount(double longRentalDiscount) {
        this.longRentalDiscount = longRentalDiscount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}
