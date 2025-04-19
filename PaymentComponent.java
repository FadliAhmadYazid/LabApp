import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PaymentComponent - Implementasi dari PaymentInterface
 * Class ini mengimplementasikan PaymentInterface (provided interface)
 * dan membutuhkan OrderInterface (required interface)
 */
public class PaymentComponent implements PaymentInterface {
    // Required interface - diinisialisasi melalui metode connect
    private OrderInterface orderInterface;
    
    // Database simulasi untuk pembayaran
    private List<Payment> payments;
    private int nextPaymentId;
    
    /**
     * Metode untuk menghubungkan komponen dengan OrderInterface
     * Metode ini mengimplementasikan koneksi required interface
     * @param orderInterface Komponen OrderInterface yang akan dikoneksikan
     */
    public void connectOrderInterface(OrderInterface orderInterface) {
        this.orderInterface = orderInterface;
    }
    
    public PaymentComponent() {
        payments = new ArrayList<>();
        nextPaymentId = 1;
    }
    
    @Override
    public int createPayment(int orderId, double amount, String paymentMethod) {
        // Required interface harus terkoneksi sebelum digunakan
        if (orderInterface == null) {
            System.out.println("Error: OrderInterface belum terkoneksi!");
            return -1;
        }
        
        // Cek apakah pesanan ada
        Order order = orderInterface.getOrderById(orderId);
        
        if (order == null) {
            System.out.println("Pesanan tidak ditemukan!");
            return -1;
        }
        
        // Cek apakah pesanan sudah dibayar
        if (!"PENDING".equals(order.getStatus())) {
            System.out.println("Pesanan ini tidak memerlukan pembayaran!");
            return -1;
        }
        
        // Buat pembayaran baru
        Payment newPayment = new Payment(nextPaymentId, orderId, amount, new Date(), paymentMethod, "COMPLETED");
        
        // Simpan pembayaran
        payments.add(newPayment);
        
        // Update status pesanan
        orderInterface.updateOrderStatus(orderId, "PAID");
        
        int paymentId = nextPaymentId;
        nextPaymentId++;
        
        return paymentId;
    }
    
    @Override
    public List<Payment> getUserPayments(int userId) {
        // Required interface harus terkoneksi sebelum digunakan
        if (orderInterface == null) {
            System.out.println("Error: OrderInterface belum terkoneksi!");
            return new ArrayList<>();
        }
        
        List<Payment> userPayments = new ArrayList<>();
        List<Order> userOrders = orderInterface.getUserOrders(userId);
        
        for (Order order : userOrders) {
            Payment payment = getPaymentByOrderId(order.getId());
            if (payment != null) {
                userPayments.add(payment);
            }
        }
        
        return userPayments;
    }
    
    @Override
    public boolean updatePaymentStatus(int paymentId, String status) {
        Payment payment = getPaymentById(paymentId);
        
        if (payment != null) {
            payment.setStatus(status);
            return true;
        }
        
        return false; // Tidak ditemukan
    }
    
    @Override
    public double calculatePaymentAmount(int orderId) {
        // Required interface harus terkoneksi sebelum digunakan
        if (orderInterface == null) {
            System.out.println("Error: OrderInterface belum terkoneksi!");
            return 0;
        }
        
        Order order = orderInterface.getOrderById(orderId);
        
        if (order == null) {
            return 0;
        }
        
        return order.getTotalAmount();
    }
    
    @Override
    public Payment getPaymentByOrderId(int orderId) {
        for (Payment payment : payments) {
            if (payment.getOrderId() == orderId) {
                return payment;
            }
        }
        
        return null; // Tidak ditemukan
    }
    
    /**
     * Mendapatkan pembayaran berdasarkan ID
     * @param paymentId ID pembayaran
     * @return Objek pembayaran jika ditemukan, null jika tidak
     */
    private Payment getPaymentById(int paymentId) {
        for (Payment payment : payments) {
            if (payment.getId() == paymentId) {
                return payment;
            }
        }
        
        return null; // Tidak ditemukan
    }
    
    // ===== Delegasi ke OrderInterface =====
    
    @Override
    public int createOrder(int userId, int equipmentId, int durationDays) {
        // Required interface harus terkoneksi sebelum digunakan
        if (orderInterface == null) {
            System.out.println("Error: OrderInterface belum terkoneksi!");
            return -1;
        }
        
        return orderInterface.createOrder(userId, equipmentId, durationDays);
    }
    
    @Override
    public List<Order> getUserOrders(int userId) {
        // Required interface harus terkoneksi sebelum digunakan
        if (orderInterface == null) {
            System.out.println("Error: OrderInterface belum terkoneksi!");
            return new ArrayList<>();
        }
        
        return orderInterface.getUserOrders(userId);
    }
    
    @Override
    public boolean returnEquipment(int orderId) {
        // Required interface harus terkoneksi sebelum digunakan
        if (orderInterface == null) {
            System.out.println("Error: OrderInterface belum terkoneksi!");
            return false;
        }
        
        return orderInterface.returnEquipment(orderId);
    }
    
    // ===== Delegasi ke EquipmentInterface (melalui OrderInterface) =====
    
    @Override
    public List<Equipment> getAvailableEquipment() {
        // Required interface harus terkoneksi sebelum digunakan
        if (orderInterface == null) {
            System.out.println("Error: OrderInterface belum terkoneksi!");
            return new ArrayList<>();
        }
        
        // Delegasikan ke OrderInterface yang terhubung ke EquipmentInterface
        return orderInterface.getAvailableEquipment();
    }
    
    @Override
    public Equipment getEquipmentById(int equipmentId) {
        // Required interface harus terkoneksi sebelum digunakan
        if (orderInterface == null) {
            System.out.println("Error: OrderInterface belum terkoneksi!");
            return null;
        }
        
        // Delegasikan ke OrderInterface yang terhubung ke EquipmentInterface
        return orderInterface.getEquipmentById(equipmentId);
    }
}