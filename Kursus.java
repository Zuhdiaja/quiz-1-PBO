public class Kursus {
    private int id;
    private String judul;
    private String deskripsi;
    private double harga;

    public  Kursus(int id, String judul, String deskripsi){
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.harga = harga;
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

    // public void tambahKonten(Konten konten){
    //     System.out.println("Konten '" + konten.getJudul() + "' ditambahkan ke kursus " + judul);
    // }
}
