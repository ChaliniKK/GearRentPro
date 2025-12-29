package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ReportDAO {

    public void getBranchRevenueReport(LocalDate startDate, LocalDate endDate) {

        String sql = """
            SELECT b.name AS branch_name,
                   COUNT(r.rental_id) AS total_rentals,
                   SUM(r.final_amount) AS total_income,
                   IFNULL(SUM(rt.late_fee), 0) AS total_late_fees,
                   IFNULL(SUM(rt.damage_charge), 0) AS total_damage_charges
            FROM rentals r
            JOIN branches b ON r.branch_id = b.branch_id
            LEFT JOIN returns rt ON r.rental_id = rt.rental_id
            WHERE r.start_date BETWEEN ? AND ?
            GROUP BY b.name
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(startDate));
            ps.setDate(2, java.sql.Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getString("branch_name") + " | " +
                                "Rentals: " + rs.getInt("total_rentals") + " | " +
                                "Income: " + rs.getDouble("total_income") + " | " +
                                "Late Fees: " + rs.getDouble("total_late_fees") + " | " +
                                "Damage: " + rs.getDouble("total_damage_charges")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getEquipmentUtilizationReport(int branchId) {

        String sql = """
        SELECT e.equipment_id,
               e.brand,
               e.model,
               IFNULL(SUM(DATEDIFF(r.end_date, r.start_date) + 1), 0) AS rented_days
        FROM equipment e
        LEFT JOIN rentals r ON e.equipment_id = r.equipment_id
        WHERE e.branch_id = ?
        GROUP BY e.equipment_id, e.brand, e.model
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, branchId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Equipment: " + rs.getInt("equipment_id") +
                                " | " + rs.getString("brand") +
                                " " + rs.getString("model") +
                                " | Rented Days: " + rs.getInt("rented_days")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
