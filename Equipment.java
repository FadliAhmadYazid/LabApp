/**
 * Equipment - Class model untuk peralatan lab
 * Class ini merepresentasikan data peralatan lab dalam sistem
 */
public class Equipment {
    private int id;
    private String name;
    private String description;
    private double rentPrice; // Harga sewa per hari
    private boolean isAvailable;
    
    public Equipment() {
    }
    
    public Equipment(int id, String name, String description, double rentPrice, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rentPrice = rentPrice;
        this.isAvailable = isAvailable;
    }
    
    // Getter dan Setter
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getRentPrice() {
        return rentPrice;
    }
    
    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    @Override
    public String toString() {
        return "ID: " + id +
               ", Nama: " + name +
               ", Deskripsi: " + description +
               ", Harga Sewa: Rp" + rentPrice + "/hari" +
               ", Status: " + (isAvailable ? "Tersedia" : "Tidak Tersedia");
    }
}