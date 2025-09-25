import java.util.ArrayList;

public class ManajemenUser {
    private static ArrayList<User> daftarUser = new ArrayList<>();

    public static void registrasi(String nama, String email, String password) {
        for (User u : daftarUser) {
            if (u.getEmail().equals(email)) {
                System.out.println("Registrasi gagal! Email sudah digunakan.");
                return;
            }
        }
        Peserta pesertaBaru = new Peserta(nama, email, password); 
        daftarUser.add(pesertaBaru);
        System.out.println("Registrasi berhasil: " + nama);
    }

    public static User login(String email, String password) {
        for (User u : daftarUser) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                System.out.println("Login berhasil! Selamat datang " + u.getNama());
                return u;
            }
        }
        System.out.println("Login gagal! Email atau password salah.");
        return null;
    }
    
    public static void tambahInstruktur(String nama, String email, String password, String bidang) {
        Instruktur instruktur = new Instruktur(nama, email, password, bidang);
        daftarUser.add(instruktur);
    }
}
