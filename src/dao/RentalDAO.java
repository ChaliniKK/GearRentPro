package dao;

import entity.Rental;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {

    public void createRental(Rental rental) {
        String sql = """
            INSERT INTO rentals (
                equipment_id, customer_id, branch_id,
                start_date, end_date, rental_amount,
                deposit_amount, membership_discount,
                long_rental_discount, final_amount,
                payment_status, rental_status
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rental.getEquipmentId());
            ps.setInt(2, rental.getCustomerId());
            ps.setInt(3, rental.getBranchId());
            ps.setDate(4, java.sql.Date.valueOf(rental.getStartDate()));
            ps.setDate(5, java.sql.Date.valueOf(rental.getEndDate()));
            ps.setDouble(6, rental.getRentalAmount());
            ps.setDouble(7, rental.getDepositAmount());
            ps.setDouble(8, rental.getMembershipDiscount());
            ps.setDouble(9, rental.getLongRentalDiscount());
            ps.setDouble(10, rental.getFinalAmount());
            ps.setString(11, rental.getPaymentStatus());
            ps.setString(12, rental.getRentalStatus());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Rental> getActiveRentals() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals WHERE rental_status = 'ACTIVE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Rental r = new Rental();
                r.setRentalId(rs.getInt("rental_id"));
                r.setEquipmentId(rs.getInt("equipment_id"));
                r.setCustomerId(rs.getInt("customer_id"));
                r.setBranchId(rs.getInt("branch_id"));
                r.setStartDate(rs.getDate("start_date").toLocalDate());
                r.setEndDate(rs.getDate("end_date").toLocalDate());
                r.setFinalAmount(rs.getDouble("final_amount"));
                r.setRentalStatus(rs.getString("rental_status"));

                rentals.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rentals;
    }

    public List<Rental> getOverdueRentals() {

        List<Rental> overdueRentals = new ArrayList<>();

        String sql = """
        SELECT * FROM rentals
        WHERE rental_status = 'ACTIVE'
        AND end_date < CURDATE()
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Rental r = new Rental();
                r.setRentalId(rs.getInt("rental_id"));
                r.setEquipmentId(rs.getInt("equipment_id"));
                r.setCustomerId(rs.getInt("customer_id"));
                r.setBranchId(rs.getInt("branch_id"));
                r.setStartDate(rs.getDate("start_date").toLocalDate());
                r.setEndDate(rs.getDate("end_date").toLocalDate());
                r.setFinalAmount(rs.getDouble("final_amount"));
                r.setRentalStatus("OVERDUE");

                overdueRentals.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return overdueRentals;
    }

    public List<String> getOverdueRentalDetails() {

        List<String> results = new ArrayList<>();

        String sql = """
        SELECT r.rental_id,
               c.name AS customer_name,
               b.name AS branch_name,
               DATEDIFF(CURDATE(), r.end_date) AS overdue_days
        FROM rentals r
        JOIN customers c ON r.customer_id = c.customer_id
        JOIN branches b ON r.branch_id = b.branch_id
        WHERE r.rental_status = 'ACTIVE'
        AND r.end_date < CURDATE()
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                results.add(
                        "Rental #" + rs.getInt("rental_id") +
                                " | Customer: " + rs.getString("customer_name") +
                                " | Branch: " + rs.getString("branch_name") +
                                " | Overdue: " + rs.getInt("overdue_days") + " days"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }


}
