package dao;

import entity.Branch;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO {

    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();

        String sql = "SELECT * FROM branches";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Branch branch = new Branch();
                branch.setBranchId(rs.getInt("branch_id"));
                branch.setBranchCode(rs.getString("branch_code"));
                branch.setName(rs.getString("name"));
                branch.setAddress(rs.getString("address"));
                branch.setContact(rs.getString("contact"));

                branches.add(branch);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return branches;
    }
}
