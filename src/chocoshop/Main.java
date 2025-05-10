package chocoshop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        Scanner scan = new Scanner(System.in);

        System.out.println("\nWelcome to Chocolate Shop system!");

        while (true) {
            System.out.print("\nList of actions:\n \n 1) Make a trade\n 2) Add a new type of chocolate\n 3) Show all suppliers\n 4) Add a new supplier\n 5) Exit\n\n Choose your action: ");
            int choice = scan.nextInt();
            scan.nextLine();
            System.out.print("\n");
            switch (choice) {
                case 1:
                    menu.Trade();
                    break;
                    /////testing area/////
                case 69:
                    menu.showAllChocolates();
                    break;
                case 68:
                    menu.showOneChocolate();
                    break;
                case 100:
                    menu.recoverQuantity();
                    break;
                case 5:
                    System.out.println("Come again!\nExiting program...");
                    return;
                default:
                    System.out.println("Try again!");
            }
        }

    }
}
