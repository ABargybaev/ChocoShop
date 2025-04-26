package chocoshop;

import java.sql.*;
import java.util.ArrayList;

public class ChocolateDB {

    public void create(Chocolate chocolate) {
        String sql = "INSERT INTO chocolate (name, description, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, chocolate.getName());
            stmt.setString(2, chocolate.getDescription());
            stmt.setInt(3, chocolate.getQuantity());
            stmt.setDouble(4, chocolate.getPrice());

            stmt.executeUpdate();
            System.out.println("Chocolate added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Chocolate> readAll() {
        ArrayList<Chocolate> chocolates = new ArrayList<>();
        String sql = "SELECT * FROM chocolate";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Chocolate c = new Chocolate(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                chocolates.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chocolates;
    }

    public void update(Chocolate chocolate) {
        String sql = "UPDATE chocolate SET name = ?, description = ?, quantity = ?, price = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, chocolate.getName());
            stmt.setString(2, chocolate.getDescription());
            stmt.setInt(3, chocolate.getQuantity());
            stmt.setDouble(4, chocolate.getPrice());
            stmt.setInt(5, chocolate.getId());

            stmt.executeUpdate();
            System.out.println("Chocolate updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM chocolate WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Chocolate removed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
