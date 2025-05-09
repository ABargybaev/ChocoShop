package chocoshop;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    private ChocolateDB chocolateDB = new ChocolateDB();
    private SupplierDB supplierDB = new SupplierDB();
    ArrayList<Chocolate> chocolates = chocolateDB.readAll();
    Scanner scan = new Scanner(System.in);

    public void Trade(){
        for (Chocolate ch : chocolates){
            System.out.println(ch.getId() +") " + ch.getName());
        }
        System.out.print("\nChoose the ID Number of the desired chocolate: ");
        int choice = scan.nextInt();
        scan.nextLine();
        for (Chocolate ch : chocolates) {
            if (ch.getId() == choice) {
                System.out.printf("%d) %s | %.2f | %d | %-30s%n", ch.getId(), ch.getName(), ch.getPrice(), ch.getQuantity(), ch.getDescription());
            }
        }
        System.out.print("\nEnter the amount of chocolate you want to buy: ");
        int amount = scan.nextInt();
        scan.nextLine();
        chocolateDB.sellUpdate(choice, amount);
    }

    ////// TESTING PURPOSES ONLY /////

    public void recoverQuantity(){
        System.out.print("\nWhat chocolate: ");
        int choice = scan.nextInt();
        scan.nextLine();
        for (Chocolate ch : chocolates) {
            if (ch.getId() == choice) {
                System.out.printf("%d) %s | %.2f | %d | %-30s%n", ch.getId(), ch.getName(), ch.getPrice(), ch.getQuantity(), ch.getDescription());
            }
        }
        System.out.print("\nAmount add: ");
        int amount = scan.nextInt();
        scan.nextLine();
        chocolateDB.recoverQuantity(choice, amount);
    }


    public void showAllChocolates(){
        for (Chocolate ch : chocolates){
            System.out.printf("%-4d | %-20s | %-8.2f | %-8d | %-30s%n", ch.getId(), ch.getName(), ch.getPrice(), ch.getQuantity(), ch.getDescription());
        }
    }

    public void showOneChocolate(){
        System.out.print("\nWhat chocolate: ");
        int choice = scan.nextInt();
        scan.nextLine();
        for (Chocolate ch : chocolates) {
            if (ch.getId() == choice) {
                System.out.printf("%d) %s | %.2f | %d | %-30s%n", ch.getId(), ch.getName(), ch.getPrice(), ch.getQuantity(), ch.getDescription());
            }
        }
    }

}
