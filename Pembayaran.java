public class Pembayaran {
    private Peserta peserta;
    private Kursus kursus;
    private double jumlahBayar;
    private boolean status;

    public Pembayaran(Peserta peserta, Kursus kursus, double jumlahBayar) {
        this.peserta = peserta;
        this.kursus = kursus;
        this.jumlahBayar = jumlahBayar;
        this.status = jumlahBayar >= kursus.getHarga();
    }

    public boolean isStatus() {
        return status;
    }

     public void infoPembayaran() {
        System.out.println("=== INFO PEMBAYARAN ===");
        System.out.println("Peserta    : " + peserta.getNama());
        System.out.println("Kursus     : " + kursus.getJudul());
        System.out.println("Harga      : Rp" + kursus.getHarga());
        System.out.println("Dibayar    : Rp" + jumlahBayar);
        System.out.println("Status     : " + (status ? "Berhasil" : "Gagal (kurang bayar)"));
    }
}
