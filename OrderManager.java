import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Kelas OrderManager - Implementasi dari OrderService (provided) dan membutuhkan (required) beberapa interface
 * Kelas ini mengelola pesanan peminjaman peralatan laboratorium dan menyediakan implementasi
 * untuk semua metode yang didefinisikan dalam interface OrderService.
 */
public class OrderManager implements OrderService {
    private Map<String, Order> orderDatabase;
    
    // Required interfaces: EquipmentManager, PaymentService, NotificationInterface
    private EquipmentManager equipmentManager; // Required - dibutuhkan untuk mengecek ketersediaan peralatan
    private PaymentService paymentService;     // Required - dibutuhkan untuk memproses pembayaran
    private NotificationInterface notificationService; // Required - dibutuhkan untuk mengirim notifikasi
    
    // Constructor dengan dependency injection
    public OrderManager(EquipmentManager equipmentManager, PaymentService paymentService, 
                       NotificationInterface notificationService) {
        this.orderDatabase = new HashMap<>();
        // Inject dependencies
        this.equipmentManager = equipmentManager;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }
    
    // Implementasi metode dari interface OrderService
    @Override
    public String createOrder(String userId, String equipmentId, int days) {
        // Memeriksa ketersediaan peralatan menggunakan EquipmentManager (required)
        if (!equipmentManager.checkAvailability(equipmentId)) {
            System.out.println("Equipment is not available");
            return null;
        }
        
        // Menghasilkan ID pesanan
        String orderId = "ORD" + new Random().nextInt(10000);
        
        // Menghitung total harga
        double totalPrice = days * Double.parseDouble(
            equipmentManager.getEquipmentDetails(equipmentId).split("\\$")[1]);
        
        // Membuat pesanan
        Order order = new Order(orderId, userId, equipmentId, days, totalPrice);
        orderDatabase.put(orderId, order);
        
        // Memperbarui status ketersediaan peralatan menggunakan EquipmentManager (required)
        equipmentManager.setEquipmentAvailability(equipmentId, false);
        
        // Mengirim notifikasi menggunakan NotificationService (required)
        notificationService.sendOrderConfirmation(userId, orderId);
        
        System.out.println("Order created: " + orderId);
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("Payment due within 7 days");
        
        return orderId;
    }
    
    @Override
    public void cancelOrder(String orderId) {
        if (orderDatabase.containsKey(orderId)) {
            Order order = orderDatabase.get(orderId);
            
            // Memperbarui status ketersediaan peralatan menggunakan EquipmentManager (required)
            equipmentManager.setEquipmentAvailability(order.getEquipmentId(), true);
            
            // Mengirim notifikasi menggunakan NotificationService (required)
            notificationService.sendCancellationNotice(order.getUserId(), orderId);
            
            // Menghapus pesanan
            orderDatabase.remove(orderId);
            
            System.out.println("Order cancelled: " + orderId);
        } else {
            System.out.println("Order not found");
        }
    }
    
    @Override
    public void returnEquipment(String orderId, boolean isDamaged) {
        if (orderDatabase.containsKey(orderId)) {
            Order order = orderDatabase.get(orderId);
            
            // Memperbarui status ketersediaan peralatan menggunakan EquipmentManager (required)
            equipmentManager.setEquipmentAvailability(order.getEquipmentId(), true);
            
            // Jika rusak, tambahkan biaya tambahan menggunakan PaymentService (required)
            if (isDamaged) {
                double repairFee = order.getTotalPrice() * 0.5; // 50% dari harga sewa
                paymentService.chargeAdditionalFee(orderId, repairFee, "Equipment damage repair");
                System.out.println("Additional charge for damage: $" + repairFee);
            }
            
            // Mengirim notifikasi menggunakan NotificationService (required)
            notificationService.sendReturnReceipt(order.getUserId(), orderId, isDamaged);
            
            // Menandai pesanan sebagai selesai
            order.setCompleted(true);
            
            System.out.println("Equipment returned: " + order.getEquipmentId());
        } else {
            System.out.println("Order not found");
        }
    }
    
    @Override
    public void displayUserOrders(String userId) {
        System.out.println("===== ORDERS FOR USER: " + userId + " =====");
        for (Order order : orderDatabase.values()) {
            if (order.getUserId().equals(userId)) {
                System.out.println(order);
            }
        }
        System.out.println("==============================");
    }
}