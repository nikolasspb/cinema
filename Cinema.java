package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seat = sc.nextInt();
        System.out.println();
        var arr = createArr(seat, row);
        boolean repeatAnswer = true;
        while (repeatAnswer) {
            var userChoice = showMenu();
            switch (userChoice) {
                case 1:
                    printSeats(arr);
                    break;
                case 2:
                    addClient(arr);
                    break;
                case 3:
                    showStatistics(arr);
                    break;
                case 0:
                    repeatAnswer = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static void printSeats(String[][] arr) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int a = 0; a < arr[0].length; a++) {
            System.out.print(" " + (a + 1));
        }
        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(" " + arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static String[][] createArr(int seat, int row) {
        String[][] arr = new String[row][seat];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < seat; j++) {
                arr[i][j] = "S";
            }
        }
        return arr;
    }

    private static int showMenu() {
        Scanner sc = new Scanner(System.in);
        int userChoice;
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
        userChoice = sc.nextInt();
        return userChoice;
    }

    private static void addClient(String[][] arr) {
        Scanner sc = new Scanner(System.in);
        boolean repeatInput = true;
        int row = 0;
        int seat = 0;
        while (repeatInput) {
            System.out.println();
            System.out.println("Enter a row number:");
            row = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = sc.nextInt();
            if (arr.length < row || arr[0].length < seat) {
                System.out.println("Wrong input!");
            } else {
                if ("B".equals(arr[row - 1][seat - 1])) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    repeatInput = false;
                }
            }
            System.out.println();
        }
        ticketPrice(arr, row - 1, true);
        System.out.println();
        arr[row - 1][seat - 1] = "B";
    }

    private static void showStatistics(String[][] arr) {
        int totalIncome = 0;
        int total = 0;
        int tickets = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                totalIncome = totalIncome + ticketPrice(arr, i, false);
                if ("B".equals(arr[i][j])) {
                    total = total + ticketPrice(arr, i, false);
                    tickets++;
                }
            }
        }
        double d = (double) tickets / (arr.length * arr[0].length) * 100;
        System.out.println("Number of purchased tickets: " + tickets);
        System.out.printf("Percentage: %.2f%s\n", d, "%");
        System.out.println("Current income: $" + total);
        System.out.println("Total income: $" + totalIncome + "\n");
    }

    private static int ticketPrice(String[][] arr, int row, boolean printResult) {
        int price;
        if (arr.length * arr[0].length > 60) {
            if (row <= (arr.length / 2 - 1)) {
                price = 10;
            } else {
                price = 8;
            }
        } else {
            price = 10;
        }
        if (printResult) {
            System.out.println("Ticket price: $" + price);
        }
        return price;
    }
}
