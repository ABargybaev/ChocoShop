package chocoshop;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    private ChocolateDB chocolateDB = new ChocolateDB();
    private SupplierDB supplierDB = new SupplierDB();
    private SalesDB salesDB = new SalesDB();

    ArrayList<Chocolate> chocolates = chocolateDB.readAll();
    ArrayList<Supplier> suppliers = supplierDB.readAll();
    ArrayList<Sale> sales = salesDB.readAll();
    Scanner scan = new Scanner(System.in);

    public void Trade() {
        for (Chocolate ch : chocolates) {
            System.out.println(ch.getId() + ") " + ch.getName());
        }
        System.out.print("\nChoose the ID Number of the desired chocolate: ");
        int id_choice = readInt();
        scan.nextLine();
        for (Chocolate ch : chocolates) {
            if (ch.getId() == id_choice) {
                System.out.printf("%d) %s | %.2f | %d | %-30s%n", ch.getId(), ch.getName(), ch.getPrice(), ch.getQuantity(), ch.getDescription());
            }
        }
        System.out.print("\nEnter the amount of chocolate you want to buy: ");
        int amount = scan.nextInt();
        scan.nextLine();
        double total = chocolateDB.getPrice(id_choice) * amount;
        System.out.println("Total price: " + total);

        chocolateDB.sellUpdate(id_choice, amount);
        salesDB.saleTableRecord(id_choice, amount, total);

        System.out.println("The trade offer confirmed!");
        menuStopper();
    }

    public void addSupply() {
        showAllChocolates();
        System.out.print("\nSupply of what chocolate would you like to add?: ");
        int choice = scan.nextInt();
        scan.nextLine();
        for (Chocolate ch : chocolates) {
            if (ch.getId() == choice) {
                System.out.printf("%d) %s | %.2f | %d | %-30s%n", ch.getId(), ch.getName(), ch.getPrice(), ch.getQuantity(), ch.getDescription());
            }
        }
        System.out.print("\nEnter the amount of chocolate you want to add: ");
        int amount = scan.nextInt();
        scan.nextLine();
        chocolateDB.addSupply(choice, amount);
        menuStopper();
    }

    public void changeSupplierInfo() {
        showAllSuppliers();
        System.out.print("\nChoose the ID of the supplier you want to change: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter the name of the supplier: ");
        String name = scan.nextLine();
        System.out.print("Enter the address: ");
        String address = scan.nextLine();
        System.out.print("Enter the city: ");
        String city = scan.nextLine();
        System.out.print("Enter the phone number: ");
        String phone = scan.nextLine();
        Supplier sup = new Supplier(id, name, address, city, phone);
        supplierDB.update(sup);
        menuStopper();
    }

    public void fullChocolateInfo() {
        showAllChocolates();
        System.out.print("\nChoose the ID of the desired chocolate: ");
        int id = scan.nextInt();
        scan.nextLine();
        chocolateDB.fullInfo(id);
        menuStopper();
    }

    public void tradeMenu() {
        System.out.print("\n 1) Show all sales by date\n 2) Show all sales by chocolate\n 3) Show total sales\n\nWhat information would you like to read?: ");
        int choice = scan.nextInt();
        scan.nextLine();
        switch (choice) {
            case 1:
                salesByDate();
                break;
            case 2:
                salesbyChocolate();
                break;
            case 3:
                salesOverall();
                break;
            default:
                System.out.println("\nTry again!");
        }
    }

    public void salesByDate() {
        System.out.print("\nEnter the date (yyyy-mm-dd): ");
        String sale_date = scan.nextLine();
        ArrayList<Sale> sales = salesDB.salesByDate(sale_date);
        for (Sale s : sales) {
            System.out.printf("ID: %d | Chocolate ID: %d | Qty: %d | Total: %.2f | Date: %s%n",
                    s.getId(), s.getChocolate_Id(), s.getQuantity(), s.getTotal(), s.getSale_Date());
        }
        menuStopper();
    }

    public void salesbyChocolate() {
        System.out.print("\nEnter the chocolate ID: ");
        int id = scan.nextInt();
        scan.nextLine();
        ArrayList<Sale> sales = salesDB.salesByChocolate(id);
        for (Sale s : sales) {
            System.out.printf("ID: %d | Chocolate ID: %d | Qty: %d | Total: %.2f | Date: %s%n",
                    s.getId(), s.getChocolate_Id(), s.getQuantity(), s.getTotal(), s.getSale_Date());
        }
        menuStopper();
    }

    public void salesOverall() {
        salesDB.salesOverall();
        menuStopper();
    }


    public void showAllSuppliers() {
        for (Supplier su : suppliers) {
            System.out.printf("%-4d | %-20s | %-25s | %-10s | %-30s%n", su.getId(), su.getName(), su.getAddress(), su.getCity(), su.getPhone());
        }
        menuStopper();
    }


    public void showAllChocolates() {
        for (Chocolate ch : chocolates) {
            System.out.printf("%-4d | %-20s | %-8.2f | %-8d | %-30s%n", ch.getId(), ch.getName(), ch.getPrice(), ch.getQuantity(), ch.getDescription());
        }
        menuStopper();
    }



    public int readInt(){
        while (true) {
            int value = scan.nextInt();
            scan.nextLine();
            if(value > 0){
                return value;
            }
            else{
                System.out.println("Error! Try again:");
            }
        }
    }

    public void menuStopper(){
        System.out.println("To continue press any key:");
        String stopper = scan.nextLine();
    }

}

