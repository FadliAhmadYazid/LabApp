import java.util.List;

/**
 * EquipmentInterface - Interface untuk mengelola peralatan lab
 * Interface ini berfungsi sebagai provided interface untuk komponen yang mengimplementasikannya
 * dan required interface untuk komponen yang membutuhkannya
 */
public interface EquipmentInterface {
    /**
     * Mendapatkan daftar semua peralatan
     * @return List peralatan
     */
    List<Equipment> getAllEquipment();
    
    /**
     * Mendapatkan daftar peralatan yang tersedia untuk dipinjam
     * @return List peralatan yang tersedia
     */
    List<Equipment> getAvailableEquipment();
    
    /**
     * Menambahkan peralatan baru
     * @param equipment Peralatan yang akan ditambahkan
     * @return true jika berhasil, false jika gagal
     */
    boolean addEquipment(Equipment equipment);
    
    /**
     * Memperbarui informasi peralatan
     * @param equipmentId ID peralatan
     * @param equipment Data peralatan yang baru
     * @return true jika berhasil, false jika gagal
     */
    boolean updateEquipment(int equipmentId, Equipment equipment);
    
    /**
     * Menghapus peralatan
     * @param equipmentId ID peralatan yang akan dihapus
     * @return true jika berhasil, false jika gagal
     */
    boolean deleteEquipment(int equipmentId);
    
    /**
     * Mendapatkan peralatan berdasarkan ID
     * @param equipmentId ID peralatan
     * @return Objek peralatan jika ditemukan, null jika tidak
     */
    Equipment getEquipmentById(int equipmentId);
    
    /**
     * Mengubah status ketersediaan peralatan
     * @param equipmentId ID peralatan
     * @param isAvailable Status ketersediaan
     * @return true jika berhasil, false jika gagal
     */
    boolean updateEquipmentAvailability(int equipmentId, boolean isAvailable);
}