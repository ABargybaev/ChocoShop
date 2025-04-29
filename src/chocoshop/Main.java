package chocoshop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWelcome to Chocolate Shop system! List of actions:\n \n 1) Make a trade\n 2) Add a new type of chocolate\n 3) Show all suppliers\n 4) Add a new supplier\n 5) Exit\n\n Choose your action: ");
        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                menu.Trade();
            }
            else if (choice == 69) {
                menu.showAllChocolates();
            }
        }
    }
}
