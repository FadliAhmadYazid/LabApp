import java.util.HashMap;
import java.util.Map;

/**
 * Kelas EquipmentManager - Implementasi dari EquipmentCatalog (provided)
 * Kelas ini mengelola katalog peralatan laboratorium dan menyediakan implementasi
 * untuk semua metode yang didefinisikan dalam interface EquipmentCatalog.
 */
public class EquipmentManager implements EquipmentCatalog {
    // Database peralatan yang tersimpan dalam Map
    private Map<String, Equipment> equipmentDatabase;
    
    // Constructor
    public EquipmentManager() {
        equipmentDatabase = new HashMap<>();
        // Menambahkan beberapa peralatan awal
        equipmentDatabase.put("E001", new Equipment("E001", "Microscope", 50.0));
        equipmentDatabase.put("E002", new Equipment("E002", "Centrifuge", 75.0));
        equipmentDatabase.put("E003", new Equipment("E003", "Spectrophotometer", 100.0));
    }
    
    // Implementasi metode dari interface EquipmentCatalog
    @Override
    public void displayAvailableEquipment() {
        System.out.println("===== AVAILABLE EQUIPMENT =====");
        for (Equipment equipment : equipmentDatabase.values()) {
            if (equipment.isAvailable()) {
                System.out.println(equipment);
            }
        }
        System.out.println("==============================");
    }
    
    @Override
    public boolean checkAvailability(String equipmentId) {
        if (equipmentDatabase.containsKey(equipmentId)) {
            return equipmentDatabase.get(equipmentId).isAvailable();
        }
        return false;
    }
    
    @Override
    public String getEquipmentDetails(String equipmentId) {
        if (equipmentDatabase.containsKey(equipmentId)) {
            return equipmentDatabase.get(equipmentId).toString();
        }
        return "Equipment not found";
    }
    
    @Override
    public void addNewEquipment(String equipmentId, String name, double price) {
        equipmentDatabase.put(equipmentId, new Equipment(equipmentId, name, price));
        System.out.println("New equipment added: " + name);
    }
    
    @Override
    public void updateEquipmentInfo(String equipmentId, String name, double price) {
        if (equipmentDatabase.containsKey(equipmentId)) {
            Equipment equipment = equipmentDatabase.get(equipmentId);
            equipment.setName(name);
            equipment.setRentalPrice(price);
            System.out.println("Equipment updated: " + equipmentId);
        } else {
            System.out.println("Equipment not found");
        }
    }
    
    // Metode tambahan untuk penggunaan internal
    // Metode ini digunakan oleh OrderManager untuk mengubah status ketersediaan peralatan
    public void setEquipmentAvailability(String equipmentId, boolean available) {
        if (equipmentDatabase.containsKey(equipmentId)) {
            equipmentDatabase.get(equipmentId).setAvailable(available);
        }
    }
}