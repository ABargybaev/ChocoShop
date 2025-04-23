import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/chocoshop";
        String user = "postgres";
        String password = "AzamatSQL69420";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Успешное подключение!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT version();");
            if (rs.next()) {
                System.out.println("PostgreSQL: " + rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения:");
            e.printStackTrace();
        }
    }
}
