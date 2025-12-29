package dao;

import entity.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public User getUserByUsername(String username) {

        String sql = """
            SELECT * FROM users
            WHERE username = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setBranchId(rs.getInt("branch_id"));
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
