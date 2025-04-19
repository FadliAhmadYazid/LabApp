import java.util.List;

/**
 * PaymentInterface - Interface untuk mengelola pembayaran dan akses ke orderInterface
 * Interface ini berfungsi sebagai provided interface untuk komponen yang mengimplementasikannya
 * dan required interface untuk komponen yang membutuhkannya
 */
public interface PaymentInterface {
    /**
     * Membuat pembayaran baru untuk pesanan
     * @param orderId ID pesanan
     * @param amount Jumlah pembayaran
     * @param paymentMethod Metode pembayaran
     * @return ID pembayaran jika berhasil, -1 jika gagal
     */
    int createPayment(int orderId, double amount, String paymentMethod);
    
    /**
     * Mendapatkan daftar pembayaran berdasarkan ID user
     * @param userId ID user
     * @return List pembayaran
     */
    List<Payment> getUserPayments(int userId);
    
    /**
     * Memperbarui status pembayaran
     * @param paymentId ID pembayaran
     * @param status Status baru
     * @return true jika berhasil, false jika gagal
     */
    boolean updatePaymentStatus(int paymentId, String status);
    
    /**
     * Menghitung jumlah yang harus dibayar untuk pesanan
     * @param orderId ID pesanan
     * @return Jumlah pembayaran
     */
    double calculatePaymentAmount(int orderId);
    
    /**
     * Mendapatkan pembayaran berdasarkan ID pesanan
     * @param orderId ID pesanan
     * @return Objek pembayaran jika ditemukan, null jika tidak
     */
    Payment getPaymentByOrderId(int orderId);
    
    // ===== Delegasi ke OrderInterface =====
    
    /**
     * Membuat pesanan baru (delegasi ke OrderInterface)
     * @param userId ID user yang melakukan peminjaman
     * @param equipmentId ID peralatan yang dipinjam
     * @param durationDays Durasi peminjaman dalam hari
     * @return ID pesanan jika berhasil, -1 jika gagal
     */
    int createOrder(int userId, int equipmentId, int durationDays);
    
    /**
     * Mendapatkan daftar pesanan berdasarkan ID user (delegasi ke OrderInterface)
     * @param userId ID user
     * @return List pesanan
     */
    List<Order> getUserOrders(int userId);
    
    /**
     * Mengembalikan peralatan yang dipinjam (delegasi ke OrderInterface)
     * @param orderId ID pesanan
     * @return true jika berhasil, false jika gagal
     */
    boolean returnEquipment(int orderId);
    
    // ===== Delegasi ke EquipmentInterface =====
    
    /**
     * Mendapatkan daftar peralatan yang tersedia (delegasi ke EquipmentInterface)
     * @return List peralatan yang tersedia
     */
    List<Equipment> getAvailableEquipment();
    
    /**
     * Mendapatkan peralatan berdasarkan ID (delegasi ke EquipmentInterface)
     * @param equipmentId ID peralatan
     * @return Objek peralatan jika ditemukan, null jika tidak
     */
    Equipment getEquipmentById(int equipmentId);
}