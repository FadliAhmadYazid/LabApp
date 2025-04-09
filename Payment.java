import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas Payment - Kelas pendukung
 * Kelas ini merepresentasikan pembayaran untuk pesanan.
 * Digunakan oleh PaymentProcessor untuk menyimpan informasi pembayaran.
 */
public class Payment {
    private String orderId;              // ID pesanan yang dibayar
    private double amount;               // Jumlah pembayaran
    private LocalDate paymentDate;       // Tanggal pembayaran
    private List<AdditionalCharge> additionalCharges; // Daftar biaya tambahan
    
    // Constructor
    public Payment(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
        this.paymentDate = LocalDate.now();
        this.additionalCharges = new ArrayList<>();
    }
    
    // Getter untuk orderId
    public String getOrderId() {
        return orderId;
    }
    
    // Getter untuk amount
    public double getAmount() {
        return amount;
    }
    
    // Getter untuk paymentDate
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    
    // Menambahkan biaya tambahan
    public void addCharge(double amount, String reason) {
        additionalCharges.add(new AdditionalCharge(amount, reason));
    }
    
    // Menghitung total semua biaya (biaya dasar + biaya tambahan)
    public double getTotalCharges() {
        double total = amount;
        for (AdditionalCharge charge : additionalCharges) {
            total += charge.getAmount();
        }
        return total;
    }
    
    // Inner class untuk merepresentasikan biaya tambahan
    private class AdditionalCharge {
        private double amount;       // Jumlah biaya tambahan
        private String reason;       // Alasan biaya tambahan
        private LocalDate chargeDate; // Tanggal biaya tambahan
        
        public AdditionalCharge(double amount, String reason) {
            this.amount = amount;
            this.reason = reason;
            this.chargeDate = LocalDate.now();
        }
        
        public double getAmount() {
            return amount;
        }
        
        public String getReason() {
            return reason;
        }
    }
}