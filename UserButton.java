import java.util.List;
import java.util.Scanner;

/**
 * UserButton - Class untuk UI pengguna dengan semua fitur user
 * Class ini mengimplementasikan UserInterface (provided interface)
 * dan membutuhkan PaymentInterface (required interface)
 */
public class UserButton implements UserInterface {
    // Required interface - diinisialisasi melalui metode connect
    private PaymentInterface paymentInterface;
    
    // Simulasi user yang sedang login
    private int currentUserId = 1;
    
    private Scanner scanner = new Scanner(System.in);
    
    /**
     * Metode untuk menghubungkan komponen dengan PaymentInterface
     * Metode ini mengimplementasikan koneksi required interface
     * @param paymentInterface Komponen PaymentInterface yang akan dikoneksikan
     */
    public void connectPaymentInterface(PaymentInterface paymentInterface) {
        this.paymentInterface = paymentInterface;
    }
    
    @Override
    public void displayMenu() {
        System.out.println("\n=== USER MENU ===");
        System.out.println("1. View Available Equipment");
        System.out.println("2. Rent Equipment");
        System.out.println("3. View My Orders");
        System.out.println("4. Make Payment");
        System.out.println("5. Return Equipment");
        System.out.println("0. Back");
    }
    
    @Override
    public void processUserInput(int choice) {
        switch (choice) {
            case 1:
                viewAvailableEquipment();
                break;
            case 2:
                rentEquipment();
                break;
            case 3:
                viewMyOrders();
                break;
            case 4:
                makePayment();
                break;
            case 5:
                returnEquipment();
                break;
            case 0:
                System.out.println("Kembali ke menu sebelumnya.");
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
    
    /**
     * Menampilkan menu utama dan memproses input user
     */
    public void showMenu() {
        boolean running = true;
        
        while (running) {
            displayMenu();
            System.out.print("Pilihan Anda: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            if (choice == 0) {
                running = false;
            } else {
                processUserInput(choice);
            }
        }
    }
    
    /**
     * Menampilkan daftar peralatan yang tersedia
     */
    private void viewAvailableEquipment() {
        // Required interface harus terkoneksi sebelum digunakan
        if (paymentInterface == null) {
            System.out.println("Error: PaymentInterface belum terkoneksi!");
            return;
        }
        
        // Dalam implementasi pola desain ini, kita mendapatkan data dari PaymentInterface
        // yang terhubung ke OrderInterface yang terhubung ke EquipmentInterface
        
        System.out.println("\n=== PERALATAN YANG TERSEDIA ===");
        
        // Mendapatkan daftar peralatan tersedia melalui composite (rantai) method
        List<Equipment> availableEquipments = paymentInterface.getAvailableEquipment();
        
        if (availableEquipments.isEmpty()) {
            System.out.println("Tidak ada peralatan yang tersedia saat ini.");
            return;
        }
        
        for (Equipment equipment : availableEquipments) {
            System.out.println(equipment);
            System.out.println("-------------------------");
        }
    }
    
    /**
     * Melakukan peminjaman peralatan
     */
    private void rentEquipment() {
        // Required interface harus terkoneksi sebelum digunakan
        if (paymentInterface == null) {
            System.out.println("Error: PaymentInterface belum terkoneksi!");
            return;
        }
        
        System.out.println("\n=== PEMINJAMAN PERALATAN ===");
        
        // Tampilkan peralatan yang tersedia
        List<Equipment> availableEquipments = paymentInterface.getAvailableEquipment();
        
        if (availableEquipments.isEmpty()) {
            System.out.println("Tidak ada peralatan yang tersedia saat ini.");
            return;
        }
        
        System.out.println("Peralatan yang tersedia:");
        for (Equipment equipment : availableEquipments) {
            System.out.println(equipment);
            System.out.println("-------------------------");
        }
        
        // Pilih peralatan
        System.out.print("\nMasukkan ID peralatan yang ingin dipinjam: ");
        int equipmentId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        // Masukkan durasi peminjaman
        System.out.print("Masukkan durasi peminjaman (dalam hari): ");
        int durationDays = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (durationDays <= 0) {
            System.out.println("Durasi harus lebih dari 0 hari!");
            return;
        }
        
        // Buat pesanan
        int orderId = paymentInterface.createOrder(currentUserId, equipmentId, durationDays);
        
        if (orderId != -1) {
            System.out.println("Pesanan berhasil dibuat dengan ID: " + orderId);
            System.out.println("Silakan lakukan pembayaran untuk menyelesaikan proses peminjaman.");
        } else {
            System.out.println("Gagal membuat pesanan!");
        }
    }
    
    /**
     * Melihat daftar pesanan user
     */
    private void viewMyOrders() {
        // Required interface harus terkoneksi sebelum digunakan
        if (paymentInterface == null) {
            System.out.println("Error: PaymentInterface belum terkoneksi!");
            return;
        }
        
        System.out.println("\n=== PESANAN SAYA ===");
        List<Order> myOrders = paymentInterface.getUserOrders(currentUserId);
        
        if (myOrders.isEmpty()) {
            System.out.println("Anda belum memiliki pesanan.");
            return;
        }
        
        for (Order order : myOrders) {
            Equipment equipment = paymentInterface.getEquipmentById(order.getEquipmentId());
            String equipmentName = (equipment != null) ? equipment.getName() : "Unknown";
            
            System.out.println(order);
            System.out.println("Nama Peralatan: " + equipmentName);
            System.out.println("-------------------------");
        }
    }
    
    /**
     * Melakukan pembayaran untuk pesanan
     */
    private void makePayment() {
        // Required interface harus terkoneksi sebelum digunakan
        if (paymentInterface == null) {
            System.out.println("Error: PaymentInterface belum terkoneksi!");
            return;
        }
        
        System.out.println("\n=== PEMBAYARAN ===");
        
        // Tampilkan pesanan yang belum dibayar
        List<Order> myOrders = paymentInterface.getUserOrders(currentUserId);
        List<Order> unpaidOrders = new java.util.ArrayList<>();
        
        for (Order order : myOrders) {
            if ("PENDING".equals(order.getStatus())) {
                unpaidOrders.add(order);
            }
        }
        
        if (unpaidOrders.isEmpty()) {
            System.out.println("Tidak ada pesanan yang menunggu pembayaran.");
            return;
        }
        
        System.out.println("Pesanan yang menunggu pembayaran:");
        for (Order order : unpaidOrders) {
            Equipment equipment = paymentInterface.getEquipmentById(order.getEquipmentId());
            String equipmentName = (equipment != null) ? equipment.getName() : "Unknown";
            
            System.out.println(order);
            System.out.println("Nama Peralatan: " + equipmentName);
            System.out.println("-------------------------");
        }
        
        // Pilih pesanan untuk dibayar
        System.out.print("\nMasukkan ID pesanan yang ingin dibayar: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        // Hitung jumlah pembayaran
        double amount = paymentInterface.calculatePaymentAmount(orderId);
        
        if (amount <= 0) {
            System.out.println("Pesanan tidak valid atau sudah dibayar!");
            return;
        }
        
        // Pilih metode pembayaran
        System.out.println("\nTotal pembayaran: Rp" + amount);
        System.out.println("Pilih metode pembayaran:");
        System.out.println("1. Transfer Bank");
        System.out.println("2. Kartu Kredit");
        System.out.println("3. E-Wallet");
        System.out.print("Pilihan Anda: ");
        
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        String paymentMethod;
        switch (paymentChoice) {
            case 1:
                paymentMethod = "Transfer Bank";
                break;
            case 2:
                paymentMethod = "Kartu Kredit";
                break;
            case 3:
                paymentMethod = "E-Wallet";
                break;
            default:
                System.out.println("Pilihan tidak valid! Menggunakan Transfer Bank sebagai default.");
                paymentMethod = "Transfer Bank";
        }
        
        // Proses pembayaran
        int paymentId = paymentInterface.createPayment(orderId, amount, paymentMethod);
        
        if (paymentId != -1) {
            System.out.println("Pembayaran berhasil dengan ID: " + paymentId);
        } else {
            System.out.println("Gagal melakukan pembayaran!");
        }
    }
    
    /**
     * Mengembalikan peralatan yang dipinjam
     */
    private void returnEquipment() {
        // Required interface harus terkoneksi sebelum digunakan
        if (paymentInterface == null) {
            System.out.println("Error: PaymentInterface belum terkoneksi!");
            return;
        }
        
        System.out.println("\n=== PENGEMBALIAN PERALATAN ===");
        
        // Tampilkan pesanan yang sudah dibayar tapi belum dikembalikan
        List<Order> myOrders = paymentInterface.getUserOrders(currentUserId);
        List<Order> activeOrders = new java.util.ArrayList<>();
        
        for (Order order : myOrders) {
            if ("PAID".equals(order.getStatus())) {
                activeOrders.add(order);
            }
        }
        
        if (activeOrders.isEmpty()) {
            System.out.println("Tidak ada peralatan yang perlu dikembalikan.");
            return;
        }
        
        System.out.println("Peralatan yang dapat dikembalikan:");
        for (Order order : activeOrders) {
            Equipment equipment = paymentInterface.getEquipmentById(order.getEquipmentId());
            String equipmentName = (equipment != null) ? equipment.getName() : "Unknown";
            
            System.out.println(order);
            System.out.println("Nama Peralatan: " + equipmentName);
            System.out.println("-------------------------");
        }
        
        // Pilih pesanan untuk dikembalikan
        System.out.print("\nMasukkan ID pesanan untuk pengembalian peralatan: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        // Proses pengembalian
        boolean success = paymentInterface.returnEquipment(orderId);
        
        if (success) {
            System.out.println("Peralatan berhasil dikembalikan!");
        } else {
            System.out.println("Gagal mengembalikan peralatan!");
        }
    }
}