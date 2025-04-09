/**
 * Kelas Equipment - Kelas pendukung
 * Kelas ini merepresentasikan peralatan laboratorium yang dapat dipinjam.
 * Digunakan oleh EquipmentManager sebagai data dasar peralatan.
 */
public class Equipment {
    private String id;           // ID unik peralatan
    private String name;         // Nama peralatan
    private double rentalPrice;  // Harga sewa per hari
    private boolean available;   // Status ketersediaan
    
    // Constructor untuk membuat objek Equipment baru
    public Equipment(String id, String name, double rentalPrice) {
        this.id = id;
        this.name = name;
        this.rentalPrice = rentalPrice;
        this.available = true;  // Peralatan baru selalu tersedia
    }
    
    // Getter untuk ID
    public String getId() {
        return id;
    }
    
    // Getter untuk nama
    public String getName() {
        return name;
    }
    
    // Setter untuk nama
    public void setName(String name) {
        this.name = name;
    }
    
    // Getter untuk harga sewa
    public double getRentalPrice() {
        return rentalPrice;
    }
    
    // Setter untuk harga sewa
    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    
    // Getter untuk status ketersediaan
    public boolean isAvailable() {
        return available;
    }
    
    // Setter untuk status ketersediaan
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    // Override toString untuk menampilkan informasi peralatan
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Price per day: $" + rentalPrice;
    }
}