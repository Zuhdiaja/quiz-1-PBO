public class Instruktur extends User {
    private String bidangKeahlian;

    public Instruktur(String nama, String email, String password, String bidangKeahlian) {
        super(nama, email, password);
        this.bidangKeahlian = bidangKeahlian;
    }

    public String getBidangKeahlian() { return bidangKeahlian; }

    
    //  public void buatKursus(String namaKursus, double harga) {
    //     System.out.println("Instruktur " + getNama() + " membuat kursus: " 
    //         + namaKursus + " (Rp" + harga + ")");
    // }

    public void unggahKonten(String namaKonten) {
        System.out.println("Instruktur " + getNama() + " mengunggah konten: " + namaKonten);
    }
}