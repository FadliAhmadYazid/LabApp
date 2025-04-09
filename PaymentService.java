/**
 * Interface PaymentService - Antarmuka (provided)
 * Interface ini mendefinisikan operasi-operasi yang dapat dilakukan pada sistem pembayaran.
 * Ini adalah interface yang PROVIDED (disediakan) oleh PaymentProcessor.
 */
public interface PaymentService {
    // Memproses pembayaran untuk pesanan
    void processPayment(String orderId, double amount);
    
    // Memverifikasi status pembayaran pesanan
    boolean verifyPayment(String orderId);
    
    // Mengeluarkan pengembalian dana
    void issueRefund(String orderId);
    
    // Menambahkan biaya tambahan (misalnya untuk kerusakan)
    void chargeAdditionalFee(String orderId, double amount, String reason);
}