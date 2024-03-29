import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Reservation {
    private String pnr; // Adding PNR number
    private String name;
    private String contactInfo;
    private String trainDetails;
    private String dateOfJourney;
    private String destination;

    public Reservation(String pnr, String name, String contactInfo, String trainDetails, String dateOfJourney, String destination) {
        this.pnr = pnr;
        this.name = name;
        this.contactInfo = contactInfo;
        this.trainDetails = trainDetails;
        this.dateOfJourney = dateOfJourney;
        this.destination = destination;
    }

    public String getPnr() {
        return pnr;
    }

    // Getters and setters for other reservation fields
}

public class OnlineReservationSystem {
    private static Map<String, User> users = new HashMap<>();
    private static Map<String, Reservation> reservations = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Populate some sample users
        users.put("user1", new User("user1", "password1"));
        users.put("user2", new User("user2", "password2"));

        // Login
        System.out.println("Welcome to Online Reservation System");
        boolean loggedIn = false;
        do {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            loggedIn = authenticate(username, password);
            if (!loggedIn) {
                System.out.println("Invalid username or password. Please try again.");
            }
        } while (!loggedIn);
        
        // Main menu
        int choice;
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Make a reservation");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 3);
    }

    private static boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).getPassword().equals(password);
    }

    private static void makeReservation() {
        System.out.println("\nReservation Form:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Contact Info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Train Details: ");
        String trainDetails = scanner.nextLine();
        System.out.print("Date of Journey: ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Destination: ");
        String destination = scanner.nextLine();

        // Generate a unique PNR number
        String pnr = UUID.randomUUID().toString();

        Reservation reservation = new Reservation(pnr, name, contactInfo, trainDetails, dateOfJourney, destination);
        reservations.put(pnr, reservation);
        System.out.println("Reservation successful! Your PNR number is: " + pnr);
    }

    private static void cancelReservation() {
        System.out.println("\nCancellation Form:");
        System.out.print("Enter PNR number for cancellation: ");
        String pnr = scanner.nextLine();
        if (reservations.containsKey(pnr)) {
            System.out.println("Reservation details:");
            System.out.println(reservations.get(pnr));
            System.out.print("Confirm cancellation? (Y/N): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("Y")) {
                reservations.remove(pnr);
                System.out.println("Reservation cancelled successfully!");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Reservation not found for the provided PNR.");
        }
    }
}
