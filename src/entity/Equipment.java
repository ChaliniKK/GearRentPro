package entity;

public class Equipment {

    private int equipmentId;
    private int categoryId;
    private int branchId;
    private String brand;
    private String model;
    private int purchaseYear;
    private double baseDailyPrice;
    private double depositAmount;
    private String status;

    public Equipment() {
    }

    public Equipment(int equipmentId, int categoryId, int branchId,
                     String brand, String model, int purchaseYear,
                     double baseDailyPrice, double depositAmount, String status) {
        this.equipmentId = equipmentId;
        this.categoryId = categoryId;
        this.branchId = branchId;
        this.brand = brand;
        this.model = model;
        this.purchaseYear = purchaseYear;
        this.baseDailyPrice = baseDailyPrice;
        this.depositAmount = depositAmount;
        this.status = status;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPurchaseYear() {
        return purchaseYear;
    }

    public void setPurchaseYear(int purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    public double getBaseDailyPrice() {
        return baseDailyPrice;
    }

    public void setBaseDailyPrice(double baseDailyPrice) {
        this.baseDailyPrice = baseDailyPrice;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
