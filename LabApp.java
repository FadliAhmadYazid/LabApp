// LabApp.java - Main application
import java.util.Scanner;

public class LabApp {
    private EquipmentManager equipmentManager;
    private PaymentProcessor paymentProcessor;
    private NotificationService notificationService;
    private OrderManager orderManager;
    private Scanner scanner;
    private String currentUserId;
    private boolean isAdmin;
    
    public LabApp() {
        // Initialize components
        equipmentManager = new EquipmentManager();
        paymentProcessor = new PaymentProcessor();
        notificationService = new NotificationService();
        
        // Connect components through interfaces
        orderManager = new OrderManager(equipmentManager, paymentProcessor, notificationService);
        paymentProcessor.setOrderManager(orderManager);
        
        scanner = new Scanner(System.in);
        currentUserId = "U001"; // Default user for simplicity
        isAdmin = false; // Default role is user
    }
    
    public void start() {
        selectRole();
        boolean exit = false;
        
        while (!exit) {
            if (isAdmin) {
                displayAdminMenu();
            } else {
                displayUserMenu();
            }
            
            int choice = getUserChoice();
            
            if (isAdmin) {
                exit = handleAdminChoice(choice);
            } else {
                exit = handleUserChoice(choice);
            }
        }
        
        scanner.close();
    }
    
    private void selectRole() {
        System.out.println("===== LAB EQUIPMENT RENTAL SYSTEM =====");
        System.out.println("Select your role:");
        System.out.println("1. User");
        System.out.println("2. Admin");
        System.out.print("Enter your choice: ");
        
        int roleChoice = getUserChoice();
        
        if (roleChoice == 2) {
            isAdmin = true;
            System.out.println("Logged in as Admin");
        } else {
            isAdmin = false;
            System.out.println("Logged in as User");
        }
    }
    
    private void displayUserMenu() {
        System.out.println("\n===== LAB EQUIPMENT RENTAL SYSTEM - USER =====");
        System.out.println("1. View Available Equipment");
        System.out.println("2. Rent Equipment");
        System.out.println("3. View My Orders");
        System.out.println("4. Make Payment");
        System.out.println("5. Return Equipment");
        System.out.println("0. Exit");
        System.out.println("==========================================");
        System.out.print("Enter your choice: ");
    }
    
    private void displayAdminMenu() {
        System.out.println("\n===== LAB EQUIPMENT RENTAL SYSTEM - ADMIN =====");
        System.out.println("1. View All Equipment");
        System.out.println("2. Add New Equipment");
        System.out.println("3. Update Equipment Info");
        System.out.println("0. Exit");
        System.out.println("===========================================");
        System.out.print("Enter your choice: ");
    }
    
    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private boolean handleUserChoice(int choice) {
        switch (choice) {
            case 1: // View available equipment
                equipmentManager.displayAvailableEquipment();
                break;
                
            case 2: // Rent equipment
                rentEquipment();
                break;
                
            case 3: // View my orders
                orderManager.displayUserOrders(currentUserId);
                break;
                
            case 4: // Make payment
                makePayment();
                break;
                
            case 5: // Return equipment
                returnEquipment();
                break;
                
            case 0: // Exit
                System.out.println("Thank you for using the Lab Equipment Rental System!");
                return true;
                
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return false;
    }
    
    private boolean handleAdminChoice(int choice) {
        switch (choice) {
            case 1: // View all equipment
                equipmentManager.displayAvailableEquipment();
                break;
                
            case 2: // Add new equipment
                addNewEquipment();
                break;
                
            case 3: // Update equipment
                updateEquipment();
                break;
                
            case 0: // Exit
                System.out.println("Thank you for using the Lab Equipment Rental System!");
                return true;
                
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return false;
    }
    
    private void rentEquipment() {
        equipmentManager.displayAvailableEquipment();
        
        System.out.print("Enter Equipment ID to rent: ");
        String equipmentId = scanner.nextLine();
        
        if (!equipmentManager.checkAvailability(equipmentId)) {
            System.out.println("Sorry, this equipment is not available.");
            return;
        }
        
        System.out.print("Enter number of days to rent: ");
        int days;
        try {
            days = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
        
        if (days <= 0) {
            System.out.println("Number of days must be positive.");
            return;
        }
        
        String orderId = orderManager.createOrder(currentUserId, equipmentId, days);
        if (orderId != null) {
            System.out.println("Equipment rented successfully! Order ID: " + orderId);
        }
    }
    
    private void makePayment() {
        orderManager.displayUserOrders(currentUserId);
        
        System.out.print("Enter Order ID to pay: ");
        String orderId = scanner.nextLine();
        
        System.out.print("Enter amount to pay: $");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
        
        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return;
        }
        
        paymentProcessor.processPayment(orderId, amount);
    }
    
    private void returnEquipment() {
        orderManager.displayUserOrders(currentUserId);
        
        System.out.print("Enter Order ID for equipment return: ");
        String orderId = scanner.nextLine();
        
        System.out.print("Is the equipment damaged? (yes/no): ");
        String damaged = scanner.nextLine().toLowerCase();
        boolean isDamaged = damaged.equals("yes") || damaged.equals("y");
        
        orderManager.returnEquipment(orderId, isDamaged);
    }
    
    private void addNewEquipment() {
        System.out.print("Enter new Equipment ID: ");
        String equipmentId = scanner.nextLine();
        
        System.out.print("Enter Equipment Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Rental Price per day: $");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
        
        if (price <= 0) {
            System.out.println("Price must be positive.");
            return;
        }
        
        equipmentManager.addNewEquipment(equipmentId, name, price);
    }
    
    private void updateEquipment() {
        equipmentManager.displayAvailableEquipment();
        
        System.out.print("Enter Equipment ID to update: ");
        String equipmentId = scanner.nextLine();
        
        System.out.print("Enter new Equipment Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter new Rental Price per day: $");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
        
        if (price <= 0) {
            System.out.println("Price must be positive.");
            return;
        }
        
        equipmentManager.updateEquipmentInfo(equipmentId, name, price);
    }
    
    public static void main(String[] args) {
        LabApp app = new LabApp();
        app.start();
    }
}