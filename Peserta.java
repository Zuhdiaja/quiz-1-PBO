import java.util.ArrayList;

public class Peserta extends User {
    private ArrayList<Kursus> kursusDiikuti = new ArrayList<>();
    private ArrayList<Kursus> kursusSudahDibayar = new ArrayList<>();

    public Peserta(String nama, String email, String password) {
        super(nama, email, password);
    }

    public void daftarKursus(Kursus k) {
        if (!kursusDiikuti.contains(k)) {
            kursusDiikuti.add(k);
            System.out.println(getNama() + " berhasil daftar kursus: " + k.getJudul());
        } else {
            System.out.println("Sudah terdaftar di kursus ini.");
        }
    }

    public Pembayaran bayarKursus(Kursus k, double jumlah) {
        if (!kursusDiikuti.contains(k)) {
            System.out.println("Anda belum daftar kursus " + k.getJudul());
            return null;
        }
        if (kursusSudahDibayar.contains(k)) {
            System.out.println("Kursus ini sudah dibayar.");
            return null;
        }

        Pembayaran p = new Pembayaran(this, k, jumlah);
        if (p.isStatus()) {
            kursusSudahDibayar.add(k);
            System.out.println("Pembayaran berhasil untuk kursus: " + k.getJudul());
        } else {
            System.out.println("Pembayaran gagal (kurang bayar).");
        }
        return p;
    }

    
    public void lihatKursusDiikuti() {
        if (kursusDiikuti.isEmpty()) {
            System.out.println("Belum ada kursus yang diikuti.");
        } else {
            System.out.println("=== Kursus yang diikuti ===");
            for (int i = 0; i < kursusDiikuti.size(); i++) {
                Kursus k = kursusDiikuti.get(i);
                System.out.println((i + 1) + ". " + k.getJudul() + " (Rp" + k.getHarga() + ")");
            }
        }
    }

    
    public void lihatKonten(Konten konten) {
 
    if (kursusSudahDibayar.isEmpty()) {
        System.out.println("Belum ada kursus yang dibayar, tidak bisa akses konten.");
        return;
    }

    System.out.println("=== Konten yang diakses: " + konten.getJudul() + " ===");
    konten.tampilkanKonten();
}
}
