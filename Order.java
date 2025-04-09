import java.time.LocalDate;

/**
 * Kelas Order - Kelas pendukung
 * Kelas ini merepresentasikan pesanan peminjaman peralatan laboratorium.
 * Digunakan oleh OrderManager untuk menyimpan informasi pesanan.
 */
public class Order {
    private String orderId;          // ID unik pesanan
    private String userId;           // ID pengguna yang memesan
    private String equipmentId;      // ID peralatan yang dipesan
    private int rentalDays;          // Jumlah hari peminjaman
    private double totalPrice;       // Total harga peminjaman
    private LocalDate orderDate;     // Tanggal pesanan dibuat
    private LocalDate paymentDueDate; // Tanggal batas pembayaran
    private boolean isPaid;          // Status pembayaran
    private boolean isCompleted;     // Status penyelesaian pesanan
    
    // Constructor
    public Order(String orderId, String userId, String equipmentId, int rentalDays, double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.equipmentId = equipmentId;
        this.rentalDays = rentalDays;
        this.totalPrice = totalPrice;
        this.orderDate = LocalDate.now();
        this.paymentDueDate = orderDate.plusDays(7); // 7 hari batas pembayaran
        this.isPaid = false;
        this.isCompleted = false;
    }
    
    // Getter untuk orderId
    public String getOrderId() {
        return orderId;
    }
    
    // Getter untuk userId
    public String getUserId() {
        return userId;
    }
    
    // Getter untuk equipmentId
    public String getEquipmentId() {
        return equipmentId;
    }
    
    // Getter untuk totalPrice
    public double getTotalPrice() {
        return totalPrice;
    }
    
    // Getter untuk paymentDueDate
    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }
    
    // Getter untuk status pembayaran
    public boolean isPaid() {
        return isPaid;
    }
    
    // Setter untuk status pembayaran
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
    
    // Getter untuk status penyelesaian
    public boolean isCompleted() {
        return isCompleted;
    }
    
    // Setter untuk status penyelesaian
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    
    // Override toString untuk menampilkan informasi pesanan
    @Override
    public String toString() {
        return "Order ID: " + orderId + 
               " | Equipment: " + equipmentId + 
               " | Days: " + rentalDays + 
               " | Total: $" + totalPrice +
               " | Due date: " + paymentDueDate +
               " | Status: " + (isPaid ? "Paid" : "Unpaid");
    }
}