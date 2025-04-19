/**
 * LabApp - Class utama yang menjalankan aplikasi peminjaman peralatan lab
 * Class ini menjadi entry point aplikasi dan menghubungkan semua komponen
 */
public class LabApp {
    private UserButton userButton;
    private AdminButton adminButton;
    
    private EquipmentComponent equipmentComponent;
    private OrderComponent orderComponent;
    private PaymentComponent paymentComponent;
    
    public static void main(String[] args) {
        LabApp app = new LabApp();
        app.initialize();
        app.run();
    }
    
    private void initialize() {
        // Inisialisasi komponen-komponen utama
        equipmentComponent = new EquipmentComponent();
        orderComponent = new OrderComponent();
        paymentComponent = new PaymentComponent();
        
        // Inisialisasi button-button (UI)
        userButton = new UserButton();
        adminButton = new AdminButton();
        
        // Menghubungkan komponen yang membutuhkan komponen lain
        orderComponent.connectEquipmentInterface(equipmentComponent);
        paymentComponent.connectOrderInterface(orderComponent);
        
        // Menghubungkan button dengan komponen
        userButton.connectPaymentInterface(paymentComponent);
        adminButton.connectAdminInterface(orderComponent);
    }
    
    private void run() {
        System.out.println("=== APLIKASI LAB EQUIPMENT MANAGEMENT SYSTEM ===");
        showMainMenu();
    }
    
    private void showMainMenu() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. User Menu");
            System.out.println("2. Admin Menu");
            System.out.println("0. Exit");
            System.out.print("Pilihan Anda: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    userButton.showMenu();
                    break;
                case 2:
                    adminButton.showMenu();
                    break;
                case 0:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi LabApp!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}