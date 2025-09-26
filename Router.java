import java.util.ArrayList;
import java.util.Scanner;

public class Router {

    private Scanner scanner;
    private User currentUser;
    private ArrayList<Kursus> daftarSemuaKursus;
    private boolean isAdminMode = false;

    public Router() {
        this.scanner = new Scanner(System.in);
        this.currentUser = null;
        this.daftarSemuaKursus = new ArrayList<>();
        setupInitialData();
    }

    public void run() {
        System.out.println("=================================================");
        System.out.println("  Selamat Datang di Learning Management System");
        System.out.println("=================================================");

        while (true) {
            if (isAdminMode) {
                handleAdminMenu();
            } else if (currentUser == null) {
                handleMainMenu();
            } else if (currentUser instanceof Peserta) {
                handlePesertaMenu();
            } else if (currentUser instanceof Instruktur) {
                handleInstrukturMenu();
            }
        }
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Input salah. Harap masukkan angka saja.");
            scanner.nextLine();
            System.out.print("Pilihan Anda: ");
        }
        int pilihan = scanner.nextInt(); 
        scanner.nextLine();
        return pilihan;
    }
    
    private double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Input salah. Harap masukkan angka (contoh: 250000).");
            scanner.nextLine(); // Clear the wrong input.
            System.out.print("Masukkan harga/jumlah: ");
        }
        double jumlah = scanner.nextDouble();
        scanner.nextLine(); // Consume the 'enter' key press.
        return jumlah;
    }


    private void setupInitialData() {
        // 1. Buat Instruktur terlebih dahulu
        ManajemenUser.tambahInstruktur("Dr. Budi", "budi@email.com", "rahasia123", "Pemrograman Java");
        // Kita login untuk mendapatkan objek Instrukturnya
        Instruktur instrukturBudi = (Instruktur) ManajemenUser.login("budi@email.com", "rahasia123");
        Instruktur instrukturCitra = ManajemenUser.tambahInstruktur("Dr. Citra", "citra@email.com", "rahasia456", "Web Development");
        // Reset currentUser agar kembali ke menu utama setelah setup
        currentUser = null;
        System.out.println("\n(Setup data awal selesai...)\n");


        // 2. Buat Kursus dan LANGSUNG TUGASKAN Instruktur Budi
        Kursus kursusJava = new Kursus(1, "Java Dasar", "Kursus untuk pemula", 250000, instrukturBudi);
        kursusJava.tambahKonten(new Artikel(101, "Pengenalan Java", "Java adalah bahasa..."));
        kursusJava.tambahKonten(new Video(102, "Instalasi JDK", 15));
        kursusJava.tambahKonten(new Kuis(103, "Kuis Variabel", "Tipe data untuk desimal?"));
        daftarSemuaKursus.add(kursusJava);

        Kursus kursusWeb = new Kursus(2, "Web Development", "Belajar HTML, CSS, JS", 300000, instrukturBudi);
        kursusWeb.tambahKonten(new Artikel(201, "Dasar HTML", "HTML adalah kerangka web..."));
        kursusWeb.tambahKonten(new Video(202, "Styling dengan CSS", 25));
        daftarSemuaKursus.add(kursusWeb);
    }

    // --- MENU HANDLERS ---
    private void handleMainMenu() {
        System.out.println("\n--- MENU UTAMA ---");
        System.out.println("1. Registrasi Peserta");
        System.out.println("2. Login");
        System.out.println("3. Keluar");
        System.out.print("Pilihan Anda: ");

        // We now use our new, safer method instead of a try-catch block.
        int pilihan = getIntInput();

        switch (pilihan) {
            case 1:
                registrasiUser();
                break;
            case 2:
                loginUser();
                break;
            case 3:
                System.out.println("Terima kasih telah menggunakan sistem kami!");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private void handleAdminMenu() {
        System.out.println("\n--- MENU ADMIN ---");
        System.out.println("1. Buat Kursus Baru");
        System.out.println("2. Logout");
        System.out.print("Pilihan Anda: ");
        int pilihan = getIntInput();

        switch (pilihan) {
            case 1:
                buatKursusBaru();
                break;
            case 2:
                isAdminMode = false;
                System.out.println("Anda berhasil logout dari mode Admin.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private void handlePesertaMenu() {
        Peserta peserta = (Peserta) currentUser;
        System.out.println("\n--- MENU PESERTA | Selamat Datang, " + peserta.getNama() + " ---");
        System.out.println("1. Lihat Semua Kursus");
        System.out.println("2. Daftar Kursus");
        System.out.println("3. Lihat Kursus Saya");
        System.out.println("4. Akses Konten Kursus");
        System.out.println("5. Logout");
        System.out.print("Pilihan Anda: ");

        int pilihan = getIntInput();

        switch (pilihan) {
            case 1:
                lihatSemuaKursus();
                break;
            case 2:
                daftarKursus(peserta);
                break;
            case 3:
                peserta.lihatKursusDiikuti();
                break;
            case 4:
                aksesKonten(peserta);
                break;
            case 5:
                currentUser = null;
                System.out.println("Anda berhasil logout.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

     private void handleInstrukturMenu() {
        Instruktur instruktur = (Instruktur) currentUser;
        System.out.println("\n--- MENU INSTRUKTUR | Selamat Datang, " + instruktur.getNama() + " ---");
        System.out.println("1. Tambah Konten ke Kursus");
        System.out.println("2. Lihat Kursus Saya");
        System.out.println("3. Logout");
        System.out.print("Pilihan Anda: ");
        
        int pilihan = getIntInput();

        switch (pilihan) {
            case 1:
                tambahKontenUntukInstruktur(instruktur);
                break;
            case 2:
                lihatKursusInstruktur(instruktur);
                break;
            case 3:
                currentUser = null;
                System.out.println("Anda berhasil logout.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    // --- METODE BARU UNTUK INSTRUKTUR ---

    private void lihatKursusInstruktur(Instruktur instruktur) {
        System.out.println("\n--- Daftar Kursus yang Anda Ajar ---");
        boolean adaKursus = false;
        for (Kursus k : daftarSemuaKursus) {
            // Kita cek apakah instruktur kursus ini adalah instruktur yang sedang login
            if (k.getInstruktur() != null && k.getInstruktur().getEmail().equals(instruktur.getEmail())) {
                System.out.println("- " + k.getJudul());
                adaKursus = true;
            }
        }

        if (!adaKursus) {
            System.out.println("Anda belum ditugaskan untuk mengajar kursus apapun.");
        }
    }

    private void tambahKontenUntukInstruktur(Instruktur instruktur) {
        System.out.println("\n--- Pilih Kursus untuk Ditambahkan Konten ---");
        ArrayList<Kursus> kursusDiajar = new ArrayList<>();
        
        // Cari dan tampilkan hanya kursus yang diajar oleh instruktur ini
        for (Kursus k : daftarSemuaKursus) {
            if (k.getInstruktur() != null && k.getInstruktur().getEmail().equals(instruktur.getEmail())) {
                kursusDiajar.add(k);
            }
        }
        
        if (kursusDiajar.isEmpty()) {
            System.out.println("Anda belum ditugaskan untuk mengajar kursus apapun.");
            return;
        }

        for (int i = 0; i < kursusDiajar.size(); i++) {
            System.out.println((i + 1) + ". " + kursusDiajar.get(i).getJudul());
        }

        System.out.print("Pilih nomor kursus: ");
        int pilihanKursus = getIntInput();

        if (pilihanKursus > 0 && pilihanKursus <= kursusDiajar.size()) {
            Kursus kursusDipilih = kursusDiajar.get(pilihanKursus - 1);
            
            System.out.println("\n--- Pilih Tipe Konten ---");
            System.out.println("1. Artikel");
            System.out.println("2. Video");
            System.out.println("3. Kuis");
            System.out.print("Pilihan tipe konten: ");
            int pilihanTipe = getIntInput();

            System.out.print("Masukkan Judul Konten: ");
            String judulKonten = scanner.nextLine();
            int idKontenBaru = (int) (Math.random() * 1000); // ID acak sederhana
            
            Konten kontenBaru = null;
            if (pilihanTipe == 1) {
                System.out.print("Masukkan Teks Artikel: ");
                String teks = scanner.nextLine();
                kontenBaru = new Artikel(idKontenBaru, judulKonten, teks);
            } else if (pilihanTipe == 2) {
                System.out.print("Masukkan Durasi Video (menit): ");
                int durasi = getIntInput();
                kontenBaru = new Video(idKontenBaru, judulKonten, durasi);
            } else if (pilihanTipe == 3) {
                System.out.print("Masukkan Soal Kuis: ");
                String soal = scanner.nextLine();
                kontenBaru = new Kuis(idKontenBaru, judulKonten, soal);
            }

            if (kontenBaru != null) {
                kursusDipilih.tambahKonten(kontenBaru);
                System.out.println("Konten '" + kontenBaru.getJudul() + "' berhasil ditambahkan!"); 
            } else {
                System.out.println("Pilihan tipe konten tidak valid.");
            }

        } else {
            System.out.println("Pilihan kursus tidak valid.");
        }
    }

    // --- ACTION METHODS ---
    private void registrasiUser() {
        System.out.println("\n--- Registrasi Peserta Baru ---");
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Email: ");
        String email = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();
        ManajemenUser.registrasi(nama, email, password);
    }

    private void loginUser() {
        System.out.println("\n--- Login ---");
        System.out.print("Masukkan Email: ");
        String email = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();
        
        // Special login for admin
        if (email.equalsIgnoreCase("admin") && password.equals("admin")) {
            System.out.println("Login berhasil! Selamat datang Admin.");
            isAdminMode = true;
            currentUser = null; // Ensure we are not logged in as a regular user
        } else {
            currentUser = ManajemenUser.login(email, password);
            // if (currentUser != null) {
            //     System.out.println("Login berhasil! Selamat datang " + currentUser.getNama());
            // }
            isAdminMode = false; // Ensure admin mode is off
        }
    }

    private void lihatSemuaKursus() {
        System.out.println("\n--- Daftar Semua Kursus Tersedia ---");
        for (int i = 0; i < daftarSemuaKursus.size(); i++) {
            Kursus k = daftarSemuaKursus.get(i);
            System.out.println((i + 1) + ". " + k.getJudul() + " (Rp" + k.getHarga() + ")");
            System.out.println("   Deskripsi: " + k.getDeskripsi());
        }
    }

    private void daftarKursus(Peserta peserta) {
        lihatSemuaKursus();
        if (daftarSemuaKursus.isEmpty()) {
            System.out.println("Saat ini belum ada kursus yang tersedia.");
            return;
        }
        
        System.out.print("\nPilih nomor kursus untuk mendaftar dan bayar: ");
        int pilihan = getIntInput();
        
        if (pilihan > 0 && pilihan <= daftarSemuaKursus.size()) {
            Kursus kursusDipilih = daftarSemuaKursus.get(pilihan - 1);
            System.out.println("\nAnda akan mendaftar untuk kursus: " + kursusDipilih.getJudul());
            System.out.println("Harga: Rp" + kursusDipilih.getHarga());
            System.out.print("Masukkan jumlah pembayaran: ");
            double jumlahBayar = getDoubleInput();
            
            // Memanggil metode baru yang sudah logis di kelas Peserta
            peserta.daftarKursus(kursusDipilih, jumlahBayar);

        } else {
            System.out.println("Nomor kursus tidak valid.");
        }
    }

    //  private void bayarKursus(Peserta peserta) {
    //     peserta.lihatKursusDiikuti();
    //     if (peserta.getKursusDiikuti().isEmpty()) return;

    //     System.out.print("Pilih nomor kursus yang akan dibayar: ");
    //     int pilihan = getIntInput();
    //     ArrayList<Kursus> kursusDiikuti = peserta.getKursusDiikuti();

    //     if (pilihan > 0 && pilihan <= kursusDiikuti.size()) {
    //         Kursus kursusDipilih = kursusDiikuti.get(pilihan - 1);
    //         System.out.print("Masukkan jumlah pembayaran (Harga: Rp" + kursusDipilih.getHarga() + "): ");
            
    //         // Use the new helper for double input
    //         double jumlah = getDoubleInput();
    //         peserta.bayarKursus(kursusDipilih, jumlah);
    //     } else {
    //         System.out.println("Nomor kursus tidak valid.");
    //     }
    // }

    private void aksesKonten(Peserta peserta) {
        peserta.lihatKursusDiikuti();
         if (peserta.getKursusDiikuti().isEmpty()) return;

        System.out.print("Pilih nomor kursus untuk melihat konten: ");
        int pilihanKursus = getIntInput();
        ArrayList<Kursus> kursusDiikuti = peserta.getKursusDiikuti();

        if (pilihanKursus > 0 && pilihanKursus <= kursusDiikuti.size()) {
            Kursus kursusDipilih = kursusDiikuti.get(pilihanKursus - 1);
            ArrayList<Konten> daftarKonten = kursusDipilih.getDaftarKonten();
            
            System.out.println("\n--- Konten Kursus: " + kursusDipilih.getJudul() + " ---");
            for(int i = 0; i < daftarKonten.size(); i++){
                System.out.println((i+1) + ". " + daftarKonten.get(i).getJudul());
            }

            System.out.print("Pilih nomor konten untuk diakses: ");
            int pilihanKonten = getIntInput();

            if(pilihanKonten > 0 && pilihanKonten <= daftarKonten.size()){
                peserta.lihatKonten(daftarKonten.get(pilihanKonten - 1));
            } else {
                System.out.println("Nomor konten tidak valid.");
            }
        } else {
            System.out.println("Nomor kursus tidak valid.");
        }
    }

    private void buatKursusBaru() {
        System.out.println("\n--- Buat Kursus Baru (Admin) ---");
        
        // 1. Dapatkan dan tampilkan daftar instruktur yang tersedia
        ArrayList<Instruktur> daftarInstruktur = ManajemenUser.getDaftarInstruktur();
        if (daftarInstruktur.isEmpty()) {
            System.out.println("Gagal membuat kursus. Tidak ada instruktur di dalam sistem.");
            return;
        }
        
        System.out.println("Pilih Instruktur untuk kursus ini:");
        for (int i = 0; i < daftarInstruktur.size(); i++) {
            System.out.println((i + 1) + ". " + daftarInstruktur.get(i).getNama() + " (" + daftarInstruktur.get(i).getBidangKeahlian() + ")");
        }
        System.out.print("Pilihan Anda: ");
        int pilihanInstruktur = getIntInput();

        if (pilihanInstruktur <= 0 || pilihanInstruktur > daftarInstruktur.size()) {
            System.out.println("Pilihan instruktur tidak valid.");
            return;
        }
        Instruktur instrukturDipilih = daftarInstruktur.get(pilihanInstruktur - 1);

        // 2. Minta detail kursus
        System.out.print("Judul Kursus: ");
        String judul = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Harga: ");
        double harga = getDoubleInput();

        // 3. Buat objek kursus baru menggunakan konstruktor yang sudah diubah
        Kursus kursusBaru = new Kursus(
            daftarSemuaKursus.size() + 1, 
            judul, 
            deskripsi, 
            harga, 
            instrukturDipilih // Menggunakan instruktur yang dipilih
        );
        daftarSemuaKursus.add(kursusBaru);
        System.out.println("Kursus '" + judul + "' oleh " + instrukturDipilih.getNama() + " berhasil dibuat!");
    }
}