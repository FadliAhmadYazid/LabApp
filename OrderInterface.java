import java.util.List;

/**
 * OrderInterface - Interface untuk mengelola pesanan peminjaman peralatan
 * Interface ini berfungsi sebagai provided interface untuk komponen yang mengimplementasikannya
 * dan required interface untuk komponen yang membutuhkannya
 */
public interface OrderInterface {
    /**
     * Membuat pesanan baru
     * @param userId ID user yang melakukan peminjaman
     * @param equipmentId ID peralatan yang dipinjam
     * @param durationDays Durasi peminjaman dalam hari
     * @return ID pesanan jika berhasil, -1 jika gagal
     */
    int createOrder(int userId, int equipmentId, int durationDays);
    
    /**
     * Mendapatkan daftar pesanan berdasarkan ID user
     * @param userId ID user
     * @return List pesanan
     */
    List<Order> getUserOrders(int userId);
    
    /**
     * Memperbarui status pesanan
     * @param orderId ID pesanan
     * @param status Status baru
     * @return true jika berhasil, false jika gagal
     */
    boolean updateOrderStatus(int orderId, String status);
    
    /**
     * Mengembalikan peralatan yang dipinjam
     * @param orderId ID pesanan
     * @return true jika berhasil, false jika gagal
     */
    boolean returnEquipment(int orderId);
    
    /**
     * Mendapatkan pesanan berdasarkan ID
     * @param orderId ID pesanan
     * @return Objek pesanan jika ditemukan, null jika tidak
     */
    Order getOrderById(int orderId);
    
    /**
     * Mendapatkan daftar peralatan yang tersedia
     * @return List peralatan yang tersedia
     */
    List<Equipment> getAvailableEquipment();
    
    /**
     * Mendapatkan peralatan berdasarkan ID
     * @param equipmentId ID peralatan
     * @return Objek peralatan jika ditemukan, null jika tidak
     */
    Equipment getEquipmentById(int equipmentId);
}