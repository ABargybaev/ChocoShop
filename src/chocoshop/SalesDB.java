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

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public ArrayList<Sale> salesByDate(String sale_date) {
            ArrayList<Sale> sales = new ArrayList<>();
            String sql = "SELECT * FROM sales WHERE sale_date = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setDate(1, java.sql.Date.valueOf(sale_date));
                ResultSet rs = stmt.executeQuery();

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

        public ArrayList<Sale> salesByChocolate(int chocolate_id){
                ArrayList<Sale> sales = new ArrayList<>();
                String sql = "SELECT * FROM sales WHERE chocolate_id = ?";
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {

                    stmt.setInt(1, chocolate_id);
                    ResultSet rs = stmt.executeQuery();

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

        public void salesOverall(){
                String sql = "SELECT SUM(quantity) AS total_quantity, SUM(total) AS total_revenue FROM sales";

                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                    if (rs.next()) {
                        int totalQuantity = rs.getInt("total_quantity");
                        double totalRevenue = rs.getDouble("total_revenue");

                        System.out.println("Total sales:");
                        System.out.printf("Overall chocolates sold: %d%n", totalQuantity);
                        System.out.printf("Overall money made: %.2f%n", totalRevenue);
                    } else {
                        System.out.println("no sales data.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
}
