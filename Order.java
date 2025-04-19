import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Order - Class model untuk pesanan peminjaman peralatan
 * Class ini merepresentasikan data pesanan dalam sistem
 */
public class Order {
    private int id;
    private int userId;
    private int equipmentId;
    private Date orderDate;
    private int durationDays;
    private Date returnDate;
    private String status; // PENDING, PAID, RETURNED
    private double totalAmount;
    
    public Order() {
    }
    
    public Order(int id, int userId, int equipmentId, Date orderDate, int durationDays, String status) {
        this.id = id;
        this.userId = userId;
        this.equipmentId = equipmentId;
        this.orderDate = orderDate;
        this.durationDays = durationDays;
        this.status = status;
        
        // Menghitung tanggal pengembalian berdasarkan durasi
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(orderDate);
        cal.add(java.util.Calendar.DAY_OF_MONTH, durationDays);
        this.returnDate = cal.getTime();
    }
    
    // Getter dan Setter
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getEquipmentId() {
        return equipmentId;
    }
    
    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }
    
    public Date getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public int getDurationDays() {
        return durationDays;
    }
    
    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
        
        // Update tanggal pengembalian
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(orderDate);
        cal.add(java.util.Calendar.DAY_OF_MONTH, durationDays);
        this.returnDate = cal.getTime();
    }
    
    public Date getReturnDate() {
        return returnDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "ID Pesanan: " + id +
               ", ID Peralatan: " + equipmentId +
               ", Tanggal Pesan: " + dateFormat.format(orderDate) +
               ", Durasi: " + durationDays + " hari" +
               ", Tanggal Kembali: " + dateFormat.format(returnDate) +
               ", Status: " + status +
               ", Total: Rp" + totalAmount;
    }
}