import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Payment - Class model untuk pembayaran
 * Class ini merepresentasikan data pembayaran dalam sistem
 */
public class Payment {
    private int id;
    private int orderId;
    private double amount;
    private Date paymentDate;
    private String paymentMethod;
    private String status; // PENDING, COMPLETED
    
    public Payment() {
    }
    
    public Payment(int id, int orderId, double amount, Date paymentDate, String paymentMethod, String status) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }
    
    // Getter dan Setter
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public Date getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "ID Pembayaran: " + id +
               ", ID Pesanan: " + orderId +
               ", Jumlah: Rp" + amount +
               ", Tanggal: " + dateFormat.format(paymentDate) +
               ", Metode: " + paymentMethod +
               ", Status: " + status;
    }
}