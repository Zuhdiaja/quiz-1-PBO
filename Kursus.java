public class Kursus {
    private int id;
    private String judul;
    private String deskripsi;
    private double harga;
    private Instruktur instruktur;

    private java.util.ArrayList<Konten> daftarKonten = new java.util.ArrayList<>();

    public  Kursus(int id, String judul, String deskripsi, double harga, Instruktur instruktur) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.instruktur=instruktur;
    }

    public Instruktur getInstruktur(){
        return instruktur;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getJudul(){
        return judul;
    }

    public void setJudul(String judul){
        this.judul = judul;
    }

    public String getDeskripsi(){
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public void tambahKonten(Konten konten){
        daftarKonten.add(konten);
        // System.out.println("Konten '" + konten.getJudul() + "' ditambahkan ke kursus " + judul);
    }

    public java.util.ArrayList<Konten> getDaftarKonten() {
    return daftarKonten;
}
}
