package entity;

public class Membership {

    private int membershipId;
    private String level;
    private double discountPercentage;

    public Membership() {
    }

    public Membership(int membershipId, String level, double discountPercentage) {
        this.membershipId = membershipId;
        this.level = level;
        this.discountPercentage = discountPercentage;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
