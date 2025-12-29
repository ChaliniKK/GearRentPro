package dao;

import entity.Reservation;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ReservationDAO {

    // Check if equipment is already reserved for given dates
    public boolean hasOverlappingReservation(int equipmentId, LocalDate start, LocalDate end) {

        String sql = """
            SELECT COUNT(*) FROM reservations
            WHERE equipment_id = ?
            AND status = 'ACTIVE'
            AND NOT (end_date < ? OR start_date > ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, equipmentId);
            ps.setDate(2, java.sql.Date.valueOf(start));
            ps.setDate(3, java.sql.Date.valueOf(end));

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getInt(1) > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    // Create reservation
    public void createReservation(Reservation reservation) {

        String sql = """
            INSERT INTO reservations (equipment_id, customer_id, start_date, end_date, status)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reservation.getEquipmentId());
            ps.setInt(2, reservation.getCustomerId());
            ps.setDate(3, java.sql.Date.valueOf(reservation.getStartDate()));
            ps.setDate(4, java.sql.Date.valueOf(reservation.getEndDate()));
            ps.setString(5, reservation.getStatus());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
