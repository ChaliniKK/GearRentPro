package dao;

import entity.ReturnRecord;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReturnDAO {

    public void saveReturn(ReturnRecord record) {

        String sql = """
            INSERT INTO returns
            (rental_id, actual_return_date, damage_description, damage_charge, late_fee)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, record.getRentalId());
            ps.setDate(2, java.sql.Date.valueOf(record.getActualReturnDate()));
            ps.setString(3, record.getDamageDescription());
            ps.setDouble(4, record.getDamageCharge());
            ps.setDouble(5, record.getLateFee());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
