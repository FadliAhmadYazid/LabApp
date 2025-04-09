/**
 * Interface EquipmentCatalog - Antarmuka (provided)
 * Interface ini mendefinisikan operasi-operasi yang dapat dilakukan pada katalog peralatan laboratorium.
 * Ini adalah interface yang PROVIDED (disediakan) oleh EquipmentManager.
 */
public interface EquipmentCatalog {
    // Menampilkan semua peralatan yang tersedia
    void displayAvailableEquipment();
    
    // Memeriksa ketersediaan peralatan berdasarkan ID
    boolean checkAvailability(String equipmentId);
    
    // Mendapatkan informasi detail peralatan berdasarkan ID
    String getEquipmentDetails(String equipmentId);
    
    // Menambahkan peralatan baru ke katalog
    void addNewEquipment(String equipmentId, String name, double price);
    
    // Memperbarui informasi peralatan yang sudah ada
    void updateEquipmentInfo(String equipmentId, String name, double price);
}