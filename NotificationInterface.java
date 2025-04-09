/**
 * Interface NotificationInterface - Antarmuka (provided)
 * Interface ini mendefinisikan operasi-operasi yang dapat dilakukan untuk mengirim notifikasi.
 * Ini adalah interface yang PROVIDED (disediakan) oleh NotificationService.
 */
public interface NotificationInterface {
    // Mengirim konfirmasi pesanan
    void sendOrderConfirmation(String userId, String orderId);
    
    // Mengirim pengingat pembayaran
    void sendPaymentReminder(String userId, String orderId);
    
    // Mengirim pemberitahuan pembatalan
    void sendCancellationNotice(String userId, String orderId);
    
    // Mengirim tanda terima pengembalian
    void sendReturnReceipt(String userId, String orderId, boolean additionalCharges);
}