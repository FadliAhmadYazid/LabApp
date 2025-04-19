/**
 * UserInterface - Interface untuk komponen user interface
 * Interface ini berfungsi sebagai provided interface untuk komponen yang mengimplementasikannya
 */
public interface UserInterface {
    /**
     * Menampilkan menu ke pengguna
     */
    void displayMenu();
    
    /**
     * Memproses input dari pengguna
     * @param choice Pilihan pengguna
     */
    void processUserInput(int choice);
}