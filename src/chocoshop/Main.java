package chocoshop;

import java.sql.*;
import java.util.ArrayList;
import chocoshop.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        ChocolateDB repo = new ChocolateDB();

        ArrayList<Chocolate> chocolates = repo.readAll();

        System.out.println("List of chocolates:");
        for (Chocolate c : chocolates) {
            System.out.println(c.getId() + " | " + c.getName() + " | " + c.getPrice() + "$ | Remaining: " + c.getQuantity());
        }

        SupplierDB supplier = new SupplierDB();
        ArrayList<Supplier> suppliers = supplier.readAll();
        System.out.println("List of suppliers:");
        for (Supplier s : suppliers) {
            System.out.println(s.getId() + " | " + s.getName() + " | " + s.getAddress() + " | " + s.getCity() + " | " + s.getPhone());
        }


    }
}
