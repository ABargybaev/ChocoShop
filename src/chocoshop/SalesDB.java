package chocoshop;

import java.sql.*;
import java.util.ArrayList;

public class SalesDB {

    public void create(Sale sale) {
        String sql = "INSERT INTO sales (chocolate_id, quantity, total, sale_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sale.getChocolate_Id());
            stmt.setInt(2, sale.getQuantity());
            stmt.setDouble(3, sale.getTotal());
            stmt.setString(4, sale.getSale_Date());
            stmt.executeUpdate();

            System.out.println("Sale added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Sale> readAll() {
        ArrayList<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Sale s = new Sale(
                        rs.getInt("id"),
                        rs.getInt("chocolate_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("total"),
                        rs.getString("sale_date")
                );
                sales.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sales;
    }


    public void update(Sale sale) {
        String sql = "UPDATE sales SET chocolate_id = ?, quantity = ?, total = ?, sale_date = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sale.getChocolate_Id());
            stmt.setInt(2, sale.getQuantity());
            stmt.setDouble(3, sale.getTotal());
            stmt.setString(4, sale.getSale_Date());
            stmt.setInt(5, sale.getId());

            stmt.executeUpdate();
            System.out.println("Sale updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int saleId) {
        String sql = "DELETE FROM sales WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, saleId);
            stmt.executeUpdate();
            System.out.println("Sale deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void saleTableRecord(int id_choice, int quantity, double total){
            String sql = "INSERT INTO sales (chocolate_id, quantity, total) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id_choice);
                stmt.setInt(2, quantity);
                stmt.setDouble(3, total);
                stmt.executeUpdate();

                System.out.println("Sale confirmed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}
