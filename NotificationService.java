/**
 * Kelas NotificationService - Implementasi dari NotificationInterface (provided)
 * Kelas ini mengelola pengiriman notifikasi dan menyediakan implementasi
 * untuk semua metode yang didefinisikan dalam interface NotificationInterface.
 */
public class NotificationService implements NotificationInterface {
    
    // Implementasi metode dari interface NotificationInterface
    @Override
    public void sendOrderConfirmation(String userId, String orderId) {
        // Dalam sistem nyata, ini akan mengirim email/SMS/notifikasi ke pengguna
        System.out.println("NOTIFICATION: Order confirmation sent to user " + userId + " for order " + orderId);
    }
    
    @Override
    public void sendPaymentReminder(String userId, String orderId) {
        // Dalam sistem nyata, ini akan mengirim email/SMS/notifikasi ke pengguna
        System.out.println("NOTIFICATION: Payment reminder sent to user " + userId + " for order " + orderId);
    }
    
    @Override
    public void sendCancellationNotice(String userId, String orderId) {
        // Dalam sistem nyata, ini akan mengirim email/SMS/notifikasi ke pengguna
        System.out.println("NOTIFICATION: Cancellation notice sent to user " + userId + " for order " + orderId);
    }
    
    @Override
    public void sendReturnReceipt(String userId, String orderId, boolean additionalCharges) {
        // Dalam sistem nyata, ini akan mengirim email/SMS/notifikasi ke pengguna
        System.out.println("NOTIFICATION: Return receipt sent to user " + userId + " for order " + orderId 
                          + (additionalCharges ? " with additional charges" : ""));
    }
}