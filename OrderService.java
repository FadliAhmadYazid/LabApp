/**
 * Interface OrderService - Antarmuka (provided)
 * Interface ini mendefinisikan operasi-operasi yang dapat dilakukan pada sistem pemesanan.
 * Ini adalah interface yang PROVIDED (disediakan) oleh OrderManager.
 */
public interface OrderService {
    // Membuat pesanan baru
    String createOrder(String userId, String equipmentId, int days);
    
    // Membatalkan pesanan
    void cancelOrder(String orderId);
    
    // Mengembalikan peralatan yang dipinjam
    void returnEquipment(String orderId, boolean isDamaged);
    
    // Menampilkan semua pesanan pengguna
    void displayUserOrders(String userId);
}