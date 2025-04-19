import java.util.List;
import java.util.Scanner;

/**
 * AdminButton - Class untuk UI admin dengan semua fitur admin
 * Class ini mengimplementasikan UserInterface (provided interface)
 * dan membutuhkan AdminInterface (required interface)
 */
public class AdminButton implements UserInterface {
    // Required interface - diinisialisasi melalui metode connect
    private AdminInterface adminInterface;
    
    private Scanner scanner = new Scanner(System.in);
    
    /**
     * Metode untuk menghubungkan komponen dengan AdminInterface
     * Metode ini mengimplementasikan koneksi required interface
     * @param adminInterface Komponen AdminInterface yang akan dikoneksikan
     */
    public void connectAdminInterface(AdminInterface adminInterface) {
        this.adminInterface = adminInterface;
    }
    
    @Override
    public void displayMenu() {
        System.out.println("\n=== ADMIN MENU ===");
        System.out.println("1. View All Equipment");
        System.out.println("2. Add New Equipment");
        System.out.println("3. Update Equipment Info");
        System.out.println("4. Delete Equipment");
        System.out.println("0. Back");
    }
    
    @Override
    public void processUserInput(int choice) {
        switch (choice) {
            case 1:
                viewAllEquipment();
                break;
            case 2:
                addNewEquipment();
                break;
            case 3:
                updateEquipmentInfo();
                break;
            case 4:
                deleteEquipment();
                break;
            case 0:
                System.out.println("Kembali ke menu sebelumnya.");
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
    
    /**
     * Menampilkan menu utama dan memproses input admin
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
     * Menampilkan semua peralatan
     */
    private void viewAllEquipment() {
        // Required interface harus terkoneksi sebelum digunakan
        if (adminInterface == null) {
            System.out.println("Error: AdminInterface belum terkoneksi!");
            return;
        }
        
        System.out.println("\n=== SEMUA PERALATAN ===");
        List<Equipment> allEquipments = adminInterface.getAllEquipment();
        
        if (allEquipments.isEmpty()) {
            System.out.println("Tidak ada peralatan yang terdaftar dalam sistem.");
            return;
        }
        
        for (Equipment equipment : allEquipments) {
            System.out.println(equipment);
            System.out.println("-------------------------");
        }
    }
    
    /**
     * Menambahkan peralatan baru
     */
    private void addNewEquipment() {
        // Required interface harus terkoneksi sebelum digunakan
        if (adminInterface == null) {
            System.out.println("Error: AdminInterface belum terkoneksi!");
            return;
        }
        
        System.out.println("\n=== TAMBAH PERALATAN BARU ===");
        
        // Generate ID baru (dalam implementasi nyata, ini mungkin akan ditangani oleh database)
        int newId = adminInterface.getAllEquipment().size() + 1;
        
        System.out.print("Nama Peralatan: ");
        String name = scanner.nextLine();
        
        System.out.print("Deskripsi: ");
        String description = scanner.nextLine();
        
        System.out.print("Harga Sewa per Hari (Rp): ");
        double rentPrice = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        
        // Buat objek peralatan baru
        Equipment newEquipment = new Equipment(newId, name, description, rentPrice, true);
        
        // Tambahkan ke sistem
        boolean success = adminInterface.addEquipment(newEquipment);
        
        if (success) {
            System.out.println("Peralatan berhasil ditambahkan dengan ID: " + newId);
        } else {
            System.out.println("Gagal menambahkan peralatan!");
        }
    }
    
    /**
     * Memperbarui informasi peralatan
     */
    private void updateEquipmentInfo() {
        // Required interface harus terkoneksi sebelum digunakan
        if (adminInterface == null) {
            System.out.println("Error: AdminInterface belum terkoneksi!");
            return;
        }
        
        System.out.println("\n=== UPDATE INFORMASI PERALATAN ===");
        
        // Tampilkan semua peralatan
        List<Equipment> allEquipments = adminInterface.getAllEquipment();
        
        if (allEquipments.isEmpty()) {
            System.out.println("Tidak ada peralatan yang terdaftar dalam sistem.");
            return;
        }
        
        System.out.println("Daftar Peralatan:");
        for (Equipment equipment : allEquipments) {
            System.out.println(equipment);
            System.out.println("-------------------------");
        }
        
        // Pilih peralatan untuk diupdate
        System.out.print("\nMasukkan ID peralatan yang ingin diupdate: ");
        int equipmentId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Equipment existingEquipment = adminInterface.getEquipmentById(equipmentId);
        
        if (existingEquipment == null) {
            System.out.println("Peralatan dengan ID tersebut tidak ditemukan!");
            return;
        }
        
        // Input informasi baru
        System.out.println("Masukkan informasi baru (kosongkan jika tidak ingin mengubah)");
        
        System.out.print("Nama Peralatan [" + existingEquipment.getName() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            existingEquipment.setName(name);
        }
        
        System.out.print("Deskripsi [" + existingEquipment.getDescription() + "]: ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            existingEquipment.setDescription(description);
        }
        
        System.out.print("Harga Sewa per Hari (Rp) [" + existingEquipment.getRentPrice() + "]: ");
        String rentPriceStr = scanner.nextLine();
        if (!rentPriceStr.isEmpty()) {
            try {
                double rentPrice = Double.parseDouble(rentPriceStr);
                existingEquipment.setRentPrice(rentPrice);
            } catch (NumberFormatException e) {
                System.out.println("Format harga tidak valid! Harga tidak diubah.");
            }
        }
        
        System.out.print("Status Ketersediaan (true/false) [" + existingEquipment.isAvailable() + "]: ");
        String availabilityStr = scanner.nextLine();
        if (!availabilityStr.isEmpty()) {
            try {
                boolean availability = Boolean.parseBoolean(availabilityStr);
                existingEquipment.setAvailable(availability);
            } catch (Exception e) {
                System.out.println("Format status tidak valid! Status tidak diubah.");
            }
        }
        
        // Update peralatan
        boolean success = adminInterface.updateEquipment(equipmentId, existingEquipment);
        
        if (success) {
            System.out.println("Informasi peralatan berhasil diperbarui!");
        } else {
            System.out.println("Gagal memperbarui informasi peralatan!");
        }
    }
    
    /**
     * Menghapus peralatan
     */
    private void deleteEquipment() {
        // Required interface harus terkoneksi sebelum digunakan
        if (adminInterface == null) {
            System.out.println("Error: AdminInterface belum terkoneksi!");
            return;
        }
        
        System.out.println("\n=== HAPUS PERALATAN ===");
        
        // Tampilkan semua peralatan
        List<Equipment> allEquipments = adminInterface.getAllEquipment();
        
        if (allEquipments.isEmpty()) {
            System.out.println("Tidak ada peralatan yang terdaftar dalam sistem.");
            return;
        }
        
        System.out.println("Daftar Peralatan:");
        for (Equipment equipment : allEquipments) {
            System.out.println(equipment);
            System.out.println("-------------------------");
        }
        
        // Pilih peralatan untuk dihapus
        System.out.print("\nMasukkan ID peralatan yang ingin dihapus: ");
        int equipmentId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        // Konfirmasi penghapusan
        System.out.print("Apakah Anda yakin ingin menghapus peralatan ini? (y/n): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y")) {
            boolean success = adminInterface.deleteEquipment(equipmentId);
            
            if (success) {
                System.out.println("Peralatan berhasil dihapus!");
            } else {
                System.out.println("Gagal menghapus peralatan! Peralatan mungkin sedang dipinjam atau tidak ditemukan.");
            }
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }
}