package dao;

import entity.Customer;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM customers";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setNicPassport(rs.getString("nic_passport"));
                customer.setContact(rs.getString("contact"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setMembershipId(rs.getInt("membership_id"));

                customers.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }
}
