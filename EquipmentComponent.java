import java.util.ArrayList;
import java.util.List;

/**
 * EquipmentComponent - Implementasi dari EquipmentInterface
 * Class ini mengimplementasikan EquipmentInterface (provided interface)
 */
public class EquipmentComponent implements EquipmentInterface {
    // Database simulasi untuk peralatan
    private List<Equipment> equipments;
    
    public EquipmentComponent() {
        // Inisialisasi dengan beberapa data sampel
        equipments = new ArrayList<>();
        equipments.add(new Equipment(1, "Mikroskop Digital", "Mikroskop digital dengan pembesaran 1000x", 50000, true));
        equipments.add(new Equipment(2, "Osiloskop", "Osiloskop digital 100MHz", 75000, true));
        equipments.add(new Equipment(3, "Multimeter", "Multimeter digital untuk pengukuran listrik", 15000, true));
        equipments.add(new Equipment(4, "pH Meter", "Alat ukur pH untuk larutan kimia", 25000, true));
        equipments.add(new Equipment(5, "Spektrofotometer", "Alat analisis spektrum cahaya", 100000, true));
    }
    
    @Override
    public List<Equipment> getAllEquipment() {
        return new ArrayList<>(equipments); // Return salinan untuk menghindari modifikasi langsung
    }
    
    @Override
    public List<Equipment> getAvailableEquipment() {
        List<Equipment> availableEquipments = new ArrayList<>();
        
        for (Equipment equipment : equipments) {
            if (equipment.isAvailable()) {
                availableEquipments.add(equipment);
            }
        }
        
        return availableEquipments;
    }
    
    @Override
    public boolean addEquipment(Equipment equipment) {
        try {
            equipments.add(equipment);
            return true;
        } catch (Exception e) {
            System.err.println("Error adding equipment: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean updateEquipment(int equipmentId, Equipment updatedEquipment) {
        for (int i = 0; i < equipments.size(); i++) {
            Equipment equipment = equipments.get(i);
            
            if (equipment.getId() == equipmentId) {
                equipments.set(i, updatedEquipment);
                return true;
            }
        }
        
        return false; // Tidak ditemukan
    }
    
    @Override
    public boolean deleteEquipment(int equipmentId) {
        for (int i = 0; i < equipments.size(); i++) {
            Equipment equipment = equipments.get(i);
            
            if (equipment.getId() == equipmentId) {
                equipments.remove(i);
                return true;
            }
        }
        
        return false; // Tidak ditemukan
    }
    
    @Override
    public Equipment getEquipmentById(int equipmentId) {
        for (Equipment equipment : equipments) {
            if (equipment.getId() == equipmentId) {
                return equipment;
            }
        }
        
        return null; // Tidak ditemukan
    }
    
    @Override
    public boolean updateEquipmentAvailability(int equipmentId, boolean isAvailable) {
        Equipment equipment = getEquipmentById(equipmentId);
        
        if (equipment != null) {
            equipment.setAvailable(isAvailable);
            return true;
        }
        
        return false; // Tidak ditemukan
    }
}