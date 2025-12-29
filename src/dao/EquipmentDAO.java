package dao;

import entity.Equipment;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO {

    public List<Equipment> getAllEquipment() {
        List<Equipment> equipmentList = new ArrayList<>();

        String sql = "SELECT * FROM equipment";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Equipment eq = new Equipment();
                eq.setEquipmentId(rs.getInt("equipment_id"));
                eq.setCategoryId(rs.getInt("category_id"));
                eq.setBranchId(rs.getInt("branch_id"));
                eq.setBrand(rs.getString("brand"));
                eq.setModel(rs.getString("model"));
                eq.setPurchaseYear(rs.getInt("purchase_year"));
                eq.setBaseDailyPrice(rs.getDouble("base_daily_price"));
                eq.setDepositAmount(rs.getDouble("deposit_amount"));
                eq.setStatus(rs.getString("status"));

                equipmentList.add(eq);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipmentList;
    }
}
