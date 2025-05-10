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
        String sql = "SELECT * FROM chocolate ORDER BY id";

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

    public void sellUpdate(int id_choice, int quantity) {
        String sql = "UPDATE chocolate SET quantity = quantity - ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, id_choice);

            stmt.executeUpdate();
            System.out.println("Yes");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public double getPrice(int id_choice){
        String sql = "SELECT price FROM chocolate WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

             stmt.setInt(1, id_choice);
             ResultSet rs = stmt.executeQuery();

             double price = 0;
            if (rs.next()) {
                price = rs.getDouble("price");
                return price;
            }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return 0;
}

    public void addSupply(int id, int quantity) {
        String sql = "UPDATE chocolate SET quantity = quantity + ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, id);

            stmt.executeUpdate();
            System.out.println("Supply refilled successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fullInfo(int chocolate_id){
        String sql = "SELECT c.id, c.name, c.description, c.quantity, c.price, s.id AS supplier_id, s.name AS supplier_name FROM chocolate c JOIN supplier s ON c.supplier_id = s.id WHERE c.id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

             stmt.setInt(1, chocolate_id);
             ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int chocoId = rs.getInt("id");
                String chocoName = rs.getString("name");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                int supplierId = rs.getInt("supplier_id");
                String supplierName = rs.getString("supplier_name");

                System.out.printf("%-4d | %-20s | %-8.2f | %-8d | %-30s%n", chocoId, chocoName, price, quantity, description);
                System.out.printf("%-4d | %-20s%n", supplierId, supplierName);
            } else {
                System.out.println("Chocolate not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    ////// TESTING PURPOSES ONLY /////



}
