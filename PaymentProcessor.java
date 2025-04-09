import java.util.HashMap;
import java.util.Map;

/**
 * Kelas PaymentProcessor - Implementasi dari PaymentService (provided) dan membutuhkan (required) OrderManager
 * Kelas ini mengelola pembayaran untuk pesanan dan menyediakan implementasi
 * untuk semua metode yang didefinisikan dalam interface PaymentService.
 */
public class PaymentProcessor implements PaymentService {
    private Map<String, Payment> paymentDatabase;
    
    // Required interface: OrderManager
    private OrderManager orderManager; // Required - dibutuhkan untuk mengakses pesanan
    
    // Constructor
    public PaymentProcessor() {
        this.paymentDatabase = new HashMap<>();
    }
    
    // Setter untuk mengatasi circular dependency
    // Ini digunakan untuk mengatasi ketergantungan melingkar antara OrderManager dan PaymentProcessor
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }
    
    // Implementasi metode dari interface PaymentService
    @Override
    public void processPayment(String orderId, double amount) {
        // Dalam sistem nyata, ini akan terhubung ke gateway pembayaran
        Payment payment = new Payment(orderId, amount);
        paymentDatabase.put(orderId, payment);
        
        // Versi sederhana - dalam sistem nyata, kita harus mencari pesanan dan memperbarui statusnya
        System.out.println("Payment processed for order: " + orderId);
        System.out.println("Please check your email for detailed payment information.");
    }
    
    @Override
    public boolean verifyPayment(String orderId) {
        return paymentDatabase.containsKey(orderId);
    }
    
    @Override
    public void issueRefund(String orderId) {
        if (paymentDatabase.containsKey(orderId)) {
            Payment payment = paymentDatabase.get(orderId);
            System.out.println("Refund issued for order: " + orderId);
            System.out.println("Amount: $" + payment.getAmount());
            paymentDatabase.remove(orderId);
        } else {
            System.out.println("No payment found for order: " + orderId);
        }
    }
    
    @Override
    public void chargeAdditionalFee(String orderId, double amount, String reason) {
        if (paymentDatabase.containsKey(orderId)) {
            Payment payment = paymentDatabase.get(orderId);
            payment.addCharge(amount, reason);
            System.out.println("Additional charge added to order: " + orderId);
            System.out.println("Amount: $" + amount);
            System.out.println("Reason: " + reason);
        } else {
            System.out.println("No payment found for order: " + orderId);
        }
    }
}
