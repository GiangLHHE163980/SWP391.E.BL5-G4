/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import model.Role;
import java.sql.*;
import java.util.*;

public class RoleDAO {

    private Connection connection;

    public RoleDAO() {
        DBContext db = new DBContext();
        this.connection = db.getConnection();
    }

    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM Roles";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Role role = new Role();
                role.setRoleID(rs.getInt("RoleID"));
                role.setRoleName(rs.getString("RoleName"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public Role getRoleByName(String roleName) {
        Role role = null;
        String query = "SELECT * FROM Roles WHERE RoleName = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, roleName); 

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                role = new Role();
                role.setRoleID(rs.getInt("RoleID"));
                role.setRoleName(rs.getString("RoleName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

}
