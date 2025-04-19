import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * OrderComponent - Implementasi dari OrderInterface dan AdminInterface
 * Class ini mengimplementasikan OrderInterface dan AdminInterface (provided interface)
 * dan membutuhkan EquipmentInterface (required interface)
 */
public class OrderComponent implements OrderInterface, AdminInterface {
    // Required interface - diinisialisasi melalui metode connect
    private EquipmentInterface equipmentInterface;
    
    // Database simulasi untuk pesanan
    private List<Order> orders;
    private int nextOrderId;
    
    /**
     * Metode untuk menghubungkan komponen dengan EquipmentInterface
     * Metode ini mengimplementasikan koneksi required interface
     * @param equipmentInterface Komponen EquipmentInterface yang akan dikoneksikan
     */
    public void connectEquipmentInterface(EquipmentInterface equipmentInterface) {
        this.equipmentInterface = equipmentInterface;
    }
    
    public OrderComponent() {
        orders = new ArrayList<>();
        nextOrderId = 1;
    }
    
    // ===== Implementasi OrderInterface =====
    
    @Override
    public int createOrder(int userId, int equipmentId, int durationDays) {
        // Required interface harus terkoneksi sebelum digunakan
        if (equipmentInterface == null) {
            System.out.println("Error: EquipmentInterface belum terkoneksi!");
            return -1;
        }
        
        // Cek ketersediaan peralatan
        Equipment equipment = equipmentInterface.getEquipmentById(equipmentId);
        
        if (equipment == null) {
            System.out.println("Peralatan tidak ditemukan!");
            return -1;
        }
        
        if (!equipment.isAvailable()) {
            System.out.println("Peralatan tidak tersedia untuk dipinjam!");
            return -1;
        }
        
        // Buat pesanan baru
        Order newOrder = new Order(nextOrderId, userId, equipmentId, new Date(), durationDays, "PENDING");
        double totalAmount = durationDays * equipment.getRentPrice();
        newOrder.setTotalAmount(totalAmount);
        
        // Simpan pesanan
        orders.add(newOrder);
        
        // Update ketersediaan peralatan
        equipmentInterface.updateEquipmentAvailability(equipmentId, false);
        
        int orderId = nextOrderId;
        nextOrderId++;
        
        return orderId;
    }
    
    @Override
    public List<Order> getUserOrders(int userId) {
        List<Order> userOrders = new ArrayList<>();
        
        for (Order order : orders) {
            if (order.getUserId() == userId) {
                userOrders.add(order);
            }
        }
        
        return userOrders;
    }
    
    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        Order order = getOrderById(orderId);
        
        if (order != null) {
            order.setStatus(status);
            return true;
        }
        
        return false; // Tidak ditemukan
    }
    
    @Override
    public boolean returnEquipment(int orderId) {
        // Required interface harus terkoneksi sebelum digunakan
        if (equipmentInterface == null) {
            System.out.println("Error: EquipmentInterface belum terkoneksi!");
            return false;
        }
        
        Order order = getOrderById(orderId);
        
        if (order == null) {
            System.out.println("Pesanan tidak ditemukan!");
            return false;
        }
        
        if (!"PAID".equals(order.getStatus())) {
            System.out.println("Pesanan tidak dalam status yang benar untuk pengembalian!");
            return false;
        }
        
        // Update status pesanan
        order.setStatus("RETURNED");
        
        // Update ketersediaan peralatan
        int equipmentId = order.getEquipmentId();
        return equipmentInterface.updateEquipmentAvailability(equipmentId, true);
    }
    
    @Override
    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        
        return null; // Tidak ditemukan
    }
    
    @Override
    public List<Equipment> getAvailableEquipment() {
        // Required interface harus terkoneksi sebelum digunakan
        if (equipmentInterface == null) {
            System.out.println("Error: EquipmentInterface belum terkoneksi!");
            return new ArrayList<>();
        }
        
        return equipmentInterface.getAvailableEquipment();
    }
    
    // ===== Implementasi AdminInterface =====
    
    @Override
    public List<Equipment> getAllEquipment() {
        // Required interface harus terkoneksi sebelum digunakan
        if (equipmentInterface == null) {
            System.out.println("Error: EquipmentInterface belum terkoneksi!");
            return new ArrayList<>();
        }
        
        return equipmentInterface.getAllEquipment();
    }
    
    @Override
    public Equipment getEquipmentById(int equipmentId) {
        // Required interface harus terkoneksi sebelum digunakan
        if (equipmentInterface == null) {
            System.out.println("Error: EquipmentInterface belum terkoneksi!");
            return null;
        }
        
        return equipmentInterface.getEquipmentById(equipmentId);
    }
    
    @Override
    public boolean addEquipment(Equipment equipment) {
        // Required interface harus terkoneksi sebelum digunakan
        if (equipmentInterface == null) {
            System.out.println("Error: EquipmentInterface belum terkoneksi!");
            return false;
        }
        
        return equipmentInterface.addEquipment(equipment);
    }
    
    @Override
    public boolean updateEquipment(int equipmentId, Equipment equipment) {
        // Required interface harus terkoneksi sebelum digunakan
        if (equipmentInterface == null) {
            System.out.println("Error: EquipmentInterface belum terkoneksi!");
            return false;
        }
        
        return equipmentInterface.updateEquipment(equipmentId, equipment);
    }
    
    @Override
    public boolean deleteEquipment(int equipmentId) {
        // Required interface harus terkoneksi sebelum digunakan
        if (equipmentInterface == null) {
            System.out.println("Error: EquipmentInterface belum terkoneksi!");
            return false;
        }
        
        return equipmentInterface.deleteEquipment(equipmentId);
    }
}