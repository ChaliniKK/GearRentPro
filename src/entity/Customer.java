package entity;

public class Customer {

    private int customerId;
    private String name;
    private String nicPassport;
    private String contact;
    private String email;
    private String address;
    private int membershipId;

    public Customer() {
    }

    public Customer(int customerId, String name, String nicPassport,
                    String contact, String email, String address, int membershipId) {
        this.customerId = customerId;
        this.name = name;
        this.nicPassport = nicPassport;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.membershipId = membershipId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNicPassport() {
        return nicPassport;
    }

    public void setNicPassport(String nicPassport) {
        this.nicPassport = nicPassport;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }
}
