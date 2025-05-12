package chocoshop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        Scanner scan = new Scanner(System.in);

        System.out.println("\nWelcome to Chocolate Shop system!");

        while (true) {
            System.out.print("\nList of actions:\n \n 1) Make a trade\n 2) Replenish supplies of the chocolate\n 3) Change information about a supplier\n 4) Show information about a chocolate\n 5) Trades information\n 6) Show all chocolates\n 7) Show all suppliers\n 8) Exit the program \n\n Choose your action: ");
            int choice = scan.nextInt();
            scan.nextLine();
            System.out.print("\n");
            switch (choice) {
                case 1:
                    menu.Trade();
                    break;
                case 2:
                    menu.addSupply();
                    break;
                case 3:
                    menu.changeSupplierInfo();
                    break;
                case 4:
                    menu.fullChocolateInfo();
                    break;
                case 5:
                    menu.tradeMenu();
                    break;
                case 6:
                    menu.showAllChocolates();
                    break;
                case 7:
                    menu.showAllSuppliers();
                    break;
                case 8:
                    System.out.println("Come again!\nExiting program...");
                    return;
                default:
                    System.out.println("Try again!");
            }
        }
    }
}
