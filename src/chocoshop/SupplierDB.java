package chocoshop;

import java.sql.*;
import java.util.ArrayList;

public class SupplierDB {

    public void create(Supplier supplier) {
        String sql = "INSERT INTO supplier (name, address, city, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getAddress());
            stmt.setString(3, supplier.getCity());
            stmt.setString(4, supplier.getPhone());

            stmt.executeUpdate();
            System.out.println("Supplier added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Supplier> readAll() {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Supplier c = new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("phone")
                );
                suppliers.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public void update(Supplier supplier) {
        String sql = "UPDATE supplier SET name = ?, address = ?, city = ?, phone = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getAddress());
            stmt.setString(3, supplier.getCity());
            stmt.setString(4, supplier.getPhone());
            stmt.setInt(5, supplier.getId());

            stmt.executeUpdate();
            System.out.println("Supplier updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM supplier WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Supplier removed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
